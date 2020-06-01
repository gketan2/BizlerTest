package com.ketan.bizlertest.room;

import android.os.AsyncTask;

import com.ketan.bizlertest.datamodel.VehicleDetail;

public class InsertAsyncTask extends AsyncTask<VehicleDetail, Void, Void> {

    private VehicleDetailsDao dao;

    public InsertAsyncTask(VehicleDetailsDao dao) {
        this.dao = dao;
    }

    @Override
    protected Void doInBackground(VehicleDetail... vehicleDetails) {
        dao.insertNotes(vehicleDetails[0]);
        return null;
    }
}
