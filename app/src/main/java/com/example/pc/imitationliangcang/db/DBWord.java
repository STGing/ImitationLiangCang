package com.example.pc.imitationliangcang.db;

/**
 * 存放数据库建表语句及其他关键字段
 */

/**
 *   CREATE TABLE test2 (
     _id          INTEGER PRIMARY KEY AUTOINCREMENT,
     goodsid      TEXT,
     goodsname    TEXT,
     goodsimage   TEXT,
     goodsprice   TEXT,
     brandname    TEXT,
     discountpric TEXT,
     goodsnumber  TEXT,
     choicesku    TEXT,
     isChecked    BOOLEAN
 );
 */

public class DBWord {

    //表名
    public static final String TABLENAME = "goodsInfo";

    //字段：商品id
    public static final String GOODID= "good_id";

    //字段：商品名称
    public static final String GOODNAME = "good_name";

    //字段：商品image
    public static final String GOODIMAGE = "good_image";

    //字段：商品价格
    public static final String GOODPRICE = "good_price";

    //字段：品牌名称
    public static final String BRANDNAME = "brand_name";

    //字段：打折价格
    public static final String DISCOUNTPRICE = "discount_price";

    //字段：商品数量
    public static final String GOODNUMBER = "good_number";

    //字段：商品sku
    public static final String GOODSKU = "good_sku";




    //1.建立表的语句
    public static final String CREATETABLE = "create table "+TABLENAME
            +" ( _id integer primary key AUTOINCREMENT , "+GOODID+" text , "+ GOODNAME+" text , "
            + GOODIMAGE+" text , "+ GOODPRICE+" text , "
            + BRANDNAME+" text , "+ DISCOUNTPRICE+" text , "
            + GOODNUMBER+" integer , "+ GOODSKU+" text " + ");";

    public static void main(String[] args){

        System.out.println(CREATETABLE);
    }
}
