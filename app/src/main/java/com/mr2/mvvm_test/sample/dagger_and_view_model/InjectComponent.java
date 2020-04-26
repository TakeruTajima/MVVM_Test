package com.mr2.mvvm_test.sample.dagger_and_view_model;

import javax.inject.Singleton;

@Singleton
@dagger.Component (modules = {InjectModule.class})
public interface InjectComponent {
    InjectViewModel getViewModel();
}
