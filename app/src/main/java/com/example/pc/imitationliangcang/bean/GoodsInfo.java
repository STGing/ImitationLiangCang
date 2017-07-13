package com.example.pc.imitationliangcang.bean;

import java.io.Serializable;

/**
 * 用来保存一个商品的信息，存放至数据库，用于购物车读取要购买的商品信息。
 */

public class GoodsInfo implements Serializable{

    private String goods_id;
    private String goods_image;
    private String goods_name;
    private String price;
    private String brand_name;//品牌名称
    private String discount_price;//折扣价格
    private int goodsNumber;//商品数量
    private String choiceSku;

    public String getChoiceSku() {
        return choiceSku;
    }

    public void setChoiceSku(String choiceSku) {
        this.choiceSku = choiceSku;
    }

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

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String owner_name) {
        this.brand_name = owner_name;
    }

    public String getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(String discount_price) {
        this.discount_price = discount_price;
    }



}
