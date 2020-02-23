package com.example.contact.models;

import android.os.Parcel;
import android.os.Parcelable;

public class contact implements Parcelable {
private String id;
private String nama;
private String nomor;

    public contact(Parcel in) {
        id = in.readString();
        nama = in.readString();
        nomor = in.readString();
    }

    public static final Creator<contact> CREATOR = new Creator<contact>() {
        @Override
        public contact createFromParcel(Parcel in) {
            return new contact(in);
        }

        @Override
        public contact[] newArray(int size) {
            return new contact[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nama);
        dest.writeString(nomor);
    }

}
