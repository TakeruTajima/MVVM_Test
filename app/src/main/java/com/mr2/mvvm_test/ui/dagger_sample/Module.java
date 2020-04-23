package com.mr2.mvvm_test.ui.dagger_sample;

import dagger.Provides;

/**
 * 依存関係を解決するインスタンスを提供する。
 */
@dagger.Module
class Module {
    @Provides
    Repository provideExampleRepository(){
        return new RepositoryImpl();
    }
}
