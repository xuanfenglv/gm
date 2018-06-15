package com.longma.mopet.gm.manager;

import com.longma.mopet.gm.config.GmConstant;
import com.longma.mopet.gm.server.ServerObject;
import com.longma.mopet.gm.util.XmlUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.TreeMap;

@Component
public class DBManager {
	private static final TreeMap<Integer, ServerObject> servers = new TreeMap<Integer, ServerObject>();
	private static Properties commonDbConfig = new Properties();
	private static final String gameDbName = "maoyou_game";
	private static final String logDbName = "maoyou_log";

	public DBManager() {
		initialize();
	}
	private SessionFactory getLogDbSessionFactory(int dbId) {
		return servers.get(dbId).getLogSessionFactory();
	}
	private SessionFactory getGameDbSessionFactory(int dbId) {
		return servers.get(dbId).getGameSessionFactory();
	}
	public Session getLogDbSession(int serverId) {
		return getLogDbSessionFactory(serverId).openSession();
	}
	public Session getGameDbSession(int serverId) {
		return getGameDbSessionFactory(serverId).openSession();
	}
	public int getServersCount() {
		return getServers().size();
	}

	public void initialize() {
		commonDbConfig.clear();
		String dbCommonPropertiesPath = GmConstant.CONFIG_ROOT_PATH+"/db_common_config.properties";
		System.out.println("load dbCommonProperties from:" + dbCommonPropertiesPath);
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(dbCommonPropertiesPath));
			commonDbConfig.load(in);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		loadGameServerDbConfig();
	}

	private void loadGameServerDbConfig() {
		String xmlPath = GmConstant.CONFIG_ROOT_PATH + "/gameserver_db.xml";
		Document document = XmlUtils.parseXmlWithSAX(xmlPath, "UTF-8");
		System.out.println("load gameserver_db_config from:" + xmlPath);
		List<Element> list = document.selectNodes("/gameservers/gameserver");
		Iterator<Element> iterator = list.iterator();
		for (; iterator.hasNext();) {
			ServerObject serverObject = new ServerObject();
			Element element = iterator.next();
			Iterator elementIterator = element.elementIterator();
			for (; elementIterator.hasNext();) {
				Element next = (Element) elementIterator.next();
				String tag = next.getName();
				String value = next.getText().trim();
				if (tag.equals("id")) {
					serverObject.setId(Integer.parseInt(value));
				} else if (tag.equals("serverName")) {
					serverObject.setServerName(value);
				} else if (tag.equals("telnetIp")) {
					serverObject.setIp(value);
				} else if (tag.equals("telnetPort")) {
					serverObject.setTelnetPort(Integer.parseInt(value));
				} else if (tag.equals("ip")) {
					serverObject.setIp(value);
				} else if (tag.equals("port")) {
					serverObject.setPort(Integer.parseInt(value));
				} else if (tag.equals("username")) {
					serverObject.setUsername(value);
				} else if (tag.equals("password")) {
					serverObject.setPassword(value);
				}
			}
			configServerObjectDb(serverObject);
			getServers().put(serverObject.getId(), serverObject);
		}
	}

	private void configServerObjectDb(ServerObject serverObject) {

//		String logDbUrl = "jdbc:mysql://" + serverObject.getIp() + ":" + serverObject.getPort() + "/"+logDbName+"?useUnicode=true&characterEncoding=utf-8";
		Properties privateProps = new Properties();
//		privateProps.put("hibernate.connection.url", gameDbUrl);
		privateProps.put("hibernate.connection.username", serverObject.getUsername());
		privateProps.put("hibernate.connection.password", serverObject.getPassword());
		privateProps.putAll(commonDbConfig);
//		Configuration cfg = new AnnotationConfiguration().setProperties(privateProps);
		Configuration cfg = new Configuration().setProperties(privateProps);
		buildSessionFactory(cfg,gameDbName,serverObject);
		buildSessionFactory(cfg,logDbName,serverObject);
	}

	/**
	 * 
	 * @description: 检查数据库是否可以连接 
	 * @author: yong_li 
	 * @param svr
	 * @param dbname
	 * @return
	 */
	public boolean canConnect(ServerObject svr, String dbname) {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		String url = "jdbc:mysql://" + svr.getIp() + ":" + svr.getPort() + "/" + dbname + "?user=" + svr.getUsername() + "&password="
				+ svr.getPassword() + "&useUnicode=true&characterEncoding=utf-8";
		try {
			con = DriverManager.getConnection(url);
			return true;
		} catch (SQLException e) {
			return false;
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public void buildSessionFactory(Configuration cfg, String dbName, ServerObject serverObject) {
		String dbUrl = "jdbc:mysql://" + serverObject.getIp() + ":" + serverObject.getPort() + "/"+dbName+"?useUnicode=true&characterEncoding=utf-8";
		cfg.getProperties().put("hibernate.connection.url", dbUrl);
		boolean canConnect = false;
		long start = 0, end = 0;
		SessionFactory sf = null;
		canConnect = canConnect(serverObject, dbName);
		if (canConnect) {
			start = System.currentTimeMillis();
			try {
				sf = cfg.buildSessionFactory();
				if(dbName.equals(gameDbName)) {
					serverObject.setGameSessionFactory(sf);
				} else {
					serverObject.setLogSessionFactory(sf);
				}
			} catch (HibernateException e) {
				System.out.println(serverObject.getServerName() + "\t"+dbName+"连接失败。常见原因是数据库账号、授权");
				e.printStackTrace();
			}
			end = System.currentTimeMillis();
			System.out.println(serverObject.getServerName() + "\t"+dbName+" connect OK.cost " + (end - start) + " ms");
		} else {
			System.out.println(serverObject.getServerName() +"\t"+dbName+" connect FAIL.");
		}
	}

	public static void main(String[] args) {
		new DBManager();
	}

	public static TreeMap<Integer, ServerObject> getServers() {
		return servers;
	}
}
