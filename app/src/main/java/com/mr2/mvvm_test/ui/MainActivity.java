package com.mr2.mvvm_test.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mr2.mvvm_test.R;
import com.mr2.mvvm_test.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
    }
}
