package com.ketan.bizlertest.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ketan.bizlertest.datamodel.VehicleDetail;
import com.ketan.bizlertest.repository.VehicleDataRepository;

public class VehicleDetailViewModel extends ViewModel {

    private boolean isNewVehicle;
    private boolean isEditMode;
    private VehicleDataRepository mVehicleDataRepository;

    public VehicleDetailViewModel(Context context){
        mVehicleDataRepository = VehicleDataRepository.getInstance(context);
        isNewVehicle = false;
        isEditMode = false;
    }

    public LiveData<VehicleDetail> getVehicleDetail(String vehicleNumber){
        return mVehicleDataRepository.getVehicleDetail(vehicleNumber);
    }

    public void insertData(VehicleDetail data){
        mVehicleDataRepository.insertVehicleDetails(data);
    }

    public void updateData(VehicleDetail data){
        mVehicleDataRepository.updateVehicleDetails(data);
    }

    public boolean isNewVehicle() {
        return isNewVehicle;
    }

    public void setNewVehicle(boolean newVehicle) {
        isNewVehicle = newVehicle;
    }

    public boolean isEditMode() {
        return isEditMode;
    }

    public void setEditMode(boolean editMode) {
        isEditMode = editMode;
    }

    public boolean canBackPress(){
        if(isNewVehicle && isEditMode)
            return true;
        else if(isEditMode)
            return false;
        else
            return true;
    }
}