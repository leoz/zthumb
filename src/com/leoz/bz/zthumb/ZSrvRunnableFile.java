package com.leoz.bz.zthumb;

import com.leoz.bz.zbase.ZFile;

public class ZSrvRunnableFile extends ZSrvRunnable {

	private int mSize = 0;

	ZSrvRunnableFile (String file, int size) {
		super(file);
		mSize = size;
	}
	
	public void run() {
				
		ZFile file = new ZFile (mName);
		
		if (file.isFile()) {
			if (!mStopped) {
		    	ZSrvExecutor.INSTANCE.executeLoadFile(file.getPath(), mSize); 				    						
			}
		}
	}
}
