package com.example.bookmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookmanager.dao.TheLoaiDao;
import com.example.bookmanager.model.TheLoai;

public class TheLoaiActivity extends AppCompatActivity {
    EditText edMaTheLoai, edTenTheLoai, edMota, edViTri;
    Button btnAdd, btnCancel, btnShow;
    TheLoaiDao theLoaiDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_loai);
        setTitle("Thể Loại Truyện");

        edMaTheLoai = (EditText) findViewById(R.id.edMaTheLoai);
        edTenTheLoai = (EditText) findViewById(R.id.edTenTheLoai);
        edMota = (EditText) findViewById(R.id.edMota);
        edViTri = (EditText) findViewById(R.id.edViTri);

        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null){
            edMaTheLoai.setText(b.getString("MATHELOAI"));
            edTenTheLoai.setText(b.getString("TENTHELOAI"));
            edMota.setText(b.getString("MOTA"));
            edViTri.setText(b.getString("VITRI"));
        }
    }

    public void showTheLoai(View view){
        Intent intent = new Intent(TheLoaiActivity.this, ListTheLoaiActivity.class);
        startActivity(intent);
    }

    public void addTheLoai(View view){
        theLoaiDao = new TheLoaiDao(TheLoaiActivity.this);

        try {
            if (validation() < 0){
                Toast.makeText(getApplicationContext(), "Vui lòng không để trống", Toast.LENGTH_SHORT).show();
            }else {
                TheLoai theLoai = new TheLoai(edMaTheLoai.getText().toString(), edTenTheLoai.getText().toString(), edMota.getText().toString(), Integer.parseInt(edViTri.getText().toString()));
                if (theLoaiDao.insertTheLoai(theLoai) > 0){
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception ex){
            Log.e("Error: ", ex.toString());
        }
    }

    public void huyTheLoai(View view){
        Intent intent = new Intent(TheLoaiActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public int validation(){
        int check = 1;
        if (edMaTheLoai.getText().length() == 0 || edTenTheLoai.getText().length() == 0 || edMota.getText().length() == 0 || edViTri.getText().length() == 0){
            check = -1;
        }
        return check;
    }
}