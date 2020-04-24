package com.mr2.mvvm_test.ui.dagger_and_view_model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class InjectViewModel extends ViewModel {
    MutableLiveData<String> liveDataText = new MutableLiveData<>();
    @Inject
    Repository repository;

    @Inject
    public InjectViewModel(Repository repository) {
        this.repository = repository;
    }

}
