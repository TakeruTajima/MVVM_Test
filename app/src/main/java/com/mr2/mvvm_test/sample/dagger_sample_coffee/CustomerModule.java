package com.mr2.mvvm_test.sample.dagger_sample_coffee;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ryosuke on 2018/02/13.
 */
@Module
class CustomerModule {

    @CustomerScope
    @Provides
    static Customer provdeCustomer(){
        return new Customer();
    }
}
