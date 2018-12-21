package com.waracle.androidtest.service;

import com.waracle.androidtest.data.Cake;
import com.waracle.androidtest.data.CakeCache;

import java.util.List;

public class CakeService {
    public enum FetchStrategy{
        USE_CACHE,
        FRESH_UPDATE,
    }

    public interface GetCakeListener extends Cloud.CloudListener {
        void onCakes(List<Cake> cakes);
    }

    public static void getCakes(final GetCakeListener listener, FetchStrategy fetchStrategy){
        final boolean hasCache = !CakeCache.getCakes().isEmpty();
        switch (fetchStrategy){
            case USE_CACHE:
                if(hasCache){
                    listener.onCakes(CakeCache.getCakes());
                    return;
                }
                default:
                    break;
        }
        new GetCakesTask(new GetCakeListener() {
            @Override
            public void onCakes(List<Cake> cakes) {
                CakeCache.addToCache(cakes);
                listener.onCakes(cakes);
            }

            @Override
            public void onFailure(String rawResponse) {
                listener.onFailure(rawResponse);
            }
        }).execute();
    }
}

