package com.waracle.androidtest.cakes;

import com.waracle.androidtest.data.Cake;
import com.waracle.androidtest.service.CakeService;

import java.util.List;

public class CakesViewModel implements CakesContract.Observable, CakeService.GetCakeListener {

    CakesContract.Observer cakesObserver;

    public CakesViewModel(CakesContract.Observer view){
        cakesObserver = view;
    }

    @Override
    public void loadCakes(CakeService.FetchStrategy fetchStrategy) {
        CakeService.getCakes(this, fetchStrategy);
    }

    @Override
    public void unsubscribe() {
        cakesObserver = null;
    }

    @Override
    public void onFailure(String rawResponse) {
        if(cakesObserver != null)
            cakesObserver.onError(rawResponse);
    }

    @Override
    public void onCakes(List<Cake> cakes) {
        if(cakesObserver != null)
            cakesObserver.onCakes(cakes);
    }
}
