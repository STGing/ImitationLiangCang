package com.example.pc.imitationliangcang.ui.adapter.bilibili;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.bean.BiLiBean;
import com.example.pc.imitationliangcang.ui.activity.LivePlayerActivity;
import com.example.pc.imitationliangcang.utils.GlideUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PC on 2017/7/21.
 */

public class BiLiItemAdapter extends RecyclerView.Adapter<BiLiItemAdapter.BiLiItemItemViewHolder> {
    private final Context mContext;
    private final List<BiLiBean.DataBean.PartitionsBean.LivesBean> lives;
    private final LayoutInflater mLayoutInflater;

    public BiLiItemAdapter(Context mContext, List<BiLiBean.DataBean.PartitionsBean.LivesBean> lives) {
        this.mContext = mContext;
        this.lives = lives;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public BiLiItemItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BiLiItemItemViewHolder(mLayoutInflater.inflate(R.layout.bili_item_item,parent,false));
    }

    @Override
    public void onBindViewHolder(BiLiItemItemViewHolder holder, int position) {

        BiLiBean.DataBean.PartitionsBean.LivesBean livesBean = lives.get(position);

        GlideUtils.loadImageView(mContext,livesBean.getCover().getSrc(),holder.biliItemItemIv);

        holder.biliItemItemTvTitle.setText(livesBean.getTitle());

        holder.biliItemItemTvAutherName.setText(livesBean.getOwner().getName());

        holder.biliItemItemTvWatchNumber.setText(livesBean.getOnline()+"");

        //设置点击事件
        holder.setListener(position);
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class BiLiItemItemViewHolder extends RecyclerView.ViewHolder{
        private final View view;
        @BindView(R.id.bili_item_item_iv)
        ImageView biliItemItemIv;
        @BindView(R.id.bili_item_item_tvTitle)
        TextView biliItemItemTvTitle;
        @BindView(R.id.bili_item_item_tvAutherName)
        TextView biliItemItemTvAutherName;
        @BindView(R.id.bili_item_item_tvWatchNumber)
        TextView biliItemItemTvWatchNumber;

        BiLiItemItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;
        }

        public void setListener(int position){

            final BiLiBean.DataBean.PartitionsBean.LivesBean livesBean = lives.get(position);

            //点击进入视频播放界面
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext,LivePlayerActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data",livesBean);
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
