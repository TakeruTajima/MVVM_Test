package com.mr2.mvvm_test.ui.dagger_sample;

import javax.inject.Singleton;

@Singleton
@dagger.Component (modules = Module.class)
public interface Component {
    Service get();
}
