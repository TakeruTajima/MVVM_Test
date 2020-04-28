package com.mr2.mvvm_test.sample.domain.inhouse.user;

import com.mr2.mvvm_test.sample.domain.common.Identity;

public class UserId extends Identity {
    public UserId() {
        super();
    }

    protected UserId(String uuid) {
        super(uuid);
    }


}
