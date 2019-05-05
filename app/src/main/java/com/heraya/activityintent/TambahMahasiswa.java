package com.heraya.activityintent;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.heraya.activityintent.entity.Mahasiswa;

public class TambahMahasiswa extends AppCompatActivity {
    private databaseKampus db;
    EditText etNim, etNama, etAlamat, etNohp;
    String nim, nama, alamat, nohp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_mahasiswa);
        etNim = findViewById(R.id.etNim);
        etNama = findViewById(R.id.etNama);
        etAlamat = findViewById(R.id.etAlamat);
        etNohp = findViewById(R.id.etNoHP);
        db = Room.databaseBuilder(this,databaseKampus.class,"dbkampus").build();
    }

    public void TambahButton(View view) {
        nim = etNim.getText().toString();
        nama = etNama.getText().toString();
        alamat = etAlamat.getText().toString();
        nohp = etNohp.getText().toString();
        if (TextUtils.isEmpty(nim) || TextUtils.isEmpty(nama) || TextUtils.isEmpty(alamat) || TextUtils.isEmpty(nohp)) {
            Toast.makeText(this, "Semua data harus diisi!", Toast.LENGTH_SHORT).show();
        } else {
                Mahasiswa m = new Mahasiswa(nim, nama, alamat, nohp);
                insertMahasiswa(m);
            }
    }

    @SuppressLint("StaticFieldLeak")
    public void insertMahasiswa(final Mahasiswa m) {
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                Long status;
                try {
                    status = db.mahasiswaDao().insertMahasiswa(m);
                }catch (Exception e){
                    status = Long.parseLong("0");
                }
                return status;
            }

            @Override
            protected void onPostExecute(Long status) {
                Log.d("Long status ","Status"+status);
                if(status == 0){
                    Toast.makeText(getApplicationContext(), "Nim sudah ada", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }.execute();
    }
}
