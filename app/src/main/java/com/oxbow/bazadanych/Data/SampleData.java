package com.oxbow.bazadanych.Data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kubap on 01.06.2017.
 */

public class SampleData {
    private int id;
    private String name;


    public SampleData() {
        super();
    }

    private SampleData(Parcel in) {
        super();
        this.id = in.readInt();
        this.name = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Data [id=" + id + ", name=" + name +"]";
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SampleData other = (SampleData) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel (Parcel parcel, int flags) {
        parcel.writeInt(getId());
        parcel.writeString(getName());
    }

    public static final Parcelable.Creator<SampleData> CREATOR = new Parcelable.Creator<SampleData>() {
        public SampleData createFromParcel(Parcel in) {
            return new SampleData(in);
        }

        public SampleData[] newArray(int size) {
            return new SampleData[size];
        }

    };
}
