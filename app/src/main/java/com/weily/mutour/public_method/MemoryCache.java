package com.weily.mutour.public_method;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

public class MemoryCache implements ImageLoader.ImageCache
{
    private LruCache<String, Bitmap> lruCache= null;
    public MemoryCache()
    {
        int maxMemory = (int) Runtime.getRuntime().maxMemory()/1024;
        int cacheSizes = maxMemory/4;
        lruCache=new LruCache<>(cacheSizes);
    }

    @Override
    public Bitmap getBitmap(String url)
    {
        return lruCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap)
    {
        lruCache.put(url, bitmap);
    }
}