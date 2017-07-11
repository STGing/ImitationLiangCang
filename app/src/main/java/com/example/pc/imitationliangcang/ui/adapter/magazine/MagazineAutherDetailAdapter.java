package com.example.pc.imitationliangcang.ui.adapter.magazine;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.bean.magazinefragment.MagazineListBean;
import com.example.pc.imitationliangcang.ui.activity.ShopFragmentWebViewActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PC on 2017/7/10.
 */

public class MagazineAutherDetailAdapter extends RecyclerView.Adapter<MagazineAutherDetailAdapter.MagazineFragmentViewHolder> {
    private final Context mContext;
    private final MagazineListBean.DataBean.ItemsBean items;
    private final LayoutInflater mLayouInflater;


    public MagazineAutherDetailAdapter(Context mContext, MagazineListBean.DataBean.ItemsBean items) {
        this.mContext = mContext;
        this.items = items;
        this.mLayouInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MagazineFragmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MagazineFragmentViewHolder magazineFragmentViewHolder = new MagazineFragmentViewHolder(mLayouInflater.inflate(R.layout.magazine_auther_detail_item, null, false));
        return magazineFragmentViewHolder;
    }

    @Override
    public void onBindViewHolder(MagazineFragmentViewHolder holder, int position) {
        String key = items.getKeys().get(position);
        MagazineListBean.DataBean.ItemsBean.Bean bean = items.getInfos().get(key).get(0);

        //设置数据
        Picasso.with(mContext)
                .load(bean.getCover_img())
                .into(holder.magazineFragmentItemIv);

        holder.magazineFragmentItemTvName.setText(bean.getTopic_name());
        holder.magazineFragmentItemCatName.setText("— "+bean.getCat_name()+" —");
        //holder.magazineFragmentItemDate.setText("— "+key+" —");

        //设置点击事件
        holder.setListener(position);
    }

    @Override
    public int getItemCount() {
        return items.getKeys() == null ? 0 : items.getKeys().size();
    }

    class MagazineFragmentViewHolder extends RecyclerView.ViewHolder {

        private final View view;
        @BindView(R.id.magazine_fragment_item_iv)
        ImageView magazineFragmentItemIv;
        @BindView(R.id.magazine_fragment_item_tvName)
        TextView magazineFragmentItemTvName;
        @BindView(R.id.magazine_fragment_item_catName)
        TextView magazineFragmentItemCatName;
        @BindView(R.id.magazine_fragment_item_date)
        TextView magazineFragmentItemDate;

        public MagazineFragmentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
           this.view = itemView;
        }

        public void setListener(int position){
            String key = items.getKeys().get(position);
            final MagazineListBean.DataBean.ItemsBean.Bean bean = items.getInfos().get(key).get(0);

           view.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   //跳转到H5界面
                   Intent intent = new Intent(mContext,ShopFragmentWebViewActivity.class);
                   intent.putExtra("topic_url",bean.getTopic_url());
                   mContext.startActivity(intent);
               }
           });
        }
    }

}
