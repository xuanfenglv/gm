package com.longma.mopet.gm.server;

import org.hibernate.SessionFactory;

public class ServerObject implements Comparable<ServerObject> {
	private int id;
	private String serverName;
	private String ip;
	private int port;
	private String username;
	private String password;
	private String telnetIp;
	private int telnetPort;
	private SessionFactory gameSessionFactory;
	private SessionFactory logSessionFactory;

	public ServerObject() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelnetIp() {
		return telnetIp;
	}

	public void setTelnetIp(String telnetIp) {
		this.telnetIp = telnetIp;
	}

	public int getTelnetPort() {
		return telnetPort;
	}

	public void setTelnetPort(int telnetPort) {
		this.telnetPort = telnetPort;
	}

	public SessionFactory getGameSessionFactory() {
		return gameSessionFactory;
	}

	public void setGameSessionFactory(SessionFactory gameSessionFactory) {
		this.gameSessionFactory = gameSessionFactory;
	}

	public SessionFactory getLogSessionFactory() {
		return logSessionFactory;
	}

	public void setLogSessionFactory(SessionFactory logSessionFactory) {
		this.logSessionFactory = logSessionFactory;
	}

	public ServerInfo getServerInfo() {
		ServerInfo inf = new ServerInfo();
		inf.setAndroidOnlinePlayerCounts(6);
		inf.setCpuRate(100);
		inf.setDbState(1);
		inf.setDelay(200);
		inf.setId(getId());
		inf.setIosOnlinePlayerCounts(200);
		inf.setIp(getIp());
		inf.setMemory("20/100");
		inf.setName(getServerName());
		inf.setPort(port);
		inf.setTotalOnlinePlayerCounts(1214);
		return inf;
	}

	@Override
	public int compareTo(ServerObject o) {
		if (o.getId() > this.getId()) {
			return 1;
		} else if (o.getId() > this.getId()) {
			return -1;
		} else {
			return 0;
		}
	}

}
