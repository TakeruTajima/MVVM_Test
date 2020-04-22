package com.mr2.mvvm_test.ui.room_for_recycler_sample;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.mr2.mvvm_test.ui.room_sample.Item;
import com.mr2.mvvm_test.ui.room_sample.ItemDao;

import java.util.List;

public class ListViewModel extends ViewModel {
    private ItemDao dao;
    public MutableLiveData<List<Item>> liveDataList;
    private static final int PAGE_SIZE = 50;
    //
    public LiveData<PagedList<Item>> pagedListLiveDataItem;

    public ListViewModel(ItemDao dao){
        this.dao = dao;
        pagedListLiveDataItem = new LivePagedListBuilder<>(dao.getAllByDataSource(), PAGE_SIZE).build();
    }

//    public static class Factory implements ViewModelProvider.Factory {
//        private ItemDao dao;
//
//        @NonNull
//        @Override
//        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
//            return null;
//        }
//    }
}
