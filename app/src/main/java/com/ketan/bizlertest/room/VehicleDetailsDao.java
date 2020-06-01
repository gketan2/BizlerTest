package com.ketan.bizlertest.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ketan.bizlertest.datamodel.VehicleDetail;

import java.util.List;

@Dao
public interface VehicleDetailsDao {
    @Insert
    long[] insertNotes(VehicleDetail... notes);

    @Query("SELECT number , path FROM vehicle_details")
    LiveData<List<VehicleDetail>> getVehicleList();

    @Query("SELECT * FROM vehicle_details WHERE number = :vehicleNumber")
    LiveData<VehicleDetail> getVehicleDetail(String vehicleNumber);

    @Delete
    int delete(VehicleDetail vehicleDetail);

    @Update()
    int update(VehicleDetail vehicleDetail);

}
