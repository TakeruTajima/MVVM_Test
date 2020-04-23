package com.mr2.mvvm_test.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mr2.mvvm_test.R;
import com.mr2.mvvm_test.ui.dagger_sample.SampleDaggerFragment;
import com.mr2.mvvm_test.ui.room_for_recycler_sample.ItemListFragment;
import com.mr2.mvvm_test.ui.view_model_sample.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            setListFragment();
        }
    }

    private void setMainFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow();
    }

    private void setListFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, ItemListFragment.newInstance())
                .commitNow();
    }

    private void setDaggerFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, SampleDaggerFragment.newInstance())
                .commitNow();
    }

}