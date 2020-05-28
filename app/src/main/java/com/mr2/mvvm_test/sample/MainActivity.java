package com.mr2.mvvm_test.sample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mr2.mvvm_test.R;
import com.mr2.mvvm_test.sample.dagger_and_view_model.InjectFragment;
import com.mr2.mvvm_test.sample.dagger_sample.SampleDaggerFragment;
import com.mr2.mvvm_test.sample.data_binding_for_recycler_view_sample.BindingListFragment;
import com.mr2.mvvm_test.sample.room_sample.ItemListFragment;
import com.mr2.mvvm_test.sample.view_model_sample.MainFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainMenuFragment.MainMenuFragmentListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            List<String> list = new ArrayList<>();
            list.add(MainFragment.class.getSimpleName());
            list.add(ItemListFragment.class.getSimpleName());
            list.add(SampleDaggerFragment.class.getSimpleName());
            list.add(BindingListFragment.class.getSimpleName());
            list.add(InjectFragment.class.getSimpleName());
            serMainMenuFragment(list);
        }
    }

//    private static final int MAIN_MENU = 0;
    private void serMainMenuFragment(List<String> list){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, MainMenuFragment.newInstance(list))
                .commitNow();
    }

    private void fragmentTransition(int target){
        switch (target){
            case MAIN_FRAGMENT:
                setMainFragment();
                break;
            case LIST_FRAGMENT:
                setListFragment();
                break;
            case DAGGER_FRAGMENT:
                setDaggerFragment();
                break;
            case BINDING_LIST_FRAGMENT:
                setBindingListFragment();
                break;
            case INJECT_FRAGMENT:
                setInjectFragment();
                break;
        }
    }

    private static final int MAIN_FRAGMENT = 0;
    private void setMainFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .addToBackStack("")
                .commit();
    }

    private static final int LIST_FRAGMENT = 1;
    private void setListFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, ItemListFragment.newInstance())
                .addToBackStack("")
                .commit();
    }

    private static final int DAGGER_FRAGMENT = 2;
    private void setDaggerFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, SampleDaggerFragment.newInstance())
                .addToBackStack("")
                .commit();
    }

    private static final int BINDING_LIST_FRAGMENT = 3;
    private void setBindingListFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, BindingListFragment.newInstance())
                .addToBackStack("")
                .commit();
    }

    private static final int INJECT_FRAGMENT = 4;
    private void setInjectFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, InjectFragment.newInstance())
                .addToBackStack("")
                .commit();
    }

    @Override
    public void transition(List<String> list, int clickedListPosition) {
        fragmentTransition(clickedListPosition);
    }
}