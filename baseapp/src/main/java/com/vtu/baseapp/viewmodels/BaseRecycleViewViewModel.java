package com.vtu.baseapp.viewmodels;

import android.content.Context;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.vtu.baseapp.adapter.BaseAdapter;
import com.vtu.baseapp.common.Callback;
import java.util.ArrayList;


public class BaseRecycleViewViewModel<T> extends BaseViewModel {
    protected Integer offset;
    private BaseAdapter baseAdapter;
    public ArrayList<T> itemSources;

    public BaseAdapter getBaseAdapter() {
        return baseAdapter;
    }

    public void setBaseAdapter(BaseAdapter baseAdapter) {
        this.baseAdapter = baseAdapter;
    }

    public ArrayList<T> getItemSources() {
        return itemSources;
    }

    public void setItemSources(ArrayList<T> itemSources) {
        this.itemSources = itemSources;
    }

    public BaseRecycleViewViewModel() {
        itemSources = new ArrayList<>();
    }

    public BaseRecycleViewViewModel(Context context) {
        super(context);
        offset = 0;
        itemSources = new ArrayList<>();
        baseAdapter = new BaseAdapter(itemSources ,context);
    }

    @Override
    public void getData(Callback... callback) {

    }

    public void refreshData(Callback... callback) {
        this.itemSources.clear();
        offset = 0;
        setItemSources(new ArrayList());
        getData(callback);
    }

    public void loadMoreData(Callback... callback) {
        getData(callback);
    }

    @BindingAdapter({"data"})
    public static void setData(RecyclerView recycleView, ArrayList data) {
        if (recycleView.getAdapter() instanceof BaseAdapter) {
            ((BaseAdapter) recycleView.getAdapter()).setData(data);
            ((BaseAdapter) recycleView.getAdapter()).notifyDataSetChanged();
        }
    }
}
