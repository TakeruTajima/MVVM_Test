package com.mr2.mvvm_test.ui.data_binding_for_recycler_view_sample;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mr2.mvvm_test.ui.room_sample.Item;

import java.util.List;

public class ListViewModel extends ViewModel {
    public LiveData<List<Item>> liveList;

    public void fetchItemList(){

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
