package com.ketan.bizlertest.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ketan.bizlertest.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class VehicleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView vehicleNumberTextView;
    CircleImageView circleImageView;
    OnVehicleListClickListener onVehicleListClickListener;

    public VehicleViewHolder(@NonNull View itemView, OnVehicleListClickListener onVehicleListClickListener) {
        super(itemView);
        this.onVehicleListClickListener = onVehicleListClickListener;
        vehicleNumberTextView = itemView.findViewById(R.id.list_item_vehicle_number_textview);
        circleImageView = itemView.findViewById(R.id.list_item_vehicle_image_imageview);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onVehicleListClickListener.onListItemClick(getAdapterPosition());
    }
}
