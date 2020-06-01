package com.ketan.bizlertest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ketan.bizlertest.datamodel.VehicleDetail;
import com.ketan.bizlertest.viewmodels.VehicleDetailViewModel;
import com.ketan.bizlertest.viewmodels.ViewModelFactory;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class VehicleDetailActivity extends AppCompatActivity implements View.OnClickListener{

    private VehicleDetail mVehicleDetail;
    private VehicleDetailViewModel mVehicleDetailViewModel;

    private TextView number_text,make_text,model_text,varient_text;
    private EditText number_field,make_field,model_field,varient_field;
    private ImageView imageView;
    private FloatingActionButton fab;
    private Button choose_image;

    private String imageuri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_detail);

        mVehicleDetailViewModel = new ViewModelProvider(this, new ViewModelFactory(getApplicationContext())).get(VehicleDetailViewModel.class);
        setUp();
        initViews();
        if(mVehicleDetailViewModel.isEditMode() || mVehicleDetailViewModel.isNewVehicle()){
            setUpEditMode();
        }else{
            setUpViewMode();
        }
    }

    private void initViews(){
        Toolbar t = findViewById(R.id.vehicledetail_toolbar);
        if(mVehicleDetail != null){
            t.setTitle(mVehicleDetail.getVehicle_number());
        }
        setSupportActionBar(t);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        number_text = findViewById(R.id.vehicledetail_number_textview);
        make_text = findViewById(R.id.vehicledetail_make_textview);
        model_text = findViewById(R.id.vehicledetail_model_textview);
        varient_text = findViewById(R.id.vehicledetail_varient_textview);
        number_field = findViewById(R.id.vehicledetail_number_edittext);
        make_field = findViewById(R.id.vehicledetail_make_edittext);
        model_field = findViewById(R.id.vehicledetail_model_edittext);
        varient_field = findViewById(R.id.vehicledetail_varient_edittext);

        imageView = findViewById(R.id.vehicledetail_imageview);
        fab = findViewById(R.id.vehicledetail_fab);
        fab.setOnClickListener(this);
        choose_image = findViewById(R.id.vehicledetail_chooseimage_button);
        choose_image.setOnClickListener(this);
    }

    private void setUp(){
        Intent i = getIntent();
        if(i.hasExtra("vehicledetail")){
            mVehicleDetail = i.getParcelableExtra("vehicledetail");
            imageuri = mVehicleDetail.getVehicle_photo_url();
            mVehicleDetailViewModel.setNewVehicle(false);
            subscribeObserver(mVehicleDetail.getVehicle_number());
        }else{
            mVehicleDetailViewModel.setNewVehicle(true);
        }
    }

    private void setUpEditMode(){
        mVehicleDetailViewModel.setEditMode(true);
        number_text.setVisibility(View.GONE);
        make_text.setVisibility(View.GONE);
        model_text.setVisibility(View.GONE);
        varient_text.setVisibility(View.GONE);

        number_field.setVisibility(View.VISIBLE);
        make_field.setVisibility(View.VISIBLE);
        model_field.setVisibility(View.VISIBLE);
        varient_field.setVisibility(View.VISIBLE);

        choose_image.setVisibility(View.VISIBLE);

        fab.setImageResource(R.drawable.ic_check);
    }

    private void setUpViewMode(){
        mVehicleDetailViewModel.setEditMode(false);
        number_text.setVisibility(View.VISIBLE);
        make_text.setVisibility(View.VISIBLE);
        model_text.setVisibility(View.VISIBLE);
        varient_text.setVisibility(View.VISIBLE);

        number_field.setVisibility(View.GONE);
        make_field.setVisibility(View.GONE);
        model_field.setVisibility(View.GONE);
        varient_field.setVisibility(View.GONE);

        choose_image.setVisibility(View.GONE);

        fab.setImageResource(R.drawable.ic_edit);
    }

    private void setUpData(VehicleDetail vehicleDetail){
        number_text.setText(vehicleDetail.getVehicle_number());
        make_text.setText(vehicleDetail.getVehicle_make());
        model_text.setText(vehicleDetail.getVehicle_model());
        varient_text.setText(vehicleDetail.getVehicle_varient());

        number_field.setText(vehicleDetail.getVehicle_number());
        make_field.setText(vehicleDetail.getVehicle_make());
        model_field.setText(vehicleDetail.getVehicle_model());
        varient_field.setText(vehicleDetail.getVehicle_varient());

        Glide.with(this)
                .setDefaultRequestOptions(new RequestOptions().error(R.drawable.ic_launcher_background))
                .load(vehicleDetail.getVehicle_photo_url())
                .into(imageView);
    }

    private void subscribeObserver(String vehicleNumber){
        mVehicleDetailViewModel.getVehicleDetail(vehicleNumber).observe(this, new Observer<VehicleDetail>() {
            @Override
            public void onChanged(VehicleDetail vehicleDetail) {
                if(vehicleDetail != null){
                    mVehicleDetail = vehicleDetail;
                    setUpData(vehicleDetail);
                }
            }
        });
    }

    private VehicleDetail getDataFromViews(){
        String number = number_field.getText().toString();
        String model = model_field.getText().toString();
        String make = make_field.getText().toString();
        String varient = varient_field.getText().toString();
        if(number.isEmpty() || model.isEmpty() || make.isEmpty() || varient.isEmpty() || imageuri == null || imageuri.isEmpty()){
            return null;
        }
        VehicleDetail data = new VehicleDetail();
        data.setVehicle_number(number);
        data.setVehicle_model(model);
        data.setVehicle_make(make);
        data.setVehicle_varient(varient);
        data.setVehicle_photo_url(imageuri);
        return data;
    }

    private void saveData(){
        VehicleDetail data = getDataFromViews();
        if(data != null){
            /*check wether it is new or update
            * as to call specefic insert or update room method
            * */
            if(mVehicleDetailViewModel.isNewVehicle()){
                mVehicleDetailViewModel.insertData(data);
                subscribeObserver(data.getVehicle_number());
            }else{
                if(!data.getVehicle_number().trim().equals(mVehicleDetail.getVehicle_number())){
                    Toast.makeText(this, "Vehicle Number can not be chnaged", Toast.LENGTH_SHORT).show();
                    return;
                }
                mVehicleDetailViewModel.updateData(data);
            }
            mVehicleDetailViewModel.setNewVehicle(false);
            setUpViewMode();
        }else{
            Toast.makeText(this, "Please input all fields and Picture", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.vehicledetail_fab:{
                if(mVehicleDetailViewModel.isEditMode()){
                    saveData();
                }else{
                    //go to edit mode
                    setUpEditMode();
                }
                break;
            }
            case R.id.vehicledetail_chooseimage_button:{
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1, 1)
                        .setRequestedSize(100,100)
                        .start(this);
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode){
            case CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE: {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if(resultCode == RESULT_OK){

                    // Currently images are stored in cache
                    // use utility.Utilities.saveImageToStorage() method

                    imageuri = result.getUri().toString();

                    imageView.setImageURI(result.getUri());


                }else if(resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                    Toast.makeText(this,result.getError().getMessage(),Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed(){
        if(mVehicleDetailViewModel.canBackPress()){
            super.onBackPressed();
        }else{
            saveData();
        }
    }
}
