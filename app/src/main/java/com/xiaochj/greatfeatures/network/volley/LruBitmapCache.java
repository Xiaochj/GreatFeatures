package com.xiaochj.greatfeatures.network.volley;


import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by xiaochj on 17/1/11.
 */

public class LruBitmapCache extends LruCache<String,Bitmap> implements ImageLoader.ImageCache{

    private static final int MAXSIZE = 10 * 1024 *1024;//10M缓存

    public LruBitmapCache(int maxSize) {
        super(maxSize);
    }

    public LruBitmapCache(){
        super(MAXSIZE);
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getRowBytes() * value.getHeight();//一个bmp的大小
    }

    @Override
    public Bitmap getBitmap(String url) {
        return get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        put(url,bitmap);
    }
}
