package com.heraya.activityintent.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity (tableName = "Kampus")
public class Mahasiswa implements Serializable {
    @PrimaryKey @NonNull
    @ColumnInfo(name = "nim")
    private String nim;
    @ColumnInfo(name = "nama")
    private String nama;
    @ColumnInfo(name = "alamat")
    private String alamat;
    @ColumnInfo(name = "nohp")
    private String nohp;

    public Mahasiswa(@NonNull String nim, String nama, String alamat, String nohp) {
        this.nim = nim;
        this.nama = nama;
        this.alamat = alamat;
        this.nohp = nohp;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }
}
