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

public class ClassifyFragmentRecyclerViewAdapter extends RecyclerView.Adapter<ClassifyFragmentRecyclerViewAdapter.ClassifyViewHolder> {
    private final FragmentActivity mContext;
    private final List<ClassifyFragmentBean.DataBean.ItemsBean> items;
    private final LayoutInflater mLayoutInflater;

    public ClassifyFragmentRecyclerViewAdapter(FragmentActivity mContext, List<ClassifyFragmentBean.DataBean.ItemsBean> items) {
        this.mContext = mContext;
        this.items = items;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ClassifyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ClassifyViewHolder(mLayoutInflater.inflate(R.layout.classify_fragment_item,null));
    }

    @Override
    public void onBindViewHolder(ClassifyViewHolder holder, int position) {
        ClassifyFragmentBean.DataBean.ItemsBean itemsBean = items.get(position);

        //设置图片
        Picasso.with(mContext)
                .load(itemsBean.getNew_cover_img())
                .into(holder.classifyFragmentIv);

        //设置点击事件
        holder.classifyFragmentIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击之后，跳转到商品详情页面

            }
        });
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }


    static class ClassifyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.classify_fragment_iv)
        ImageView classifyFragmentIv;

        ClassifyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
