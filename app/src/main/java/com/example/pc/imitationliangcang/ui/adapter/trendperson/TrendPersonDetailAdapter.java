package com.example.pc.imitationliangcang.ui.adapter.trendperson;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.bean.trenpersonfragment.TrendPersonDetailBean;
import com.example.pc.imitationliangcang.ui.activity.GoodsDetailActivity;
import com.example.pc.imitationliangcang.ui.activity.TrendPersonGoodsDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PC on 2017/7/10.
 */

public class TrendPersonDetailAdapter extends RecyclerView.Adapter {
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private final TrendPersonDetailBean.DataBean.ItemsBean items;
    private List<TrendPersonDetailBean.DataBean.ItemsBean.GoodsBean> goods;
    private List<TrendPersonDetailBean.DataBean.ItemsBean.UsersBean> users;


    public TrendPersonDetailAdapter(Context mContext, TrendPersonDetailBean.DataBean.ItemsBean items) {
        this.mContext = mContext;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.items = items;
        goods = items.getGoods();
        users = items.getUsers();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (goods != null && goods.size() > 0) {
            return new TrendPersonDetailGoodsViewHolder(mLayoutInflater.inflate(R.layout.trend_person_detail_goods_item, parent, false));
        } else if (users != null && users.size() > 0) {
            return new TrendPersonDetailUserViewHolder(mLayoutInflater.inflate(R.layout.trend_person_detail_users_item, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        final TrendPersonDetailBean.DataBean.ItemsBean.GoodsBean goodsBean = goods.get(position);

        //设置数据
        if (holder instanceof TrendPersonDetailGoodsViewHolder){

            //设置图片
            Picasso.with(mContext)
                    .load(goodsBean.getGoods_image())
                    .into(((TrendPersonDetailGoodsViewHolder) holder).trendPersonDetailGoosIv);

            //点击事件
            final String is_outter = goodsBean.getIs_outter();
            ((TrendPersonDetailGoodsViewHolder) holder).trendPersonDetailGoosIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //判断跳转到哪里
                    if (is_outter.equals("0")){
                        //跳转到商品详情页面
                        Intent intent = new Intent(mContext, GoodsDetailActivity.class);
                        intent.putExtra("goods_id",goodsBean.getGoods_id());
                        mContext.startActivity(intent);

                    } else if (is_outter.equals("1")){
                        //跳转到达人商品详情页面
                        Intent intent = new Intent(mContext, TrendPersonGoodsDetailActivity.class);
                        intent.putExtra("goods_id",goodsBean.getGoods_id());
                        mContext.startActivity(intent);
                    }
                }
            });


        } else if (holder instanceof TrendPersonDetailUserViewHolder){
            //设置用户图片，用户名称
            Picasso.with(mContext)
                    .load(users.get(position).getUser_image().getOrig())
                    .into(((TrendPersonDetailUserViewHolder) holder).trendPersonDetailUserPic);
            //名称
            ((TrendPersonDetailUserViewHolder) holder).trendPersonDetailUserName.setText(users.get(position).getUser_name());
            //工作
            ((TrendPersonDetailUserViewHolder) holder).trendPersonDetailUserWork.setText(users.get(position).getUser_desc()+"");
        }
    }

    @Override
    public int getItemCount() {

        if (goods != null && goods.size() > 0) {
            return goods.size();
        } else if (users != null && users.size() > 0) {
            return users.size();
        }
        return 0;
    }

    /**
     * 商品列表的viewholder
     */
    class TrendPersonDetailGoodsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.trend_person_detail_goosIv)
        ImageView trendPersonDetailGoosIv;

        TrendPersonDetailGoodsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    /**
     * 用户列表的viewholder
     */
    class TrendPersonDetailUserViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.trend_person_detail_userPic)
        ImageView trendPersonDetailUserPic;
        @BindView(R.id.trend_person_detail_userName)
        TextView trendPersonDetailUserName;
        @BindView(R.id.trend_person_detail_userWork)
        TextView trendPersonDetailUserWork;

        TrendPersonDetailUserViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
