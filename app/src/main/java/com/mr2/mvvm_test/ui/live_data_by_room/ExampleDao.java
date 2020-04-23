package com.mr2.mvvm_test.ui.live_data_by_room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ExampleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ExampleData exampleData);
    @Update
    void update(ExampleData exampleData);
    @Delete
    void delete(ExampleData exampleData);
    @Query("select * from example_table")
    List<ExampleData> getAll();
//    @Query("select * from example_table")
    @Query("select * from example_table")
    LiveData<List<ExampleData>> getAllLiveData();
//    @Query("select * from example_table")
//    LiveData<ExampleData> findById(int id);

}
