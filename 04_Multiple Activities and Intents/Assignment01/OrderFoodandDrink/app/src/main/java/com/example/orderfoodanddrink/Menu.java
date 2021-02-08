package com.example.orderfoodanddrink;

import android.os.Parcel;
import android.os.Parcelable;

// Implement Parcelable interface to pass an object to another Activity
public class Menu implements Parcelable {
    private String name;    // variable for menu name
    private double price;   // variable for menu price
    private boolean checked;// variable for menu checked

    public Menu(String name, double price, boolean checked) {
        this.name = name;
        this.price = price;
        this.checked = checked;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    protected Menu(Parcel in) {
        name = in.readString();
        price = in.readDouble();
        checked = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeDouble(price);
        dest.writeByte((byte) (checked ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Menu> CREATOR = new Creator<Menu>() {
        @Override
        public Menu createFromParcel(Parcel in) {
            return new Menu(in);
        }

        @Override
        public Menu[] newArray(int size) {
            return new Menu[size];
        }
    };
}