package com.mr2.mvvm_test.sample.dagger_and_view_model;

import android.content.Context;

import javax.inject.Inject;

public class RepositoryImpl implements Repository {
    private String data = "初期値";

    @Inject
    public RepositoryImpl(Context context) {

    }

    @Override
    public void set(String data){
        System.out.println(this.data) ;
        this.data = data;
        System.out.println(this.data) ;
    }
    @Override
    public String get(){ return data; }
    @Override
    public void delete(){ this.data = ""; }
}
