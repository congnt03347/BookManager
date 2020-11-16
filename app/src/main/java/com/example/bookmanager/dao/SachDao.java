package com.example.bookmanager.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bookmanager.database.DatabaseHelper;
import com.example.bookmanager.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachDao {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public static final String TABLE_NAME = "Sach";
    public static final String SQL_SACH="CREATE TABLE Sach (maSach text primary key, " +
            "maTheLoai text, tenSach text, tacGia text, NXB text, giaBia double, soLuong number);";
    public static final String TAG = "SachDao";

    public SachDao(Context context){
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //Insert Data
    public int insertSach(Sach s){
        ContentValues values = new ContentValues();
        values.put("maSach",s.getMaSach());
        values.put("maTheLoai", s.getMaTheLoai());
        values.put("tenSach", s.getTenSach());
        values.put("tacGia", s.getTacGia());
        values.put("NXB", s.getNXB());
        values.put("giaBia",s.getGiaBia());
        values.put("soLuong", s.getSoLuong());

        if(checkPrimaryKey(s.getMaSach())){
            int result = db.update(TABLE_NAME, values, "masach=?", new String[]{s.getMaSach()});
            if (result == 0){
                return -1;
            }
        }else {
            try {
                if (db.insert(TABLE_NAME, null, values) == -1){
                    return -1;
                }
            }catch (Exception ex){
                Log.e(TAG, ex.toString());
            }

        }
        return 1;
    }

    //Get All
    public List<Sach> getAllSach(){
        List<Sach> dsSach = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false){
            Sach s = new Sach();
            s.setMaSach(c.getString(0));
            s.setMaTheLoai(c.getString(1));
            s.setTenSach(c.getString(2));
            s.setTacGia(c.getString(3));
            s.setNXB(c.getString(4));
            s.setGiaBia(c.getDouble(5));
            s.setSoLuong(c.getInt(6));
            dsSach.add(s);
            Log.d("//=====",s.toString());
            c.moveToNext();
        }
        c.close();
        return dsSach;
    }

    //Update Data
    public int updateSach(Sach s){
        ContentValues values = new ContentValues();
        values.put("maSach",s.getMaSach());
        values.put("maTheLoai", s.getMaTheLoai());
        values.put("tenSach", s.getTenSach());
        values.put("tacGia", s.getTacGia());
        values.put("NXB", s.getNXB());
        values.put("giaBia",s.getGiaBia());
        values.put("soLuong", s.getSoLuong());
        int result = db.update(TABLE_NAME, values, "maSach=?", new String[]{s.getMaSach()});
        if (result == 0){
            return -1;
        }
        return 1;
    }

    //Delete Data
    public int deleteSachByID(String maSach){
        int result = db.delete(TABLE_NAME, "maSach=?", new String[]{maSach});
        if (result == 0){
            return -1;
        }
        return 1;
    }

    //check primary key

    public boolean checkPrimaryKey(String strPrimaryKey){
        String [] columns = {"masach"};
        String selection = "masach=?";
        String [] selectionArgs= {strPrimaryKey};
        Cursor c = null;
        try {
            c = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
            c.moveToFirst();
            int i = c.getCount();
            c.close();
            if (i <= 0){
                return false;
            }
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    //check book

    public Sach checkBook(String strPrimaryKey){
        Sach s = new Sach();
        String [] columns = {"masach"};
        String selection = "masach=?";
        String [] selectionArgs= {strPrimaryKey};
        Cursor c = null;
        try {
            c = db.query(TABLE_NAME, columns,selection,selectionArgs, null, null, null);
            c.moveToFirst();

            while (c.isAfterLast() == false){
                s.setMaSach(c.getString(0));
                s.setMaTheLoai(c.getString(1));
                s.setTenSach(c.getString(2));
                s.setTacGia(c.getString(3));
                s.setNXB(c.getString(4));
                s.setGiaBia(c.getDouble(5));
                s.setSoLuong(c.getInt(6));
                Log.d("//=====", s.toString());
                break;

            }
            c.close();
            return s;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    //Get All Sach By ID
    public Sach getAllSachByID(String maSach){
        Sach s = null;
        String selection = "masach=?";
        String[] selectionArgs= {maSach};
        Cursor c = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);
        Log.d("getAllSachByID", "===>"+c.getCount());
        c.moveToFirst();
        while (c.isAfterLast() == false){
            s = new Sach();
            s.setMaSach(c.getString(0));
            s.setMaTheLoai(c.getString(1));
            s.setTenSach(c.getString(2));
            s.setTacGia(c.getString(3));
            s.setNXB(c.getString(4));
            s.setGiaBia(c.getDouble(5));
            s.setSoLuong(c.getInt(6));
            break;
        }
        c.close();
        return s;
    }

    //Get All Sach Top 10
    public List<Sach> getSachTop10(String month){
        List<Sach> dsSach = new ArrayList<>();
        if(Integer.parseInt(month) < 10){
            month = "0"+month;
        }
        String sSQL = "SELECT maSach, SUM(soLuong) as soluong FROM HoaDonChiTiet INNER JOIN HoaDon " +
                "ON HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon WHERE strftime('%m',HoaDon.ngayMua) = '"+month+"' " +
                "GROUP BY maSach ORDER BY soluong DESC LIMIT 10";

        Cursor c = db.rawQuery(sSQL, null);
        c.moveToFirst();
        while (c.isAfterLast() == false){
            Log.d("//=====", c.getString(0));
            Sach s = new Sach();

            s.setMaSach(c.getString(0));
            s.setSoLuong(c.getInt(1));
            s.setGiaBia(0);
            s.setMaTheLoai("");
            s.setTenSach("");
            s.setTacGia("");
            s.setNXB("");
            dsSach.add(s);
            c.moveToNext();
        }
        c.close();
        return dsSach;
    }
}
