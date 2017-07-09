package com.example.pc.imitationliangcang.ui.fragment;

import android.support.annotation.IdRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.base.BaseFragment;
import com.example.pc.imitationliangcang.bean.trenpersonfragment.TrendPersonFragmentBean;
import com.example.pc.imitationliangcang.common.NetWorkUrl;
import com.example.pc.imitationliangcang.ui.adapter.trendperson.TrendPersonFragmentAdaper;
import com.example.pc.imitationliangcang.utils.HttpUtils;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by PC on 2017/7/6.
 */

public class TrendPersonFragment extends BaseFragment {
    @BindView(R.id.title_iv_search)
    ImageView titleIvSearch;
    @BindView(R.id.title_tv_name)
    TextView titleTvName;
    @BindView(R.id.title_iv_menu)
    ImageView titleIvMenu;
    @BindView(R.id.trend_person_recyclerView)
    RecyclerView trendPersonRecyclerView;
    @BindView(R.id.base_title_layout)
    RelativeLayout baseTitleLayout;

    private TrendPersonFragmentAdaper adaper;
    private PopupWindow popWnd;

    @Override
    public int getLayoutID() {
        return R.layout.trendperson_fragment;
    }

    @Override
    public void initTitle() {
        super.initTitle();
        //设置标题
        titleIvSearch.setVisibility(View.VISIBLE);
        titleTvName.setText("达人");
        titleIvMenu.setVisibility(View.VISIBLE);
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void processData(String s) {
        super.processData(s);

        //解析数据
        TrendPersonFragmentBean trendPersonFragmentBean = new Gson().fromJson(s, TrendPersonFragmentBean.class);
        List<TrendPersonFragmentBean.DataBean.ItemsBean> items = trendPersonFragmentBean.getData().getItems();

        if (items != null) {

            GridLayoutManager manager = new GridLayoutManager(mContext, 3);
            trendPersonRecyclerView.setLayoutManager(manager);

            adaper = new TrendPersonFragmentAdaper(mContext, items);
            trendPersonRecyclerView.setAdapter(adaper);
        }
    }

    @Override
    public String getUrl() {
        return NetWorkUrl.TRENDPERSONDEFAULTRECOMMEND;
    }

    @OnClick({R.id.title_iv_search, R.id.title_tv_name, R.id.title_iv_menu, R.id.trend_person_recyclerView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_iv_search:
                break;
            case R.id.title_tv_name:
                break;
            case R.id.title_iv_menu://按钮的点击事件

                //点击之后，弹出/隐藏 PopupWindow
                showPopWindow();

                break;
            case R.id.trend_person_recyclerView:
                break;

        }
    }

    /**
     * 用于显示popwindow
     */
    private void showPopWindow() {

        //校验
        if (popWnd == null) {

            //获取屏幕的参数
            WindowManager windowManager = getActivity().getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            View contentView = LayoutInflater.from(mContext).inflate(R.layout.trend_person_popupwindow, null);
            //初始化popupwindow，占据全屏
            popWnd = new PopupWindow(contentView, display.getWidth(), display.getHeight());

            //初始化popupwindows中的子view
            final RadioGroup radioGroup = (RadioGroup) contentView.findViewById(R.id.trend_person_menu_RadioGroup);
            //点击事件
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                    switch (checkedId) {
                        case R.id.trend_person_defaultRecommend://默认推荐
                            //切换数据
                            switchData(NetWorkUrl.TRENDPERSONDEFAULTRECOMMEND);


                            break;
                        case R.id.trend_person_MostRecommend://最多推荐

                            switchData(NetWorkUrl.TRENDPERSONMOSTRECOMMEND);

                            break;
                        case R.id.trend_person_MostWelcome://最受欢迎

                            switchData(NetWorkUrl.TRENDPERSONMOSTWELCOME);
                            //切换按钮状态
                            radioGroup.check(R.id.trend_person_MostWelcome);
                            break;
                        case R.id.trend_person_latestRecommend://最新推荐
                            switchData(NetWorkUrl.TRENDPERSONLATESTRECOMMEND);
                            //切换按钮状态
                            radioGroup.check(R.id.trend_person_latestRecommend);
                            break;
                        case R.id.trend_person_latestJoin://最新加入
                            switchData(NetWorkUrl.TRENDPERSONLATESTJOIN);
                            //切换按钮状态
                            radioGroup.check(R.id.trend_person_latestJoin);
                            break;
                        default:

                            break;
                    }
                }
            });

        }
        if (popWnd.isShowing()) {
            closePopupWindow();
        } else {

            popWnd.showAsDropDown(baseTitleLayout);
        }
    }

    private void switchData(String url) {
        HttpUtils.getInstance().getNetData(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        if (!TextUtils.isEmpty(s)) {

                            processData(s);
                        }
                    }
                });

        //关闭popupwindos
        closePopupWindow();
    }

    /**
     * 关闭窗口
     */
    private void closePopupWindow() {
        if (popWnd != null && popWnd.isShowing()) {
            popWnd.dismiss();
            popWnd = null;
            WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
            params.alpha = 1f;
            getActivity().getWindow().setAttributes(params);
        }
    }

}
