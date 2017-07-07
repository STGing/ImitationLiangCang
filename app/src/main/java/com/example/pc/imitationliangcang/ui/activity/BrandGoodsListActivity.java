package com.example.pc.imitationliangcang.ui.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.base.BaseActivity;
import com.example.pc.imitationliangcang.bean.shopfragment.BrandFragmentChildBean;
import com.example.pc.imitationliangcang.common.NetWorkUrl;
import com.example.pc.imitationliangcang.ui.adapter.shopfragment.BrandGoodsListRecyclerViewAdapter;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class BrandGoodsListActivity extends BaseActivity {


    @BindView(R.id.brand_goodsList_iv)
    CircleImageView brandGoodsListIv;
    @BindView(R.id.brand_goodsList_tvName)
    TextView brandGoodsListTvName;
    @BindView(R.id.brand_goodsList_brandStory)
    RadioButton brandGoodsListBrandStory;
    @BindView(R.id.brand_goodsList_brandProduct)
    RadioButton brandGoodsListBrandProduct;
    @BindView(R.id.brand_goodsList_recyclerView)
    RecyclerView brandGoodsListRecyclerView;
    @BindView(R.id.brand_goodsList_brandStoryDetail)
    TextView brandGoodsListBrandStoryDetail;
    @BindView(R.id.brand_goodsList_radioGroup)
    RadioGroup brandGoodsListRadioGroup;

    private BrandGoodsListRecyclerViewAdapter adapter;

    @Override
    public String getUrl() {

        //获取数据
        int brand_id = getIntent().getIntExtra("brand_id", 0);
        if (brand_id == 0) {
            return null;
        }

        //获取地址
        String url = NetWorkUrl.BrandChildUrl01 + brand_id + NetWorkUrl.BrandChildUrl02;

        return url;
    }

    @Override
    public void processData(String s) {
        super.processData(s);
        //处理数据
        if (!TextUtils.isEmpty(s)) {

            //解析数据
            BrandFragmentChildBean brandFragmentChildBean = new Gson().fromJson(s, BrandFragmentChildBean.class);
            List<BrandFragmentChildBean.DataBean.ItemsBean> items = brandFragmentChildBean.getData().getItems();

            //设置顶部头像
            String brand_logo = items.get(0).getBrand_info().getBrand_logo();
            Picasso.with(this)
                    .load(brand_logo)
                    .into(brandGoodsListIv);

            //设置名称
            brandGoodsListTvName.setText(items.get(0).getBrand_info().getBrand_name());

            //设置品牌故事
            brandGoodsListBrandStoryDetail.setText(items.get(0).getBrand_info().getBrand_desc());

            //设置布局和适配器
            GridLayoutManager manager = new GridLayoutManager(this, 2);
            brandGoodsListRecyclerView.setLayoutManager(manager);

            adapter = new BrandGoodsListRecyclerViewAdapter(this, items);
            brandGoodsListRecyclerView.setAdapter(adapter);


        }
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_brand_goods_list;
    }

    @OnClick({R.id.brand_goodsList_brandStory, R.id.brand_goodsList_brandProduct})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.brand_goodsList_brandStory:

                //点击品牌故事，隐藏产品，显示故事
                brandGoodsListRecyclerView.setVisibility(View.GONE);
                brandGoodsListBrandStoryDetail.setVisibility(View.VISIBLE);
                break;
            case R.id.brand_goodsList_brandProduct:
                //和上面相反
                brandGoodsListBrandStoryDetail.setVisibility(View.GONE);
                brandGoodsListRecyclerView.setVisibility(View.VISIBLE);
                break;
        }
    }

}
