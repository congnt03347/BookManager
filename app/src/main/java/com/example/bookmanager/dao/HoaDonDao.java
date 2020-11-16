package com.example.bookmanager.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bookmanager.database.DatabaseHelper;
import com.example.bookmanager.model.HoaDon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDao {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public static final String TABLE_NAME = "HoaDon";
    public static final String SQL_HOA_DON ="CREATE TABLE HoaDon (mahoadon text PRIMARY KEY, ngayMua date);";
    public static final String TAG = "HoaDonDao";

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public HoaDonDao(Context context){
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //Insert Data

    public int insertHoaDon(HoaDon hd){
        ContentValues values = new ContentValues();
        values.put("mahoadon", hd.getMaHoaDon());
        values.put("ngaymua", sdf.format(hd.getNgayMua()));
        try{
            if (db.insert(TABLE_NAME, null, values) == -1){
                return -1;
            }

        }catch (Exception ex){
            Log.e(TAG, ex.toString());
        }
        return 1;
    }

    //Get All

    public List<HoaDon> getAllHoaDon() throws ParseException{
        List<HoaDon> dsHoaDon = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false){
            HoaDon ee = new HoaDon();
            ee.setMaHoaDon(c.getString(0));
            ee.setNgayMua(sdf.parse(c.getString(1)));
            dsHoaDon.add(ee);
            Log.d("//=====", ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsHoaDon;
    }

    //Update Data
    public int updateHoaDon(HoaDon hd){
        ContentValues values = new ContentValues();
        values.put("mahoadon", hd.getMaHoaDon());
        values.put("ngaymua", hd.getNgayMua().toString());
        int result = db.update(TABLE_NAME, values,"mahoadon=?", new String[]{hd.getMaHoaDon()});
        if (result == 0){
            return -1;
        }
        return 1;
    }

    //Delete Data

    public int deleteHoaDonByID(String mahoadon){
        int result = db.delete(TABLE_NAME, "mahoadon=?", new String[]{mahoadon});
        if(result == 0)
            return -1;
        return 1;
    }
}
