package com.ketan.bizlertest.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ketan.bizlertest.datamodel.VehicleDetail;

@Database(entities = {VehicleDetail.class}, version = 1)
public abstract class VehicleDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "notes-db";

    private static VehicleDatabase instance;

    public static VehicleDatabase getInstance(final Context context){
        if(instance == null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    VehicleDatabase.class,
                    DATABASE_NAME
            ).build();
        }
        return instance;
    }

    public abstract VehicleDetailsDao getVehicleDetailsDao();
}