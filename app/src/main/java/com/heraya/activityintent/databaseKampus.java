package com.heraya.activityintent;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.heraya.activityintent.dao.MahasiswaDao;
import com.heraya.activityintent.entity.Mahasiswa;

@Database(entities = {Mahasiswa.class},version = 1)
public abstract class databaseKampus extends RoomDatabase {
    public abstract MahasiswaDao mahasiswaDao();
}
