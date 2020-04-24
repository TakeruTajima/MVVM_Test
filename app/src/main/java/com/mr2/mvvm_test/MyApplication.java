package com.mr2.mvvm_test;

import android.app.Application;

import com.mr2.mvvm_test.ui.room_sample.MyDatabase;

public class MyApplication extends Application {
    public MyDatabase db = MyDatabase.getInstance(getApplicationContext());

    @Override
    public void onTerminate() {
        super.onTerminate();
        db = null;
    }
}
