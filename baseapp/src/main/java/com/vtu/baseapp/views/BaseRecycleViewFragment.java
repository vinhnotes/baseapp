package com.vtu.baseapp.views;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.vtu.baseapp.common.GridSpacingItemDecoration;
import com.vtu.baseapp.common.Utilities;
import com.vtu.baseapp.viewmodels.BaseRecycleViewViewModel;

public class BaseRecycleViewFragment extends BaseFragment {

    static int DEFAULT_COLUMN_COUNT     =   3;

    protected BaseRecycleViewViewModel viewModel;
    protected XRecyclerView recycleView;

    protected View view;

    public BaseRecycleViewViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(BaseRecycleViewViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public int getLayoutID() {
        return 0;
    }

    public static BaseRecycleViewFragment newInstance() {
        return new BaseRecycleViewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (view != null) {
            if ((ViewGroup)view.getParent() != null)
                ((ViewGroup)view.getParent()).removeView(view);
        } else {
            view = inflater.inflate(getLayoutID(), container, false);
        }

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(getColumn(), StaggeredGridLayoutManager.VERTICAL);
        recycleView.setLayoutManager(staggeredGridLayoutManager);
        recycleView.addItemDecoration(new GridSpacingItemDecoration(Utilities.dpToPx(getActivity(), 4)));
        recycleView.setItemAnimator(new DefaultItemAnimator());

        recycleView.setAdapter(viewModel.getBaseAdapter());

        recycleView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                viewModel.refreshData((v)->{
                    recycleView.refreshComplete();
                });
            }

            @Override
            public void onLoadMore() {
                viewModel.loadMoreData((v)->{
                    recycleView.loadMoreComplete();
                });
            }
        });

        return view;
    }

    public int getColumn() {
        return DEFAULT_COLUMN_COUNT;
    }

}
