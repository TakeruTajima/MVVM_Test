package com.mr2.mvvm_test.sample;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mr2.mvvm_test.R;

import java.util.ArrayList;
import java.util.List;


public class MainMenuFragment extends Fragment {
    /* ---------------------------------------------------------------------- */
    /* Field                                                                  */
    /* ---------------------------------------------------------------------- */
    public static final String TAG = MainMenuFragment.class.getSimpleName() + "(4156)";

    private View view = null;
    private Context context;
    private List<String> list = new ArrayList<>();

    public static MainMenuFragment newInstance(List<String> list) {
        Bundle args = new Bundle();
        args.putInt("listSize", list.size());
        for (int i = 0; i < list.size(); i++) {
            args.putString("key" + i, list.get(i));
        }
        MainMenuFragment fragment = new MainMenuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /* Example */
    private MainMenuFragmentListener listener = null;

    /* ---------------------------------------------------------------------- */
    /* Listener                                                               */
    /* ---------------------------------------------------------------------- */
    /* Example */
    public interface MainMenuFragmentListener {
        void transition(List<String> list, int clickedListPosition);
    }

    /* ---------------------------------------------------------------------- */
    /* Lifecycle                                                              */
    /* ---------------------------------------------------------------------- */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
        this.context = context;

        /* Example */
        if (!(context instanceof MainMenuFragmentListener)) {
            throw new UnsupportedOperationException(
                    TAG + ":" + "Listener is not Implementation.");
        } else {
            listener = (MainMenuFragmentListener) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        Bundle args = getArguments();
        if (null == args) throw new IllegalArgumentException();
        if (0 != list.size()) return;
        int listSize = args.getInt("listSize");
        for (int i = 0; i < listSize; i++) {
            list.add(args.getString("key" + i));
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
//        view = inflater.inflate(R.layout./*このフラグメントで使用するレイアウトのID*/, container, false);
        view = inflater.inflate(R.layout.main_menu_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.menuList);
        MainMenuRecyclerAdapter adapter = new MainMenuRecyclerAdapter(list);
        adapter.serOnClickListener(this::onItemClicked);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        return view;
    }

    private void onItemClicked(int position) {
        listener.transition(list, position);
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


}

