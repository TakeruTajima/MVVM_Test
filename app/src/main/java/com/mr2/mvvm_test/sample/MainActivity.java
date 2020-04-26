package com.mr2.mvvm_test.sample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mr2.mvvm_test.R;
import com.mr2.mvvm_test.sample.dagger_and_view_model.InjectFragment;
import com.mr2.mvvm_test.sample.dagger_sample.SampleDaggerFragment;
import com.mr2.mvvm_test.sample.data_binding_for_recycler_view_sample.BindingListFragment;
import com.mr2.mvvm_test.sample.room_sample.ItemListFragment;
import com.mr2.mvvm_test.sample.view_model_sample.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            setInjectFragment();
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

    private void setInjectFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, InjectFragment.newInstance())
                .commitNow();
    }

}