package com.example.pc.imitationliangcang.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

public class TrendPersonGoodsDetailActivity extends BaseActivity {


    @BindView(R.id.title_iv_back)
    ImageView titleIvBack;
    @BindView(R.id.title_tv_name)
    TextView titleTvName;
    @BindView(R.id.title_iv_share)
    ImageView titleIvShare;
    @BindView(R.id.trend_person_goods_banner)
    Banner trendPersonGoodsBanner;
    @BindView(R.id.trend_person_goods_IvlikeCount)
    ImageView trendPersonGoodsIvlikeCount;
    @BindView(R.id.trend_person_goods_goodName)
    TextView trendPersonGoodsGoodName;
    @BindView(R.id.trend_person_goods_tvLikeCount)
    TextView trendPersonGoodsTvLikeCount;
    @BindView(R.id.trend_person_goods_current_price)
    TextView trendPersonGoodsCurrentPrice;
    @BindView(R.id.trend_person_goods_last_price)
    TextView trendPersonGoodsLastPrice;
    @BindView(R.id.trend_person_goodsLink_btn)
    Button trendPersonGoodsLinkBtn;
    @BindView(R.id.trend_person_goodsTrend_iv)
    ImageView trendPersonGoodsTrendIv;
    @BindView(R.id.trend_person_goods_trendName)
    TextView trendPersonGoodsTrendName;

    @Override
    public void initView() {
        super.initView();

        //设置标题
        titleIvBack.setVisibility(View.VISIBLE);
        titleTvName.setText("良品");
        titleIvShare.setVisibility(View.VISIBLE);
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

        //1.解析json数据
        GoodsDetailBean goodsDetailBean = new Gson().fromJson(s, GoodsDetailBean.class);
        GoodsDetailBean.DataBean.ItemsBean items = goodsDetailBean.getData().getItems();

        //2.设置商品轮播图片
        List<String> images_item = items.getImages_item();//获取轮播图片集合
        trendPersonGoodsBanner.setImageLoader(new MyImageLoader());//设置图片加载器
        trendPersonGoodsBanner.setImages(images_item);//设置图片集合
        trendPersonGoodsBanner.setDelayTime(3000);//轮播时间
        trendPersonGoodsBanner.start();//启动轮播


        //4.设置商品名称
        trendPersonGoodsGoodName.setText(items.getGoods_name());

        //5.设置喜欢的数量
        trendPersonGoodsTvLikeCount.setText(items.getLike_count());

        //6.设置价格
        trendPersonGoodsCurrentPrice.setText(items.getPrice());

        //7.设置推荐的达人的图片
        Picasso.with(this)
                .load(items.getHeadimg())
                .into(trendPersonGoodsTrendIv);

        //8.设置推荐的达人的名字
        trendPersonGoodsTrendName.setText(items.getOwner_name());
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_trend_person_goods_detail;
    }


    @OnClick({R.id.title_iv_back, R.id.title_iv_share, R.id.trend_person_goods_IvlikeCount, R.id.trend_person_goodsLink_btn, R.id.trend_person_goodsTrend_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_iv_back:
                finish();
                break;
            case R.id.title_iv_share:
                break;
            case R.id.trend_person_goods_IvlikeCount:
                break;
            case R.id.trend_person_goodsLink_btn:
                break;
            case R.id.trend_person_goodsTrend_iv:
                break;
        }
    }

}
