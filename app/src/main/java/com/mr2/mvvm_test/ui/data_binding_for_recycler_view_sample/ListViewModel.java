package com.mr2.mvvm_test.ui.data_binding_for_recycler_view_sample;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mr2.mvvm_test.MyApplication;
import com.mr2.mvvm_test.ui.room_sample.Item;
import com.mr2.mvvm_test.ui.room_sample.ItemDao;

import java.util.List;

/**
 *
 */
public class ListViewModel extends AndroidViewModel {
    public LiveData<List<Item>> liveList;
    private MyApplication app;

    public ListViewModel(@NonNull Application application) {
        super(application);
        this.app = (MyApplication) application;
    }

    public void fetchItemList(){
        ItemDao dao = app.db.itemDao();
        new Thread(()->{
            liveList = dao.getAllByLiveData();
        }).start();
    }

    public void insertItem(Item item){
        ItemDao dao = app.db.itemDao();
        new Thread(()->{
            dao.insert(item);
        }).start();
    }

    public void deleteItem(Item item){
        ItemDao dao = app.db.itemDao();
        new Thread(()->{
            dao.delete(item);
        }).start();
    }

    public void insertItem(String s) {
        ItemDao dao = app.db.itemDao();
        Item newItem = new Item(s);
        new Thread(()->{
            dao.insert(newItem);
        }).start();
    }


//    private ItemDao dao;
//    private static final int PAGE_SIZE = 50;
//    public LiveData<PagedList<Item>> pagedListLiveDataItem;
//
//    public ListViewModel(ItemDao dao){
//        this.dao = dao;
//        pagedListLiveDataItem = new LivePagedListBuilder<>(dao.getAllByDataSource(), PAGE_SIZE).build();
//    }
//
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
