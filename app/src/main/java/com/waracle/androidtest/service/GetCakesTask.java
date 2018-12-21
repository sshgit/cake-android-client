package com.waracle.androidtest.service;

import android.os.AsyncTask;

import com.waracle.androidtest.data.Cake;

import java.util.List;

class GetCakesTask extends AsyncTask<Void, Void, Cloud.CloudResponse> {

    private final CakeService.GetCakeListener listener;
    private static final String URL = "https://gist.githubusercontent.com/hart88/198f29ec5114a3ec3460/" +
            "raw/8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json";

    public GetCakesTask(CakeService.GetCakeListener listener){
        this.listener = listener;
    }

    @Override
    protected Cloud.CloudResponse doInBackground(Void... voids) {
        return Cloud.getCakes(URL);
    }

    @Override
    protected void onPostExecute(Cloud.CloudResponse cloudResponse) {
        super.onPostExecute(cloudResponse);
        if(cloudResponse.error == null)
            listener.onCakes((List<Cake>) cloudResponse.response);
        else
            listener.onFailure(cloudResponse.error);
    }
}
