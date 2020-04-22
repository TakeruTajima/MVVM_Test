package com.mr2.mvvm_test.ui.room_sample;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * RoomDatabase用のDao。インターフェースとアノテーションで構成される。
 *
 */
@Dao
public interface ItemDao {
    @Insert
    void insert(Item item);
    @Update
    void update(Item item);
    @Delete
    void delete(Item item);
    @Query("select * from item")
    List<Item> getAll();
    @Query("select * from item order by name asc")
    List<Item> getAllNameAsc();
    @Query("select * from item")
    LiveData<List<Item>> getAllByLiveData();
}
