package com.example.pc.imitationliangcang.ui.adapter.shopfragment;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.bean.shopfragment.HomeFragmentBean;
import com.example.pc.imitationliangcang.ui.activity.ShopFragmentWebViewActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 分类型Recycler的适配器
 */

public class HomeFragmentRecyclerViewAdapter extends RecyclerView.Adapter {


    /**
     * 共有5中类型
     *
     * @param list
     */
    public static final int TYPE1 = 0;
    public static final int TYPE2 = 1;
    public static final int TYPE3 = 2;
    public static final int TYPE4 = 3;
    public static final int TYPE6 = 6;//一张图片，但是指向商品列表



    //当前类型是什么
    private int currentType;

    private LayoutInflater mLayoutInflater;
    private final FragmentActivity mContext;

    private final List<HomeFragmentBean.DataBean.ItemsBean.ListBean> list;


    public HomeFragmentRecyclerViewAdapter(FragmentActivity mContext, List<HomeFragmentBean.DataBean.ItemsBean.ListBean> list) {
        this.mContext = mContext;
        this.list = list;
        mLayoutInflater = LayoutInflater.from(mContext);

        initWebView();
    }

    /**
     * 初始化WebView
     */
    private void initWebView() {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        //根据ViewType建立对应的ViewHolder
        switch (viewType) {
            case TYPE1:
                viewHolder = new Type1ViewHolder(mLayoutInflater.inflate(R.layout.home_fragment_item1, null,false));
                break;
            case TYPE2:
                viewHolder = new Type2ViewHolder(mLayoutInflater.inflate(R.layout.home_fragment_item2, parent,false));
                break;
            case TYPE3:
                viewHolder = new Type3ViewHolder(mLayoutInflater.inflate(R.layout.home_fragment_item3, null,false));
                break;
            case TYPE4:
                viewHolder = new Type4ViewHolder(mLayoutInflater.inflate(R.layout.home_fragment_item4, parent,false));
                break;
            case TYPE6:
                viewHolder = new Type6ViewHolder(mLayoutInflater.inflate(R.layout.home_fragment_item1, null,false));
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
            ((Type1ViewHolder) holder).setListener(position);
        } else if (holder instanceof Type2ViewHolder) {
            ((Type2ViewHolder) holder).setData(position);
            ((Type2ViewHolder) holder).setListener(position);
        } else if (holder instanceof Type3ViewHolder) {
            ((Type3ViewHolder) holder).setData(position);
            ((Type3ViewHolder) holder).setListener(position);
        } else if (holder instanceof Type4ViewHolder) {
            ((Type4ViewHolder) holder).setData(position);
            ((Type4ViewHolder) holder).setListener(position);
        } else if (holder instanceof Type6ViewHolder){
            ((Type6ViewHolder) holder).setData(position);
        }
    }

    @Override
    public int getItemViewType(int position) {

        //判断是那个ViewHolder类型
        //获取bean
        HomeFragmentBean.DataBean.ItemsBean.ListBean listBean = list.get(position);
        int content_type = listBean.getContent_type();
        if (content_type == 0){
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
        } else {
            currentType = TYPE6;
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

        /**
         * 设置数据
         * @param position
         */
        public void setData(int position) {
            Picasso.with(mContext).load(list.get(position).getOne().getPic_url()).into(type1Iv);
        }

        /**
         * 设置监听器
         * @param position
         */
        public void setListener(int position) {
            HomeFragmentBean.DataBean.ItemsBean.ListBean listBean = list.get(position);
            final String topic_url = listBean.getOne().getTopic_url();
            //点击图片跳转到H5页面
            type1Iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Log.e("TAG","首页面的第一种类型点击事件");
                    Intent intent = new Intent(mContext, ShopFragmentWebViewActivity.class);
                    intent.putExtra("topic_url",topic_url);
                    mContext.startActivity(intent);

                }
            });
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

        /**
         * 设置监听器
         * @param position
         */
        public void setListener(int position) {
            HomeFragmentBean.DataBean.ItemsBean.ListBean listBean = list.get(position);
            final String topic_url1 = listBean.getOne().getTopic_url();
            final String topic_url2 = listBean.getTwo().getTopic_url();
            //左侧图片
            type2LeftIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ShopFragmentWebViewActivity.class);
                    intent.putExtra("topic_url",topic_url1);
                    mContext.startActivity(intent);
                }
            });
            //右侧图片
            type2RightIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ShopFragmentWebViewActivity.class);
                    intent.putExtra("topic_url",topic_url2);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    class Type3ViewHolder extends RecyclerView.ViewHolder {

        public Type3ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(int position) {

        }

        /**
         * 设置监听器
         * @param position
         */
        public void setListener(int position) {

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

        /**
         * 设置监听器
         * @param position
         */
        public void setListener(int position) {
            HomeFragmentBean.DataBean.ItemsBean.ListBean listBean = list.get(position);
            final String topic_url1 = listBean.getOne().getTopic_url();
            final String topic_url2 = listBean.getTwo().getTopic_url();
            final String topic_url3 = listBean.getThree().getTopic_url();
            final String topic_url4 = listBean.getFour().getTopic_url();

            //左上图片
            type4LeftTopIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ShopFragmentWebViewActivity.class);
                    intent.putExtra("topic_url",topic_url1);
                    mContext.startActivity(intent);
                }
            });
            //左下图片
            type4LeftBottomIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ShopFragmentWebViewActivity.class);
                    intent.putExtra("topic_url",topic_url2);
                    mContext.startActivity(intent);
                }
            });

            //右上图片
            type4RightTopIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ShopFragmentWebViewActivity.class);
                    intent.putExtra("topic_url",topic_url3);
                    mContext.startActivity(intent);
                }
            });

            //右下图片
            type4RightBottomIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ShopFragmentWebViewActivity.class);
                    intent.putExtra("topic_url",topic_url4);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    class Type6ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.type1_iv)
        ImageView type1Iv;

        public Type6ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        /**
         * 设置数据
         * @param position
         */
        public void setData(int position) {
            Picasso.with(mContext).load(list.get(position).getPic_url()).into(type1Iv);
        }

        /**
         * 设置监听器
         * @param position
         */
        public void setListener(int position) {
//            HomeFragmentBean.DataBean.ItemsBean.ListBean listBean = list.get(position);
//            String pic_url = listBean.getPic_url();
//            final String topic_url = listBean.getOne().getTopic_url();
//            //点击图片跳转到H5页面
//            type1Iv.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //Log.e("TAG","首页面的第一种类型点击事件");
//                    Intent intent = new Intent(mContext, ShopFragmentWebViewActivity.class);
//                    intent.putExtra("topic_url",topic_url);
//                    mContext.startActivity(intent);
//
//                }
//            });
        }
    }
}
