package com.example.pc.imitationliangcang.ui.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.bean.GoodsInfo;
import com.example.pc.imitationliangcang.ui.view.AddSubView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 购物车RecyclerView的适配器
 */

public class ShopCarAdapter extends RecyclerView.Adapter<ShopCarAdapter.ShopCarViewHolder> {


    private final Context mContext;
    private final List<GoodsInfo> list;
    private final LayoutInflater mLayoutInflater;

    //构造器
    public ShopCarAdapter(Context mContext, List<GoodsInfo> list) {
        this.mContext = mContext;
        this.list = list;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ShopCarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mLayoutInflater.inflate(R.layout.shop_car_item, null, false);
        return new ShopCarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShopCarViewHolder holder, int position) {
        //获取数据
        GoodsInfo goodsInfo = list.get(position);

        //设置数据
        //商品图片
        Picasso.with(mContext)
                .load(goodsInfo.getGoods_image())
                .into(holder.shopCarItemIvGoodsPic);

        holder.shopCarItemTvBrandName.setText(goodsInfo.getBrand_name());//品牌名称
        holder.shopCarItemTvGoodName.setText(goodsInfo.getGoods_name());//商品名称
        holder.shopCarItemTvSku.setText(goodsInfo.getChoiceSku());
        //设置数量
        holder.shopCarItemTvGoodsNumber.setVisibility(View.VISIBLE);
        String numb = holder.shopCarItemTvGoodsNumber.getText().toString();
        String number = String.format(numb, goodsInfo.getGoodsNumber());
        holder.shopCarItemTvGoodsNumber.setText(number);

        //打折价格
        //判断有无折扣
        if (goodsInfo.getDiscount_price().equals("0")){
            //没有折扣，不显示
            holder.shopCarItemDiscountPrice.setVisibility(View.GONE);
        } else {
            String dispri = holder.shopCarItemDiscountPrice.getText().toString();
            String disprice = String.format(dispri, goodsInfo.getDiscount_price());
            holder.shopCarItemDiscountPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            holder.shopCarItemDiscountPrice.setText(disprice);
        }

        //当前价格 = 价格-打折价格
        String pri = holder.shopCarItemPrice.getText().toString();
        double currPrice = Double.parseDouble(goodsInfo.getPrice()) - Double.parseDouble(goodsInfo.getDiscount_price());
        String price = String.format(pri, currPrice+"");
        holder.shopCarItemPrice.setText(price);



    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    class ShopCarViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.shop_car_item_iv_Check)
        CheckBox shopCarItemIvCheck;
        @BindView(R.id.shop_car_item_iv_goodsPic)
        ImageView shopCarItemIvGoodsPic;
        @BindView(R.id.shop_car_item_addsub)
        AddSubView shopCarItemAddsub;
        @BindView(R.id.shop_car_item_tv_brandName)
        TextView shopCarItemTvBrandName;
        @BindView(R.id.shop_car_item_tv_goodName)
        TextView shopCarItemTvGoodName;
        @BindView(R.id.shop_car_item_tvSku)
        TextView shopCarItemTvSku;
        @BindView(R.id.shop_car_item_price)
        TextView shopCarItemPrice;
        @BindView(R.id.shop_car_item_discountPrice)
        TextView shopCarItemDiscountPrice;
        @BindView(R.id.shop_car_item_tvdel)
        TextView shopCarItemTvDel;
        @BindView(R.id.shop_car_item_tv_goodsNumber)
        TextView shopCarItemTvGoodsNumber;

        ShopCarViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    //显示编辑的View
    public void showEdit(){
        //显示addsbu
        //显示删除按钮
    }

    //隐藏编辑的View
    public void hideEdit(){
        //隐藏addsbu
        //隐藏删除按钮
    }
}
