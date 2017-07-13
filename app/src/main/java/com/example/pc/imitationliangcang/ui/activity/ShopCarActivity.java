package com.example.pc.imitationliangcang.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.base.BaseActivity;
import com.example.pc.imitationliangcang.bean.GoodsInfo;
import com.example.pc.imitationliangcang.db.DBDao;
import com.example.pc.imitationliangcang.ui.adapter.ShopCarAdapter;

import java.util.ArrayList;
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
    CheckBox shopCarSwiAllCheck;
    @BindView(R.id.shop_car_totalPrice)
    TextView shopCarTotalPrice;
    @BindView(R.id.shop_car_savePrice)
    TextView shopCarSavePrice;
    @BindView(R.id.shop_car_settlement)
    TextView shopCarSettlement;

    private ShopCarAdapter adapter;
    private DBDao dbDao;

    /**
     * 购物车状态
     * 2种状态：1：表示完成状态    2：表示编辑状态
     */
    private static final int COMPLITE = 1;
    private static final int EDIT = 2;

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
        titleTvEdit.setVisibility(View.VISIBLE);//显示编辑
        titleTvEdit.setTag(EDIT);//设置状态

        //设置监听
        titleTvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tag = (int) titleTvEdit.getTag();
                switch (tag) {
                    case 1://编辑
                        //点击编辑，显示完成,隐藏编辑
                        showComplite();
                        break;
                    case 2://点击完成，显示编辑，隐藏完成
                        showEdit();
                        break;
                }
            }


        });


    }


    /**
     * 显示完成，隐藏编辑
     */
    private void showComplite() {
        titleTvEdit.setText("完成");
        titleTvEdit.setTag(COMPLITE);


    }

    /**
     * 显示编辑，隐藏完成
     */
    private void showEdit() {
        titleTvEdit.setText("编辑");
        titleTvEdit.setTag(EDIT);


    }

    @Override
    public void initData() {
        super.initData();

//        //从数据库获取数据
//        dbDao = DBDao.getInstance();
//        List<GoodsInfo> goodsInfos = dbDao.getData();

        //从intent接受数据
        GoodsInfo goodsInfo = (GoodsInfo) getIntent().getExtras().getSerializable("shopGoodsInfo");
        List<GoodsInfo> list = new ArrayList<>();
        list.add(goodsInfo);


        //设置布局和适配器
        if (list != null && list.size() > 0){
            LinearLayoutManager manamger = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
            shopCarRv.setLayoutManager(manamger);

            adapter = new ShopCarAdapter(this,list);
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
