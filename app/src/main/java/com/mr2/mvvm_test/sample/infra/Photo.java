package com.mr2.mvvm_test.sample.infra;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(tableName = "photos", primaryKeys = {"equipment_id", "file_name"})
public class Photo {
    @NonNull String equipment_id;
    @NonNull String file_name;

    Photo(@NonNull String equipment_id, @NonNull String file_name) {
        this.equipment_id = equipment_id;
        this.file_name = file_name;
    }
}
