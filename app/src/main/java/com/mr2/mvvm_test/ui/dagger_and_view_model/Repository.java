package com.mr2.mvvm_test.ui.dagger_and_view_model;

public class Repository {
    private String data = "";
    public void set(String data){ this.data = data; }
    public String get(){ return data; }
    public void delete(){ this.data = ""; }
}
