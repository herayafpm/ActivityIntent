package com.heraya.activityintent.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.heraya.activityintent.entity.Mahasiswa;

@Dao
public interface MahasiswaDao {
    @Query("SELECT * FROM kampus")
    Mahasiswa[] ambildaftarMhs();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertrepMahasiswa(Mahasiswa m);

    @Insert(onConflict = OnConflictStrategy.FAIL)
    Long insertMahasiswa(Mahasiswa m);

    @Query("SELECT * FROM kampus WHERE nim = :nim")
    Mahasiswa[] ambildataMhs(String nim);

    @Query("UPDATE kampus SET nama = :nama,alamat=:alamat,nohp=:nohp WHERE nim=:nim")
    int updateMahasiswa(String nim,String nama,String alamat,String nohp);

    @Query("DELETE FROM kampus WHERE nim=:nim")
    int deleteMahasiswa(String nim);
}
