package com.mr2.mvvm_test.sample.dagger_and_view_model;

import dagger.Provides;

@dagger.Module
public class InjectModule {
    @Provides
    Repository provideRepository(){ return new RepositoryImpl(null); }
}
