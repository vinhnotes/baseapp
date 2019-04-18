package com.vtu.baseapp.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vtu.baseapp.models.BaseModel;

import java.util.ArrayList;


public class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder>
                                        implements IAdapter {
    protected Context context;
    private ClickListener onClickListener;
    protected ArrayList<T> list;

    public BaseAdapter(ArrayList<T> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setData(ArrayList list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public ClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(ClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public BaseViewHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(
                layoutInflater, viewType, parent, false);

        return new BaseViewHolder(binding);
    }

    public void onBindViewHolder(final BaseViewHolder holder,
                                 final int position) {
        final BaseModel obj = (BaseModel) getObjForPosition(position);
        holder.bind(obj);
        /*layout for item*/
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        if (layoutParams != null && layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams staggerLayoutParams = (StaggeredGridLayoutManager.LayoutParams) layoutParams;
            if (obj.isFullSpan()) {
                staggerLayoutParams.setFullSpan(true);
            } else {
                staggerLayoutParams.setFullSpan(false);
            }
        }
        /*handle click*/
        holder.getBinding().getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getOnClickListener() == null) {
                    obj.onSelected(view, context);
                } else {
                    onClickListener.onItemClick(position, holder.getBinding().getRoot());
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }


    public interface ClickListener {
        void onItemClick(int position, View v);
    }

    @Override
    public Object getObjForPosition(int position) {
        return list.get(position);
    }

    @Override
    public int getLayoutIdForPosition(int position) {
        BaseModel model = (BaseModel) list.get(position);
        return model.getLayoutID();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

