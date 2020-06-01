package com.ketan.bizlertest.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.ketan.bizlertest.datamodel.VehicleDetail;
import com.ketan.bizlertest.room.DeleteAsyncTask;
import com.ketan.bizlertest.room.InsertAsyncTask;
import com.ketan.bizlertest.room.UpdateAsyncTask;
import com.ketan.bizlertest.room.VehicleDatabase;

import java.util.List;

public class VehicleDataRepository {

    private VehicleDatabase mNoteDatabase;
    private static VehicleDataRepository instance;

    public static VehicleDataRepository getInstance(Context context) {
        if(instance == null){
            instance = new VehicleDataRepository(context);
        }
        return instance;
    }
    public VehicleDataRepository(Context context) {
        mNoteDatabase = VehicleDatabase.getInstance(context);
    }

    public void insertVehicleDetails(VehicleDetail vehicleDetail){
        new InsertAsyncTask(mNoteDatabase.getVehicleDetailsDao()).execute(vehicleDetail);
    }

    public void updateVehicleDetails(VehicleDetail vehicleDetail){
        new UpdateAsyncTask(mNoteDatabase.getVehicleDetailsDao()).execute(vehicleDetail);
    }

    public LiveData<List<VehicleDetail>> getVehicleList(){
        return mNoteDatabase.getVehicleDetailsDao().getVehicleList();
    }

    public LiveData<VehicleDetail> getVehicleDetail(String vehicleNumber){
        return mNoteDatabase.getVehicleDetailsDao().getVehicleDetail(vehicleNumber);
    }

    public void deleteVehicleDetails(VehicleDetail vehicleDetail){
        new DeleteAsyncTask(mNoteDatabase.getVehicleDetailsDao()).execute(vehicleDetail);
    }
}