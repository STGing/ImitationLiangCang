package com.example.pc.imitationliangcang.ui.adapter.sharefragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.bean.sharefragment.ShareRecommendBean;
import com.example.pc.imitationliangcang.utils.GlideUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by PC on 2017/7/11.
 */

public class ShareRecommendAdapter extends RecyclerView.Adapter {

    /**
     * 共有4中类型
     */
    public static final int IMAGE = 1;//image
    public static final int VIDEO = 2;//video
    public static final int GIF = 3;//gif
    public static final int TEXT = 4;//text

    //当前类型是什么
    private int currentType = 0;

    private LayoutInflater mLayoutInflater;

    private final Context mContext;
    private final List<ShareRecommendBean.ListBean> list;

    public ShareRecommendAdapter(Context mContext, List<ShareRecommendBean.ListBean> list) {
        this.mContext = mContext;
        this.list = list;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup paren, int viewType) {
        if (viewType == 1) {//image
            return new ImageViewHolder(mLayoutInflater.inflate(R.layout.share_recommend_image, paren, false));
        } else if (viewType == 2) {//video
            return new VideoViewHolder(mLayoutInflater.inflate(R.layout.share_recommend_video, paren, false));
        } else if (viewType == 3) {//gif
            return new GifViewHolder(mLayoutInflater.inflate(R.layout.share_recommend_gif, paren, false));
        } else if (viewType == 4) {//text
            return new TextViewHolder(mLayoutInflater.inflate(R.layout.share_recommend_text, paren, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ImageViewHolder) {
            ((ImageViewHolder) holder).setData(position);
        } else if (holder instanceof VideoViewHolder) {
            ((VideoViewHolder) holder).setData(position);
        } else if (holder instanceof GifViewHolder) {
            ((GifViewHolder) holder).setData(position);
        } else if (holder instanceof TextViewHolder) {
            ((TextViewHolder) holder).setData(position);
        }
//        if (getItemViewType(position) == 0){
//            ((ImageViewHolder) holder).setData(position);
//        }
    }

    @Override
    public int getItemViewType(int position) {
        //判断是哪个ViewHolder类型
        //获取bean
        ShareRecommendBean.ListBean listBean = list.get(position);
        String type = listBean.getType();
        //Log.e("TAG","type类型有----"+type);
        //判断
        if (type.equals("image")) {//1
            currentType = 1;
        } else if (type.equals("video")) {//2
            currentType = 2;
        } else if (type.equals("gif")) {//3
            currentType = 3;
        } else if (type.equals("text")) {//4
            currentType = 4;
        }
        //Log.e("TAG","currentType有----"+currentType);
        return currentType;
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
        @BindView(R.id.common_bottom_comments_ll)
        LinearLayout commonBottomCommentsLL;

        public BaseViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(int position) {
            ShareRecommendBean.ListBean listBean = list.get(position);
            ShareRecommendBean.ListBean.UBean uBean = listBean.getU();

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

            //底部讨论的内容

            //获取评论的数据
            List<ShareRecommendBean.ListBean.TopCommentsBean> top_comments = listBean.getTop_comments();
            if (top_comments != null && top_comments.size()>0){
                for (int i = 0; i < top_comments.size(); i++) {
                    ShareRecommendBean.ListBean.TopCommentsBean topCommentsBean = top_comments.get(i);
                    String userName = topCommentsBean.getU().getName();
                    String content = topCommentsBean.getContent();

                    TextView tvUserName = (TextView) mLayoutInflater.inflate(R.layout.comments_username_text,null,false);
                    TextView tvUserContent = (TextView) mLayoutInflater.inflate(R.layout.comments_usertalk_text,null,false);
                    tvUserName.setText(userName+"：");
                    tvUserContent.setText(content);

                    //加入到ll布局中
                    LinearLayout ll = (LinearLayout) mLayoutInflater.inflate(R.layout.share_fragment_bottom_comments, null, false);
                    ll.addView(tvUserName);
                    ll.addView(tvUserContent);

                    //加入到总布局中
                    commonBottomCommentsLL.addView(ll);
                }
            }
        }

    }

    /**
     * 类型：image
     */
    public class ImageViewHolder extends BaseViewHolder {


        @BindView(R.id.recommend_image_tv_title)
        TextView recommendImageTvTitle;
        @BindView(R.id.recommend_image_iv_image)
        ImageView recommendImageIvImage;


        public ImageViewHolder(View view) {
            super(view);
        }

        @Override
        public void setData(int position) {
            super.setData(position);
            //获取数据
            ShareRecommendBean.ListBean listBean = list.get(position);

            //设置标题
            recommendImageTvTitle.setText(listBean.getText());

            //设置图片
            //GlideUtils.loadImageView(mContext,listBean.getImage().getBig().get(0),recommendImageIvImage);
            Glide.with(mContext)
                    .load(listBean.getImage().getBig().get(0))
                    .into(recommendImageIvImage);

        }
    }

    /**
     * 类型：video
     */
    public class VideoViewHolder extends BaseViewHolder {

        @BindView(R.id.recommend_video_tv_title)
        TextView recommendVideoTvTitle;
        @BindView(R.id.recommend_video_videoplayer)
        JCVideoPlayerStandard recommendVideoVideoplayer;

        public VideoViewHolder(View view) {
            super(view);
        }

        @Override
        public void setData(int position) {
            super.setData(position);

            //获取数据
            ShareRecommendBean.ListBean listBean = list.get(position);

            //设置标题
            recommendVideoTvTitle.setText(listBean.getText());

            //设置视频播放数据
            recommendVideoVideoplayer.setUp(listBean.getVideo().getVideo().get(0)
                    , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL);
            //缩略图
            GlideUtils.loadImageView(mContext,listBean.getVideo().getThumbnail().get(0),recommendVideoVideoplayer.thumbImageView);
        }
    }

    /**
     * 类型：gif
     */
    public class GifViewHolder extends BaseViewHolder {
        @BindView(R.id.recommend_gif_tv_title)
        TextView recommendGifTvTitle;
        @BindView(R.id.recommend_gif_iv)
        ImageView recommendGifIv;

        public GifViewHolder(View view) {
            super(view);
        }

        @Override
        public void setData(int position) {
            super.setData(position);

            ShareRecommendBean.ListBean listBean = list.get(position);

            //标题
            recommendGifTvTitle.setText(listBean.getText());

            //设置图片
            //GlideUtils.loadImageView(mContext, listBean.getGif().getImages().get(0), recommendGifIv);
            Glide.with(mContext)
                    .load(listBean.getGif().getImages().get(0))
                    .asGif()
                    .into(recommendGifIv);


        }
    }

    /**
     * 类型：text
     */
    public class TextViewHolder extends BaseViewHolder {

        @BindView(R.id.recommend_text_tv)
        TextView recommendTextTv;

        public TextViewHolder(View view) {
            super(view);
        }

        @Override
        public void setData(int position) {
            super.setData(position);

            recommendTextTv.setText(list.get(position).getText());

        }
    }

}
