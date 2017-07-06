package com.example.pc.imitationliangcang.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment的基类
 */

public abstract class BaseFragment extends Fragment {

    public FragmentActivity mContext;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        View view = View.inflate(mContext,getLayoutID(),null);
        unbinder = ButterKnife.bind(this, view);
        initTitle();
        initView();

        return view;
    }

    public void initTitle() {

    }

    public void initListener() {

    }

    public abstract int getLayoutID();

    public abstract void initView();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
        initListener();
    }

    public abstract void initData();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
