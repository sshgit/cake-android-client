package com.waracle.androidtest.ui;

import android.graphics.Bitmap;
import android.util.Log;

import com.waracle.androidtest.service.ImageService;

public class NetworkImageViewModel implements NetworkImageContract.Observable, ImageService.GetImageListener {

    private NetworkImageContract.Observer mNetworkImageObserver;
    private static final String TAG = "NetworkImagePresenter";
    public NetworkImageViewModel(NetworkImageContract.Observer observer){
        this.mNetworkImageObserver = observer;
    }

    public void unsubscribe(){
        mNetworkImageObserver = null;
    }

    @Override
    public void loadImage(String url) {
       ImageService.getImage(url, this);
    }


    @Override
    public void onFailure(String rawResponse) {
        Log.d(TAG, rawResponse);
    }

    @Override
    public void onImage(Bitmap bitmap) {
        if(mNetworkImageObserver != null)
            mNetworkImageObserver.onImageAvailable(bitmap);
    }
}
