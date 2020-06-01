package com.ketan.bizlertest.room;

import android.os.AsyncTask;

import com.ketan.bizlertest.datamodel.VehicleDetail;
import com.ketan.bizlertest.room.VehicleDetailsDao;

public class InsertAsyncTask extends AsyncTask<VehicleDetail, Void, Void> {

    private VehicleDetailsDao dao;

    public InsertAsyncTask(VehicleDetailsDao dao) {
        this.dao = dao;
    }

    @Override
    protected Void doInBackground(VehicleDetail... notes) {
        dao.insertNotes(notes);
        return null;
    }
}
