package com.longma.mopet.gm.server;

public class ServerSwitchInfo {
	private boolean recharge;
	private boolean login;
	private boolean antiAddiction;
	private boolean dirtyWord;

	public boolean isRecharge() {
		return recharge;
	}

	public void setRecharge(boolean recharge) {
		this.recharge = recharge;
	}

	public boolean isLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}

	public boolean isAntiAddiction() {
		return antiAddiction;
	}

	public void setAntiAddiction(boolean antiAddiction) {
		this.antiAddiction = antiAddiction;
	}

	public boolean isDirtyWord() {
		return dirtyWord;
	}

	public void setDirtyWord(boolean dirtyWord) {
		this.dirtyWord = dirtyWord;
	}

}
