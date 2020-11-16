package com.example.bookmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    String strUsername, strPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Thư Viện Quản Lý Sách");
        setContentView(R.layout.activity_main);
//        if (checkLoginShap() < 0){
//            Intent i = new Intent(MainActivity.this, LoginActivity.class);
//            startActivity(i);
//        }
    }

//    public int checkLoginShap(){
//        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
//        boolean chk = pref.getBoolean("REMEMBER", false);
//
//        if (chk){
//            strUsername = pref.getString("USERNAME", "");
//            strPassword = pref.getString("PASSWORD", "");
//            return 1;
//        }
//        return -1;
//    }

    public void viewNguoiDung(View view){
        Intent intent = new Intent(MainActivity.this, ListNguoiDungActivity.class);
        startActivity(intent);
    }

    public void viewTheLoai(View view){
        Intent intent = new Intent(MainActivity.this, ListTheLoaiActivity.class);
        startActivity(intent);
    }

    public void viewListBook(View view){
        Intent intent = new Intent(MainActivity.this, ListBookActivity.class);
        startActivity(intent);
    }

    public void viewListHoaDon(View view){
        Intent intent = new Intent(MainActivity.this, ListHoaDonActivity.class);
        startActivity(intent);
    }

    public void viewTopSach(View view){
        Intent intent = new Intent(MainActivity.this, LuotSachBanChayActivity.class);
        startActivity(intent);
    }

    public void viewThongKe(View view){
        Intent intent = new Intent(MainActivity.this, ThongKeActivity.class);
        startActivity(intent);
    }

}