package com.heraya.activityintent;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.heraya.activityintent.adapter.MahasiswaAdapter;
import com.heraya.activityintent.entity.Mahasiswa;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private databaseKampus db;
    private databaseKampus dbinsert;
    private ListView listView;

    private ArrayList<Mahasiswa> daftarMahasiswa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbinsert = Room.databaseBuilder(this,databaseKampus.class,"dbkampus").build();
        db = Room.databaseBuilder(this,databaseKampus.class,"dbkampus").allowMainThreadQueries().build();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Mahasiswa m = new Mahasiswa("17.12.0100","Heraya Fitra","Banjarnegara","0895378036526");
        insertMahasiswa(m);
        listView = findViewById(R.id.lv_daftarMahasiswa);
        daftarMahasiswa = new ArrayList<>();
        daftarMahasiswa.addAll(Arrays.asList(db.mahasiswaDao().ambildaftarMhs()));
        MahasiswaAdapter md = new MahasiswaAdapter(this,R.layout.rows,daftarMahasiswa);
        listView.setAdapter(md);
        md.notifyDataSetChanged();
    }
    @SuppressLint("StaticFieldLeak")
    private void insertMahasiswa(final Mahasiswa m){
        new AsyncTask<Void,Void,Long>(){
            @Override
            protected Long doInBackground(Void... voids) {
                Long status = dbinsert.mahasiswaDao().insertrepMahasiswa(m);
                return status;
            }

            @Override
            protected void onPostExecute(Long status) {
                Log.d("TAG","status :"+status);
            }
        }.execute();
    }

    public void TambahButton(View view) {
        Intent intent = new Intent(this,TambahMahasiswa.class);
        startActivity(intent);
    }
}
