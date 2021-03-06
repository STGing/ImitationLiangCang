package com.example.pc.imitationliangcang.ui.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.bean.GoodsInfo;
import com.example.pc.imitationliangcang.db.DBDao;
import com.example.pc.imitationliangcang.ui.activity.PayActivity;
import com.example.pc.imitationliangcang.ui.view.AddSubView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
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
    private TextView shopCarSettlement;//结算

    //是否全选所有商品(true为默认全选)
    private String discountPriceFomat;//折扣价格的string.format
    private String toltalPriceFormat;//总价格的string.format
    private String savePriceFormat;//节省价格的string.format
    private String goodsNumberFormat;



    //构造器
    public ShopCarAdapter(Context mContext, List<GoodsInfo> list) {
        this.mContext = mContext;
        this.list = list;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }


    public void setView(TextView shopCarTvFullSbuPrice, TextView shopCarTvDiscountPrice
            , TextView shopCarTvPackPrice, TextView shopCarTvShipPrice
            , final CheckBox shopCarSwiAllCheck, TextView shopCarTotalPrice
            , final TextView shopCarSavePrice,TextView shopCarSettlement) {

        this.shopCarTvFullSbuPrice = shopCarTvFullSbuPrice;
        this.shopCarTvDiscountPrice = shopCarTvDiscountPrice;
        this.shopCarTvPackPrice = shopCarTvPackPrice;
        this.shopCarTvShipPrice = shopCarTvShipPrice;
        this.shopCarSwiAllCheck = shopCarSwiAllCheck;
        this.shopCarTotalPrice = shopCarTotalPrice;
        this.shopCarSavePrice = shopCarSavePrice;
        this.shopCarSettlement = shopCarSettlement;


        //获取3个价格的format格式
        savePriceFormat = shopCarSavePrice.getText().toString();
        toltalPriceFormat = shopCarTotalPrice.getText().toString();
        discountPriceFomat = shopCarTvDiscountPrice.getText().toString();

        //判断有无数据，是否设置全选 checkBox
        if (list != null && list.size() > 0){
            shopCarSwiAllCheck.setChecked(true);
        } else {
            shopCarSwiAllCheck.setChecked(false);
        }

        //设置底部6个价格
        setBottomPrice();

        //结算的点击事件
        shopCarSettlement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //建立一个新的集合
                ArrayList<GoodsInfo> payGoods = new ArrayList<GoodsInfo>();

                //便利数据，凡是有被选中的，就将数据放入到新的集合中。
                for (int i = 0; i < list.size(); i++) {
                    GoodsInfo goodsInfo = list.get(i);
                    if (goodsInfo.isChecked()){
                        //如果被选中
                        payGoods.add(goodsInfo);
                    }
                }


                //将数据传递到新的Activity
                Intent intent = new Intent(mContext, PayActivity.class);
                intent.putExtra("payGoods",payGoods);
                mContext.startActivity(intent);
            }
        });

    }




    @Override
    public ShopCarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        shopCarViewHolder = new ShopCarViewHolder(mLayoutInflater.inflate(R.layout.shop_car_item, null, false));
        return shopCarViewHolder;
    }

    @Override
    public void onBindViewHolder(ShopCarViewHolder holder, int position) {
        //获取数据
        final GoodsInfo goodsInfo = list.get(position);

        //holder.shopCarItemIvCheck.setOnCheckedChangeListener(null);
        //checkBox的选择:根据商品是否被选中设置
        holder.shopCarItemIvCheck.setChecked(goodsInfo.isChecked());

        //设置数据
        //商品图片
        Picasso.with(mContext)
                .load(goodsInfo.getGoods_image())
                .into(holder.shopCarItemIvGoodsPic);

        holder.shopCarItemTvBrandName.setText(goodsInfo.getBrand_name());//品牌名称
        holder.shopCarItemTvGoodName.setText(goodsInfo.getGoods_name());//商品名称
        holder.shopCarItemTvSku.setText(goodsInfo.getChoiceSku());//设置商品sku

        //设置商品显示数量
        holder.shopCarItemTvGoodsNumber.setVisibility(View.VISIBLE);
        holder.shopCarItemTvGoodsNumber.setText("× "+goodsInfo.getGoodsNumber());

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

        //设置AddSubView的当前数值
        holder.shopCarItemAddsub.setValue(goodsInfo.getGoodsNumber());
        //设置最小值
        holder.shopCarItemAddsub.setMinValue(1);

        //设置是否显示删除/加减按钮
        if (goodsInfo.isShowEdit()){
            //true说明显示
            holder.shopCarItemTvDel.setVisibility(View.VISIBLE);
            holder.shopCarItemAddsub.setVisibility(View.VISIBLE);
        } else {
            //false说明不显示
            holder.shopCarItemTvDel.setVisibility(View.GONE);
            holder.shopCarItemAddsub.setVisibility(View.GONE);
        }

        //设置item的view的点击事件
        holder.setListener();

    }


    /**
     * 设置购物车底部的各个价格
     */
    public void setBottomPrice() {
        //1.满减价格//设置为0.0
        String fullPrice = shopCarTvFullSbuPrice.getText().toString();
        String format1 = String.format(fullPrice, "0.0");
        shopCarTvFullSbuPrice.setText(format1);
        //2.折扣价格 = 未打折价格-打折价格
        setDiscountPrice();

        //3.包装价格//设置为0.0
        String packPrice = shopCarTvPackPrice.getText().toString();
        String format2 = String.format(packPrice, "0.0");
        shopCarTvPackPrice.setText(format2);
        //4.邮费//设置为0.0
        String shipPrice = shopCarTvShipPrice.getText().toString();
        String format3 = String.format(shipPrice, "0.0");
        shopCarTvShipPrice.setText(format3);

        //5.设置总价格 : 1.有折扣 * 数量  2:无折扣   价格* 数量
        setTotalPrice();

        //6.节省价格 = 折扣价格
        setSavePrice();
    }

    /**
     * 根据数据设置节省的价格
     */
    private void setSavePrice() {

        Double savePrice = 0.0;

        if (list != null && list.size()>0) {

            for (int i = 0; i < list.size(); i++) {

                GoodsInfo goodsInfo = list.get(i);

                //判断该商品有没有checked
                if (goodsInfo.isChecked()){
                    //选中，计算
                    if (goodsInfo.getDiscount_price().equals("0")){
                        //没有折扣
                    } else {
                        //有折扣
                        Double svaePri = (Double.parseDouble(goodsInfo.getPrice())-Double.parseDouble(goodsInfo.getDiscount_price()))
                                *goodsInfo.getGoodsNumber();
                        savePrice += svaePri;
                    }
                } else {
                    //没有选中，不用计算
                }

            }
        }

        String format5 = String.format(savePriceFormat, savePrice+"");
        shopCarSavePrice.setText(format5);
    }

    /**
     * 根据数据设置总价格
     */
    private void setTotalPrice() {
        Double totalPrice = 0.0;

        if (list != null && list.size()>0){

            for (int i = 0; i < list.size(); i++) {

                GoodsInfo goodsInfo = list.get(i);

                //判断该商品有没有checked
                if (goodsInfo.isChecked()) {
                    //选中，计算

                    //判断有无折扣
                    if (goodsInfo.getDiscount_price().equals("0")){
                        //没有折扣
                        totalPrice += Double.parseDouble(goodsInfo.getPrice())*goodsInfo.getGoodsNumber();
                    } else {
                        //有折扣
                        totalPrice += Double.parseDouble(goodsInfo.getDiscount_price())*goodsInfo.getGoodsNumber();
                    }
                }
            }
            ;
        } else {
            //没有数据
        }
        String format4 = String.format(toltalPriceFormat, totalPrice+"");
        shopCarTotalPrice.setText(format4);
    }

    /**
     * 根据数据设置折扣价格
     */
    private void setDiscountPrice() {
        Double discountPrice = 0.0;

        if (list != null && list.size()>0) {

            for (int i = 0; i < list.size(); i++) {
                GoodsInfo goodsInfo = list.get(i);

                //判断该商品有没有checked
                if (goodsInfo.isChecked()) {
                    //选中，计算
                    if (goodsInfo.getDiscount_price().equals("0")){
                        //没有折扣
                    } else {
                        //有折扣
                        Double disPri = (Double.parseDouble(goodsInfo.getPrice())-Double.parseDouble(goodsInfo.getDiscount_price()))
                                *goodsInfo.getGoodsNumber();
                        discountPrice += disPri;
                    }
                }

            }
        }

        String format = String.format(discountPriceFomat, discountPrice+"");
        shopCarTvDiscountPrice.setText(format);
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

        public void setListener(){
            /**
             *item的checked点击事件的监听
             */
            shopCarItemIvCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int layoutPosition = getLayoutPosition();//当前item的位置
                    GoodsInfo goodsInfo = list.get(layoutPosition);//获取对应的数据
                    goodsInfo.setChecked(isChecked);//改变数据状态
                    //检查数据以设置全选按钮状态
                    checkIsAllChenked();
                    // item 的 checkBox 变化了，重新计算价格
                    setBottomPrice();

                }
            });


            /**
             * 设置增加和减少商品数量按钮的监听
             */
            shopCarItemAddsub.setOnNumberChangeListener(new AddSubView.OnNumberChangeListener() {
                @Override
                public void numberChange(int value) {

                    //商品数量发生改变

                    //1.修改当前显示的商品数量
                    shopCarItemTvGoodsNumber.setText("× "+value);

                    //2.修改当前goodinfo信息的 number
                    int layoutPosition = getLayoutPosition();
                    GoodsInfo goodsInfo = list.get(layoutPosition);
                    goodsInfo.setGoodsNumber(value);

                    //3.数量发生了变化，计算的价格也改变
                    setBottomPrice();

                    //4.同步数据库
                    DBDao.getInstance().update(goodsInfo.getGoods_id(),goodsInfo);
                    //5.同步服务器


                }
            });

            /**
             * 点击删除按钮，删除数据
             */
            shopCarItemTvDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //点击之后

                    //弹出dialog确认
                    new android.support.v7.app.AlertDialog.Builder(mContext)
                            .setMessage("是否删除此物品")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //1.list集合中删除
                                    String goods_id = list.get(getLayoutPosition()).getGoods_id();
                                    list.remove(getLayoutPosition());

                                    //3.刷新数据
                                    notifyDataSetChanged();

                                    //4.刷新价格
                                    setDiscountPrice();
                                    setTotalPrice();
                                    setSavePrice();

                                    //5.同步数据库
                                    DBDao.getInstance().deleteData(goods_id);
                                    //6.同步服务器
                                }
                            })
                            .setNegativeButton("取消",null)
                            .show();

                }
            });
        }

    }


    /**
     * 检查是否全部选中，如果不是将全选的状态改变
     */
    private void checkIsAllChenked() {
        if (list != null && list.size() > 0){
            //有数据

            for (int i = 0; i < list.size(); i++) {
                GoodsInfo goodsInfo = list.get(i);
                if (!goodsInfo.isChecked()){
                    //如果有没有被选中的
                    //设置全选的checkBox状态为不全选
                    shopCarSwiAllCheck.setChecked(false);
                    return;//结束，只要有1个没选中，全选就不设置
                }
            }
            //如果都是选中状态，设置为全选状态
            shopCarSwiAllCheck.setChecked(true);

        } else {
            //没有数据
            //设置全选按钮为不全选
            shopCarSwiAllCheck.setChecked(false);
        }
    }

    /**
     * 根据传递进来的 boolean 设置数据是否选中状态，同时改变全选的 checkBox 的状态
     * @param isCheck
     */
    public void setAllDatasIsChecked(boolean isCheck) {
        Log.d("tag111", "checkIsAllChenked: ");
        if (list != null && list.size() > 0) {
            //有数据
            for (int i = 0; i < list.size(); i++) {
                GoodsInfo goodsInfo = list.get(i);
                goodsInfo.setChecked(isCheck);
            }

            notifyDataSetChanged();
        } else {
            shopCarSwiAllCheck.setChecked(false);
        }
    }

    /**
     * 根据传入的变量改变GoodInfo中的是否显示 删除/加减 的变量的值
     */
    public void setShowEdidView(boolean isShow) {
        for (int j = 0; j < list.size(); j++) {
            GoodsInfo goodsInfo = list.get(j);
            goodsInfo.setShowEdit(isShow);
        }
        notifyDataSetChanged();
    }

}
