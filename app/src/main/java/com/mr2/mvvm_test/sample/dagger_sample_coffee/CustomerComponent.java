package com.mr2.mvvm_test.sample.dagger_sample_coffee;

import dagger.Subcomponent;

/**
 * Created by ryosuke on 2018/02/14.
 */

@CustomerScope
@Subcomponent
public interface CustomerComponent {

    Customer enter();

    @Subcomponent.Builder
    interface Builder{
        CustomerComponent build();
    }
}
