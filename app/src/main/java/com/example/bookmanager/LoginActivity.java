package com.example.bookmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookmanager.dao.NguoiDungDao;

public class LoginActivity extends AppCompatActivity {
    EditText edUserName, edPassword;
    Button btnLogin, btnCancel;
    CheckBox chkRememberPass;
    NguoiDungDao nguoiDungDao;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Đăng Nhập");
        setContentView(R.layout.activity_login);
        edUserName = (EditText) findViewById(R.id.edUsername);
        edPassword = (EditText) findViewById(R.id.edPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        chkRememberPass = (CheckBox) findViewById(R.id.chkRememberPAss);
        nguoiDungDao = new NguoiDungDao(LoginActivity.this);

        sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        edUserName.setText(sharedPreferences.getString("Username", ""));
        edPassword.setText(sharedPreferences.getString("Password", ""));
        chkRememberPass.setChecked(sharedPreferences.getBoolean("Checked", false));

    }

    public void checkLogin(View v) {

        String strUser = edUserName.getText().toString();
        String strPass = edPassword.getText().toString();
        if (strUser.length() > 0 && strPass.length() > 0){
            if (strUser.equals("admin") && strPass.equals("admin")){
                if (chkRememberPass.isChecked()){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Username", strUser);
                    editor.putString("Password", strPass);
                    editor.putBoolean("Checked", true);
                    editor.commit();
                }else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove("Username");
                    editor.remove("Password");
                    editor.remove("Checked");
                    editor.commit();
                }
            }else {
                Toast.makeText(getApplicationContext(), "Sai tài khoản và mật khẩu", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(), "Vui lòng không bỏ trống", Toast.LENGTH_SHORT).show();

        }

    }
}