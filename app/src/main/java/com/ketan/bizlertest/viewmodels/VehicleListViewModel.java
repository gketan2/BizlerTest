package com.ketan.bizlertest.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ketan.bizlertest.datamodel.VehicleDetail;
import com.ketan.bizlertest.repository.VehicleDataRepository;

import java.util.List;

public class VehicleListViewModel extends ViewModel {

    private VehicleDataRepository mVehicleDataRepository;
    //private boolean isCheckMode;  //select status for multiple delete
    // todo implement multiple delete via multiple select

    public VehicleListViewModel(Context context){
        mVehicleDataRepository = VehicleDataRepository.getInstance(context);
    }

    public LiveData<List<VehicleDetail>> getVehicleList(){
        return mVehicleDataRepository.getVehicleList();
    }

    public void deleteVehicleDetail(VehicleDetail vehicleDetail){
        mVehicleDataRepository.deleteVehicleDetails(vehicleDetail);
    }
}
