package com.waracle.androidtest.service;

import android.graphics.Bitmap;
import android.os.AsyncTask;

class GetImageTask extends AsyncTask<String, Void, Cloud.CloudResponse> {

    private final ImageService.GetImageListener listener;

    public GetImageTask(ImageService.GetImageListener listener){
        this.listener = listener;
    }

    @Override
    protected Cloud.CloudResponse doInBackground(String... strings) {
        String url = strings[0];
        return Cloud.getImage(url);
    }

    @Override
    protected void onPostExecute(Cloud.CloudResponse cloudResponse) {
        super.onPostExecute(cloudResponse);
        if(cloudResponse.error == null)
            listener.onImage((Bitmap) cloudResponse.response);
        else
            listener.onFailure(cloudResponse.error);
    }
}
