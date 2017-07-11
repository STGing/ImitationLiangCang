package com.example.pc.imitationliangcang.ui.adapter.sharefragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.pc.imitationliangcang.bean.sharefragment.ShareFragmentBean;

import java.util.List;

/**
 * Created by PC on 2017/7/11.
 */

public class ShareFragmentAdapter extends RecyclerView.Adapter {
    private final Context mContext;
    private final List<ShareFragmentBean.ListBean> list;
    private final LayoutInflater mLayoutInflater;

    public ShareFragmentAdapter(Context mContext, List<ShareFragmentBean.ListBean> list) {
        this.mContext = mContext;
        this.list = list;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
