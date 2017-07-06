package com.example.pc.imitationliangcang.ui.adapter.shopfragment;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.bean.shopfragment.ClassifyFragmentBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PC on 2017/7/6.
 */

public class ClassifyFragmentRecyclerViewAdapter extends RecyclerView.Adapter<ClassifyFragmentRecyclerViewAdapter.ViewHolder> {
    private final FragmentActivity mContext;
    private final List<ClassifyFragmentBean.DataBean.ItemsBean> items;
    private final LayoutInflater mLayoutInflater;

    public ClassifyFragmentRecyclerViewAdapter(FragmentActivity mContext, List<ClassifyFragmentBean.DataBean.ItemsBean> items) {
        this.mContext = mContext;
        this.items = items;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.classify_fragment_item,null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ClassifyFragmentBean.DataBean.ItemsBean itemsBean = items.get(position);

        //设置图片
        Picasso.with(mContext)
                .load(itemsBean.getNew_cover_img())
                .into(holder.classifyFragmentIv);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.classify_fragment_iv)
        ImageView classifyFragmentIv;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
