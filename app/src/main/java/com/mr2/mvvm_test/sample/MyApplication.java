package com.mr2.mvvm_test.sample;

import android.app.Application;
import android.content.Context;

import com.mr2.mvvm_test.sample.dagger_sample.Component;
import com.mr2.mvvm_test.sample.dagger_sample.DaggerComponent;
import com.mr2.mvvm_test.sample.dagger_sample.Module;

public class MyApplication extends Application {
    public MyDatabase db;
    public Component component;
    public Context context = this;

    @Override
    public void onCreate() {
        super.onCreate();
        db = MyDatabase.getInstance(this);
        setupComponent();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        db = null;
    }

    private void setupComponent(){
        component = DaggerComponent.builder().module(new Module(this)).build();
    }
}
