package com.leoz.bz.zthumb;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.util.Date;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap.CompressFormat;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.leoz.bz.zthumb.ZSrvRemoteService;
import com.leoz.bz.zbase.ZBaseData;
import com.leoz.bz.zbase.ZBaseMessage;
import com.leoz.bz.zbase.ZFile;

public class ZSrvService extends Service {

	private static final String TAG = "[z::remote] ZSrvService"; /// TODO: FIX ME
	
	private Proc mProc = new Proc();
	private Handler mHandlerD = null;
	private Handler mHandlerF = null;

	private Handler mHandler;
	private Intent mIntentA;
	private Intent mIntentM;
	
	private static final int IMAGE_MAX_SIZE = 1024;
	private String mDir = "";
	private int mSize = IMAGE_MAX_SIZE;
	
	private volatile boolean mScanStarted = false;

	@Override
	public void onCreate() {

		super.onCreate();
		
		mHandler = new Handler();
		mHandlerD = new Handler();
		mHandlerF = new Handler();

		mIntentA = new Intent(ZBaseData.ACTION);
		mIntentM = new Intent(ZBaseData.MESSAGE);
		
		ZSrvExecutor.INSTANCE.setService(this);
		ZSrvLoader.setContext(this);

		Log.v(TAG, "onCreate"); /// TODO: FIX ME    	
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		Log.v(TAG, "onStartCommand"); /// TODO: FIX ME    	
		
		mHandler.removeCallbacks(mProc);
		mHandlerD.removeCallbacksAndMessages(null);
		mHandlerF.removeCallbacksAndMessages(null);
		
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		
		mHandlerD.removeCallbacksAndMessages(null);
		mHandlerF.removeCallbacksAndMessages(null);
		mHandler.removeCallbacks(mProc);
		
		mScanStarted = false;    		
		
		mHandlerD = null;
		mHandlerF = null;
		mHandler = null;
		
    	Log.v(TAG, "onDestroy"); /// TODO: FIX ME    	

		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {

		Log.v(TAG, "onBind"); /// TODO: FIX ME    
		
		return mServiceStub;
	}

	private ZSrvRemoteService.Stub mServiceStub = new ZSrvRemoteService.Stub() {
		
		public void setDir(String dir) throws RemoteException {
			
	    	Log.v(TAG, "STUB: setDir"); /// TODO: FIX ME
	    	
			mDir = dir;
			
	    	sendMessage(ZBaseMessage.BM_SET_DIR);
		}

		public void setSize(int size) throws RemoteException {
			
	    	Log.v(TAG, "STUB: setSize"); /// TODO: FIX ME

	    	mSize = size;
			
	    	sendMessage(ZBaseMessage.BM_SET_SIZE);
		}

		public void setThreads(int count) throws RemoteException {
			
	    	Log.v(TAG, "STUB: setThreads"); /// TODO: FIX ME
	    	
	    	ZSrvExecutor.INSTANCE.setPoolSize(count);
			
	    	sendMessage(ZBaseMessage.BM_SET_THREADS);
		}

		public void startScan() throws RemoteException {
			
	    	Log.v(TAG, "STUB: startScan"); /// TODO: FIX ME
	    	
	    	if (mScanStarted == true){
	        	Log.v(TAG, "Scan is already started"); /// TODO: FIX ME    		
	    	}
	    	else {
	    		mHandler.removeCallbacks(mProc);
	    		mHandler.post(mProc);
	    		mScanStarted = true;
		    	sendMessage(ZBaseMessage.BM_SCAN_START);
	    	}
		}

		public void scanDir(String dir, int[] sizes) throws RemoteException {
			
	    	Log.v(TAG, "STUB: scanDir"); /// TODO: FIX ME
	    	
	    	if (mScanStarted == true){
	        	Log.v(TAG, "Scan is already started"); /// TODO: FIX ME    		
	    	}
	    	else {
	    		mHandlerD.removeCallbacksAndMessages(null);
	    		mHandlerD.post(new ZSrvRunnableDir(dir, sizes));
	    		mScanStarted = true;
		    	sendMessage(ZBaseMessage.BM_SCAN_START);
	    	}
		}
		
		public void scanFile(String file, int size) throws RemoteException {
			
	    	Log.v(TAG, "STUB: scanFile"); /// TODO: FIX ME
	    	
//	    	mHandlerF.removeCallbacksAndMessages(null);
	    	mHandlerF.post(new ZSrvRunnableFile(file, size));
		}

		public void stopScan() throws RemoteException {
			
	    	Log.v(TAG, "STUB: stopScan"); /// TODO: FIX ME
	    	
	    	if (mScanStarted == true){
	    		mHandler.removeCallbacks(mProc);
	    		mHandlerD.removeCallbacksAndMessages(null);
	    		mHandlerF.removeCallbacksAndMessages(null);
	    		mScanStarted = false;    		
		    	sendMessage(ZBaseMessage.BM_SCAN_STOP);
	    	}
	    	else {
	        	Log.v(TAG, "Scan is already stopped"); /// TODO: FIX ME    		    		
	    	}
		}

		public void clearCache() throws RemoteException {
			
	    	Log.v(TAG, "STUB: clearCache"); /// TODO: FIX ME
	    	
	    	sendMessage(ZBaseMessage.BM_CLEAR_CACHE_BEGIN);

	    	ZSrvLoader.INSTANCE.clearCache();
			
	    	sendMessage(ZBaseMessage.BM_CLEAR_CACHE_END);
		}
	};
	
	// Process to run
	class Proc implements Runnable {
		public void run() {
			
	    	Log.v(TAG, "run"); /// TODO: FIX ME
	    	
	    	sendMessage(ZBaseMessage.BM_DIR_LOAD_BEGIN);
			
			ZFile dir = new ZFile (mDir);
			
			if (dir.isDirectory()) {
				
				ZFile[] files = dir.listFiles();
				
				if (files != null) {
					sendCount(files.length);
				}
				
				for (ZFile file : files) {
					// Check if we still scanning
					if (mScanStarted == false) {
						break;
					}
			    	sendMessage(ZBaseMessage.BM_FILE_LOAD_BEGIN);
			    	ZSrvExecutor.INSTANCE.executeLoadFile(file.getPath(), mSize); 				    						
				}
			}
			
	    	sendMessage(ZBaseMessage.BM_DIR_LOAD_END);
		}
	}
	
    public void sendData(ZSrvData data) {
    	
    	Log.v(TAG, "broadcastData"); /// TODO: FIX ME
    	
		// Check if we still scanning
//		if (mScanStarted == false) {
//			return;
//		}

		if (data != null) {
			String time = DateFormat.getDateTimeInstance().format(new Date());
			
			mIntentA.putExtra("time", time);
			mIntentA.putExtra("dir", mDir);
			mIntentA.putExtra("file", data.mUrl);
			mIntentA.putExtra("size", data.mSize);
	
			if (data.mBitmap != null) {
	        	ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
	        	data.mBitmap.compress(CompressFormat.PNG, 0 /*ignored for PNG*/, bos); 
				byte[] bitmapdata = bos.toByteArray();      	
				mIntentA.putExtra("image",bitmapdata);    		
	    	}
	
	    	sendMessage(ZBaseMessage.BM_FILE_LOAD_END);
			sendBroadcast(mIntentA);	
    	}
    }
    
    private void sendMessage(ZBaseMessage m) {
    	mIntentM.putExtra("msg", m.ordinal());
		sendBroadcast(mIntentM);	
    }
    
    private void sendCount(int count) {
    	mIntentM.putExtra("msg", ZBaseMessage.BM_FILE_COUNT.ordinal());
    	mIntentM.putExtra("count", count);
		sendBroadcast(mIntentM);	    	
    }
}

