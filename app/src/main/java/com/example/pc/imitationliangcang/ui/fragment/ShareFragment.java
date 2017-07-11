package com.example.pc.imitationliangcang.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.base.BaseFragment;
import com.example.pc.imitationliangcang.bean.sharefragment.ShareFragmentBean;
import com.example.pc.imitationliangcang.common.NetWorkUrl;
import com.example.pc.imitationliangcang.ui.adapter.sharefragment.ShareFragmentAdapter;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;

/**
 * Created by PC on 2017/7/6.
 */

public class ShareFragment extends BaseFragment {
    @BindView(R.id.share_fragment_rv)
    RecyclerView shareFragmentRv;

    private ShareFragmentAdapter adapter;

    @Override
    public int getLayoutID() {
        return R.layout.share_fragment;
    }

    @Override
    public void initView() {

    }

    @Override
    public String getUrl() {
        return NetWorkUrl.SHAREFRAGMENTUR;
    }

    @Override
    public void processData(String s) {
        super.processData(s);

        if (!TextUtils.isEmpty(s)) {

            //解析数据
            ShareFragmentBean shareFragmentBean = new Gson().fromJson(s, ShareFragmentBean.class);
            List<ShareFragmentBean.ListBean> list = shareFragmentBean.getList();

            //设置布局及适配器
//            LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
//            shareFragmentRv.setLayoutManager(manager);
//            adapter = new ShareFragmentAdapter(this,list);
//            shareFragmentRv.setAdapter(adapter);
        }
    }
}
