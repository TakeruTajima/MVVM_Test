package com.mr2.mvvm_test.sample.dagger_and_view_model;

interface Repository {
    void set(String data);

    String get();

    void delete();
}
