package com.mr2.mvvm_test.ui.room_for_recycler_sample;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.mr2.mvvm_test.ui.room_sample.Item;
import com.mr2.mvvm_test.ui.room_sample.ItemDao;

import java.util.List;

public class ListViewModel extends ViewModel {
    private ItemDao dao;
    public MutableLiveData<List<Item>> liveDataList;
    //
    public LiveData<PagedList<Item>> pagedListLiveDataItem;

//    public ListViewModel(ItemDao dao){
//        this.dao = dao;
//        pagedListLiveDataItem = new LivePagedListBuilder<>(dao.getAllNameAsc(), 50).build();
//    }
}
