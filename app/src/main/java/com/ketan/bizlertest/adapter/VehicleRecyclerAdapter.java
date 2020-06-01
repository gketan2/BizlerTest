package com.ketan.bizlertest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ketan.bizlertest.R;
import com.ketan.bizlertest.datamodel.VehicleDetail;

import java.util.List;

public class VehicleRecyclerAdapter extends RecyclerView.Adapter<VehicleViewHolder> {

    private List<VehicleDetail> dataList;
    private OnVehicleListClickListener onVehicleListClickListener;
    private RequestOptions requestOptions;

    public VehicleRecyclerAdapter(OnVehicleListClickListener onVehicleListClickListener){
        this.onVehicleListClickListener = onVehicleListClickListener;
        requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background);
    }

    @NonNull
    @Override
    public VehicleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_list_card,parent,false);
        return new VehicleViewHolder(v, onVehicleListClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleViewHolder holder, int position) {
        holder.vehicleNumberTextView.setText(dataList.get(position).getVehicle_number());
        System.out.println(dataList.get(position).getVehicle_photo_url());

        Glide.with(holder.itemView.getContext())
                .setDefaultRequestOptions(requestOptions)
                .load(dataList.get(position).getVehicle_photo_url())
                .into(holder.circleImageView);
    }

    @Override
    public int getItemCount() {
        return dataList != null ? dataList.size() : 0;
    }

    public void setDataList(List<VehicleDetail> dataList){
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public VehicleDetail getItemAt(int position){
        return dataList.get(position);
    }
}
