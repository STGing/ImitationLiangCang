package com.example.pc.imitationliangcang.ui.activity;

import android.text.TextUtils;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.base.BaseActivity;
import com.example.pc.imitationliangcang.common.NetWorkUrl;

public class GoodsDetailActivity extends BaseActivity {


    @Override
    public String getUrl() {

        //获取数据
        int goods_id = getIntent().getIntExtra("goods_id", 0);
        if (goods_id == 0) {
            return null;
        }

        //获取地址
        String url = NetWorkUrl.GOODSDETAILURL01 + goods_id + NetWorkUrl.GOODSDETAILURL02;

        if (!TextUtils.isEmpty(url)) {
            return url;
        }
        return super.getUrl();
    }

    @Override
    public void processData(String s) {
        super.processData(s);

        //处理获取的数据

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_goods_detail;
    }
}
