package com.longma.mopet.gm.server;

import com.longma.mopet.gm.base.message.s2b.S2BMessage;

public class ServerInfo extends S2BMessage {
	private int id;
	private String name;
	private String ip;
	private int port;
	private int totalOnlinePlayerCounts;
	private int androidOnlinePlayerCounts;
	private int iosOnlinePlayerCounts;
	private String memory;
	private float cpuRate;
	private int version;
	private int dbState;
	private long delay;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getTotalOnlinePlayerCounts() {
		return totalOnlinePlayerCounts;
	}

	public void setTotalOnlinePlayerCounts(int totalOnlinePlayerCounts) {
		this.totalOnlinePlayerCounts = totalOnlinePlayerCounts;
	}

	public int getAndroidOnlinePlayerCounts() {
		return androidOnlinePlayerCounts;
	}

	public void setAndroidOnlinePlayerCounts(int androidOnlinePlayerCounts) {
		this.androidOnlinePlayerCounts = androidOnlinePlayerCounts;
	}

	public int getIosOnlinePlayerCounts() {
		return iosOnlinePlayerCounts;
	}

	public void setIosOnlinePlayerCounts(int iosOnlinePlayerCounts) {
		this.iosOnlinePlayerCounts = iosOnlinePlayerCounts;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public float getCpuRate() {
		return cpuRate;
	}

	public void setCpuRate(float cpuRate) {
		this.cpuRate = cpuRate;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getDbState() {
		return dbState;
	}

	public void setDbState(int dbState) {
		this.dbState = dbState;
	}

	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

}
