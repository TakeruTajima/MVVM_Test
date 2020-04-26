package com.mr2.mvvm_test.sample.dagger_and_view_model;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

/**
 * ViewModelのインスタンス生成時にDaggerによるInject。
 * LiveDataの中身はInjectしたRepositoryから取得。
 * その状態でRepositoryの中身を更新した場合にどうなるのか？
 */
public class InjectViewModel extends ViewModel {
    public MutableLiveData<String> liveDataText2 = new MutableLiveData<>("data: ");
    public MutableLiveData<String> liveDataText;
    @Inject Repository repository;

    @Inject
    public InjectViewModel() {
//        this.repository = new RepositoryImpl();
        liveDataText = new MutableLiveData<>(repository.get());
    }

//    public InjectViewModel(Repository repository) {
//        this.repository = repository;
//        liveDataText = new MutableLiveData<>(repository.get());
//    }

    void update(String data){
        repository.set(data);
    }

    public static class Factory implements ViewModelProvider.Factory{
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (InjectViewModel.class != modelClass) throw new UnsupportedOperationException();
            InjectComponent component = DaggerInjectComponent.create();
            InjectViewModel vm = component.getViewModel();
            //noinspection unchecked
            return (T) vm;
//            return null;
        }
    }
}
