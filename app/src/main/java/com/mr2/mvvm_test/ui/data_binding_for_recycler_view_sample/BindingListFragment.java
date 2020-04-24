package com.mr2.mvvm_test.ui.data_binding_for_recycler_view_sample;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mr2.mvvm_test.R;
import com.mr2.mvvm_test.databinding.BindingListFragmentBinding;
import com.mr2.mvvm_test.ui.TextInputDialogFragment;
import com.mr2.mvvm_test.ui.room_sample.Item;
import com.mr2.mvvm_test.ui.room_sample.ItemDao;
import com.mr2.mvvm_test.ui.room_sample.ItemListRecyclerAdapter;
import com.mr2.mvvm_test.ui.room_sample.MyDatabase;

import java.util.List;


public class BindingListFragment extends Fragment {
    /* ---------------------------------------------------------------------- */
    /* Field                                                                  */
    /* ---------------------------------------------------------------------- */
    public static final String TAG = BindingListFragment.class.getSimpleName() + "(4156)";

    private View view = null;
    private Context context;
    private ListViewModel listViewModel;
    private BindingListFragmentBinding binding;

    public static BindingListFragment newInstance(){
        return new BindingListFragment();
    }

    /* Example */
//    private BindingListFragmentListener listener = null;

    /* ---------------------------------------------------------------------- */
    /* Listener                                                               */
    /* ---------------------------------------------------------------------- */
    /* Example */
//    public interface BindingListFragmentListener {
//        void onHogeEvent();
//    }

    /* ---------------------------------------------------------------------- */
    /* Lifecycle                                                              */
    /* ---------------------------------------------------------------------- */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
        this.context = context;

        /* Example */
//        if (!(context instanceof ItemDataActivityFragmentListener)) {
//            throw new UnsupportedOperationException(
//                    TAG + ":" + "Listener is not Implementation.");
//        } else {
//            listener = (ItemDataActivityFragmentListener) context;
//        }
//        this.activity = (Activity) context;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        assert getActivity() != null;
        listViewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()).create(ListViewModel.class);
        listViewModel.fetchItemList();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
//        view = inflater.inflate(R.layout./*このフラグメントで使用するレイアウトのID*/, container, false);
        binding = DataBindingUtil.inflate(inflater, R.layout.binding_list_fragment, container, false);
        binding.setLifecycleOwner(this);
        binding.setViewModel(listViewModel);
        binding.floatingActionButton5.setOnClickListener(this::onClickAddButton);
        binding.bindingRecyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        loadData(binding.bindingRecyclerView, listViewModel.liveList.getValue());
        assert null != binding.bindingRecyclerView.getAdapter();
        ((ItemListRecyclerAdapter) binding.bindingRecyclerView.getAdapter()).setListener(this::onClickItem);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach");
    }

    /* ---------------------------------------------------------------------- */
    /* other method                                                           */
    /* ---------------------------------------------------------------------- */

//    @BindingAdapter("items")
//    public static void bindItems(RecyclerView recyclerView, String s){
//
//    }

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
    @BindingAdapter("recycler_adapter")
    public static void loadData(RecyclerView recyclerView, List<Item> newList){
        ItemListRecyclerAdapter adapter = (ItemListRecyclerAdapter) recyclerView.getAdapter();
        if (null == adapter) {
            adapter = new ItemListRecyclerAdapter(newList);
            recyclerView.setAdapter(adapter);
            return;
        }
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(adapter.getDiffUtilCallback(newList), true);
        adapter.update(newList);
        result.dispatchUpdatesTo(adapter);
    }



    //fabから
    public void onClickAddButton(View view){
        TextInputDialogFragment dialog = TextInputDialogFragment.newInstance("テキストを入力してください。");
        dialog.setOnDialogResultListener(this::onDialogResult); //Listener復帰は？
        dialog.show(getChildFragmentManager(), "");
    }

    public void onDialogResult(String s){
        listViewModel.insertItem(s);
    }

    //recyclerView.adapterから
    public void onClickItem(Item item){
        List<Item> list = listViewModel.liveList.getValue();
        String s = "item position is `" + list.indexOf(item) + "` from liveList.";
        System.out.println(s);
        listViewModel.deleteItem(item);
    }
}

