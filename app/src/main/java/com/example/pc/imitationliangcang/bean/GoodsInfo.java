package com.example.pc.imitationliangcang.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 用来保存一个商品的信息，存放至数据库，用于购物车读取要购买的商品信息。
 */

public class GoodsInfo implements Serializable{
    /**
     * goods_id : 255507
     * goods_image : http://imgs-qn.iliangcang.com/ware/goods/big/2/255/255507.jpg?t=1479707976
     * goods_url : http://www.iliangcang.com/i/goods/?id=255507
     * goods_name : 4.5mm天然橡胶瑜伽垫（五色可选）
     * goods_desc : 尺寸：186cm*68cm*4.5mm
     颜色：天使粉、马卡龙蓝、樱花粉、高贵紫、活力绿
     舒丽皮+欧标天然橡胶
     发货说明：
     发货时间：下单后两日内发货，节假日有延迟，春节以快递管控时间为准。
     发货地：北京
     物流：圆通
     售后服务：无损坏或使用痕迹，7日内可退换货
     * price : 899.00
     * comment_count : 0
     * like_count : 182
     * liked : 0
     * owner_id : 0
     * shipping_type : 1
     * shipping_time : 3
     * shipping_str : 现货商品3天发货
     * is_overseas_goods : 0
     * brand_info : {"brand_id":"605","brand_name":"易伽猫","brand_desc":"易伽猫，是一只专注修练瑜伽的猫，\r\n对产品细节和品质要求极为苛刻；\r\n喵深信，当你全身心投入瑜伽的时候，\r\n好产品带给你的不止是练习中的便利，\r\n更会带来更深的羁绊与愉悦！","brand_logo":"http://imgs-qn.iliangcang.com/ware/brand/605.jpg"}
     * owner_name : 易伽猫
     * owner_desc :
     * is_daren : 0
     * headimg : http://imgs-qn.iliangcang.com/ware/brand/605.jpg
     * rec_reason : 1. 进口天然橡胶更加环保安全，可在大自然中完全降解；
     2. 进口舒丽皮历经十几道工序，执行标准更严格，环保达欧盟标准，防滑及抓地性能更强；
     3. 日本进口隔水膜隔水除菌防敏，除使垫子更柔软外，汗液可完全渗透并挥发，不会因长时间储存汗液而产生异味及滋生细菌；
     4. 更专业的防滑：只需一次尝试，颠覆你对防滑的所有认知；
     5. 超强吸水透气性能：表面含聚氨酯吸水曾，充分吸附你的汗水，减少对瑜伽动作的影响，让你的下犬式淋漓尽致！
     6. 保养方式：高级天然橡胶，忌在阳光下暴晒，会氧化分解；联系中如大量出汗，请用白醋、水1：1调配，用干净的毛巾沾湿后轻轻擦拭。
     * is_gift : 0
     * promotion_imgurl : http://imgs-qn.iliangcang.com/ware/promotion/icon/11.jpg
     * discount_price : 719.00
     * promotion_note : 8折闪购-实用又时髦的瑜伽神器都在这里了
     * sku_info : [{"type_id":"7","type_name":"数量","isColor":"0","attrList":[{"attr_id":"0","attr_name":"一只","img_path":""}]},{"type_id":"13","type_name":"种类","isColor":"0","attrList":[{"attr_id":"0","attr_name":"天使粉","img_path":""},{"attr_id":"1","attr_name":"马卡龙蓝","img_path":""},{"attr_id":"2","attr_name":"樱花粉","img_path":""},{"attr_id":"3","attr_name":"高贵紫","img_path":""},{"attr_id":"4","attr_name":"活力绿","img_path":""}]}]
     * good_ship_rule :
     * good_guide : {"title":"购物指南","content":"所有商品均为正品保证。\r\n中国大陆地区免运费，默认商家合作快递。\r\n蜡烛、液态品、手表等含电池产品无法空运，运输时间相较普通空运件会更久。\r\n如出现产品质量问题请在签收后72小时内联系客服。"}
     * category_id : 0
     * able_buy : 1
     * able_buy_note :
     * images_item : ["http://imgs-qn.iliangcang.com/ware/upload/big/2/367/367600.jpg","http://imgs-qn.iliangcang.com/ware/upload/big/2/367/367599.jpg","http://imgs-qn.iliangcang.com/ware/upload/big/2/367/367601.jpg","http://imgs-qn.iliangcang.com/ware/upload/big/2/367/367602.jpg","http://imgs-qn.iliangcang.com/ware/upload/big/2/367/367603.jpg","http://imgs-qn.iliangcang.com/ware/upload/big/2/367/367604.jpg"]
     * is_sold_out : 0
     * sold_out_img_url :
     * sku_inv : [{"goods_sku_sn":"0101000000000000255507000700000013000000000000000000000000000000","type_keys":"7,13","attr_keys":"0,0","price":"899.00","discount_price":"719.00","amount":"80"},{"goods_sku_sn":"0101000000000000255507000700000013000100000000000000000000000000","type_keys":"7,13","attr_keys":"0,1","price":"899.00","discount_price":"719.00","amount":"499"},{"goods_sku_sn":"0101000000000000255507000700000013000200000000000000000000000000","type_keys":"7,13","attr_keys":"0,2","price":"899.00","discount_price":"719.00","amount":"498"},{"goods_sku_sn":"0101000000000000255507000700000013000300000000000000000000000000","type_keys":"7,13","attr_keys":"0,3","price":"899.00","discount_price":"719.00","amount":"79"},{"goods_sku_sn":"0101000000000000255507000700000013000400000000000000000000000000","type_keys":"7,13","attr_keys":"0,4","price":"899.00","discount_price":"719.00","amount":"50"}]
     * goods_info : [{"type":0,"content":{"text":"94.taobao.com/p/rd489200.htm","length":28}},{"type":1,"content":{"img":"https://img.alicdn.com/imgextra/i4/2587257472/TB2yjMrnxlmpuFjSZPfXXc9iXXa_!!2587257472.jpg","width":750,"height":647,"length":90}},{"type":1,"content":{"img":"https://img.alicdn.com/imgextra/i3/2587257472/TB20SRCdCFmpuFjSZFrXXayOXXa_!!2587257472.jpg","width":750,"height":684,"length":90}},{"type":1,"content":{"img":"https://img.alicdn.com/imgextra/i3/2587257472/TB2yGuIcStkpuFjy0FhXXXQzFXa_!!2587257472.jpg","width":750,"height":278,"length":90}},{"type":1,"content":{"img":"https://img.alicdn.com/imgextra/i4/2587257472/TB2t8XDdrBmpuFjSZFuXXaG_XXa_!!2587257472.jpg","width":750,"height":339,"length":90}},{"type":1,"content":{"img":"https://img.alicdn.com/imgextra/i4/2587257472/TB2hx0xdyRnpuFjSZFCXXX2DXXa_!!2587257472.jpg","width":750,"height":372,"length":90}},{"type":1,"content":{"img":"https://img.alicdn.com/imgextra/i1/2587257472/TB2UsXldC4mpuFjSZFOXXaUqpXa_!!2587257472.jpg","width":750,"height":404,"length":90}},{"type":1,"content":{"img":"https://img.alicdn.com/imgextra/i3/2587257472/TB2WSRFdr4npuFjSZFmXXXl4FXa_!!2587257472.jpg","width":750,"height":558,"length":90}},{"type":1,"content":{"img":"https://img.alicdn.com/imgextra/i3/2587257472/TB2Mi8BdC8mpuFjSZFMXXaxpVXa_!!2587257472.jpg","width":750,"height":560,"length":90}},{"type":1,"content":{"img":"https://img.alicdn.com/imgextra/i1/2587257472/TB2qMNydEdnpuFjSZPhXXbChpXa_!!2587257472.jpg","width":750,"height":752,"length":90}},{"type":1,"content":{"img":"https://img.alicdn.com/imgextra/i2/2587257472/TB2WY1ycSBjpuFjy1XdXXaooVXa_!!2587257472.jpg","width":750,"height":715,"length":90}},{"type":1,"content":{"img":"https://img.alicdn.com/imgextra/i2/2587257472/TB21RyKcSxjpuFjSszeXXaeMVXa_!!2587257472.jpg","width":750,"height":596,"length":90}},{"type":1,"content":{"img":"https://img.alicdn.com/imgextra/i2/2587257472/TB2NeWCcRNkpuFjy0FaXXbRCVXa_!!2587257472.jpg","width":750,"height":539,"length":90}},{"type":1,"content":{"img":"https://img.alicdn.com/imgextra/i2/2587257472/TB2k8CKcSxjpuFjSszeXXaeMVXa_!!2587257472.jpg","width":750,"height":741,"length":90}},{"type":1,"content":{"img":"https://img.alicdn.com/imgextra/i1/2587257472/TB28kuzcSXlpuFjy0FeXXcJbFXa_!!2587257472.jpg","width":750,"height":598,"length":90}},{"type":1,"content":{"img":"https://img.alicdn.com/imgextra/i1/2587257472/TB2t3OFcMNlpuFjy0FfXXX3CpXa_!!2587257472.jpg","width":750,"height":818,"length":90}}]
     */

    private String goods_id;
    private String goods_image;
    private String goods_name;
    private String price;
    private String owner_name;//品牌名称
    private String discount_price;//折扣价格
    private List<GoodsDetailBean.DataBean.ItemsBean.SkuInfoBean > sku_info;//选择的种类样式信息
    private int goodsNumber;

    public int getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(int goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_image() {
        return goods_image;
    }

    public void setGoods_image(String goods_image) {
        this.goods_image = goods_image;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(String discount_price) {
        this.discount_price = discount_price;
    }

    public List<GoodsDetailBean.DataBean.ItemsBean.SkuInfoBean > getSku_info() {
        return sku_info;
    }

    public void setSku_info(List<GoodsDetailBean.DataBean.ItemsBean.SkuInfoBean > sku_info) {
        this.sku_info = sku_info;
    }






}
