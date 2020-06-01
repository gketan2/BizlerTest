package com.ketan.bizlertest.room;

import android.os.AsyncTask;

import com.ketan.bizlertest.datamodel.VehicleDetail;

public class UpdateAsyncTask extends AsyncTask<VehicleDetail, Void, Void> {

    private VehicleDetailsDao dao;

    public UpdateAsyncTask(VehicleDetailsDao dao) {
        this.dao = dao;
    }

    @Override
    protected Void doInBackground(VehicleDetail... vehicleDetails) {
        dao.update(vehicleDetails[0]);
        System.out.println("update called");
        return null;
    }
}
