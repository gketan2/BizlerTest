package com.ketan.bizlertest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ketan.bizlertest.adapter.OnVehicleListClickListener;
import com.ketan.bizlertest.adapter.VehicleRecyclerAdapter;
import com.ketan.bizlertest.datamodel.VehicleDetail;
import com.ketan.bizlertest.viewmodels.VehicleListViewModel;
import com.ketan.bizlertest.viewmodels.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class VehicleListActivity extends AppCompatActivity implements OnVehicleListClickListener, View.OnClickListener {

    private VehicleListViewModel mVehicleListViewModel;
    private VehicleRecyclerAdapter mAdapter;

    //Views
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFab;
    private TextView noVehicle_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_list);
        mRecyclerView = findViewById(R.id.vehiclelistactivity_recyclerview);
        mFab = findViewById(R.id.fab);
        mFab.setOnClickListener(this);
        noVehicle_textview = findViewById(R.id.novehicle_textview);

        mVehicleListViewModel = new ViewModelProvider(this, new ViewModelFactory(getApplicationContext())).get(VehicleListViewModel.class);

        initRecyclerView();
        subscribeObserver();
        //insertFake();
    }

    private void initRecyclerView(){
        mAdapter = new VehicleRecyclerAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);
    }

    /*Subscribing to live data returned by Room Framework*/
    private void subscribeObserver(){
        mVehicleListViewModel.getVehicleList().observe(this, new Observer<List<VehicleDetail>>() {
            @Override
            public void onChanged(List<VehicleDetail> vehicleDetails) {
                if(vehicleDetails == null || vehicleDetails.size() == 0){
                    // todo for empty list
                    noVehicle_textview.setVisibility(View.VISIBLE);
                }else{
                    mAdapter.setDataList(vehicleDetails);
                    noVehicle_textview.setVisibility(View.GONE);
                }
            }
        });
    }

    /*To insert Fake notes (for testing only)*/
//    private void insertFake(){
//        List<VehicleDetail> fake = new ArrayList<>();
//        for(int i=0;i<20;i++){
//            VehicleDetail v = new VehicleDetail();
//            v.setVehicle_number("Vehicle Number : " + i);
//            fake.add(v);
//        }
//        mAdapter.setDataList(fake);
//    }

    @Override
    public void onListItemClick(int position) {
        Intent i = new Intent(this, VehicleDetailActivity.class);
        i.putExtra("vehicledetail",mAdapter.getItemAt(position));
        startActivity(i);
    }

    private ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            mVehicleListViewModel.deleteVehicleDetail(mAdapter.getItemAt(viewHolder.getAdapterPosition()));
        }
    };

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.fab: {
                Intent i = new Intent(this, VehicleDetailActivity.class);
                startActivity(i);
                break;
            }
            default : break;
        }
    }
}