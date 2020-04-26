package com.mr2.mvvm_test.sample.dagger_sample_coffee;

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
