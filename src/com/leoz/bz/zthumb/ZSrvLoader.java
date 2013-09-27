package com.leoz.bz.zthumb;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public enum ZSrvLoader {
	INSTANCE;

	private static final String TAG = "[z::remote] ZSrvLoader"; /// TODO: FIX ME
	
	private static final ZSrvCache CACHE = new ZSrvCacheFile();

	public static void setContext(Context c) {
    	
    	Log.v(TAG, "setContext"); /// TODO: FIX ME    	
	    
    	ZSrvCacheFile.setContext(c);
	}

	public void loadImages(final String url, final int[] sizes) {
		for (int i : sizes) {
			if (i > 0) {
				loadImage(url, i);				
			}
		}
	}

	public Bitmap loadImage(final String url, final int size) {
		
		Log.w(TAG, "####### loadImage: BEGIN");
    	
    	Log.v(TAG, "loadImage"); /// TODO: FIX ME    	
	    
		//Get image from cache
		Bitmap b = CACHE.getImage(url, size);
		
		if (b == null) {
			
	    	Log.v(TAG, "loadImage: no image in cache. Generating..."); /// TODO: FIX ME    				
			
			try {
		        //Decode image size
		        BitmapFactory.Options o = new BitmapFactory.Options();
		        o.inJustDecodeBounds = true;

		        FileInputStream fis = new FileInputStream(url);
		        BitmapFactory.decodeStream(fis, null, o);
		        fis.close();

		        int scale = 1;
		        if (o.outHeight > size || o.outWidth > size) {
		            scale = (int)Math.pow(2, (int) Math.round(Math.log(size / (double) Math.max(o.outHeight, o.outWidth)) / Math.log(0.5)));
		        }

		        //Decode with inSampleSize
		        BitmapFactory.Options o2 = new BitmapFactory.Options();
		        o2.inSampleSize = scale;
		        fis = new FileInputStream(url);
		        b = BitmapFactory.decodeStream(fis, null, o2);
		        fis.close();
		        
		        if (b != null) {
			        Log.v(TAG, "loadImage: scale " + scale + " : image " + url); /// TODO: FIX ME
			        
			        //Put image to cache
			        CACHE.putImage(url, size, b);
		        }
		        else {
			        Log.e(TAG, "loadImage: Bitmap is null!"); /// TODO: FIX ME		        			        	
		        }		        		        
			}
			catch (MalformedURLException e) {
		        Log.e(TAG, "loadImage: wrong URL", e); /// TODO: FIX ME
				e.printStackTrace();
			}
			catch (IOException e) {
		        Log.e(TAG, "loadImage: Error", e); /// TODO: FIX ME
				e.printStackTrace();
			}			
		}
		else {
	    	Log.v(TAG, "loadImage: loaded from cache"); /// TODO: FIX ME    				
		}
		
		Log.w(TAG, "####### loadImage: END");

		return b;
	}
	
	public void clearCache() {
    	
    	Log.v(TAG, "clearCache"); /// TODO: FIX ME    	
	    
		CACHE.clear();	
	}
}
