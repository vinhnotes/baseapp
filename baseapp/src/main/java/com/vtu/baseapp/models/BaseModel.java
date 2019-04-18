package com.vtu.baseapp.models;

import android.content.Context;
import androidx.databinding.BaseObservable;
import android.view.View;

import java.io.Serializable;


public abstract class BaseModel extends BaseObservable implements Serializable {

    public abstract int getLayoutID();

    public abstract boolean isFullSpan();

    public abstract void onSelected(View view, Context context);
}

