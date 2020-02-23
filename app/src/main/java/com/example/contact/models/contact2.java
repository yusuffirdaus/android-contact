package com.example.contact.models;

import android.os.Parcel;
import android.os.Parcelable;


public class contact2 implements Parcelable {
    private String id, name,phone;

    public contact2(String id, String name,String phone) {
        this.id= id;
        this.name= name;
        this.phone= phone;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.phone);
    }

    public contact2(){
    }

    protected contact2(Parcel in ){
        this.id= in.readString();
        this.name= in.readString();
        this.phone = in.readString();
    }

    public static final Creator<contact2> CREATOR = new Creator<contact2>(){

        @Override
        public contact2 createFromParcel(Parcel source) {
            return new contact2(source);
        }

        @Override
        public contact2[] newArray(int size) {
            return new contact2[size];
        }
    };
}