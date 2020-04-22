package com.mr2.mvvm_test.ui.view_model_sample;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mr2.mvvm_test.MainRepositoryImpl;

/**
 * ViewModel とは？
 * ライフサイクルを意識した方法で UI 関連のデータを保存および管理するためのクラス。
 * ActivityやFragmentで、ViewModelProviderからクラスを指定してgetすることで、
 * ライフサイクルを超えてインスタンスを引き継ぐことができる。
 */
public class MainViewModel extends ViewModel {
    /**
     * MutableLiveData
     * 自動生成されるBindingクラスによって値の変更を監視可能なデータホルダ。
     * tips: publicじゃないとLayoutが読んでくれない。
     */
    public MutableLiveData<String> liveDataText = new MutableLiveData<>();
    public MutableLiveData<Integer> liveDataColor = new MutableLiveData<>();

    /**
     * ViewModelクラスのインスタンス生成はViewModelProviderから取得するので
     * コンストラクタインジェクションは使えない？　Daggerの出番か。
     * -> ViewModel内にでもstaticファクトリを実装して
     * ViewModelProviderのコンストラクタに渡してやるとViewModelのコンストラクタも使えるっぽい
     */
    private MainRepositoryImpl repository = new MainRepositoryImpl();

    /**
     * BindingクラスによってViewに反映される。
     * tips: setValue() はメインスレッドでしか呼べない。ワーカーから呼ぶ場合はpostValue()。
     */
    void fetchText(){
        liveDataText.postValue(repository.fetchText());
    }
}
