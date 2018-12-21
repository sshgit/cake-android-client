package com.waracle.androidtest.data;

import java.util.ArrayList;
import java.util.List;

public class CakeCache {
    private static List<Cake> cakeList = new ArrayList<>();

    public static void addToCache(List<Cake> cakes){
        if(cakes != null)
            cakeList = cakes;
    }

    public static List<Cake> getCakes(){
        return cakeList;
    }

}
