package com.example.bookmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.bookmanager.dao.HoaDonChiTietDao;

public class ThongKeActivity extends AppCompatActivity {
    TextView tvNgay, tvThang, tvNam;
    HoaDonChiTietDao hoaDonChiTietDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        setTitle("Doanh Thu");
        tvNgay = (TextView) findViewById(R.id.tvThongKeNgay);
        tvThang = (TextView) findViewById(R.id.tvThongKeThang);
        tvNam = (TextView) findViewById(R.id.tvThongKeNam);

        hoaDonChiTietDao = new HoaDonChiTietDao(this);
        tvNgay.setText("Hôm nay:  "+hoaDonChiTietDao.getDoanhThuTheoNgay());
        tvThang.setText("Tháng này:  "+hoaDonChiTietDao.getDoanhThuTheoThang());
        tvNam.setText("Năm nay: "+hoaDonChiTietDao.getDoanhThuTheoNam());
    }
}