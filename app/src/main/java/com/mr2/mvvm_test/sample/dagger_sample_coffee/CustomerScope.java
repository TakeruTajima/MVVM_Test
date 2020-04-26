package com.mr2.mvvm_test.sample.dagger_sample_coffee;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by ryosuke on 2018/02/14.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomerScope {
}
