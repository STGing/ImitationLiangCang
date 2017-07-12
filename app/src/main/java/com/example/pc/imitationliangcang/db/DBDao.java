package com.example.pc.imitationliangcang.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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

    private DBHelper dbHelper;

    public DBDao() {

        dbHelper = new DBHelper(MyApplication.getContext());
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
            Object[] objects = {data};
            objectOutputStream.close();
            arrayOutputStream.close();

            database.execSQL("insert into "+DBWord.TABLENAME+ " ( "+DBWord.ID+" , " + DBWord.GOODINFO+" ) values( "+good.getGoods_id()+" ,"+objects+" )");
            database.close();
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
        List<GoodsInfo> list = new ArrayList<GoodsInfo>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from " + DBWord.TABLENAME, null);

        //遍历游标
        if (cursor != null){

            while (cursor.moveToNext()){

                byte[] blob = cursor.getBlob(cursor.getColumnIndex(DBWord.GOODINFO));
                ByteArrayInputStream bais = new ByteArrayInputStream(blob);
                try {
                    ObjectInputStream ois = new ObjectInputStream(bais);
                    GoodsInfo o = (GoodsInfo) ois.readObject();
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
