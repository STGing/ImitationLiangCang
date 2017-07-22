package com.example.pc.imitationliangcang.ui.adapter.bilibili;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.bean.BiLiBean;
import com.example.pc.imitationliangcang.utils.GlideUtils;
import com.example.pc.imitationliangcang.utils.MyImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PC on 2017/7/21.
 */

public class BiLiAdapter extends RecyclerView.Adapter {


    private final Context mContext;
    private final LayoutInflater mLayoutInflater;

    public static final int TYPE_HEADER = 0;//头View
    public static final int TYPE_NORMAL = 1;//item
    private final BiLiBean.DataBean data;
    private final List<BiLiBean.DataBean.BannerBean> banner;
    private final List<BiLiBean.DataBean.PartitionsBean> partitions;


    public BiLiAdapter(Context mContext, BiLiBean.DataBean data) {
        this.mContext = mContext;
        this.data = data;
        mLayoutInflater = LayoutInflater.from(mContext);

        banner = data.getBanner();
        partitions = data.getPartitions();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new HeadView(mLayoutInflater.inflate(R.layout.bili_item_head, parent, false));
        }
        return new BiLiViewHolder(mLayoutInflater.inflate(R.layout.bili_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeadView) {

            //设置headView
            ((HeadView) holder).bannerBili.setImageLoader(new MyImageLoader());//设置图片加载器
            List<String> images_item = new ArrayList<>();//图片集合

            for (int i = 0; i < banner.size(); i++) {
                String img = banner.get(i).getImg();
                images_item.add(img);
            }

            if (images_item.size() > 0){
                ((HeadView) holder).bannerBili.setImages(images_item);//设置图片集合
                ((HeadView) holder).bannerBili.setDelayTime(3000);//轮播时间
                ((HeadView) holder).bannerBili.start();//启动轮播
            }


        } else if (holder instanceof BiLiViewHolder) {
            //获取数据
            BiLiBean.DataBean.PartitionsBean.PartitionBean bean = partitions.get(position - 1).getPartition();

            //设置数据
            //1.分类图片
            GlideUtils.loadImageView(mContext, bean.getSub_icon().getSrc(), ((BiLiViewHolder) holder).biliItemIv);

            //2.分类名称
            ((BiLiViewHolder) holder).biliItemTvName.setText(bean.getName());

            //3.设置数量
            ((BiLiViewHolder) holder).biliItemTvNumber.setText(bean.getCount() + "");

            //4.设置RV
            List<BiLiBean.DataBean.PartitionsBean.LivesBean> lives = this.partitions.get(position - 1).getLives();
            //Log.e("TAG","获取的lives的数量是："+lives.size());
            GridLayoutManager manager = new GridLayoutManager(mContext, 2);
            ((BiLiViewHolder) holder).biliItemRv.setLayoutManager(manager);
            BiLiItemAdapter adapter = new BiLiItemAdapter(mContext, lives);
            ((BiLiViewHolder) holder).biliItemRv.setAdapter(adapter);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return 0;
        return 1;
    }


    @Override
    public int getItemCount() {
        return partitions == null ? 0 : partitions.size();
    }


    static class BiLiViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.bili_item_iv)
        ImageView biliItemIv;
        @BindView(R.id.bili_item_tvName)
        TextView biliItemTvName;
        @BindView(R.id.bili_item_tvNumber)
        TextView biliItemTvNumber;
        @BindView(R.id.bili_item_rv)
        RecyclerView biliItemRv;
        @BindView(R.id.bili_item_btn)
        Button biliItemBtn;
        @BindView(R.id.bili_item_tvRefresh)
        TextView biliItemTvReFresh;

        BiLiViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class HeadView extends RecyclerView.ViewHolder {
        @BindView(R.id.banner_bili)
        Banner bannerBili;
        @BindView(R.id.tv_follow_bili)
        TextView tvFollowBili;
        @BindView(R.id.tv_center_bili)
        TextView tvCenterBili;
        @BindView(R.id.tv_slipvideo_bili)
        TextView tvSlipvideoBili;
        @BindView(R.id.tv_search_bili)
        TextView tvSearchBili;
        @BindView(R.id.tv_all_category_bili)
        TextView tvAllCategoryBili;
        @BindView(R.id.head_ll_bili)
        LinearLayout headLlBili;

        HeadView(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
