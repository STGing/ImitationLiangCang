package com.example.pc.imitationliangcang.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.pc.imitationliangcang.bean.GoodsInfo;
import com.example.pc.imitationliangcang.common.MyApplication;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

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

        SQLiteDatabase database = dbHelper.getReadableDatabase();
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(arrayOutputStream);
            objectOutputStream.writeObject(good);
            objectOutputStream.flush();

            byte data[] = arrayOutputStream.toByteArray();
            objectOutputStream.close();
            arrayOutputStream.close();

            Object[] objects = {data};
            database.execSQL("insert into "+DBWord.TABLENAME+ " ( "+DBWord.ID+" , " + DBWord.GOODINFO+" ) values( "+good.getGoods_id()+" ,"+objects+" )");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    //删除一条数据(根据商品ID)
    public void deleteData(String id){
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.delete(DBWord.TABLENAME,DBWord.ID+"=?",new String[]{id});
    }

    //修改一条数据(根据商品ID)
    public void update(String id,GoodsInfo newGood){
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.execSQL("update "+DBWord.TABLENAME+" set "+DBWord.GOODINFO
                + "="+newGood+" where "+DBWord.ID+" = "+id);
    }

    //查询所有数据(数据类型：对象)
    public  List<GoodsInfo> getData(){
        List<GoodsInfo> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from " + DBWord.TABLENAME, null);

        //遍历游标
        if (cursor != null){

            while (cursor.moveToNext()){

                byte[] blob = cursor.getBlob(cursor.getColumnIndex(DBWord.GOODINFO));
                Log.e("TAG","数据库中读取数据，查询到的数组的长度"+blob.length);
                ByteArrayInputStream bais = new ByteArrayInputStream(blob);
                try {
                    ObjectInputStream ois = new ObjectInputStream(bais);
                    GoodsInfo o = (GoodsInfo) ois.readObject();
                    Log.e("TAG","数据库中读取数据，查询到的数据有："+o.getGoods_name());
                    list.add(o);

                    //最后关闭流
                    ois.close();
                    bais.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }

        }

        return list;
    }
}
