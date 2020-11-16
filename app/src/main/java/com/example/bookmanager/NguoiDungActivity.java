package com.example.bookmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookmanager.dao.NguoiDungDao;
import com.example.bookmanager.model.NguoiDung;

public class NguoiDungActivity extends AppCompatActivity {
    Button btnThemNguoiDung;
    NguoiDungDao nguoiDungDao;
    EditText edUser, edPass, edRePass, edPhone, edFullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Thêm Người Dùng");
        setContentView(R.layout.activity_nguoi_dung);

        btnThemNguoiDung = (Button) findViewById(R.id.btnAddUser);
        edUser = (EditText) findViewById(R.id.edUsername);
        edPass = (EditText) findViewById(R.id.edPassword);
        edPhone = (EditText) findViewById(R.id.edPhone);
        edFullName = (EditText) findViewById(R.id.edFullName);
        edRePass = (EditText) findViewById(R.id.edRePassword);
    }

    public void showUsers(View view){
        Intent intent = new Intent(NguoiDungActivity.this, ListNguoiDungActivity.class);
        startActivity(intent);
    }

    public void addUser(View view){
        nguoiDungDao = new NguoiDungDao(NguoiDungActivity.this);
        NguoiDung user = new NguoiDung(edUser.getText().toString(), edPass.getText().toString(),
                edPhone.getText().toString(), edFullName.getText().toString());

        try {
            if (validateForm() > 0){
                if (nguoiDungDao.insertNguoiDung(user) > 0){
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception ex){
            Log.e("Error: ", ex.toString());
        }
    }

    public int validateForm() {
        int check = 1;
        if (edUser.getText().length() == 0 || edFullName.getText().length() == 0 || edPhone.getText().length() == 0
                || edPass.getText().length() == 0 || edRePass.getText().length() == 0){
            Toast.makeText(getApplicationContext(), "Vui lòng không để trống", Toast.LENGTH_SHORT).show();
            check = -1;
        }else {
            String pass = edPass.getText().toString();
            String rePass = edRePass.getText().toString();
            if (!pass.equals(rePass)){
                Toast.makeText(getApplicationContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }

    public void cancelUser(View view){
        edUser.setText("");
        edPass.setText("");
        edRePass.setText("");
        edPhone.setText("");
        edFullName.setText("");
    }
}