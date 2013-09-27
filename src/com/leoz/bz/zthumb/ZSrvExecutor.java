package com.leoz.bz.zthumb;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public enum ZSrvExecutor {
	INSTANCE;

	private static final String TAG = "[z::remote] ZSrvExecutor"; /// TODO: FIX ME
	
	private int THREAD_POOL_SIZE = 10;
	private ThreadPoolExecutor pool;
	private ZSrvService mService;

	ZSrvExecutor() {
		pool = (ThreadPoolExecutor)Executors.newFixedThreadPool(THREAD_POOL_SIZE);
	}
	
	public void setService(ZSrvService s) {
		mService = s;		
	}
	
	public void setPoolSize(int size) {
		THREAD_POOL_SIZE = size;
        pool.setCorePoolSize(THREAD_POOL_SIZE);
		Log.v(TAG, "setPoolSize: " + pool.getCorePoolSize()); /// TODO: FIX ME    	
	}


	public void executeLoadDir(final String url, final int[] sizes) {
		pool.submit(new Runnable() {
			public void run() {
				ZSrvLoader.INSTANCE.loadImages(url, sizes);
			}
		});		
	}
	
	public void executeLoadFile(final String url, final int size) {

		Log.v(TAG, "executeLoad"); /// TODO: FIX ME    	

		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (mService != null) {
					mService.sendData((ZSrvData)msg.obj);
				}
			}
		};

		pool.submit(new Runnable() {
			public void run() {

				Log.v(TAG, "run"); /// TODO: FIX ME 
				
				final Bitmap b = ZSrvLoader.INSTANCE.loadImage(url, size);
				
				if (b != null) {
					final ZSrvData data = new ZSrvData();
					data.mUrl = url;
					data.mSize = size;
					data.mBitmap = b;
					Message message = Message.obtain();
					message.obj = data;
					handler.sendMessage(message);					
				}
			}
		});
	}
}
