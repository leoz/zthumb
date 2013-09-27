package com.leoz.bz.zthumb;

interface ZSrvRemoteService {

	void setDir(String dir);
	void setSize(int size);
	void setThreads(int count);
	void startScan();
	void scanDir(String dir, in int[] sizes);
	void scanFile(String file, int size);
	void stopScan();
	void clearCache();
}
