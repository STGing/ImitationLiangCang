package com.example.pc.imitationliangcang.ui.adapter.sharefragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.bean.sharefragment.ShareDuanZiBean;
import com.example.pc.imitationliangcang.utils.GlideUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PC on 2017/7/11.
 */

public class ShareDuanZiAdapter extends RecyclerView.Adapter<ShareDuanZiAdapter.TextViewHolder>{

    private final List<ShareDuanZiBean.ListBean> list;
    private LayoutInflater mLayoutInflater;
    private final Context mContext;


    public ShareDuanZiAdapter(Context mContext, List<ShareDuanZiBean.ListBean> list) {
        this.mContext = mContext;
        this.list = list;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    @Override
    public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TextViewHolder(mLayoutInflater.inflate(R.layout.share_duanzi_item,parent,false));
    }

    @Override
    public void onBindViewHolder(TextViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    //基础ViewHolder，共有的部分(头部和底部)
    public class BaseViewHolder extends RecyclerView.ViewHolder {

        //顶部
        @BindView(R.id.user_headpic)
        ImageView userHeadpic;
        @BindView(R.id.tv_userName)
        TextView tvUserName;
        @BindView(R.id.tv_time_refresh)
        TextView tvTimeRefresh;
        @BindView(R.id.common_user_info_ll)
        LinearLayout commonUserInfoLl;

        //底部
        @BindView(R.id.iv_zan)
        TextView ivZan;
        @BindView(R.id.tv_zan_number)
        TextView tvZanNumber;
        @BindView(R.id.ll_dianZan)
        LinearLayout llDianZan;
        @BindView(R.id.iv_cai)
        TextView ivCai;
        @BindView(R.id.tv_cai_number)
        TextView tvCaiNumber;
        @BindView(R.id.ll_cai)
        LinearLayout llCai;
        @BindView(R.id.tv_share_number)
        TextView tvShareNumber;
        @BindView(R.id.ll_share)
        LinearLayout llShare;
        @BindView(R.id.tv_comment_number)
        TextView tvCommentNumber;
        @BindView(R.id.ll_comment)
        LinearLayout llTalk;

        public BaseViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(int position) {
            ShareDuanZiBean.ListBean listBean = list.get(position);
            ShareDuanZiBean.ListBean.UBean uBean = listBean.getU();

            //头像
            GlideUtils.loadImageView(mContext, uBean.getHeader().get(0), userHeadpic);

            //用户名称
            tvUserName.setText(uBean.getName());

            //发布时间
            tvTimeRefresh.setText(listBean.getPasstime());

            //底部
            //赞的数量
            tvZanNumber.setText(listBean.getUp());
            //菜的数量
            tvCaiNumber.setText(listBean.getDown() + "");
            //分享的数量
            tvShareNumber.setText(listBean.getForward() + "");
            //讨论的数量
            tvCommentNumber.setText(listBean.getComment());

        }

    }


    /**
     * 类型：text
     */
    public class TextViewHolder extends BaseViewHolder {

        @BindView(R.id.duanzi_tv_text)
        TextView duanziTvText;

        public TextViewHolder(View view) {
            super(view);
        }

        @Override
        public void setData(int position) {
            super.setData(position);

            duanziTvText.setText(list.get(position).getText());

        }
    }

}
