package com.ketan.bizlertest.room;

import android.os.AsyncTask;

import com.ketan.bizlertest.datamodel.VehicleDetail;

public class DeleteAsyncTask extends AsyncTask<VehicleDetail, Void, Void> {

    private VehicleDetailsDao dao;

    public DeleteAsyncTask(VehicleDetailsDao dao) {
        this.dao = dao;
    }

    @Override
    protected Void doInBackground(VehicleDetail... vehicleDetails) {
        dao.delete(vehicleDetails[0]);
        return null;
    }
}
