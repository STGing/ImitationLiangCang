package com.example.pc.imitationliangcang.ui.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

    //是否全选所有商品(true为默认全选)
    private boolean isAllCheckd = true;
    private String discountPriceFomat;//折扣价格的string.format
    private String toltalPriceFormat;//总价格的string.format
    private String savePriceFormat;//节省价格的string.format

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

        //设置屏幕底部6个价格
        setBottomData(goodsInfo);

        //checkBox的选择
        holder.shopCarItemIvCheck.setChecked(true);

        //设置增加和减少商品数量按钮的监听
        holder.shopCarItemAddsub.setOnNumberChangeListener(new AddSubView.OnNumberChangeListener() {
            @Override
            public void numberChange(int value) {
                //1.修改goodinfo的数量信息
                goodsInfo.setGoodsNumber(value);
                //2.刷新价格
            }
        });


        /**
         * 点击全选的checkBox的点击事件
         */
        shopCarSwiAllCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isAllCheckd){
                    //现在为全选，点击了之后，切换到全不选的状态
                    isAllCheckd = false;

                    //1.清空 折扣 节省 总价 的全局变量
                    clearAllPrice();

                    //2.清空底部的价格选项
                    clearBottomData();

                    Toast.makeText(mContext, "全不选", Toast.LENGTH_SHORT).show();
                } else {
                    //现在为全不选，点击了之后，切换到全选的状态
                    isAllCheckd = true;//设置状态全选

                    //从新计算价格(底部的6个价格)
                    flashPrice();

                    Toast.makeText(mContext, "全选", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    /**
     * 清空底部的价格选项
     */
    private void clearBottomData() {
        refreshBottomPrice();
    }

    /**
     * 清空 折扣 节省 总价 的全局变量
     */
    private void clearAllPrice() {
        discountPri = 0.0;
        totalPri = 0.0;
        savePri = 0.0;
    }

    /**
     * 计算屏幕底部的6个价格
     */
    private void flashPrice() {

        if (list != null && list.size() > 0){

            for (int i = 0; i < list.size(); i++) {

                GoodsInfo goodsInfo = list.get(i);

                //判断有无折扣
                if (!goodsInfo.getDiscount_price().equals("0")){
                    //有折扣

                    //重新计算总折扣价格
                    Double disPri = (Double.parseDouble(goodsInfo.getPrice())-Double.parseDouble(goodsInfo.getDiscount_price()))
                            *goodsInfo.getGoodsNumber();
                    discountPri += disPri;

                    //重新计算总节省价格
                    Double svaePri = (Double.parseDouble(goodsInfo.getPrice())-Double.parseDouble(goodsInfo.getDiscount_price()))
                            *goodsInfo.getGoodsNumber();
                    savePri += svaePri;

                    //重新计算总价格
                    totalPri += Double.parseDouble(goodsInfo.getDiscount_price())*goodsInfo.getGoodsNumber();

                } else {
                    //没有折扣

                    //重新计算总价格
                    totalPri += Double.parseDouble(goodsInfo.getPrice())*goodsInfo.getGoodsNumber();
                }
            }

            //循环完成之后，设置数据
            refreshBottomPrice();

        }


    }

    //刷新底部的价格
    private void refreshBottomPrice() {
        //设置折扣价
        setDiscountPrice();
        //设置总价格
        setTatalPrice();
        //设置节省价格
        setSavePrice();
    }

    /**
     * 根据传入的Bean对象，设置底部数据
     * @param goodsInfo
     */
    private void setBottomData(GoodsInfo goodsInfo) {
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
        setDiscountPrice();

        //3.包装价格//设置为0.0
        String packPrice = shopCarTvPackPrice.getText().toString();
        String format2 = String.format(packPrice, "0.0");
        shopCarTvPackPrice.setText(format2);
        //4.邮费//设置为0.0
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
        setTatalPrice();

        //6.节省价格 = 折扣价格
        if (goodsInfo.getDiscount_price().equals("0")){
            //没有折扣
            discountPri += 0.0;
        } else {
            //有折扣
            Double svaePri = (Double.parseDouble(goodsInfo.getPrice())-Double.parseDouble(goodsInfo.getDiscount_price()))
                    *goodsInfo.getGoodsNumber();
            savePri += svaePri;
        }
        setSavePrice();
    }

    /**
     * 根据savePri设置节省的价格
     */
    private void setSavePrice() {
        String format5 = String.format(savePriceFormat, savePri+"");
        shopCarSavePrice.setText(format5);
    }

    /**
     * 根据totlaPri设置总价格
     */
    private void setTatalPrice() {
        String format4 = String.format(toltalPriceFormat, this.totalPri+"");
        shopCarTotalPrice.setText(format4);
    }

    /**
     * 根据discountPri设置折扣价格
     */
    private void setDiscountPrice() {
        String format = String.format(discountPriceFomat, discountPri+"");
        shopCarTvDiscountPrice.setText(format);
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

        //获取3个价格的format格式
        savePriceFormat = shopCarSavePrice.getText().toString();
        toltalPriceFormat = shopCarTotalPrice.getText().toString();
        discountPriceFomat = shopCarTvDiscountPrice.getText().toString();
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
