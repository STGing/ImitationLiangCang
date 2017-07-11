package com.example.pc.imitationliangcang.ui.fragment.magazine_fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.base.BaseFragment;
import com.example.pc.imitationliangcang.bean.magazinefragment.MagazineAutherBean;
import com.example.pc.imitationliangcang.common.NetWorkUrl;
import com.example.pc.imitationliangcang.ui.adapter.magazine.MaganizeAuterAdapter;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;

/**
 * Created by PC on 2017/7/10.
 */

public class MagazineAutherFragment extends BaseFragment {
    @BindView(R.id.magazine_auther_recyclerView)
    RecyclerView magazineAutherRecyclerView;

    private MaganizeAuterAdapter auterAdapter;

    @Override
    public int getLayoutID() {
        return R.layout.magazine_auther_fragment;
    }

    @Override
    public void initView() {

    }

    @Override
    public String getUrl() {
        return NetWorkUrl.MAGAZINEAUTHERURL;
    }

    @Override
    public void processData(String s) {
        super.processData(s);

        if (!TextUtils.isEmpty(s)) {

            //解析数据
            MagazineAutherBean magazineAutherBean = new Gson().fromJson(s, MagazineAutherBean.class);
            List<MagazineAutherBean.DataBean.ItemsBean> items = magazineAutherBean.getData().getItems();

            //设置布局/适配器
            LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
            magazineAutherRecyclerView.setLayoutManager(manager);
            auterAdapter = new MaganizeAuterAdapter(getActivity(),items);
            magazineAutherRecyclerView.setAdapter(auterAdapter);
        }

    }
}
