package com.example.bookmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookmanager.dao.NguoiDungDao;

public class NguoiDungDateilActivity extends AppCompatActivity {
    EditText edFullName, edPhone;
    NguoiDungDao nguoiDungDao;
    String username, fullname, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Chi Tiết Người Dùng");
        setContentView(R.layout.activity_nguoi_dung_dateil);
        edFullName = (EditText) findViewById(R.id.edFullName);
        edPhone = (EditText) findViewById(R.id.edPhone);
        nguoiDungDao = new NguoiDungDao(NguoiDungDateilActivity.this);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        username = b.getString("USERNAME");
        fullname = b.getString("FULLNAME");
        phone = b.getString("PHONE");
        edFullName.setText(fullname);
        edPhone.setText(phone);
    }

    public void updateUser(View view){
        if (nguoiDungDao.updateInfoNguoiDung(username, edPhone.getText().toString(), edFullName.getText().toString()) > 0){
            Toast.makeText(getApplicationContext(), "Lưu thành công", Toast.LENGTH_SHORT).show();
        }
    }

    public void Huy(View view){
        Intent intent = new Intent(NguoiDungDateilActivity.this, ListNguoiDungActivity.class);
        startActivity(intent);
    }
}