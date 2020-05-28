package com.mr2.mvvm_test.sample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mr2.mvvm_test.R;

import java.util.ArrayList;
import java.util.List;

public class MainMenuRecyclerAdapter extends RecyclerView.Adapter<MainMenuRecyclerAdapter.MainMenuViewHolder>{
    private List<String> list = new ArrayList<>();
    private OnClickListener listener;

    public MainMenuRecyclerAdapter(List<String> list) {
        if (null != list) this.list = list;
    }

    public interface OnClickListener{
        void onItemClicked(int position);
    }

    @NonNull
    @Override
    public MainMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_menu_raw, parent, false);
        return new MainMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainMenuViewHolder holder, int position) {
        holder.textView1.setText(list.get(position));
        holder.textView1.setOnClickListener(v -> {
            if (null != listener) listener.onItemClicked(position);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MainMenuViewHolder extends RecyclerView.ViewHolder{
        View view;
        TextView textView1;
        TextView textView2;

        public MainMenuViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            textView1 = view.findViewById(R.id.mainMenuText1);
            textView2 = view.findViewById(R.id.mainMenuText2);
        }
    }

    public void serOnClickListener(OnClickListener listener){
        this.listener = listener;
    }
}
