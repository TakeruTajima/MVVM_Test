package com.mr2.mvvm_test.ui.room_for_recycler_sample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mr2.mvvm_test.R;
import com.mr2.mvvm_test.ui.room_sample.Item;

import java.util.List;

public class ItemListRecyclerAdapter extends RecyclerView.Adapter<ItemListRecyclerAdapter.ItemListViewHolder> {
    private List<Item> list;
    private OnItemClickListener listener;

    public ItemListRecyclerAdapter(List<Item> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ItemListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_raw, parent, false);
        return new ItemListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemListViewHolder holder, int position) {
        holder.item = list.get(position);
        holder.textView.setText(list.get(position).name);
        holder.textView.setOnClickListener(v -> {
            if (null != listener) listener.onItemClick(list.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ItemListViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        Item item;
        ItemListViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.itemListRawText);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Item item);
    }

    public void setListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
