package com.ketan.bizlertest.viewmodels;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private Context context;

    public ViewModelFactory(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if(modelClass.equals(VehicleListViewModel.class)){
            return (T) new VehicleListViewModel(context);
        } else if(modelClass.equals(VehicleDetailViewModel.class)){
            return (T) new VehicleDetailViewModel(context);
        }
        return null;
    }
}
