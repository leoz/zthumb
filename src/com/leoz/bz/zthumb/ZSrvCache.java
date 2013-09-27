package com.leoz.bz.zthumb;

import android.graphics.Bitmap;

public abstract class ZSrvCache {
	public abstract Bitmap getImage(final String url, final int size);
	public abstract void putImage(final String url, final int size, Bitmap b);
	public abstract void clear();
}
