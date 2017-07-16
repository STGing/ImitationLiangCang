package com.example.pc.imitationliangcang.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.base.BaseActivity;
import com.example.pc.imitationliangcang.bean.GoodsDetailBean;
import com.example.pc.imitationliangcang.bean.GoodsInfo;
import com.example.pc.imitationliangcang.common.NetWorkUrl;
import com.example.pc.imitationliangcang.db.DBDao;
import com.example.pc.imitationliangcang.ui.view.AddSubView;
import com.example.pc.imitationliangcang.utils.MyImageLoader;
import com.example.pc.imitationliangcang.utils.ShareSDK;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 用于显示具体的某个商品的详细信息
 */

public class GoodsDetailActivity extends BaseActivity {


    @BindView(R.id.goods_detail_banner)
    Banner goodsDetailBanner;
    @BindView(R.id.goods_detail_brandName)
    TextView goodsDetailBrandName;
    @BindView(R.id.goods_detail_IvlikeCount)
    ImageView goodsDetailIvlikeCount;
    @BindView(R.id.goods_detail_goodName)
    TextView goodsDetailGoodName;
    @BindView(R.id.goods_detail_tvLikeCount)
    TextView goodsDetailTvLikeCount;
    @BindView(R.id.goods_detail_shared)
    ImageView goodsDetailShared;
    @BindView(R.id.goods_detail_send_)
    TextView goodsDetailSend;
    @BindView(R.id.goods_detail_goodsChoiceType)
    TextView goodsDetailGoodsChoiceType;
    @BindView(R.id.goods_detail_brandIv)
    ImageView goodsDetailBrandIv;
    @BindView(R.id.goods_detail_brand_detail_rl)
    RelativeLayout goodsDetailBrandDetailRl;
    @BindView(R.id.goods_detail_goodsRb)
    RadioButton goodsDetailGoodsRb;
    @BindView(R.id.goods_detail_buyReadRb)
    RadioButton goodsDetailBuyReadRb;
    @BindView(R.id.goods_detail_radioGroup)
    RadioGroup goodsDetailRadioGroup;
    @BindView(R.id.goods_detail_brandName2)
    TextView goodsDetailBrandName2;
    @BindView(R.id.goods_detail_brandStory)
    TextView goodsDetailBrandStory;
    @BindView(R.id.goods_detail_recommendReasonTv)
    TextView goodsDetailRecommendReasonTv;
    @BindView(R.id.goods_detail_goodsDetail_ll)
    LinearLayout goodsDetailGoodsDetailLl;
    @BindView(R.id.goods_detail_buyRead_Detail_tv)
    TextView goodsDetailBuyReadDetailTv;
    @BindView(R.id.goods_detail_buyRead_Detail_btn)
    Button goodsDetailBuyReadDetailBtn;
    @BindView(R.id.goods_detail_buyRead)
    LinearLayout goodsDetailBuyRead;
    @BindView(R.id.goods_detail_ibServer)
    ImageButton goodsDetailIbServer;
    @BindView(R.id.goods_detail_addShopCar)
    Button goodsDetailAddShopCar;
    @BindView(R.id.goods_detail_buy)
    Button goodsDetailBuy;
    @BindView(R.id.goods_detail_current_price)
    TextView goodsDetailCurrentPrice;
    @BindView(R.id.goods_detail_goodsPack)
    TextView goodsDetailGoodsPack;
    @BindView(R.id.goods_detail_brandName3)
    TextView goodsDetailBrandName3;
    @BindView(R.id.goods_detail_promotionNote)
    TextView goodsDetailPromotionNote;
    @BindView(R.id.goods_detail_last_price)
    TextView goodsDetailLastPrice;
    @BindView(R.id.goods_detail_goodsInfo_pic_ll)
    LinearLayout goodsDetailGoodsInfoPicLl;
    @BindView(R.id.title_iv_back)
    ImageView titleIvBack;
    @BindView(R.id.title_iv_shopCar)
    ImageView titleIvShopCar;
    @BindView(R.id.base_title_layout)
    RelativeLayout baseTitleLayout;

    private PopupWindow popWnd;
    private GoodsDetailBean.DataBean.ItemsBean items;
    private LayoutInflater mInflater;

    /**
     * 商品sku标题的标记
     */
    private static final String COLOR = "1";
    private static final String NUMBER = "7";
    private static final String CAPACITY = "6";//容量
    private static final String SPECIES = "13";//种类
    private static final String STYLE = "37";//款式

    /**
     * 存放每个sku属性的集合
     */
    private List<String> colorlist = new ArrayList<>();
    private List<String> numberlist = new ArrayList<>();
    private List<String> capacitylist = new ArrayList<>();
    private List<String> specielist = new ArrayList<>();
    private List<String> stylelist = new ArrayList<>();

    private static int goodsValue = 1;//购买商品数量

    private DBDao dbDao;//数据库Dao
    /**
     * 购物车要用的数据
     */
    private GoodsInfo shopGoodsInfo;

    //当前选择的sku
    private String choiceColor;
    private String choiceNumber;
    private String choiceCapacity;
    private String choiceSpecie;
    private String choiceStyle;

    private String sku = "";//当前选择的sku

    @Override
    public void initView() {
        super.initView();

        mInflater = LayoutInflater.from(this);

        //设置标题
        baseTitleLayout.setBackgroundColor(Color.parseColor("#00ffffff"));//背景透明
        titleIvBack.setVisibility(View.VISIBLE);//显示返回键
        titleIvShopCar.setVisibility(View.VISIBLE);//显示购物车
    }

    @Override
    public String getUrl() {

        //获取数据
        String goods_id = getIntent().getStringExtra("goods_id");

        if (!TextUtils.isEmpty(goods_id)) {
            //获取地址
            String url = NetWorkUrl.GOODSDETAILURL01 + goods_id + NetWorkUrl.GOODSDETAILURL02;
            //Log.e("TAG", "商品的url===" + url);
            return url;
        }

        return super.getUrl();
    }

    @Override
    public void processData(String s) {
        super.processData(s);

        //处理获取的数据

        //1.解析json数据
        GoodsDetailBean goodsDetailBean = new Gson().fromJson(s, GoodsDetailBean.class);
        items = goodsDetailBean.getData().getItems();

        //2.设置商品轮播图片
        List<String> images_item = items.getImages_item();//获取轮播图片集合
        goodsDetailBanner.setImageLoader(new MyImageLoader());//设置图片加载器
        goodsDetailBanner.setImages(images_item);//设置图片集合
        goodsDetailBanner.setDelayTime(3000);//轮播时间
        goodsDetailBanner.start();//启动轮播

        //3.设置商品品牌
        goodsDetailBrandName.setText(items.getBrand_info().getBrand_name());

        //4.设置商品名称
        goodsDetailGoodName.setText(items.getGoods_name());

        //5.设置喜欢的数量
        goodsDetailTvLikeCount.setText(items.getLike_count());

        //6.设置价格
        goodsDetailCurrentPrice.setText(items.getPrice());

        //7.设置发货的预计时间
        goodsDetailSend.setText(items.getShipping_str());

        //8.设置品牌相关信息
        Picasso.with(this)//品牌logo
                .load(items.getBrand_info().getBrand_logo())
                .into(goodsDetailBrandIv);
        goodsDetailBrandName2.setText(items.getBrand_info().getBrand_name());//品牌名称

        //9.设置商品的包装(根据不同的商品，判断是否显示)
        List<GoodsDetailBean.DataBean.ItemsBean.GoodsInfoBean> goods_info = items.getGoods_info();
        //Log.e("TAG","goods_info的信息是"+goods_info);
        if (goods_info.size() == 0) {
            goodsDetailGoodsPack.setVisibility(View.VISIBLE);
            goodsDetailGoodsPack.setText(items.getGoods_desc());
        } else {
            goodsDetailGoodsPack.setVisibility(View.GONE);
        }


        //10.设置商品的图片列表（如果有数据的话就显示)
        if (goods_info.size() > 0) {
            goodsDetailGoodsInfoPicLl.setVisibility(View.VISIBLE);

            //根据商品信息动态的建立图片
            for (int i = 0; i < goods_info.size(); i++) {
                //获取图片链接
                GoodsDetailBean.DataBean.ItemsBean.GoodsInfoBean goodsInfoBean = goods_info.get(i);
                GoodsDetailBean.DataBean.ItemsBean.GoodsInfoBean.ContentBean content = goodsInfoBean.getContent();
                int type = goodsInfoBean.getType();
                switch (type) {
                    case 1://1表示是商品图片信息

                        //建立图片
                        ImageView iv = new ImageButton(this);

                        LinearLayout.LayoutParams params1 =
                                new LinearLayout.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT);
                        iv.setLayoutParams(params1);
                        params1.bottomMargin = -60;
                        iv.setScaleType(ImageView.ScaleType.FIT_XY);
                        //背景透明
                        iv.setBackgroundColor(Color.parseColor("#00ffffff"));
                        //加载图片
                        Picasso.with(this)
                                .load(content.getImg())
                                .resize(content.getWidth(), content.getHeight())
                                .into(iv);

                        //图片添加到布局上
                        goodsDetailGoodsInfoPicLl.addView(iv);

                        break;
                    case 0://1表示是商品说明

                        //动态添加文字
                        TextView tv = new TextView(this);
                        tv.setText(content.getText());
                        LinearLayout.LayoutParams params0 =
                                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        params0.setMargins(10, 30, 10, 10);
                        tv.setLayoutParams(params0);
                        tv.setTextColor(Color.parseColor("#959697"));
                        goodsDetailGoodsInfoPicLl.addView(tv);

                        break;
                    case 2://1表示是商品注意事项

                        //动态添加文字
                        TextView tv2 = new TextView(this);
                        tv2.setText(content.getText());
                        LinearLayout.LayoutParams params2 =
                                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        params2.setMargins(10, 10, 10, 10);
                        tv2.setLayoutParams(params2);
                        tv2.setTextColor(Color.parseColor("#e1e2e2"));
                        goodsDetailGoodsInfoPicLl.addView(tv2);

                        break;
                    default:

                        break;
                }

            }


        } else {
            goodsDetailGoodsInfoPicLl.setVisibility(View.GONE);
        }


        //11.设置品牌故事
        goodsDetailBrandName3.setText(items.getBrand_info().getBrand_name());//名称
        goodsDetailBrandStory.setText(items.getBrand_info().getBrand_desc());//故事描述

        //12.设置良仓推荐
        goodsDetailRecommendReasonTv.setText(items.getRec_reason());

        //13.设置购物须知
        goodsDetailBuyReadDetailTv.setText(items.getGood_guide().getContent());

        //14.建立一个Bean对象保存要购买的商品信息
        shopGoodsInfo = new GoodsInfo();

        //15.初始化数据库Dao
        dbDao = DBDao.getInstance();
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_goods_detail;
    }


    @OnClick({R.id.title_iv_back, R.id.title_iv_shopCar, R.id.goods_detail_IvlikeCount, R.id.goods_detail_shared, R.id.goods_detail_goodsChoiceType, R.id.goods_detail_brandIv, R.id.goods_detail_brand_detail_rl, R.id.goods_detail_goodsRb, R.id.goods_detail_buyReadRb, R.id.goods_detail_radioGroup, R.id.goods_detail_buyRead_Detail_btn, R.id.goods_detail_ibServer, R.id.goods_detail_addShopCar, R.id.goods_detail_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.goods_detail_IvlikeCount:
                break;
            case R.id.goods_detail_shared://点击分享之后弹出
                ShareSDK.showShare(this,items.getGoods_name(),"分享");
                break;
            case R.id.goods_detail_goodsChoiceType://选择颜色类型
                showPopUpWindow();
                break;
            case R.id.goods_detail_brandIv:
                break;
            case R.id.goods_detail_brand_detail_rl:
                break;
            case R.id.goods_detail_goodsRb:

                //点击显示商品情况
                goodsDetailBuyRead.setVisibility(View.GONE);//隐藏
                goodsDetailGoodsDetailLl.setVisibility(View.VISIBLE);//显示
                break;
            case R.id.goods_detail_buyReadRb:

                //点击显示购买须知
                goodsDetailGoodsDetailLl.setVisibility(View.GONE);//隐藏
                goodsDetailBuyRead.setVisibility(View.VISIBLE);//显示

                break;
            case R.id.goods_detail_radioGroup:
                break;
            case R.id.goods_detail_buyRead_Detail_btn:
                break;
            case R.id.goods_detail_ibServer:
                break;
            case R.id.goods_detail_addShopCar://点击加入购物车

                showPopUpWindow();//点击显示/关闭popupwindow

                break;
            case R.id.goods_detail_buy:
                break;
            case R.id.title_iv_back://标题返回
                finish();
                break;
            case R.id.title_iv_shopCar://标题购物车

                Intent intent = new Intent(this,ShopCarActivity.class);
                //这里测试数据，将shopgoodsInfo传入到购物车
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("shopGoodsInfo",shopGoodsInfo);
//                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }

    /**
     * 用于显示popupwindow
     */
    private void showPopUpWindow() {

        //校验:如果为空就初始化
        if (popWnd == null) {

            //获取屏幕的参数
            WindowManager windowManager = this.getWindowManager();
            Display display = windowManager.getDefaultDisplay();

            View contentView = LayoutInflater.from(this).inflate(R.layout.add_goods_popwin, null, false);

            //初始化popupwindow，占据全屏
            popWnd = new PopupWindow(contentView, display.getWidth(), display.getHeight());

            //设置popupwindow动画
            popWnd.setAnimationStyle(R.style.popAnimation);

            popWnd.setFocusable(true);

            popWnd.setBackgroundDrawable(new BitmapDrawable());

            //初始化popupwindow里面的子View
            ImageView addGoodsPopIv = (ImageView) contentView.findViewById(R.id.add_goods_pop_iv);
            ImageView addGoodsPopIvBack = (ImageView) contentView.findViewById(R.id.add_goods_pop_ivBack);
            TextView addGoodsPopBrandName = (TextView) contentView.findViewById(R.id.add_goods_pop_brandName);
            TextView addGoodsPopGoodName = (TextView) contentView.findViewById(R.id.add_goods_pop_goodName);
            TextView addGoodsPopGoodPrice = (TextView) contentView.findViewById(R.id.add_goods_pop_goodPrice);
            LinearLayout skuLLs = (LinearLayout) contentView.findViewById(R.id.add_goods_pop_skull);
            AddSubView addsub = (AddSubView) contentView.findViewById(R.id.add_goods_pop_addsub);
            Button btnConfirm = (Button) contentView.findViewById(R.id.add_goods_pop_btnConfirm);

            //设置数据
            Picasso.with(this)
                    .load(items.getGoods_image())
                    .into(addGoodsPopIv);

            //点击x号退出popupwindow
            addGoodsPopIvBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popWnd.dismiss();
                }
            });

            //设置品牌名称
            addGoodsPopBrandName.setText(items.getBrand_info().getBrand_name());

            //设置商品名称
            addGoodsPopGoodName.setText(items.getGoods_name());

            //价格
            final String discount_price = items.getDiscount_price();
            if (!TextUtils.isEmpty(discount_price)) {
                addGoodsPopGoodPrice.setText(discount_price);
            } else {
                addGoodsPopGoodPrice.setText(items.getPrice());
            }

            //动态建立商品的sku
            final List<GoodsDetailBean.DataBean.ItemsBean.SkuInfoBean> sku_info = items.getSku_info();
            //循环遍历sku
            for (int i = 0; i < sku_info.size(); i++) {

                //获取数据
                final GoodsDetailBean.DataBean.ItemsBean.SkuInfoBean skuInfoBean = sku_info.get(i);

                //动态生成装有一个TextView 和 flowLayout的LL布局
                LinearLayout skull = (LinearLayout) mInflater.inflate(R.layout.sku_layout,null,false);
                final TextView skuTitle = (TextView) skull.findViewById(R.id.sku_title);
                //建立了flowlayout
                final TagFlowLayout skufl = (TagFlowLayout) skull.findViewById(R.id.sku_flowlayout);
                //为flowLayout设置tag
                skufl.setTag(skuInfoBean.getType_id());
                //点击事件
                skufl.setOnTagClickListener(new MyOnTagClickLIstener());


                //1.sku标题
                String type_name = skuInfoBean.getType_name();//获取类型名称
                skuTitle.setText(type_name);

                //2.sku具体属性
                final List<String> skuNamelist = new ArrayList<>();//名称的集合
                final List<GoodsDetailBean.DataBean.ItemsBean.SkuInfoBean.AttrListBean> attrList = skuInfoBean.getAttrList();
                for (int j = 0; j < attrList.size(); j++) {
                    GoodsDetailBean.DataBean.ItemsBean.SkuInfoBean.AttrListBean attrListBean = attrList.get(j);
                    String attr_name = attrListBean.getAttr_name();//获取属性名称
                    skuNamelist.add(attr_name);

                    //根据标题添加数据到对应的sku属性集合
                    if (skuInfoBean.getType_id().equals(COLOR)){//颜色
                        colorlist.add(attr_name);
                    } else if (skuInfoBean.getType_id().equals(NUMBER)){//数量单位
                        numberlist.add(attr_name);
                    }else if (skuInfoBean.getType_id().equals(CAPACITY)){//容量
                        capacitylist.add(attr_name);
                    } else if (skuInfoBean.getType_id().equals(SPECIES)){//种类
                        specielist.add(attr_name);
                    } else if (skuInfoBean.getType_id().equals(STYLE)) {//款式
                        stylelist.add(attr_name);
                    }
                }

                if (skuNamelist != null && skuNamelist.size() > 0) {
                    //将sku属性的集合添加到flowlayout中
                    skufl.setAdapter(new TagAdapter<String>(skuNamelist) {
                        @Override
                        public View getView(FlowLayout parent, int position, String s) {
                            TextView tv = (TextView) mInflater.inflate(R.layout.sku_text,
                                    skufl, false);
                            tv.setText(s);
                            return tv;
                        }

                        //设置默认选中
                        @Override
                        public boolean setSelected(int position, String s) {

                            String skuName = skuNamelist.get(0);

                            return skuName.equals(s);
                        }
                    });

                }

                //3.最后将skulayout布局添加到pop总布局上
                skuLLs.addView(skull);
            }



            //设置商品数量加减的监听
            addsub.setOnNumberChangeListener(new AddSubView.OnNumberChangeListener() {
                @Override
                public void numberChange(int value) {
                    goodsValue = value;
                }
            });


            //设置点击确定，添加到购物车的监听
            btnConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //获取数据
                    shopGoodsInfo.setGoods_id(items.getGoods_id());
                    shopGoodsInfo.setGoods_name(items.getGoods_name());
                    shopGoodsInfo.setGoods_image(items.getGoods_image());
                    shopGoodsInfo.setBrand_name(items.getOwner_name());
                    shopGoodsInfo.setPrice(items.getPrice());//原价

                    //判断有无折扣价格
                    String discount_price1 = items.getDiscount_price();
                    if (!TextUtils.isEmpty(discount_price)) {
                        shopGoodsInfo.setDiscount_price(discount_price1);//打折价格
                    } else {
                        shopGoodsInfo.setDiscount_price("0");//打折价格为0
                    }


                    //一定要设置数量
                    shopGoodsInfo.setGoodsNumber(goodsValue);

                    /**
                            COLOR = "1";
                             NUMBER = "7";
                             CAPACITY = "6";//容量
                            SPECIES = "13";//种类
                             STYLE = "37";//款式
                     */

                    //判断当前商品有什么sku标题，及当前选择的sku属性，并添加到集合
                    if (colorlist != null && colorlist.size()>0){//有颜色
                        sku += "颜色: "+choiceColor+" ";
                    }
                    if (colorlist != null && numberlist.size()>0){//数量单位
                        sku += "数量: "+choiceNumber+" ";
                    }
                    if (colorlist != null && capacitylist.size()>0){//容量
                        sku += "容量: "+choiceCapacity+" ";
                    }
                    if (colorlist != null && specielist.size()>0){//种类
                        sku += "种类: "+choiceSpecie+" ";
                    }
                    if (colorlist != null && stylelist.size()>0) {//款式
                        sku += "款式: "+choiceStyle+" ";
                    }


                    //将上面的数据添加到goodsinfo种
//                    Log.e("TAG","当前选择的属性有"+sku);
                    shopGoodsInfo.setChoiceSku(sku);

                    //将数据保存到数据库
                    dbDao.addData(shopGoodsInfo);

                    //结束当前页面，并提示
                    popWnd.dismiss();
                    Toast.makeText(GoodsDetailActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                }
            });

        }


        //如果不为空，就显示/关闭
        if (popWnd.isShowing()) {
            closePopupWindow();
        } else {
            //获取rootView
            //ViewGroup rootView = (ViewGroup) findViewById(android.R.id.content);
            //设置从底部弹出
            popWnd.showAtLocation(GoodsDetailActivity.this.findViewById(R.id.goods_detail_ll), Gravity.BOTTOM, 0, 0);
            //popWnd.showAtLocation(rootView, Gravity.BOTTOM,0,0);

        }

    }


    /**
     * 关闭窗口
     */
    private void closePopupWindow() {
        if (popWnd != null && popWnd.isShowing()) {
            popWnd.dismiss();
            popWnd = null;
            WindowManager.LayoutParams params = this.getWindow().getAttributes();
            params.alpha = 1f;
            this.getWindow().setAttributes(params);
        }
    }

    //点击事件
    /**
     *   private String color = "1";
         private String number = "7";
         private String capacity = "6";//容量
         private String species = "13";//种类
         private String style = "37";//款式
     */
    private class MyOnTagClickLIstener implements TagFlowLayout.OnTagClickListener {
        @Override
        public boolean onTagClick(View view, int position, FlowLayout parent) {
            String tag = (String) parent.getTag();
            switch (tag) {
                case COLOR://颜色
                    //从颜色集合获取数据
                    choiceColor = colorlist.get(position);

                    break;
                case NUMBER://数量单位
                    choiceNumber = numberlist.get(position);
                    break;
                case CAPACITY://容量单位
                    choiceCapacity = capacitylist.get(position);
                    break;
                case SPECIES://种类单位
                    choiceSpecie = specielist.get(position);

                    break;
                case STYLE://款式单位
                    choiceStyle = stylelist.get(position);
                    break;
                default:
                    break;
            }

            return false;
        }
    }
}
