package com.mr2.mvvm_test.ui.dagger_sample;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.mr2.mvvm_test.R;


public class SampleDaggerFragment extends Fragment {
    /* ---------------------------------------------------------------------- */
    /* Field                                                                  */
    /* ---------------------------------------------------------------------- */
    public static final String TAG = SampleDaggerFragment.class.getSimpleName() + "(4156)";
    private View view = null;

    public static SampleDaggerFragment newInstance(){
        return new SampleDaggerFragment();
    }
    /* ---------------------------------------------------------------------- */
    /* Listener                                                               */
    /* ---------------------------------------------------------------------- */
    /* ---------------------------------------------------------------------- */
    /* Lifecycle                                                              */
    /* ---------------------------------------------------------------------- */

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        view = inflater.inflate(R.layout.dagger_sample_fragment, container, false);
        TextView textView = view.findViewById(R.id.daggerText);
        //「依存関係をDagger2により解決したオブジェクト」を取得するためのComponentを取得
        Component component = DaggerComponent.create();
        //Componentから目当てのオブジェクトを取得
        Service service = component.get();
        //実行
        textView.setText(service.getText());
        return view;
    }

    /* ---------------------------------------------------------------------- */
    /* other method                                                           */
    /* ---------------------------------------------------------------------- */

}

