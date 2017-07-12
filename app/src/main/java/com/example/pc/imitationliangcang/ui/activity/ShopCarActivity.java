package com.example.pc.imitationliangcang.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.base.BaseActivity;
import com.example.pc.imitationliangcang.bean.GoodsInfo;
import com.example.pc.imitationliangcang.db.DBDao;
import com.example.pc.imitationliangcang.ui.adapter.ShopCarAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ShopCarActivity extends BaseActivity {

    @BindView(R.id.title_iv_back)
    ImageView titleIvBack;
    @BindView(R.id.title_tv_name)
    TextView titleTvName;
    @BindView(R.id.title_tv_edit)
    TextView titleTvEdit;
    @BindView(R.id.shop_car_rv)
    RecyclerView shopCarRv;
    @BindView(R.id.shop_car_tv_fullSbuPrice)
    TextView shopCarTvFullSbuPrice;
    @BindView(R.id.shop_car_tv_discountPrice)
    TextView shopCarTvDiscountPrice;
    @BindView(R.id.shop_car_tv_packPrice)
    TextView shopCarTvPackPrice;
    @BindView(R.id.shop_car_tv_shipPrice)
    TextView shopCarTvShipPrice;
    @BindView(R.id.shop_car_swiAllCheck)
    ImageView shopCarSwiAllCheck;
    @BindView(R.id.shop_car_totalPrice)
    TextView shopCarTotalPrice;
    @BindView(R.id.shop_car_savePrice)
    TextView shopCarSavePrice;
    @BindView(R.id.shop_car_settlement)
    TextView shopCarSettlement;

    private ShopCarAdapter adapter;
    private DBDao dbDao;

    @Override
    public int getLayoutID() {
        return R.layout.activity_shop_car;
    }

    @Override
    public void initView() {
        super.initView();

        //初始化标题
        titleIvBack.setVisibility(View.VISIBLE);
        titleTvName.setVisibility(View.VISIBLE);
        titleTvName.setText("购物车");
        titleTvEdit.setVisibility(View.VISIBLE);
    }

    @Override
    public void initData() {
        super.initData();

        //从数据库获取数据
        dbDao = new DBDao();
        List<GoodsInfo> goodsInfos = dbDao.getData();

        //设置布局和适配器
        if (goodsInfos != null && goodsInfos.size() > 0){
            LinearLayoutManager manamger = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
            shopCarRv.setLayoutManager(manamger);

            adapter = new ShopCarAdapter(this,goodsInfos);
            shopCarRv.setAdapter(adapter);
        }

    }

    @OnClick({R.id.title_iv_back, R.id.title_tv_edit, R.id.shop_car_swiAllCheck, R.id.shop_car_settlement})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_iv_back:
                break;
            case R.id.title_tv_edit:
                break;
            case R.id.shop_car_swiAllCheck:
                break;
            case R.id.shop_car_settlement:
                break;
        }
    }
}
