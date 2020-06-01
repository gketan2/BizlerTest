package com.ketan.bizlertest.datamodel;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "vehicle_details")
public class VehicleDetail implements Parcelable {
    @PrimaryKey
    @ColumnInfo(name = "number")
    @NonNull
    private String vehicle_number;
    @ColumnInfo(name = "make")
    private String vehicle_make;
    @ColumnInfo(name = "model")
    private String vehicle_model;
    @ColumnInfo(name = "varient")
    private String vehicle_varient;
    @ColumnInfo(name = "path")
    private String vehicle_photo_url;


    public VehicleDetail(){
    }

    public String getVehicle_number() {
        return vehicle_number;
    }

    public void setVehicle_number(String vehicle_number) {
        this.vehicle_number = vehicle_number;
    }

    public String getVehicle_make() {
        return vehicle_make;
    }

    public void setVehicle_make(String vehicle_make) {
        this.vehicle_make = vehicle_make;
    }

    public String getVehicle_model() {
        return vehicle_model;
    }

    public void setVehicle_model(String vehicle_model) {
        this.vehicle_model = vehicle_model;
    }

    public String getVehicle_varient() {
        return vehicle_varient;
    }

    public void setVehicle_varient(String vehicle_varient) {
        this.vehicle_varient = vehicle_varient;
    }

    public String getVehicle_photo_url() {
        return vehicle_photo_url;
    }

    public void setVehicle_photo_url(String vehicle_photo_url) {
        this.vehicle_photo_url = vehicle_photo_url;
    }

    protected VehicleDetail(Parcel in) {
        vehicle_number = in.readString();
        vehicle_make = in.readString();
        vehicle_model = in.readString();
        vehicle_varient = in.readString();
        vehicle_photo_url = in.readString();
    }

    public static Creator<VehicleDetail> getCREATOR() {
        return CREATOR;
    }

    public static final Creator<VehicleDetail> CREATOR = new Creator<VehicleDetail>() {
        @Override
        public VehicleDetail createFromParcel(Parcel in) {
            return new VehicleDetail(in);
        }

        @Override
        public VehicleDetail[] newArray(int size) {
            return new VehicleDetail[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(vehicle_number);
        dest.writeString(vehicle_make);
        dest.writeString(vehicle_model);
        dest.writeString(vehicle_varient);
        dest.writeString(vehicle_photo_url);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj == null)
            return false;

        if(obj instanceof VehicleDetail){
            VehicleDetail object = (VehicleDetail) obj;
            if(vehicle_number.equalsIgnoreCase(object.getVehicle_number())){
                return true;
                // todo implement more condition for checking equality of object.
            }
        }
        return false;
    }
}
