package com.mr2.mvvm_test.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mr2.mvvm_test.R;
import com.mr2.mvvm_test.ui.dagger_sample.SampleDaggerFragment;
import com.mr2.mvvm_test.ui.data_binding_for_recycler_view_sample.BindingListFragment;
import com.mr2.mvvm_test.ui.room_sample.ItemListFragment;
import com.mr2.mvvm_test.ui.view_model_sample.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            setBindingListFragment();
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

    private void setBindingListFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, BindingListFragment.newInstance())
                .commitNow();
    }

}