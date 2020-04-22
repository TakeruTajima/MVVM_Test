package com.mr2.mvvm_test.ui.dagger_sample;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ryosuke on 2018/02/13.
 */
@Module
class DripCoffeeModule{

    @Provides static Pump providePump(Thermosiphon pump){
        return pump;
    }

    @Singleton
    @Provides
    static Heater provideHeater(){
        return new ElectricHeater();
    }
}

