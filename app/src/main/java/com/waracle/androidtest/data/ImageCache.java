package com.waracle.androidtest.data;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.util.LruCache;

public class ImageCache extends  LruCache<String, Bitmap> {

    private static LruCache<String, Bitmap> self;

    public ImageCache(int maxSize) {
        super(maxSize);
    }

    private static LruCache<String, Bitmap> instance(){
        if(self == null){
            synchronized (LruCache.class){
                if(self == null){
                    final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
                    final int cacheSize = maxMemory / 8;
                    self = new ImageCache(cacheSize);

                }
            }
        }
        return self;
    }

    @Override
    protected int sizeOf(@NonNull String key, @NonNull Bitmap bitmap) {
        return bitmap.getByteCount() / 1024;
    }

    public static void addBitmap(String key, Bitmap bitmap){
        if(getBitmap(key) == null){
            instance().put(key, bitmap);
        }
    }

    public static Bitmap getBitmap(String key){
        return instance().get(key);
    }
}
