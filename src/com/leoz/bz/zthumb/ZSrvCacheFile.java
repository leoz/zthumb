package com.leoz.bz.zthumb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class ZSrvCacheFile extends ZSrvCache {

	private static final String TAG = "[z::remote] ZSrvCacheFile"; /// TODO: FIX ME
	
	private static String mDir;
	private static final String SEP = "/";
	private static Context mContext;

	ZSrvCacheFile() {
		mDir = "";
	}
	
	public static void setContext(Context c) {
		mContext = c;
		if (mContext != null) {
			mDir = mContext.getCacheDir().getAbsolutePath();
		}
    	Log.v(TAG, "setContext: cache dir is " + mDir); /// TODO: FIX ME
	}

	@Override
	public Bitmap getImage(final String url, final int size) {
		
    	Log.v(TAG, "getImage for size: " + size + " url " + url); /// TODO: FIX ME
    	
    	String file = generateUrl(url, size);
    	
    	Log.v(TAG, "getImage cache file: " + file); /// TODO: FIX ME
    	
		return readCacheFile(file);
	}
	
	@Override
	public void putImage(final String url, final int size, Bitmap b) {
		
    	Log.v(TAG, "putImage for size: " + size + " url " + url); /// TODO: FIX ME    	
    	
    	String file = generateUrl(url, size);
    	
    	Log.v(TAG, "putImage cache file: " + file); /// TODO: FIX ME
    	
    	generateCacheSubdir(size);
    	
    	writeCacheFile(file, b);
	}
	
	@Override
	public void clear() {
		
    	Log.v(TAG, "clear"); /// TODO: FIX ME

    	try {
    		
	        File dir = mContext.getCacheDir();
	        
	        if (dir != null && dir.isDirectory()) {
	            deleteDir(dir);
	        }
	    }
	    catch (Exception e) {
	    	
	        Log.e(TAG, "clear: Error", e); /// TODO: FIX ME
	        
	        e.printStackTrace();	    	
	    }
	}

	private static boolean deleteDir(File dir) {
		
    	Log.v(TAG, "deleteDir"); /// TODO: FIX ME

	    if (dir != null && dir.isDirectory()) {
	    	
	    	Log.v(TAG, "deleteDir: deleting " + dir.getName()); /// TODO: FIX ME

	    	String[] children = dir.list();
	    	
	        for (int i = 0; i < children.length; i++) {
	        	
	            boolean success = deleteDir(new File(dir, children[i]));
	            
	            if (!success) {
	                return false;
	            }
	        }
	    }
	    
	    return dir.delete();
	}
	
	private void generateCacheSubdir(final int size) {
		
    	Log.v(TAG, "generateCacheSubdir for size: " + size); /// TODO: FIX ME

    	String dir = mDir + SEP + String.valueOf(size);
		
		File f = new File (dir);
		
		if (!f.exists()) {
			f.mkdirs();
	    	Log.v(TAG, "generateCacheSubdir generated: " + dir); /// TODO: FIX ME
		}
		else {
	    	Log.v(TAG, "generateCacheSubdir exists: " + dir); /// TODO: FIX ME			
		}
	}
	
	private void writeCacheFile(final String url, Bitmap bmp) {
		
    	Log.v(TAG, "writeCacheFile: " + url); /// TODO: FIX ME
    	
        File file = new File(url);

        if (file.exists()) {
	    	Log.v(TAG, "writeCacheFile: file exists"); /// TODO: FIX ME
        }
        else {
        	
	    	Log.v(TAG, "writeCacheFile: file does not exist"); /// TODO: FIX ME
	    	
	        try {
	        	
		        if (file.createNewFile()) {
	
		        	Log.v(TAG, "writeCacheFile - file created: " + url); /// TODO: FIX ME
		        	
			        // Write to file
			        FileOutputStream os = new FileOutputStream(file);
			        bmp.compress(Bitmap.CompressFormat.PNG, 100, os);
			        os.close();	        	
		        }
		        else {
			        Log.e(TAG, "writeCacheFile: Error creating file - file existed"); /// TODO: FIX ME
		        }
		    }
		    catch (IOException e) {
		        Log.e(TAG, "writeCacheFile: Error writing file", e); /// TODO: FIX ME
				e.printStackTrace();
		    }
		    catch (Exception e) {
		        Log.e(TAG, "writeCacheFile: Error", e); /// TODO: FIX ME
		        e.printStackTrace();
		    }		
        }
	}

	private Bitmap readCacheFile(final String url) {
		
    	Log.v(TAG, "readCacheFile: " + url); /// TODO: FIX ME
    	
		Bitmap bmp = null;

        File file = new File(url);

        if (file.exists()) {
			
	    	Log.v(TAG, "readCacheFile: file exists"); /// TODO: FIX ME
			
		    try {
		    	FileInputStream is = new FileInputStream(url);
		    	bmp = BitmapFactory.decodeStream(is, null, null);
		        is.close();
		    }
			catch (MalformedURLException e) {
		        Log.e(TAG, "readCacheFile: wrong URL", e); /// TODO: FIX ME
				e.printStackTrace();
			}
			catch (IOException e) {
		        Log.e(TAG, "readCacheFile: Error", e); /// TODO: FIX ME
				e.printStackTrace();
			}			
		}
		else {
	    	Log.v(TAG, "readCacheFile: file does not exist"); /// TODO: FIX ME			
		}
		
	    return bmp;
	}
	
	private String generateUrl(final String url, final int size) {
    	return mDir + SEP + String.valueOf(size) + SEP + generateFileName(url);
	}
	
	private static String generateFileName(String url) {
        // replace all special URI characters with a single + symbol
        return url.replaceAll("[.:/,%?&=]", "+").replaceAll("[+]+", "+");
    }
}
