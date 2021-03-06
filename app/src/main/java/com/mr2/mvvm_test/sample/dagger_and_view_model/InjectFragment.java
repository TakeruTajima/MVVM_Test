package com.mr2.mvvm_test.sample.dagger_and_view_model;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.mr2.mvvm_test.R;
import com.mr2.mvvm_test.databinding.ExampleFragmentBinding;
import com.mr2.my_genelic_ui_library.dialog.PromptDialogFragment;

public class InjectFragment extends Fragment {
    public InjectViewModel viewModel;
    public ExampleFragmentBinding binding;

    public static InjectFragment newInstance(){
        return new InjectFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(getViewModelStore(), getDefaultViewModelProviderFactory()).get(InjectViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.example_fragment, container, false);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        binding.floatingActionButton2.setOnClickListener(this::showInputDialog);
        return binding.getRoot();
    }

    void showInputDialog(View view){
        PromptDialogFragment dialog = new PromptDialogFragment.Builder("新規登録", "登録する")
                .setHint("アイテム名")
                .setNegativeButton("cancel")
                .setMessage("アイテム名を入力してください")
                .setListener((dialog1, which, input) -> {
                    if (DialogInterface.BUTTON_POSITIVE == which) onDialogResult(input);
                    dialog1.dismiss();
                })
                .create();
        dialog.show(getChildFragmentManager(), "");
    }

    void onDialogResult(String inputText){
        viewModel.update(inputText);
    }
}
