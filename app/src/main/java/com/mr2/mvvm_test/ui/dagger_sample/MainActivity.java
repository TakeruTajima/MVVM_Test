package com.mr2.mvvm_test.ui.dagger_sample;
//
//import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mr2.mvvm_test.R;

import javax.inject.Singleton;

import dagger.Component;

/**
 * link: https://satoshun.github.io/2015/05/dagger2/
 * .@Inject: 依存を注入します.
 * .@Module: インスタンスをProvideするメソッド郡を定義します.
 * .@Provide; 依存を解決するためのインスタンスを提供します.
 * .@Component: @Injectと@Module間の関係を定義します.
 *
 * 基本的なDagger2の使い方をまとめると
 * .@Injectで, 依存関係を定義する
 * .@Module, @Providesで依存関係を解決するようにインスタンスを提供する
 * .@Componentで複数のModuleを用いて, 依存解決Graphオブジェクトを生成する
 * アプリで依存解決Graphを使い, DIを行う
 */

public class MainActivity extends AppCompatActivity {

    private CoffeeShop coffeeShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        coffeeShop = DaggerMainActivity_CoffeeShop.builder()
//                .dripCoffeeModule(new DripCoffeeModule())
                .build();

        coffeeShop.open().drip();

        CustomerComponent customerComponent = coffeeShop.customerBuilder().build();

        Customer customer = customerComponent.enter();

        System.out.println("客が入店しました");
        System.out.println(customer.login());

        System.out.println("客が退出しました。");
        customer = null;




    }

    @Singleton
    @Component(modules = { DripCoffeeModule.class, HogeModule.class})
    interface CoffeeShop{
        CoffeeMaker open();
        CustomerComponent.Builder customerBuilder();

    }



}
