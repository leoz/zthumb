package com.leoz.bz.zthumb;

import java.util.HashMap;

import android.graphics.Bitmap;
import android.util.Log;

public class ZSrvCacheMem extends ZSrvCache {

	private static final String TAG = "[z::remote] ZSrvCacheMem"; /// TODO: FIX ME

	private final HashMap<String, Bitmap> mCache;
	
	private static final String SEP = "#";

	ZSrvCacheMem() {
		mCache = new HashMap<String, Bitmap>();
	}

	@Override
	public Bitmap getImage(final String url, final int size) {
    	
    	Log.v(TAG, "getImage for " + url); /// TODO: FIX ME    	
		
    	String key = getKey(url, size);
		
    	if (mCache.containsKey(key)) {
	    	return mCache.get(key);
		}

		return null;
	}
	
	@Override
	public void putImage(final String url, final int size, Bitmap b) {
    	
    	Log.v(TAG, "putImage for " + url + " " + (b != null)); /// TODO: FIX ME    	
		
		if (url != null && b != null) {
			mCache.put(getKey(url, size), b);			
		}
		
    	Log.v(TAG, "putImage. Cache size is " + mCache.size()); /// TODO: FIX ME    	
	}
	
	@Override
	public void clear() {
		/// TODO
	}

	private String getKey(final String url, final int size) {
		String key = SEP + String.valueOf(size) + SEP + url;
		return key;
	}
}
