package com.seatrend.cd.electricbicyclesalesystem.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.seatrend.cd.electricbicyclesalesystem.entity.CodeEntity;
import com.seatrend.cd.electricbicyclesalesystem.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CodeTableSQLiteUtils {


    //添加数据

    /**
     * "xtlb": "00",
     "dmlb": "1007",
     "dmz": "01",
     "mldh": "01",
     "dmsm1": "大型汽车",
     "mlmc": "大型汽车",
     "dmsm2": "黄底黑字",
     "dmsm3": null,
     "zt": "1",
     "dmsm4": null
     * @param
     */
    public static void insert(List<CodeEntity.DataBean> data){
        CodeTableSQLiteOpenHelper dbHelper = CodeTableSQLiteOpenHelper.getInstance();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        for (CodeEntity.DataBean dataBean: data) {
            cv.clear();
            cv.put("xtlb", StringUtils.isNull(dataBean.getXtlb()));
            cv.put("dmlb", StringUtils.isNull(dataBean.getDmlb()));
            cv.put("dmz", StringUtils.isNull(dataBean.getDmz()));
            cv.put("dmsm1", StringUtils.isNull(dataBean.getDmsm1()));
            cv.put("mlmc", StringUtils.isNull(dataBean.getXtlb()));
            cv.put("dmsm2", StringUtils.isNull(dataBean.getDmsm2()));
            cv.put("dmsm3", StringUtils.isNull(dataBean.getDmsm3()));
            cv.put("zt", StringUtils.isNull(dataBean.getZt()));
            cv.put("dmsm4", StringUtils.isNull(dataBean.getDmsm4()));
            db.insert(CodeTableSQLiteOpenHelper.TABLE_NAME, null, cv);
        }

        db.close();
    }

    /**
     * "xtlb": "00",
     "dmlb": "1007",
     "dmz": "01",
     "mldh": "01",
     "dmsm1": "大型汽车",
     "mlmc": "大型汽车",
     "dmsm2": "黄底黑字",
     "dmsm3": null,
     "zt": "1",
     "dmsm4": null
     * @param
     */
    //查询数据
    public static List<CodeEntity.DataBean> queryByDMLB(String DMLB){
        CodeTableSQLiteOpenHelper dbHelper = CodeTableSQLiteOpenHelper.getInstance();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<CodeEntity.DataBean> list=new ArrayList<>();

        //db.insert(CodeTableSQLiteOpenHelper.TABLE_NAME,null,cv);//"dmlb="+DMLB
        Cursor query = db.query(CodeTableSQLiteOpenHelper.TABLE_NAME, null, "dmlb=?", new String[]{DMLB},null, null, null, null);
        while (query.moveToNext()){

            String dmlb= query.getString(query.getColumnIndex("dmlb"));
            String dmz= query.getString(query.getColumnIndex("dmz"));
            String dmsm1= query.getString(query.getColumnIndex("dmsm1"));
            CodeEntity.DataBean  dataBean=new CodeEntity.DataBean();
            dataBean.setDmlb(dmlb);
            dataBean.setDmz(dmz);
            dataBean.setDmsm1(dmsm1);
            list.add(dataBean);
        }
        db.close();
        query.close();

        return list;

    }
    //查询数据 DMLB 和 dmz 种类查询
    public static List<CodeEntity.DataBean> queryByDmlbAndDmz(String DMLB,String Dmz) {
        CodeTableSQLiteOpenHelper dbHelper = CodeTableSQLiteOpenHelper.getInstance();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<CodeEntity.DataBean> list = new ArrayList<>();

        //db.insert(CodeTableSQLiteOpenHelper.TABLE_NAME,null,cv);
        Cursor query = db.query(CodeTableSQLiteOpenHelper.TABLE_NAME, new String[]{}, "dmlb=? and dmz=?", new String[]{DMLB,Dmz}, null, null, null, null);
        while (query.moveToNext()) {
            String dmlb = query.getString(query.getColumnIndex("dmlb"));
            String dmz = query.getString(query.getColumnIndex("dmz"));
            String dmsm1 = query.getString(query.getColumnIndex("dmsm1"));
            CodeEntity.DataBean dataBean = new CodeEntity.DataBean();
            dataBean.setDmlb(dmlb);
            dataBean.setDmz(dmz);
            dataBean.setDmsm1(dmsm1);
            list.add(dataBean);
        }
        db.close();
        query.close();

        return list;
    }


        public static List<CodeEntity.DataBean> queryAll(){
        CodeTableSQLiteOpenHelper dbHelper = CodeTableSQLiteOpenHelper.getInstance();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<CodeEntity.DataBean> list=new ArrayList<>();

        //db.insert(CodeTableSQLiteOpenHelper.TABLE_NAME,null,cv);
        Cursor query = db.query(CodeTableSQLiteOpenHelper.TABLE_NAME, new String[]{}, null, null, null, null, null, null);
        while (query.moveToNext()){
            String dmlb= query.getString(query.getColumnIndex("dmlb"));
            String dmz= query.getString(query.getColumnIndex("dmz"));
            String dmsm1= query.getString(query.getColumnIndex("dmsm1"));
            CodeEntity.DataBean  dataBean=new CodeEntity.DataBean();
            dataBean.setDmlb(dmlb);
            dataBean.setDmz(dmz);
            dataBean.setDmsm1(dmsm1);
            list.add(dataBean);
        }
        db.close();
        query.close();

        return list;

    }


        //查询字符 是否存在
    public static boolean isExist(Context context,String text){
        CodeTableSQLiteOpenHelper dbHelper = CodeTableSQLiteOpenHelper.getInstance();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //db.insert(CodeTableSQLiteOpenHelper.TABLE_NAME,null,cv);
        Cursor query = db.query(CodeTableSQLiteOpenHelper.TABLE_NAME, null, null, null, null, null, null, null);
        if(query.moveToFirst()){
            String exist= query.getString(query.getColumnIndex("text"));
            if(exist.equals(text) ){
                db.close();
                query.close();
                return true;
            }
        }
        db.close();
        query.close();
        return false;
    }

    public static void deleteAll(){
        CodeTableSQLiteOpenHelper dbHelper = CodeTableSQLiteOpenHelper.getInstance();
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //db.insert(CodeTableSQLiteOpenHelper.TABLE_NAME,null,cv);
      //db.delete(CodeTableSQLiteOpenHelper.TABLE_NAME,)

        String sql=String.format("delete from %s", CodeTableSQLiteOpenHelper.TABLE_NAME);
        db.execSQL(sql);

        db.close();

    }



}
