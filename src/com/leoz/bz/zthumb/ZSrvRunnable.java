package com.leoz.bz.zthumb;

public abstract class ZSrvRunnable implements Runnable {

	protected volatile boolean mStopped = false;
	
	protected String mName = null;
	
	ZSrvRunnable (String name) {
		mName = name;
	}

	public void Stop() {
		mStopped = true;
	}
}
