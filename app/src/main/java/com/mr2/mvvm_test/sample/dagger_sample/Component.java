package com.mr2.mvvm_test.sample.dagger_sample;

import javax.inject.Singleton;

@Singleton
@dagger.Component (modules = Module.class)
public interface Component {
    Service getService();
}
