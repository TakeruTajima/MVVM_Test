package com.mr2.mvvm_test.ui.room_for_recycler_sample;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mr2.mvvm_test.R;
import com.mr2.mvvm_test.ui.TextInputDialogFragment;
import com.mr2.mvvm_test.ui.room_sample.Item;
import com.mr2.mvvm_test.ui.room_sample.ItemDao;
import com.mr2.mvvm_test.ui.room_sample.MyDatabase;

import java.util.ArrayList;
import java.util.List;


public class ItemListFragment extends Fragment {
    /* ---------------------------------------------------------------------- */
    /* Field                                                                  */
    /* ---------------------------------------------------------------------- */
    public static final String TAG = ItemListFragment.class.getSimpleName() + "(4156)";

    private View view = null;
    private Context context;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private MutableLiveData<List<Item>> liveDataList;

    public static ItemListFragment newInstance(){
        return new ItemListFragment();
    }

    /* ---------------------------------------------------------------------- */
    /* Listener                                                               */
    /* ---------------------------------------------------------------------- */

    /* ---------------------------------------------------------------------- */
    /* Lifecycle                                                              */
    /* ---------------------------------------------------------------------- */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
        this.context = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        view = inflater.inflate(R.layout.item_list_fragment, container, false);
        recyclerView = view.findViewById(R.id.itemListRecycler);
        recyclerView.setAdapter(new ItemListRecyclerAdapter(getList()));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        fab = view.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(v -> onClickFab());
        hoge();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchListByRoom();
    }

    private void showToast(String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
    /* ---------------------------------------------------------------------- */
    /* other method                                                           */
    /* ---------------------------------------------------------------------- */

    private List<Item> getList(){
        List<Item> list = new ArrayList<>();
        list.add(new Item("sample:1"));
        return list;
    }

    private List<Item> list = new ArrayList<>();

    private void fetchListByRoom(){
        MyDatabase db = MyDatabase.getInstance(context);
        db.setupDefaultData(context);
        ItemDao dao = db.itemDao();
        new Thread(new Runnable() {
            @Override
            public void run() {
                list = dao.getAllNameAsc();
                if (null == getActivity()) return;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        onLoadComplete();
                        showToast("completed.");
                    }
                });
            }
        }).start();
    }

    private void onLoadComplete(){
        ItemListRecyclerAdapter adapter = new ItemListRecyclerAdapter(list);
        adapter.setListener(this::onItemClicked);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void onItemClicked(Item item){
        ItemDao dao = MyDatabase.getInstance(context).itemDao();
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.delete(item);
                list = dao.getAllNameAsc();
                if (null == getActivity()) return;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        onLoadComplete();
                        showToast("deleted.");
                    }
                });
            }
        }).start();
    }

    private void onClickFab(){
        TextInputDialogFragment fragment = TextInputDialogFragment.newInstance("テキストを入力してください。");
        fragment.setOnDialogResultListener(this::onDialogResult);
        fragment.show(getChildFragmentManager(), "");
    }

    private void onDialogResult(String s){
        Item item = new Item(s);
        ItemDao dao = MyDatabase.getInstance(context).itemDao();
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.insert(item);
                list = dao.getAllNameAsc();
                if (null == getActivity()) return;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast("item created.");
                        onLoadComplete();
                    }
                });
            }
        }).start();
    }

    private void hoge(){
        ListViewModel vm = new ViewModelProvider(getViewModelStore(), getDefaultViewModelProviderFactory()).get(ListViewModel.class);


        liveDataList.observe(getViewLifecycleOwner(), new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> item) {
                list = item;
                onLoadComplete();
            }
        });
    }
}

