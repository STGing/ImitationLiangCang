package com.example.pc.imitationliangcang.ui.fragment.shop_fragment_detail;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.base.BaseFragment;
import com.example.pc.imitationliangcang.bean.HomeFragmentBean;
import com.example.pc.imitationliangcang.common.NetWorkUrl;
import com.example.pc.imitationliangcang.ui.adapter.shopfragment.HomeFragmentRecyclerViewAdapter;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;

/**
 * Created by PC on 2017/7/6.
 */

public class HomeFragment extends BaseFragment {


    @BindView(R.id.homeFragment_recyclerView)
    RecyclerView homeFragmentRecyclerView;

    private HomeFragmentRecyclerViewAdapter adapter;
    private static final String TAG = "HomeFragment";
    private HomeFragmentBean homeFragmentBean;
    private List<HomeFragmentBean.DataBean.ItemsBean.ListBean> list;

    @Override
    public int getLayoutID() {
        //Log.e(TAG, "getLayoutID:方法执行");
        return R.layout.shop_fragment_home_fragment;
    }

    @Override
    public void initView() {
        //Log.e(TAG, "initView: 方法执行" );
    }

    @Override
    public void initData() {
        super.initData();
        //Log.e(TAG, "initData: 方法执行");

    }

    @Override
    public String getUrl() {
        return NetWorkUrl.HOMEFRAGMENT;
    }

    @Override
    public void processData(String s) {
        super.processData(s);

        if (TextUtils.isEmpty(s)) {
            throw new NullPointerException();
        }

        homeFragmentBean = new Gson().fromJson(s, HomeFragmentBean.class);

        list = homeFragmentBean.getData().getItems().getList();
        //Log.e(TAG, "list === "+list.get(1).getOne().getTopic_name() );
        if (list != null){

            //布局管理
            LinearLayoutManager manager = new LinearLayoutManager(mContext);
            homeFragmentRecyclerView.setLayoutManager(manager);

            //设置适配器
            adapter = new HomeFragmentRecyclerViewAdapter(mContext,list);
            homeFragmentRecyclerView.setAdapter(adapter);

        }
    }






}
