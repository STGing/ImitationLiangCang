package com.example.pc.imitationliangcang.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.pc.imitationliangcang.base.BaseFragment;

import java.util.List;

/**
 * Created by PC on 2017/7/6.
 */

public class ShopFragmenViewPagerAdapter extends FragmentPagerAdapter {

    private final List<BaseFragment> fragments;
    private final List<String> title_list;

    public ShopFragmenViewPagerAdapter(FragmentManager fm, List<BaseFragment> fragments, List<String> title_list) {
        super(fm);
        this.fragments = fragments;
        this.title_list = title_list;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title_list.get(position);
    }
}
