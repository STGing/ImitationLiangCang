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
    private ShopCarViewHolder shopCarViewHolder;
    private TextView shopCarTvFullSbuPrice;
    private TextView shopCarTvDiscountPrice;
    private TextView shopCarTvPackPrice;
    private TextView shopCarTvShipPrice;
    private CheckBox shopCarSwiAllCheck;
    private TextView shopCarTotalPrice;
    private TextView shopCarSavePrice;

    //折扣的价格
    private Double discountPri = 0.0;

    //节约的价格
    private Double savePri = 0.0;

    //总价格
    private Double totalPri = 0.0;

    //构造器
    public ShopCarAdapter(Context mContext, List<GoodsInfo> list) {
        this.mContext = mContext;
        this.list = list;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ShopCarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mLayoutInflater.inflate(R.layout.shop_car_item, null, false);
        shopCarViewHolder = new ShopCarViewHolder(view);
        return shopCarViewHolder;
    }

    @Override
    public void onBindViewHolder(ShopCarViewHolder holder, int position) {
        //获取数据
        final GoodsInfo goodsInfo = list.get(position);

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
            //有折扣，显示折扣价格
            holder.shopCarItemDiscountPrice.setVisibility(View.VISIBLE);
            String dispri = holder.shopCarItemDiscountPrice.getText().toString();
            String disprice = String.format(dispri, goodsInfo.getDiscount_price());
            holder.shopCarItemDiscountPrice.setText(disprice);

        }

        //未打折价格
        if (goodsInfo.getDiscount_price().equals("0")){
            //没有打折，显示
            String pri = holder.shopCarItemPrice.getText().toString();
            String price = String.format(pri, goodsInfo.getPrice());
            holder.shopCarItemPrice.setText(price);
        } else {
            //有打折，带横线的显示
            String pri = holder.shopCarItemPrice.getText().toString();
            String price = String.format(pri, goodsInfo.getPrice());
            holder.shopCarItemPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            holder.shopCarItemPrice.setText(price);
        }



        /**
         * 设置购物车Activity界面的数据
         */
        //1.满减价格//设置为0.0
        String fullPrice = shopCarTvFullSbuPrice.getText().toString();
        String format1 = String.format(fullPrice, "0.0");
        shopCarTvFullSbuPrice.setText(format1);
        //2.折扣价格 = 未打折价格-打折价格
        //判断有无折扣
        if (goodsInfo.getDiscount_price().equals("0")){
            //没有折扣
            discountPri += 0.0;
        } else {
            //有折扣
            Double disPri = (Double.parseDouble(goodsInfo.getPrice())-Double.parseDouble(goodsInfo.getDiscount_price()))
                    *goodsInfo.getGoodsNumber();
            discountPri += disPri;
        }
        String s = shopCarTvDiscountPrice.getText().toString();
        String format = String.format(s, discountPri+"");
        shopCarTvDiscountPrice.setText(format);

        //3.包装价格//设置为0.0
        String packPrice = shopCarTvPackPrice.getText().toString();
        String format2 = String.format(packPrice, "0.0");
        shopCarTvPackPrice.setText(format2);
//        //4.邮费//设置为0.0
        String shipPrice = shopCarTvShipPrice.getText().toString();
        String format3 = String.format(shipPrice, "0.0");
        shopCarTvShipPrice.setText(format3);
        //5.总价 : 1.有折扣 * 数量  2:无折扣   价格* 数量
        if (goodsInfo.getDiscount_price().equals("0")){
            //没有折扣
            totalPri += Double.parseDouble(goodsInfo.getPrice())*goodsInfo.getGoodsNumber();
        } else {
            //有折扣
            totalPri += Double.parseDouble(goodsInfo.getDiscount_price())*goodsInfo.getGoodsNumber();
        }
        String tolPri = shopCarTotalPrice.getText().toString();
        String format4 = String.format(tolPri, this.totalPri+"");
        shopCarTotalPrice.setText(format4);

        //6.节省价格 = 折扣价格
        if (goodsInfo.getDiscount_price().equals("0")){
            //没有折扣
            discountPri += 0.0;
        } else {
            //有折扣
            Double disPri = (Double.parseDouble(goodsInfo.getPrice())-Double.parseDouble(goodsInfo.getDiscount_price()))
                    *goodsInfo.getGoodsNumber();
            savePri += disPri;
        }
        String save = shopCarSavePrice.getText().toString();
        String format5 = String.format(save, savePri+"");
        shopCarSavePrice.setText(format5);






        //设置增加和减少商品数量按钮的监听
        holder.shopCarItemAddsub.setOnNumberChangeListener(new AddSubView.OnNumberChangeListener() {
            @Override
            public void numberChange(int value) {
                //1.修改goodinfo的数量信息
                goodsInfo.setGoodsNumber(value);
                //2.刷新价格
            }
        });

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void setView(TextView shopCarTvFullSbuPrice, TextView shopCarTvDiscountPrice
            , TextView shopCarTvPackPrice, TextView shopCarTvShipPrice
            , CheckBox shopCarSwiAllCheck, TextView shopCarTotalPrice
            , TextView shopCarSavePrice) {

        this.shopCarTvFullSbuPrice = shopCarTvFullSbuPrice;
        this.shopCarTvDiscountPrice = shopCarTvDiscountPrice;
        this.shopCarTvPackPrice = shopCarTvPackPrice;
        this.shopCarTvShipPrice = shopCarTvShipPrice;
        this.shopCarSwiAllCheck = shopCarSwiAllCheck;
        this.shopCarTotalPrice = shopCarTotalPrice;
        this.shopCarSavePrice = shopCarSavePrice;
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
        //隐藏数量
        shopCarViewHolder.shopCarItemTvGoodsNumber.setVisibility(View.GONE);
        //隐藏sku
        shopCarViewHolder.shopCarItemTvSku.setVisibility(View.GONE);

        //显示addsbu
        shopCarViewHolder.shopCarItemAddsub.setVisibility(View.VISIBLE);
        //显示删除按钮
        shopCarViewHolder.shopCarItemTvDel.setVisibility(View.VISIBLE);
    }

    //隐藏编辑的View
    public void hideEdit(){

        //隐藏addsbu
        shopCarViewHolder.shopCarItemAddsub.setVisibility(View.GONE);
        //隐藏删除按钮
        shopCarViewHolder.shopCarItemTvDel.setVisibility(View.GONE);

        //显示数量
        shopCarViewHolder.shopCarItemTvGoodsNumber.setVisibility(View.VISIBLE);
        //显示sku
        shopCarViewHolder.shopCarItemTvSku.setVisibility(View.VISIBLE);
    }


}
