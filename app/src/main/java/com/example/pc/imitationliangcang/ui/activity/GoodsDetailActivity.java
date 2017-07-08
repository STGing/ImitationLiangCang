package com.example.pc.imitationliangcang.ui.activity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.base.BaseActivity;
import com.example.pc.imitationliangcang.bean.GoodsDetailBean;
import com.example.pc.imitationliangcang.common.NetWorkUrl;
import com.example.pc.imitationliangcang.utils.MyImageLoader;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 用于显示具体的某个商品的详细信息
 */

public class GoodsDetailActivity extends BaseActivity {


    @BindView(R.id.goods_detail_banner)
    Banner goodsDetailBanner;
    @BindView(R.id.goods_detail_brandName)
    TextView goodsDetailBrandName;
    @BindView(R.id.goods_detail_IvlikeCount)
    ImageView goodsDetailIvlikeCount;
    @BindView(R.id.goods_detail_goodName)
    TextView goodsDetailGoodName;
    @BindView(R.id.goods_detail_tvLikeCount)
    TextView goodsDetailTvLikeCount;
    @BindView(R.id.goods_detail_shared)
    ImageView goodsDetailShared;
    @BindView(R.id.goods_detail_send_)
    TextView goodsDetailSend;
    @BindView(R.id.goods_detail_goodsChoiceType)
    TextView goodsDetailGoodsChoiceType;
    @BindView(R.id.goods_detail_brandIv)
    ImageView goodsDetailBrandIv;
    @BindView(R.id.goods_detail_brand_detail_rl)
    RelativeLayout goodsDetailBrandDetailRl;
    @BindView(R.id.goods_detail_goodsRb)
    RadioButton goodsDetailGoodsRb;
    @BindView(R.id.goods_detail_bugRb)
    RadioButton goodsDetailBugRb;
    @BindView(R.id.goods_detail_radioGroup)
    RadioGroup goodsDetailRadioGroup;
    @BindView(R.id.goods_detail_brandName2)
    TextView goodsDetailBrandName2;
    @BindView(R.id.goods_detail_brandStory)
    TextView goodsDetailBrandStory;
    @BindView(R.id.goods_detail_recommendReasonTv)
    TextView goodsDetailRecommendReasonTv;
    @BindView(R.id.goods_detail_goodsDetail_ll)
    LinearLayout goodsDetailGoodsDetailLl;
    @BindView(R.id.goods_detail_buyRead_Detail_tv)
    TextView goodsDetailBuyReadDetailTv;
    @BindView(R.id.goods_detail_buyRead_Detail_btn)
    Button goodsDetailBuyReadDetailBtn;
    @BindView(R.id.goods_detail_buyRead)
    LinearLayout goodsDetailBuyRead;
    @BindView(R.id.goods_detail_ibServer)
    ImageButton goodsDetailIbServer;
    @BindView(R.id.goods_detail_addShopCar)
    Button goodsDetailAddShopCar;
    @BindView(R.id.goods_detail_buy)
    Button goodsDetailBuy;
    @BindView(R.id.goods_detail_price)
    TextView goodsDetailPrice;
    @BindView(R.id.goods_detail_goodsPack)
    TextView goodsDetailGoodsPack;
    @BindView(R.id.goods_detail_brandName3)
    TextView goodsDetailBrandName3;

    @Override
    public String getUrl() {

        //获取数据
        String goods_id = getIntent().getStringExtra("goods_id");

        if (!TextUtils.isEmpty(goods_id)) {
            //获取地址
            String url = NetWorkUrl.GOODSDETAILURL01 + goods_id + NetWorkUrl.GOODSDETAILURL02;
            Log.e("TAG", "商品的url===" + url);
            return url;
        }

        return super.getUrl();
    }

    @Override
    public void processData(String s) {
        super.processData(s);

        //处理获取的数据

        //1.解析json数据
        GoodsDetailBean goodsDetailBean = new Gson().fromJson(s, GoodsDetailBean.class);
        GoodsDetailBean.DataBean.ItemsBean items = goodsDetailBean.getData().getItems();

        //2.设置商品轮播图片
        List<String> images_item = items.getImages_item();//获取轮播图片集合
        goodsDetailBanner.setImageLoader(new MyImageLoader());//设置图片加载器
        goodsDetailBanner.setImages(images_item);//设置图片集合
        goodsDetailBanner.setDelayTime(3000);//轮播时间
        goodsDetailBanner.start();//启动轮播

        //3.设置商品品牌
        goodsDetailBrandName.setText(items.getBrand_info().getBrand_name());

        //4.设置商品名称
        goodsDetailGoodName.setText(items.getGoods_name());

        //5.设置喜欢的数量
        goodsDetailTvLikeCount.setText(items.getLike_count());

        //6.设置价格
        goodsDetailPrice.setText(items.getPrice());

        //7.设置发货的预计时间
        goodsDetailSend.setText(items.getShipping_str());

        //8.设置品牌相关信息
        Picasso.with(this)//品牌logo
                .load(items.getBrand_info().getBrand_logo())
                .into(goodsDetailBrandIv);
        goodsDetailBrandName2.setText(items.getBrand_info().getBrand_name());//品牌名称

        //9.设置商品的包装
        goodsDetailGoodsPack.setText(items.getGoods_desc());

        //10.设置品牌故事
        goodsDetailBrandName3.setText(items.getBrand_info().getBrand_name());//名称
        goodsDetailBrandStory.setText(items.getBrand_info().getBrand_desc());//故事描述

        //11.设置良仓推荐
        goodsDetailRecommendReasonTv.setText(items.getRec_reason());
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_goods_detail;
    }


    @OnClick({R.id.goods_detail_IvlikeCount, R.id.goods_detail_shared, R.id.goods_detail_goodsChoiceType, R.id.goods_detail_brandIv, R.id.goods_detail_brand_detail_rl, R.id.goods_detail_goodsRb, R.id.goods_detail_bugRb, R.id.goods_detail_radioGroup, R.id.goods_detail_buyRead_Detail_btn, R.id.goods_detail_ibServer, R.id.goods_detail_addShopCar, R.id.goods_detail_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.goods_detail_IvlikeCount:
                break;
            case R.id.goods_detail_shared:
                break;
            case R.id.goods_detail_goodsChoiceType:
                break;
            case R.id.goods_detail_brandIv:
                break;
            case R.id.goods_detail_brand_detail_rl:
                break;
            case R.id.goods_detail_goodsRb:
                break;
            case R.id.goods_detail_bugRb:
                break;
            case R.id.goods_detail_radioGroup:
                break;
            case R.id.goods_detail_buyRead_Detail_btn:
                break;
            case R.id.goods_detail_ibServer:
                break;
            case R.id.goods_detail_addShopCar:
                break;
            case R.id.goods_detail_buy:
                break;
        }
    }


}
