package com.mr2.mvvm_test.sample.dagger_sample;

import com.mr2.mvvm_test.sample.MyApplication;

import dagger.Provides;

/**
 * 依存関係を解決するインスタンスを提供する。
 */
@dagger.Module
public class Module {
    private final MyApplication app;

    public Module(MyApplication app) {
        this.app = app;
    }

    @Provides
    Repository provideExampleRepository(){
        return new RepositoryImpl();
    }

    /*
    Repository<Interface>
    部品の規格。
    RepositoryImpl
    部品。バリエーションがある

    Service
    フレーム。部品を組み付けたら完成の状態　@Inject:部品をInjectionしたいフィールド/コンストラクタ
    Component<Interface>
    "組立工場/ディーラー"のカタログ。@Component:Serviceを配布すると表明
    (ComponentImpl?)
    (DaggerComponent)
    Modules
    部品工場。RepositoryImplを製造。
     */
}
