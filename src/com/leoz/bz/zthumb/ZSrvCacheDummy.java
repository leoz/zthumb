package com.leoz.bz.zthumb;

import android.graphics.Bitmap;
import android.util.Log;

public class ZSrvCacheDummy extends ZSrvCache {

	private static final String TAG = "[z::remote] ZSrvCacheDummy"; /// TODO: FIX ME

	ZSrvCacheDummy() {
	}

	@Override
	public Bitmap getImage(final String url, final int size) {
    	Log.v(TAG, "getImage for size: " + size + " url " + url); /// TODO: FIX ME    	
		return null;
	}
	
	@Override
	public void putImage(final String url, final int size, Bitmap b) {
    	Log.v(TAG, "putImage for size: " + size + " url " + url); /// TODO: FIX ME    	
	}

	@Override
	public void clear() {
	}
}
