package com.leoz.bz.zthumb;

import com.leoz.bz.zbase.ZFile;

public class ZSrvRunnableDir extends ZSrvRunnable {

	private int[] mSizes = null;

	ZSrvRunnableDir (String dir, int[] sizes) {
		super(dir);
		mSizes = sizes;
	}
	
	public void run() {
				
		ZFile dir = new ZFile (mName);
		
		if (dir.isDirectory()) {
			
			ZFile[] files = dir.listFiles();
						
			for (ZFile file : files) {
				// Check if we still scanning
				if (mStopped) {
					break;
				}
		    	ZSrvExecutor.INSTANCE.executeLoadDir(file.getPath(), mSizes); 				    						
			}
		}
	}
}
