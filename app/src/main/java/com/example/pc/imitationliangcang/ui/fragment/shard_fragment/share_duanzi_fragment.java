package com.example.pc.imitationliangcang.ui.fragment.shard_fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.base.BaseFragment;
import com.example.pc.imitationliangcang.bean.sharefragment.ShareDuanZiBean;
import com.example.pc.imitationliangcang.common.NetWorkUrl;
import com.example.pc.imitationliangcang.ui.adapter.sharefragment.ShareDuanZiAdapter;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;

/**
 * Created by PC on 2017/7/14.
 */

public class share_duanzi_fragment extends BaseFragment {
    @BindView(R.id.share_duanzi_rv)
    RecyclerView shareDuanziRv;

    private ShareDuanZiAdapter adapter;

    @Override
    public int getLayoutID() {
        return R.layout.share_duanzi_fragment;
    }

    @Override
    public void initView() {

    }

    @Override
    public String getUrl() {
        return NetWorkUrl.SHAREREDUANZIURL;
    }

    @Override
    public void processData(String s) {
        super.processData(s);

        if (!TextUtils.isEmpty(s)) {

            //解析数据
            List<ShareDuanZiBean.ListBean> list = new Gson().fromJson(s, ShareDuanZiBean.class).getList();

            //Log.e("TAG","推荐list的数据是"+list.get(0).getType());
            if (list != null && list.size() > 0){

                //设置布局/适配器
                LinearLayoutManager manager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
                shareDuanziRv.setLayoutManager(manager);

                adapter = new ShareDuanZiAdapter(mContext,list);
                shareDuanziRv.setAdapter(adapter);

            }
        }
    }

}
