package com.mr2.mvvm_test.ui.data_binding_for_recycler_view_sample;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;
import java.util.Objects;

public abstract class BaseCallback<T extends List> extends DiffUtil.Callback {
    protected T oldList;
    protected T newList;

    public BaseCallback(T oldList, T newList){
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() { return oldList.size(); }

    @Override
    public int getNewListSize() { return newList.size(); }

    /**
     * 識別子等で見て同一オブジェクトならTrue、違うならFalse。
     * ListItem自体にequals()の実装が必要。
     * 返り値がTrueの場合areContentsTheSame()が呼ばれる。
     */
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return Objects.equals(oldList.get(oldItemPosition), newList.get(newItemPosition));
    }

    /**
     * Callback.areItemTheSame(識別子等で見て同一オブジェクトである)がtrueの場合に呼ばれる。
     * オブジェクトが変化している場合にfalseを返すように実装してください。
     * @return オブジェクトは変化していない
     */
    @Override
    public abstract boolean areContentsTheSame(int oldItemPosition, int newItemPosition);
}
