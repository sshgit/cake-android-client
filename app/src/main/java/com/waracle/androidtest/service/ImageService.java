package com.waracle.androidtest.service;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.waracle.androidtest.data.ImageCache;

public class ImageService {
    public interface GetImageListener extends Cloud.CloudListener {
        void onImage(Bitmap bitmap);
    }

    public static void getImage(final String url, final GetImageListener listener){
        Bitmap bitmap = ImageCache.getBitmap(url);
        if(bitmap != null)
            listener.onImage(bitmap);
        else
            new GetImageTask(new GetImageListener() {
                @Override
                public void onImage(Bitmap bitmap) {
                    ImageCache.addBitmap(url, bitmap);
                    listener.onImage(bitmap);
                }

                @Override
                public void onFailure(String rawResponse) {
                    listener.onFailure(rawResponse);
                }
            }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, url);
    }
}
