package com.mr2.mvvm_test.sample.view_model_sample;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.mr2.mvvm_test.R;
import com.mr2.mvvm_test.databinding.MainFragmentBinding;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    // <layout>で括ったlayoutから自動生成される。命名規則は「layoutファイル名のパスカルケース+Binding」。
    private MainFragmentBinding binding;
    private Context context;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=context;
        // ViewModelProviderからViewModelをget。
        mViewModel = new ViewModelProvider(getViewModelStore(), getDefaultViewModelProviderFactory()).get(MainViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // (以下レイアウトをインフレートしている間に行うことを推奨)
        // 通常のlayout inflateは行わず、DataBindingUtilからBindingクラスのインスタンスを取得。
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false);
        binding.setLifecycleOwner(getViewLifecycleOwner()); // lifecycle ownerを設定。
        binding.setViewModel(mViewModel); // view modelを設定。

        // LiveDataにObserver(購読者)を設定。
        // 第一引数のLifeCycleに従って購読を停止する。
        // liveDataTextの値更新時に、第二引数のonChanged(string)としてthis::onTextChangedが呼ばれる。
        mViewModel.liveDataText.observe(getViewLifecycleOwner(), this::onTextChanged);
//        mViewModel.liveDataText.observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(String s) {
//                onTextChanged(s);
//            }
//        });

        // inflate & bindされたviewを返す
        return binding.getRoot();
//        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // TODO: Use the ViewModel

        //初回　mViewModelにデータをLoad
        if (null == savedInstanceState) mViewModel.fetchText();
    }

    private void onTextChanged(String newName){
        // 文字色の変更は以下の様にしてカラーコードを取得、IntegerとしてliveDataColorに放り込む。
        if (newName.length() <= 3)
            mViewModel.liveDataColor.postValue(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        else
            mViewModel.liveDataColor.postValue(ContextCompat.getColor(context, R.color.colorAccent));
    }

    @BindingAdapter("custom_setter")
    public static void customSetter(TextView textView, String liveData){
        System.out.println("Custom setter called. Live data value is `" + liveData + "`." );
    }
}
