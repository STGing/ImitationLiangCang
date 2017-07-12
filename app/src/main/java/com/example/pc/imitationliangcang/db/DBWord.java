package com.example.pc.imitationliangcang.db;

/**
 * 存放数据库建表语句及其他关键字段
 */

public class DBWord {

    //表名
    public static final String TABLENAME = "goodsList";

    //字段：保存的对象
    public static final String GOODINFO = "goodsList";

    //字段：id
    public static final String ID = "_id";

    //1.建立表的语句
    public static final String CREATETABLE = "create table "+TABLENAME+" ( "+ID+" text primary key , "+ GOODINFO+" text);";
}
