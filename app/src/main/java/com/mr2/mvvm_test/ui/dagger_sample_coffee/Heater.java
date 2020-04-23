package com.mr2.mvvm_test.ui.dagger_sample_coffee;

import javax.inject.Inject;

/**
 * Created by ryosuke on 2018/02/12.
 */

public class Heater{

    public Boolean isHot = false;

    @Inject
    Heater(){}

    public void heating(){
        isHot = true;
        System.out.println("heating");
    }

    public Boolean isHot(){
        return isHot;
    }
}