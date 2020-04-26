package com.mr2.mvvm_test.sample;

public class MainRepositoryImpl implements MainRepository {
    public String data = "初期値";
    @Override
    public String fetchText(){ return data; }
}
