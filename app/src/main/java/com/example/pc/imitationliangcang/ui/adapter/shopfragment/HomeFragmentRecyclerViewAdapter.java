package com.example.pc.imitationliangcang.ui.adapter.shopfragment;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.bean.shopfragment.HomeFragmentBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 分类型Recycler的适配器
 */

public class HomeFragmentRecyclerViewAdapter extends RecyclerView.Adapter {


    /**
     * 共有4中类型
     *
     * @param list
     */
    public static final int TYPE1 = 0;
    public static final int TYPE2 = 1;
    public static final int TYPE3 = 2;
    public static final int TYPE4 = 3;



    //当前类型是什么
    private int currentType;

    private LayoutInflater mLayoutInflater;
    private final FragmentActivity mContext;

    private final List<HomeFragmentBean.DataBean.ItemsBean.ListBean> list;

    public HomeFragmentRecyclerViewAdapter(FragmentActivity mContext, List<HomeFragmentBean.DataBean.ItemsBean.ListBean> list) {
        this.mContext = mContext;
        this.list = list;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        //根据ViewType建立对应的ViewHolder
        switch (viewType) {
            case TYPE1:
                viewHolder = new Type1ViewHolder(mLayoutInflater.inflate(R.layout.home_fragment_item1, null));
                break;
            case TYPE2:
                viewHolder = new Type2ViewHolder(mLayoutInflater.inflate(R.layout.home_fragment_item2, null));
                break;
            case TYPE3:
                viewHolder = new Type3ViewHolder(mLayoutInflater.inflate(R.layout.home_fragment_item3, null));
                break;
            case TYPE4:
                viewHolder = new Type4ViewHolder(mLayoutInflater.inflate(R.layout.home_fragment_item4, null));
                break;
            default:

                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //数据操作
        if (holder instanceof Type1ViewHolder) {
            ((Type1ViewHolder) holder).setData(position);
        } else if (holder instanceof Type2ViewHolder) {
            ((Type2ViewHolder) holder).setData(position);
        } else if (holder instanceof Type3ViewHolder) {
            ((Type3ViewHolder) holder).setData(position);
        } else if (holder instanceof Type4ViewHolder) {
            ((Type4ViewHolder) holder).setData(position);
        }
    }

    @Override
    public int getItemViewType(int position) {

        //判断是那个ViewHolder类型
        //获取bean
        HomeFragmentBean.DataBean.ItemsBean.ListBean listBean = list.get(position);
        //获取type
        int type = Integer.parseInt(listBean.getHome_type());
        //判断
        switch (type) {
            case 1://第一种类型
                currentType = TYPE1;
                break;
            case 2://第二种类型
                currentType = TYPE2;
                break;
            case 3://第三种类型
                currentType = TYPE3;
                break;
            case 4://第四种类型
                currentType = TYPE4;
                break;
            default:

                break;
        }

        // return super.getItemViewType(position);
        return currentType;
    }


    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class Type1ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.type1_iv)
        ImageView type1Iv;

        public Type1ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(int position) {
            Picasso.with(mContext).load(list.get(position).getOne().getPic_url()).into(type1Iv);
        }
    }

    class Type2ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.type2_left_iv)
        ImageView type2LeftIv;
        @BindView(R.id.type2_right_iv)
        ImageView type2RightIv;

        public Type2ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(int position) {
            Picasso.with(mContext).load(list.get(position).getOne().getPic_url()).into(type2LeftIv);
            Picasso.with(mContext).load(list.get(position).getTwo().getPic_url()).into(type2RightIv);
        }
    }

    class Type3ViewHolder extends RecyclerView.ViewHolder {

        public Type3ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(int position) {

        }
    }

    class Type4ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.type4_left_top_iv)
        ImageView type4LeftTopIv;
        @BindView(R.id.type4_left_bottom_iv)
        ImageView type4LeftBottomIv;
        @BindView(R.id.type4_right_top_iv)
        ImageView type4RightTopIv;
        @BindView(R.id.type4_right_bottom_iv)
        ImageView type4RightBottomIv;

        public Type4ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(int position) {
            Picasso.with(mContext).load(list.get(position).getOne().getPic_url()).into(type4LeftTopIv);
            Picasso.with(mContext).load(list.get(position).getTwo().getPic_url()).into(type4LeftBottomIv);
            Picasso.with(mContext).load(list.get(position).getThree().getPic_url()).into(type4RightTopIv);
            Picasso.with(mContext).load(list.get(position).getFour().getPic_url()).into(type4RightBottomIv);
        }
    }
}
