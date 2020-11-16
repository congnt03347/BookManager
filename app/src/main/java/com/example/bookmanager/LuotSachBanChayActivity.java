package com.example.bookmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bookmanager.adapter.BookAdapter;
import com.example.bookmanager.dao.SachDao;
import com.example.bookmanager.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class LuotSachBanChayActivity extends AppCompatActivity {
    public static List<Sach> dsSach = new ArrayList<>();
    ListView lvBook;
    BookAdapter adapter = null;
    SachDao sachDao;
    EditText edThang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Sách Top 10");
        setContentView(R.layout.activity_luot_sach_ban_chay);
        lvBook = (ListView) findViewById(R.id.lvBookTop);
        edThang = (EditText) findViewById(R.id.edThang);

    }

    public void VIEW_SACH_TOP_10(View view){

        if (Integer.parseInt(edThang.getText().toString()) > 12 || Integer.parseInt(edThang.getText().toString()) < 0 || Integer.parseInt(edThang.getText().toString()) == 0){
            Toast.makeText(getApplicationContext(), "Vui lòng nhập tháng từ 1-12", Toast.LENGTH_SHORT).show();
        } else {
            sachDao = new SachDao(LuotSachBanChayActivity.this);
            dsSach = sachDao.getSachTop10(edThang.getText().toString());
            adapter = new BookAdapter(this, dsSach);
            lvBook.setAdapter(adapter);
            }


    }
}