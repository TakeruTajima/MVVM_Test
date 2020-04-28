package com.mr2.mvvm_test.sample.domain.inhouse.storageLocation;

public interface StorageLocationRepository {
    StorageLocation get(StorageLocationId storageLocationId);

    boolean save(StorageLocation storageLocation);
}
