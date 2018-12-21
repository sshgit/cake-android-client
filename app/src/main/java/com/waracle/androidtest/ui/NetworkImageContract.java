package com.waracle.androidtest.ui;

import android.graphics.Bitmap;

public interface NetworkImageContract {

    interface Observer {
        void onImageAvailable(Bitmap bitmap);
    }

    interface Observable {
        void loadImage(String url);
        void unsubscribe();
    }

}
