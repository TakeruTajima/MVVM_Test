package com.mr2.mvvm_test.sample.live_data_by_room;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ExampleViewModel extends ViewModel {

    /**
     * Daoからの取得でExampleData単品を取得する方法わからん
     * ListだとTextViewにバインドするやりかたわからん
     * ListからRecyclerViewにバインドするやりかたわからん
     *
     */
    LiveData<List<ExampleData>> listLiveData;


}
