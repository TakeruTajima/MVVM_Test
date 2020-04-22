package com.mr2.mvvm_test.ui.dagger_sample;

import javax.inject.Inject;

/**
 * Created by ryosuke on 2018/02/13.
 */

public class CoffeeMaker {


        @Inject Heater heater;
        @Inject Pump pump;

        @Inject
        CoffeeMaker(){
        }

        public void drip(){
            heater.heating();
            pump.pumping();
            System.out.println("Complete!");
        }

}
