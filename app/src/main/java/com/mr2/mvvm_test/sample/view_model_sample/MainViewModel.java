package com.mr2.mvvm_test.sample.view_model_sample;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mr2.mvvm_test.sample.MainRepositoryImpl;

/**
 * ViewModel とは？
 * ライフサイクルを意識した方法で UI 関連のデータを保存および管理するためのクラス。
 * ActivityやFragmentで、ViewModelProviderからクラスを指定してgetすることで、
 * ライフサイクルを超えてインスタンスを引き継ぐことができる。
 */
public class MainViewModel extends ViewModel {
    /**
     * LiveDataとは？
     * 値の更新を監視することができるデータホルダクラス。ViewModel内で使われることが多い。
     * abstractなのでLiveData自体には値を更新する手段はない。
     *
     * MutableLiveData:
     * postValue(若しくはsetValue)で値を更新できる。
     * 更新されるとobserveで設定したonChangedメソッドが呼ばれる。
     *
     * DataBindingにより自動生成されるBindingクラスによって値の変更を監視可能。
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

    public MainViewModel() {
        liveDataText = new MutableLiveData<>(repository.fetchText());
    }

    /**
     * BindingクラスによってViewに反映される。
     * tips: setValue() はメインスレッドでしか呼べない。ワーカーから呼ぶ場合はpostValue()。
     */
    void fetchText(){
//        liveDataText.postValue(repository.fetchText());
    }

//    @Query("select e.name as e_name, p.model as model, c.name as c_name, sum(quantity) as quantity, e.price_value as value, e.price_name as name, e.unit as unit\n" +
//            "from products as p\n" +
//            "inner join equipments as e\n" +
//            "on p._id = e.product_id\n" +
//            "inner join companies as c\n" +
//            "on p.company_id = c._id\n" +
//            "left outer join storage_locations as s\n" +
//            "on e._id = s.equipment_id\n" +
//            "group by e._id")
}
