package com.example.pc.imitationliangcang.ui.activity;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
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
    @BindView(R.id.goods_detail_buyReadRb)
    RadioButton goodsDetailBuyReadRb;
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
    @BindView(R.id.goods_detail_current_price)
    TextView goodsDetailCurrentPrice;
    @BindView(R.id.goods_detail_goodsPack)
    TextView goodsDetailGoodsPack;
    @BindView(R.id.goods_detail_brandName3)
    TextView goodsDetailBrandName3;
    @BindView(R.id.goods_detail_promotionNote)
    TextView goodsDetailPromotionNote;
    @BindView(R.id.goods_detail_last_price)
    TextView goodsDetailLastPrice;
    @BindView(R.id.goods_detail_goodsInfo_pic_ll)
    LinearLayout goodsDetailGoodsInfoPicLl;
    @BindView(R.id.title_iv_back)
    ImageView titleIvBack;
    @BindView(R.id.title_iv_shopCar)
    ImageView titleIvShopCar;
    @BindView(R.id.base_title_layout)
    RelativeLayout baseTitleLayout;

    @Override
    public void initView() {
        super.initView();

        //设置标题
        baseTitleLayout.setBackgroundColor(Color.parseColor("#00ffffff"));//背景透明
        titleIvBack.setVisibility(View.VISIBLE);//显示返回键
        titleIvShopCar.setVisibility(View.VISIBLE);//显示购物车
    }

    @Override
    public String getUrl() {

        //获取数据
        String goods_id = getIntent().getStringExtra("goods_id");

        if (!TextUtils.isEmpty(goods_id)) {
            //获取地址
            String url = NetWorkUrl.GOODSDETAILURL01 + goods_id + NetWorkUrl.GOODSDETAILURL02;
            //Log.e("TAG", "商品的url===" + url);
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
        goodsDetailCurrentPrice.setText(items.getPrice());

        //7.设置发货的预计时间
        goodsDetailSend.setText(items.getShipping_str());

        //8.设置品牌相关信息
        Picasso.with(this)//品牌logo
                .load(items.getBrand_info().getBrand_logo())
                .into(goodsDetailBrandIv);
        goodsDetailBrandName2.setText(items.getBrand_info().getBrand_name());//品牌名称

        //9.设置商品的包装(根据不同的商品，判断是否显示)
        List<GoodsDetailBean.DataBean.ItemsBean.GoodsInfoBean> goods_info = items.getGoods_info();
        //Log.e("TAG","goods_info的信息是"+goods_info);
        if (goods_info.size() == 0) {
            goodsDetailGoodsPack.setVisibility(View.VISIBLE);
            goodsDetailGoodsPack.setText(items.getGoods_desc());
        } else {
            goodsDetailGoodsPack.setVisibility(View.GONE);
        }


        //10.设置商品的图片列表（如果有数据的话就显示)
        if (goods_info.size() > 0) {
            goodsDetailGoodsInfoPicLl.setVisibility(View.VISIBLE);

            //根据商品信息动态的建立图片
            for (int i = 0; i < goods_info.size(); i++) {
                //获取图片链接
                GoodsDetailBean.DataBean.ItemsBean.GoodsInfoBean goodsInfoBean = goods_info.get(i);
                GoodsDetailBean.DataBean.ItemsBean.GoodsInfoBean.ContentBean content = goodsInfoBean.getContent();
                int type = goodsInfoBean.getType();
                switch (type) {
                    case 1://1表示是商品图片信息

                        //建立图片
                        ImageView iv = new ImageButton(this);

                        LinearLayout.LayoutParams params1 =
                                new LinearLayout.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT);
                        iv.setLayoutParams(params1);
                        params1.bottomMargin = -60;
                        iv.setScaleType(ImageView.ScaleType.FIT_XY);
                        //背景透明
                        iv.setBackgroundColor(Color.parseColor("#00ffffff"));
                        //加载图片
                        Picasso.with(this)
                                .load(content.getImg())
                                .resize(content.getWidth(), content.getHeight())
                                .into(iv);

                        //图片添加到布局上
                        goodsDetailGoodsInfoPicLl.addView(iv);

                        break;
                    case 0://1表示是商品说明

                        //动态添加文字
                        TextView tv = new TextView(this);
                        tv.setText(content.getText());
                        LinearLayout.LayoutParams params0 =
                                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        params0.setMargins(10, 30, 10, 10);
                        tv.setLayoutParams(params0);
                        tv.setTextColor(Color.parseColor("#959697"));
                        goodsDetailGoodsInfoPicLl.addView(tv);

                        break;
                    case 2://1表示是商品注意事项

                        //动态添加文字
                        TextView tv2 = new TextView(this);
                        tv2.setText(content.getText());
                        LinearLayout.LayoutParams params2 =
                                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        params2.setMargins(10, 10, 10, 10);
                        tv2.setLayoutParams(params2);
                        tv2.setTextColor(Color.parseColor("#e1e2e2"));
                        goodsDetailGoodsInfoPicLl.addView(tv2);

                        break;
                    default:

                        break;
                }

            }


        } else {
            goodsDetailGoodsInfoPicLl.setVisibility(View.GONE);
        }


        //11.设置品牌故事
        goodsDetailBrandName3.setText(items.getBrand_info().getBrand_name());//名称
        goodsDetailBrandStory.setText(items.getBrand_info().getBrand_desc());//故事描述

        //12.设置良仓推荐
        goodsDetailRecommendReasonTv.setText(items.getRec_reason());

        //13.设置购物须知
        goodsDetailBuyReadDetailTv.setText(items.getGood_guide().getContent());
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_goods_detail;
    }


    @OnClick({R.id.title_iv_back, R.id.title_iv_shopCar,R.id.goods_detail_IvlikeCount, R.id.goods_detail_shared, R.id.goods_detail_goodsChoiceType, R.id.goods_detail_brandIv, R.id.goods_detail_brand_detail_rl, R.id.goods_detail_goodsRb, R.id.goods_detail_buyReadRb, R.id.goods_detail_radioGroup, R.id.goods_detail_buyRead_Detail_btn, R.id.goods_detail_ibServer, R.id.goods_detail_addShopCar, R.id.goods_detail_buy})
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

                //点击显示商品情况
                goodsDetailBuyRead.setVisibility(View.GONE);//隐藏
                goodsDetailGoodsDetailLl.setVisibility(View.VISIBLE);//显示
                break;
            case R.id.goods_detail_buyReadRb:

                //点击显示购买须知
                goodsDetailGoodsDetailLl.setVisibility(View.GONE);//隐藏
                goodsDetailBuyRead.setVisibility(View.VISIBLE);//显示

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
            case R.id.title_iv_back://标题返回
                finish();
                break;
            case R.id.title_iv_shopCar://标题购物车
                break;
        }
    }



}
