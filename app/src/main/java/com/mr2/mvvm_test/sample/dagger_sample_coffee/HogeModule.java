package com.mr2.mvvm_test.sample.dagger_sample_coffee;

import android.content.Context;

import com.mr2.mvvm_test.sample.MyApplication;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ryosuke on 2018/02/14.
 */

@Module(subcomponents = {CustomerComponent.class})
public class HogeModule {
    private final MyApplication app;

    public HogeModule(MyApplication app) {
        this.app = app;
    }

    @Provides
    Context provideApplicationContext(){
        return app.getApplicationContext();
    }
}
