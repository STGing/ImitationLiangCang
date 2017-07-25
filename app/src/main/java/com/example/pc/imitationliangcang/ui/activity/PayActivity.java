package com.example.pc.imitationliangcang.ui.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.chaychan.library.ExpandableLinearLayout;
import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.alipay.PayKeys;
import com.example.pc.imitationliangcang.alipay.PayResult;
import com.example.pc.imitationliangcang.alipay.SignUtils;
import com.example.pc.imitationliangcang.base.BaseActivity;
import com.example.pc.imitationliangcang.bean.GoodsInfo;
import com.example.pc.imitationliangcang.utils.GlideUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayActivity extends BaseActivity {

    /**
     * 支付宝相关
     */
    // 商户PID
    public static final String PARTNER = PayKeys.DEFAULT_PARTNER;
    // 商户收款账号
    public static final String SELLER = PayKeys.DEFAULT_SELLER;

    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = PayKeys.PRIVATE;
    // 支付宝公钥
    public static final String RSA_PUBLIC = PayKeys.PUBLIC;

    private static final int SDK_PAY_FLAG = 1;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(PayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(PayActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(PayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();

                        }
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };

    @BindView(R.id.title_iv_back)
    ImageView titleIvBack;
    @BindView(R.id.title_tv_name)
    TextView titleTvName;
    @BindView(R.id.pay_tv_address)
    TextView payTvAddress;
    @BindView(R.id.pay_goodList_exll)
    ExpandableLinearLayout payGoodListExll;
    @BindView(R.id.pay_tv_price)
    TextView payTvPrice;
    @BindView(R.id.pay_tv_discount_price)
    TextView payTvDiscountPrice;
    @BindView(R.id.pay_tv_redbao)
    TextView payTvRedbao;
    @BindView(R.id.pay_tv_pack_price)
    TextView payTvPackPrice;
    @BindView(R.id.pay_alipay_cb)
    CheckBox payAlipayCb;
    @BindView(R.id.pay_wechat_cb)
    CheckBox payWechatCb;
    @BindView(R.id.pay_paytype_exll)
    ExpandableLinearLayout payPaytypeExll;
    @BindView(R.id.pay_total_price)
    TextView payTotalPrice;
    @BindView(R.id.pay_save_price)
    TextView paySavePrice;
    @BindView(R.id.pay_tv_paying)
    TextView payTvPaying;
    private ArrayList<GoodsInfo> payGoods;//商品列表

    @Override
    public int getLayoutID() {
        return R.layout.activity_pay;
    }

    @Override
    public void initView() {
        super.initView();

        //初始化标题
        titleIvBack.setVisibility(View.VISIBLE);
        titleTvName.setText("订单详情");

    }

    @Override
    public void initData() {
        super.initData();

        //从传递过来的intent 获取数据
        payGoods = (ArrayList) getIntent().getSerializableExtra("payGoods");

        //设置数据
        if (payGoods != null && payGoods.size() > 0) {
            //显示商品列表
            showGoodsList();
        }

        //设置价格
        setPrice();

        //默认支付方式：支付宝
        payAlipayCb.setChecked(true);

    }

    /**
     * 显示所有的商品数据
     */
    public void showGoodsList() {

        //清除所有的子View（避免重新刷新数据时重复添加）
        payGoodListExll.removeAllViews();

        //循环遍历数据，建立view
        for (int i = 0; i < payGoods.size(); i++) {
            //建立itemVIew
            View item = View.inflate(this, R.layout.pay_item, null);

            //获取数据
            GoodsInfo goodsInfo = payGoods.get(i);

            //设置ViewHolder
            PayViewHolder holder = new PayViewHolder(this, item, goodsInfo);

            //设置holder的数据
            holder.setData();

            //将数据添加到exll布局中
            payGoodListExll.addItem(item);
        }

    }

    /**
     * 设置价格
     */
    private void setPrice() {

        /**
         * 设置中间的4个价格：原价 折扣 红包 包装 的价格
         */
        Double price = 0.0;//原价
        Double disPrice = 0.0;//折扣价格

        for (int i = 0; i < payGoods.size(); i++) {
            GoodsInfo goodsInfo = payGoods.get(i);
            //原来总价格
            price += Double.parseDouble(goodsInfo.getPrice());

            //现在的折扣价格
            double dis = Double.parseDouble(goodsInfo.getDiscount_price());
            if (dis > 0) {
                //有折扣价格
                disPrice += (Double.parseDouble(goodsInfo.getPrice()) - Double.parseDouble(goodsInfo.getDiscount_price()));
            }
        }

        //最后设置数据
        if (price > 0 && disPrice > 0) {
            payTvPrice.setText("￥" + price);
            payTvDiscountPrice.setText("-￥" + disPrice);
        }
        //设置红包
        payTvRedbao.setText("-￥0.00");
        payTvPackPrice.setText("+￥0.00");


        /**
         * 设置底部的总价
         */
        payTotalPrice.setText("总计：￥" + (price - disPrice));
        //设置节省了多少钱
        if (disPrice > 0) {
            paySavePrice.setVisibility(View.VISIBLE);
            paySavePrice.setText("已节省：￥" + disPrice);
        } else {
            paySavePrice.setVisibility(View.GONE);
        }
    }

    /**
     * 点击事件
     * @param view
     */
    @OnClick({R.id.title_iv_back, R.id.pay_tv_address, R.id.pay_alipay_cb, R.id.pay_wechat_cb, R.id.pay_tv_paying})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_iv_back://返回
                finish();
                break;
            case R.id.pay_tv_address://地址的点击事件
                Toast.makeText(this, "地址", Toast.LENGTH_SHORT).show();
                break;
            case R.id.pay_alipay_cb://支付宝的checkBox 的点击事件

                if (payAlipayCb.isChecked()){
                    //取消alipay
                    payWechatCb.setChecked(false);
                }

                break;
            case R.id.pay_wechat_cb://微信的checkBox 的点击事件

                if (payWechatCb.isChecked()){
                    //取消alipay
                    payAlipayCb.setChecked(false);
                }

                break;
            case R.id.pay_tv_paying://点击支付，跳转支付宝
                if (payAlipayCb.isChecked()){
                    //如果勾选支付宝，跳转到支付宝
                    aliPay();
                } else if (payWechatCb.isChecked()){
                    Toast.makeText(this, "微信无法支付", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "请选择一种支付方式", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    static class PayViewHolder {
        private final GoodsInfo goodsInfo;
        private final Context mContext;
        @BindView(R.id.pay_item_iv_good)
        ImageView payItemIvGood;
        @BindView(R.id.pay_item_goods_name)
        TextView payItemGoodsName;
        @BindView(R.id.pay_item_goods_sku)
        TextView payItemGoodsSku;
        @BindView(R.id.pay_item_goodsPrice)
        TextView payItemGoodsPrice;
        @BindView(R.id.pay_item_tv_goodCount)
        TextView payItemTvGoodCount;

        PayViewHolder(Context mContext, View view, GoodsInfo goodsInfo) {
            ButterKnife.bind(this, view);
            this.goodsInfo = goodsInfo;
            this.mContext = mContext;
        }

        public void setData() {
            //商品图片
            GlideUtils.loadImageView(mContext, goodsInfo.getGoods_image(), payItemIvGood);
            //商品名称
            payItemGoodsName.setText(goodsInfo.getGoods_name());
            //sku
            payItemGoodsSku.setText(goodsInfo.getChoiceSku());
            //价格(判断有无折扣，如果有折扣，显示折扣价格，否则显示未打折价格)
            double disPrice = Double.parseDouble(goodsInfo.getDiscount_price());

            if (disPrice > 0) {
                //有折扣价
                payItemGoodsPrice.setText("￥" + disPrice);
            } else {
                //没有折扣价
                payItemGoodsPrice.setText("￥" + goodsInfo.getPrice());
            }
            //数量
            payItemTvGoodCount.setText("×" + goodsInfo.getGoodsNumber());
        }
    }


    /**
     * call alipay sdk pay. 调用SDK支付
     *  这里只是进行测试，实际中的 key 为了安全起见，应该放到服务器中。
     *  下面的数据：也应该根据实际的商品的设置
     */
    public void aliPay() {
        if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(RSA_PRIVATE) || TextUtils.isEmpty(SELLER)) {
            new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置PARTNER | RSA_PRIVATE| SELLER")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            //
                            finish();
                        }
                    }).show();
            return;
        }

        //获取了商品的 订单信息:
        String orderInfo = getOrderInfo("测试的商品", "该测试商品的详细描述", "0.01");

        /**
         * 特别注意，这里的签名逻辑需要放在服务端，切勿将私钥泄露在代码中！
         * 对商品信息进行 签名 处理。
         */
        String sign = sign(orderInfo);
        try {
            /**
             * 仅需对sign 做URL编码
             */
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        /**
         * 完整的符合支付宝参数规范的订单信息
         */
        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(PayActivity.this);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * create the order info. 创建订单信息
     *
     */
    private String getOrderInfo(String subject, String body, String price) {

        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm" + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        return orderInfo;
    }

    /**
     * get the sign type we use. 获取签名方式
     *
     */
    private String getSignType() {
        return "sign_type=\"RSA\"";
    }

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content
     *            待签名订单信息
     */
    private String sign(String content) {
        return SignUtils.sign(content, RSA_PRIVATE);
    }

    /**
     * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
     *
     */
    private String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }
}
