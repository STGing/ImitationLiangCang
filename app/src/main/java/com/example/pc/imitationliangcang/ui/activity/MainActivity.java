package com.example.pc.imitationliangcang.ui.activity;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.base.BaseActivity;
import com.example.pc.imitationliangcang.base.BaseFragment;
import com.example.pc.imitationliangcang.ui.fragment.MagazineFragment;
import com.example.pc.imitationliangcang.ui.fragment.ShareFragment;
import com.example.pc.imitationliangcang.ui.fragment.ShopFragment;
import com.example.pc.imitationliangcang.ui.fragment.TrendPersonFragment;
import com.example.pc.imitationliangcang.ui.fragment.UserCenterFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.mainActivity_frameLayout)
    FrameLayout mainActivityFrameLayout;
    @BindView(R.id.mainActivity_radioGroup)
    RadioGroup mainActivityRadioGroup;

    //存放fragment的集合
    private List<BaseFragment> fragments;

    private int position = 0;//当前页面

    private BaseFragment currentFragment;//当前页面的fragment
    private BaseFragment lastFragment;//之前页面的fragment

    @Override
    public void initView() {
        super.initView();

        //初始化fragments集合
        fragments = new ArrayList<>();
        fragments.add(new ShopFragment());
        fragments.add(new MagazineFragment());
        fragments.add(new TrendPersonFragment());
        fragments.add(new ShareFragment());
        fragments.add(new UserCenterFragment());
    }

    @Override
    public void initLIstener() {
        super.initLIstener();

        //RadioGroup的事件监听
        mainActivityRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton_shop://商店
                        position = 0;
                        break;
                    case R.id.radioButton_magazine://杂志
                        position = 1;
                        break;
                    case R.id.radioButton_trendPerson://达人
                        position = 2;
                        break;
                    case R.id.radioButton_share://分享
                        position = 3;
                        break;
                    case R.id.radioButton_user://用户
                        position = 4;
                        break;
                }
                //根据位置，切换Fragment
                switchFragment(position);
            }
        });

        //默认设置为第一个radioButton
        mainActivityRadioGroup.check(R.id.radioButton_shop);
    }

    /**
     * 根据位置切换Fragment
     * 使用：隐藏和显示的方法来切换fragmnet
     * @param position
     */
    private void switchFragment(int position) {
        //1.根据位置获取当前的fragment
        currentFragment = fragments.get(position);

        //2.获取事务
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        //3.如果不是之前的页面就切换
        if(lastFragment != currentFragment) {

            //如果当前的页面没有添加过
            if(!currentFragment.isAdded()) {

                //隐藏之前的
                if(lastFragment != null) {
                    ft.hide(lastFragment);
                }

                //添加当前页面
                ft.add(R.id.mainActivity_frameLayout,currentFragment);
            } else {
                //如果当前的添加过

                //隐藏之前的
                ft.hide(lastFragment);

                //显示当前的
                ft.show(currentFragment);
            }

            //提交事务
            ft.commit();

            //最后一定要赋值
            lastFragment = currentFragment;

        }
    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

}
