package com.vtu.baseapp.adapter;

public interface IAdapter {
    Object getObjForPosition(int position);
    /*return layout for item*/
    int getLayoutIdForPosition(int position);
}
