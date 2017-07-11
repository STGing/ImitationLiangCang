package com.example.pc.imitationliangcang.ui.adapter.magazine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.bean.magazinefragment.MagazineAutherBean;
import com.example.pc.imitationliangcang.ui.activity.MagazineAutherDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PC on 2017/7/10.
 */

public class MaganizeAuterAdapter extends RecyclerView.Adapter<MaganizeAuterAdapter.MagazineAutherViewHolder> {
    private final Context mContext;
    private final List<MagazineAutherBean.DataBean.ItemsBean> items;
    private final LayoutInflater mLayoutInflater;

    public MaganizeAuterAdapter(Context mContext, List<MagazineAutherBean.DataBean.ItemsBean> items) {
        this.mContext = mContext;
        this.items = items;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MagazineAutherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MagazineAutherViewHolder(mLayoutInflater.inflate(R.layout.magazine_auther_item,null));
    }

    @Override
    public void onBindViewHolder(MagazineAutherViewHolder holder, int position) {
        MagazineAutherBean.DataBean.ItemsBean itemsBean = items.get(position);
        //设置数据
        Picasso.with(mContext)
                .load(itemsBean.getThumb())
                .into(holder.magazineAutherItemIv);

        holder.magazineAutherItemTvName.setText(itemsBean.getAuthor_name());
        holder.magazineAutherItemTvWork.setText(itemsBean.getNote());

        //设置点击事件
        holder.setListener(position);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 :items.size();
    }


    class MagazineAutherViewHolder extends RecyclerView.ViewHolder{
        private final View view;
        @BindView(R.id.magazine_auther_item_iv)
        de.hdodenhof.circleimageview.CircleImageView magazineAutherItemIv;
        @BindView(R.id.magazine_auther_item_tvName)
        TextView magazineAutherItemTvName;
        @BindView(R.id.magazine_auther_item_tvWork)
        TextView magazineAutherItemTvWork;

        MagazineAutherViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;
        }

        public void setListener(int position){
            final MagazineAutherBean.DataBean.ItemsBean itemsBean = items.get(position);
            final String author_id = itemsBean.getAuthor_id();

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //点击之后,跳转新页面，结束当前页面
                    Intent intent = new Intent(mContext,MagazineAutherDetailActivity.class);
                    intent.putExtra("author_id",author_id);
                    mContext.startActivity(intent);

                    //结束当前页面
                    Activity activity = (Activity) mContext;
                    activity.finish();
                }
            });
        }
    }
}
