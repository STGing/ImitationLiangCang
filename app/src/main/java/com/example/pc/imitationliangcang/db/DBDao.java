package com.example.pc.imitationliangcang.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.example.pc.imitationliangcang.bean.GoodsInfo;
import com.example.pc.imitationliangcang.common.MyApplication;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

/**
 * 操作数据库:增删改查
 */

public class DBDao {

    private static DBDao mDBDao = null;
    private DBHelper dbHelper;


    private DBDao(){
        dbHelper = new DBHelper(MyApplication.getContext());
    }
    public static DBDao getInstance() {
        synchronized (DBDao.class) {
            if (mDBDao == null) {
                mDBDao = new DBDao();
            }
        }
        return mDBDao;
    }


    //增加一条数据(数据类型：对象)
    public void addData(GoodsInfo good){
        if (good != null){
            SQLiteDatabase database = dbHelper.getReadableDatabase();

            ContentValues value = new ContentValues();
            value.put(DBWord.GOODID,good.getGoods_id());
            value.put(DBWord.GOODIMAGE,good.getGoods_image());
            value.put(DBWord.GOODNAME,good.getGoods_name());
            value.put(DBWord.GOODNUMBER,good.getGoodsNumber());
            value.put(DBWord.GOODPRICE,good.getPrice());
            value.put(DBWord.GOODSKU,good.getChoiceSku());
            value.put(DBWord.BRANDNAME,good.getBrand_name());
            value.put(DBWord.DISCOUNTPRICE,good.getDiscount_price());
            database.insert(DBWord.TABLENAME,null,value);
        } else {
            Log.e("TAG","增加数据失败，数据为null");
        }

    }

    //删除一条数据(根据商品ID)
    public void deleteData(String id){
        if (!TextUtils.isEmpty(id)) {
            SQLiteDatabase database = dbHelper.getReadableDatabase();
            database.delete(DBWord.TABLENAME,DBWord.GOODID+"=?",new String[]{id});
        } else {
            Log.e("TAG","删除数据失败，数据为null");
        }
    }

    //修改一条数据(根据商品ID)
    public void update(String id,GoodsInfo newGood){
        if (!TextUtils.isEmpty(id) && newGood != null) {
            SQLiteDatabase database = dbHelper.getReadableDatabase();
            ContentValues value=new ContentValues();
            value.put(DBWord.GOODID,newGood.getGoods_id());
            value.put(DBWord.GOODIMAGE,newGood.getGoods_image());
            value.put(DBWord.GOODNAME,newGood.getGoods_name());
            value.put(DBWord.GOODNUMBER,newGood.getGoodsNumber());
            value.put(DBWord.GOODPRICE,newGood.getPrice());
            value.put(DBWord.GOODSKU,newGood.getChoiceSku());
            value.put(DBWord.BRANDNAME,newGood.getBrand_name());
            value.put(DBWord.DISCOUNTPRICE,newGood.getDiscount_price());
            database.update(DBWord.TABLENAME,value,DBWord.GOODID+"=?",new String[]{id});


        } else {
            Log.e("TAG","修改数据失败，数据为null");
        }
    }

    //查询所有数据(数据类型：对象)
    public Observable<List<GoodsInfo>> getData(){

        return Observable.create(new ObservableOnSubscribe<List<GoodsInfo>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<GoodsInfo>> e) throws Exception {
                SQLiteDatabase database = dbHelper.getReadableDatabase();
                List<GoodsInfo> list = new ArrayList<>();
                Cursor cursor = database.rawQuery("select * from " + DBWord.TABLENAME+";", null);
                while (cursor.moveToNext()){
                    GoodsInfo good = new GoodsInfo();
                    String id = cursor.getString(cursor.getColumnIndex(DBWord.GOODID));
                    //Log.e("TAG","商品信息"+id);
                    good.setGoods_id(id);
                    String goodname = cursor.getString(cursor.getColumnIndex(DBWord.GOODNAME));
                    //Log.e("TAG","商品信息"+goodname);
                    good.setGoods_name(goodname);
                    String image = cursor.getString(cursor.getColumnIndex(DBWord.GOODIMAGE));
                    //Log.e("TAG","商品信息"+image);
                    good.setGoods_image(image);
                    String price = cursor.getString(cursor.getColumnIndex(DBWord.GOODPRICE));
                    //Log.e("TAG","商品信息"+price);
                    good.setPrice(price);
                    String brandname = cursor.getString(cursor.getColumnIndex(DBWord.BRANDNAME));
                   // Log.e("TAG","商品信息"+brandname);
                    good.setBrand_name(brandname);
                    String dispri = cursor.getString(cursor.getColumnIndex(DBWord.DISCOUNTPRICE));
                    //Log.e("TAG","商品信息"+dispri);
                    good.setDiscount_price(dispri);
                    int number = cursor.getInt(cursor.getColumnIndex(DBWord.GOODNUMBER));
                    //Log.e("TAG","商品信息"+number);
                    good.setGoodsNumber(number);
                    String sku = cursor.getString(cursor.getColumnIndex(DBWord.GOODSKU));
                    //Log.e("TAG","商品信息"+sku);
                    good.setChoiceSku(sku);
                    list.add(good);
                    //Log.e("TAG","listsize"+list.size());
                }

                if (list != null && list.size() >0){
                    e.onNext(list);
                    e.onComplete();
                } else {
                    e.onError(new Exception("获取数据失败"));
                }
            }
        });


    }

}
