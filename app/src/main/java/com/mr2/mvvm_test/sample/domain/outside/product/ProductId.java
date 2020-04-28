package com.mr2.mvvm_test.sample.domain.outside.product;

import com.mr2.mvvm_test.sample.domain.common.Identity;

public class ProductId extends Identity {
    protected ProductId() {
        super();
    }

    public ProductId(String uuid) {
        super(uuid);
    }
}
