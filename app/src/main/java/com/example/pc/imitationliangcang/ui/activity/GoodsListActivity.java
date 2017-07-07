package com.example.pc.imitationliangcang.ui.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.base.BaseActivity;
import com.example.pc.imitationliangcang.bean.GoodsListBean;
import com.example.pc.imitationliangcang.ui.adapter.GoodsListRecyclerViewAdapter;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;

/**
 * 展示商品的页面
 */
public class GoodsListActivity extends BaseActivity {


    @BindView(R.id.title_iv_search)
    ImageView titleIvSearch;
    @BindView(R.id.title_tv_name)
    TextView titleTvName;
    @BindView(R.id.title_iv_shopCar)
    ImageView titleIvShopCar;
    @BindView(R.id.goodsList_recyclerView)
    RecyclerView goodsListRecyclerView;
    private GoodsListRecyclerViewAdapter adapter;

    @Override
    public void initView() {
        super.initView();

        //设置标题
        titleIvSearch.setVisibility(View.VISIBLE);
        titleTvName.setText("商店");
        titleIvShopCar.setVisibility(View.VISIBLE);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public String getUrl() {

        String topic_url = getIntent().getStringExtra("topic_url");
        if (!TextUtils.isEmpty(topic_url)) {
            return topic_url;
        }
        return super.getUrl();
    }

    @Override
    public void processData(String s) {
        super.processData(s);

        if (!TextUtils.isEmpty(s)) {
            //Log.e("TAG","商品详情页面解析数据"+s);
            //解析数据
            GoodsListBean goodsListBean = new Gson().fromJson(s, GoodsListBean.class);
            List<GoodsListBean.DataBean.ItemsBean> items = goodsListBean.getData().getItems();
            if (items != null) {
                //设置适配器及布局
                //Log.e("TAG","设置---数据"+items.get(0).getGoods_name());
                GridLayoutManager manager = new GridLayoutManager(this, 2);
                goodsListRecyclerView.setLayoutManager(manager);

                adapter = new GoodsListRecyclerViewAdapter(this, items);
                goodsListRecyclerView.setAdapter(adapter);
            }
        }
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_goods_list;
    }

}
