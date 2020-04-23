package com.mr2.mvvm_test.ui.dagger_sample;

import javax.inject.Inject;

/**
 * Injectionしたオブジェクトを実際に使う場所。
 * RepositoryパターンでいえばRepositoryを使うUseCase等にあたる。
 *
 * 以下ふたつの方法でInject出来る。
 * constructor injection:
 * Injectしたいオブジェクトを引数にとるコンストラクタに@Injectアノテーションを付与する。
 * field injection:
 * 引数なしのコンストラクタと、Injectしたいフィールドに@Injectアノテーションを付与する。
 */
public class Service {
    @Inject /* field injection */
    Repository repository;
    @Inject
    Service(){}

//    @Inject  /* constructor injection */
//    Service(Repository repository) {
//        this.repository = repository;
//    }

    public String getText() {
        return null != repository? repository.getData() : "failed...";
    }
}
