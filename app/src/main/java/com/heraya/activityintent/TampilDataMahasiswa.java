package com.heraya.activityintent;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.heraya.activityintent.entity.Mahasiswa;

import java.util.ArrayList;
import java.util.Arrays;

public class TampilDataMahasiswa extends AppCompatActivity {
    databaseKampus db;
    ArrayList<Mahasiswa> dataMahasiswa;
    TextView tvJudul,tvNim,tvNama,tvAlamat,tvNoHP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_data_mahasiswa);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        String nim = intent.getStringExtra("nim");
        db = Room.databaseBuilder(this,databaseKampus.class,"dbkampus").allowMainThreadQueries().build();
        dataMahasiswa = new ArrayList<>();
        dataMahasiswa.addAll(Arrays.asList(db.mahasiswaDao().ambildataMhs(nim)));
        tvNim = findViewById(R.id.tvNim);
        tvNama = findViewById(R.id.tvNama);
        tvAlamat = findViewById(R.id.tvAlamat);
        tvNoHP = findViewById(R.id.tvnoHP);
        tvJudul = findViewById(R.id.tvJudul);
        tvJudul.setText("Data Mahasiswa "+dataMahasiswa.get(0).getNama());
        tvNim.setText(dataMahasiswa.get(0).getNim());
        tvNama.setText(dataMahasiswa.get(0).getNama());
        tvAlamat.setText(dataMahasiswa.get(0).getAlamat());
        tvNoHP.setText(dataMahasiswa.get(0).getNohp());
    }

    public void EditButton(View view) {
        Intent intent = new Intent(this,EditMahasiswa.class);
        intent.putExtra("nim",dataMahasiswa.get(0).getNim());
        startActivity(intent);
    }
}
