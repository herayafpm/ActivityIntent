package com.heraya.activityintent;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.heraya.activityintent.entity.Mahasiswa;

import java.util.ArrayList;
import java.util.Arrays;

public class EditMahasiswa extends AppCompatActivity {
    TextView tvJudul;
    databaseKampus db;
    ArrayList<Mahasiswa> dataMahasiswa;
    String nim,nama,alamat,nohp;
    EditText etNim,etNama,etAlamat,etNoHP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_mahasiswa);
        Intent intent = getIntent();
        String nim = intent.getStringExtra("nim");
        db = Room.databaseBuilder(this,databaseKampus.class,"dbkampus").allowMainThreadQueries().build();
        dataMahasiswa = new ArrayList<>();
        dataMahasiswa.addAll(Arrays.asList(db.mahasiswaDao().ambildataMhs(nim)));
        etNim = findViewById(R.id.etNim);
        etNama = findViewById(R.id.etNama);
        etAlamat = findViewById(R.id.etAlamat);
        etNoHP = findViewById(R.id.etNoHP);
        etNim = findViewById(R.id.etNim);
        tvJudul = findViewById(R.id.tvJudul);
        tvJudul.setText("Edit Mahasiswa "+dataMahasiswa.get(0).getNama());
        etNim.setText(dataMahasiswa.get(0).getNim());
        etNama.setText(dataMahasiswa.get(0).getNama());
        etAlamat.setText(dataMahasiswa.get(0).getAlamat());
        etNoHP.setText(dataMahasiswa.get(0).getNohp());
    }

    public void SimpanButton(View view) {
        nim = etNim.getText().toString();
        nama = etNama.getText().toString();
        alamat = etAlamat.getText().toString();
        nohp = etNoHP.getText().toString();
        if(TextUtils.isEmpty(nama)||TextUtils.isEmpty(alamat)||TextUtils.isEmpty(nohp)){
            Toast.makeText(getApplicationContext(),"Isi semua data",Toast.LENGTH_SHORT).show();
        }
        else{
            Mahasiswa m = new Mahasiswa(nim,nama,alamat,nohp);
            updateMahasiswa(m);
        }
    }
    @SuppressLint("StaticFieldLeak")
    private void updateMahasiswa(final Mahasiswa m){
        new AsyncTask<Void,Void,Integer>(){
            @Override
            protected Integer doInBackground(Void... voids) {
                db.mahasiswaDao().updateMahasiswa(m.getNim(),m.getNama(),m.getAlamat(),m.getNohp());
                return null;
            }

            @Override
            protected void onPostExecute(Integer aint) {
                Toast.makeText(getApplicationContext(),"Data berhasil diUbah",Toast.LENGTH_SHORT).show();
                finish();
            }
        }.execute();
    }
}
