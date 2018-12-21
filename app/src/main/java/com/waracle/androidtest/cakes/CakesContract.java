package com.waracle.androidtest.cakes;

import com.waracle.androidtest.data.Cake;
import com.waracle.androidtest.service.CakeService;

import java.util.List;

public interface CakesContract {

    interface Observable {
        void loadCakes(CakeService.FetchStrategy fetchStrategy);
        void unsubscribe();
    }

    interface Observer {
        void onCakes(List<Cake> cakes);
        void onError(String error);
    }


}
