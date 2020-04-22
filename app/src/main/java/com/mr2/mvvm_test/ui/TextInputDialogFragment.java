package com.mr2.mvvm_test.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class TextInputDialogFragment extends DialogFragment {

    private EditText editText;
    private OnDialogResultListener listener;

    public interface OnDialogResultListener{
        void onDialogResult(String input);
    }

    public static TextInputDialogFragment newInstance(String s) {
        TextInputDialogFragment fragment = new TextInputDialogFragment();
        Bundle arg = new Bundle();
        arg.putString("message", s);
        fragment.setArguments(arg);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        editText = new EditText(getContext());
        editText.setSingleLine();
        editText.setHint("text input");
        assert getArguments() != null;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(getArguments().getString("message"))
                .setView(editText)
                .setNegativeButton("cancel", (dialog, which) -> { })
                .setPositiveButton("OK", (dialog, which) -> {
                    listener.onDialogResult(editText.getText().toString());
                });
        return builder.create();
    }

    public void setOnDialogResultListener(OnDialogResultListener listener){
        this.listener = listener;
    }
}
