package com.vtu.baseapp.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

public class BaseViewHolder extends RecyclerView.ViewHolder {
    private final ViewDataBinding binding;

    public ViewDataBinding getBinding() {
        return binding;
    }

    public BaseViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Object obj) {
        binding.setVariable(com.vtu.baseapp.BR.obj, obj);
        binding.executePendingBindings();
    }
}