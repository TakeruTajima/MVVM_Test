package com.mr2.mvvm_test.ui.live_data_by_room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "example_table")
public class ExampleData {
    @PrimaryKey(autoGenerate = true)
    int id;
    String name;

    public ExampleData(String name) { this.name = name; }
}
