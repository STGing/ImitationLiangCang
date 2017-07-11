package com.example.pc.imitationliangcang.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.base.BaseActivity;
import com.example.pc.imitationliangcang.bean.magazinefragment.MagazineListBean;
import com.example.pc.imitationliangcang.common.NetWorkUrl;
import com.example.pc.imitationliangcang.ui.adapter.magazine.MagazineAutherDetailAdapter;
import com.google.gson.Gson;

import butterknife.BindView;

public class MagazineAutherDetailActivity extends BaseActivity {


    @BindView(R.id.magazine_auther_detail_rv)
    RecyclerView magazineAutherDetailRv;

    private MagazineAutherDetailAdapter adapter;

    @Override
    public int getLayoutID() {
        return R.layout.activity_magazine_auther_detail;
    }

    @Override
    public String getUrl() {

        String author_id = getIntent().getStringExtra("author_id");

        String url = NetWorkUrl.MAGAZINEAUTHERDETAILURL01 + author_id + NetWorkUrl.MAGAZINEAUTHERDETAILURL02;

        return url;
    }

    @Override
    public void processData(String s) {
        super.processData(s);

        //解析数据
        MagazineListBean.DataBean.ItemsBean items =
                new Gson().fromJson(s, MagazineListBean.class).getData().getItems();

        //设置数据
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        magazineAutherDetailRv.setLayoutManager(manager);
        adapter = new MagazineAutherDetailAdapter(this, items);
        magazineAutherDetailRv.setAdapter(adapter);
    }

}
