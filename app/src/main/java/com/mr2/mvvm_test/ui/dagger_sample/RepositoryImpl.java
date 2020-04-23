package com.mr2.mvvm_test.ui.dagger_sample;

public class RepositoryImpl implements Repository {
    @Override
    public String getData() {
        return "successful.";
    }
}
