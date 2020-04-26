package com.mr2.mvvm_test.sample;

import android.app.Application;

import com.mr2.mvvm_test.sample.dagger_sample.Component;
import com.mr2.mvvm_test.sample.dagger_sample.DaggerComponent;

public class MyApplication extends Application {
    public MyDatabase db;
    public Component component;

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
        component = DaggerComponent.create();
    }
}
