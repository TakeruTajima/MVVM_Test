package com.mr2.mvvm_test.ui.room_sample;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mr2.mvvm_test.R;
import com.mr2.mvvm_test.ui.TextInputDialogFragment;

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
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchListByRoom();
        setCountObserve();
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
                List<Item> list = dao.getAllNameAsc();
                if (null == getActivity()) return;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        onLoadComplete();
                        loadData(recyclerView, list);
                        showToast(item.name + "deleted.");
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
                List<Item> list = dao.getAllNameAsc();
                if (null == getActivity()) return;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast("item created.");
//                        onLoadComplete();
                        loadData(recyclerView, list);
                    }
                });
            }
        }).start();
    }

    /**
     * RoomからLiveDataとして取り出したデータはDBが更新されるたびにobserverを呼ぶ。
     * これでViewModel内のLiveDataをLayoutのほうでcustomSetterにBindしてやれば
     * DB更新のたびにcustomSetterが呼ばれる。
     */
    private void setCountObserve(){
        ItemDao dao = MyDatabase.getInstance(context).itemDao();
        new Thread(()-> {
            LiveData<Integer> liveCount = dao.count();
            if (null != getActivity()) {
                getActivity().runOnUiThread(()-> {
                    liveCount.observe(this, integer -> {
                        System.out.println("Live data onChanged. Count is `" + integer + "`.");
                    });
                });
            }
        }).start();
    }

    /**
     * custom setter
     * LiveDataのListをAdapterにしてSet
     * @param recyclerView  BindingAdapterを設定したView自身
     * @param newList customSetterの引数はMutableLiveDataだが実際に渡されるのはString？
     */
//    @BindingAdapter("recycler_adapter")
    public static void loadData(RecyclerView recyclerView, List<Item> newList){
        ItemListRecyclerAdapter adapter = (ItemListRecyclerAdapter) recyclerView.getAdapter();
        if (null == adapter) return;
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(adapter.getDiffUtilCallback(newList), true);
        adapter.update(newList);
        result.dispatchUpdatesTo(adapter);
    }
}

