package com.example.bankapp.Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bankapp.Database.InsuranceData;
import com.example.bankapp.R;

import java.util.ArrayList;

public class InsuranceAdapter extends RecyclerView.Adapter<InsuranceAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<InsuranceData> insuranceDataList;


    public InsuranceAdapter(Context context, ArrayList<InsuranceData> insuranceDataList) {
        this.context = context;
        this.insuranceDataList = insuranceDataList;
    }

    @NonNull
    @Override
    public InsuranceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.insurance_item, parent, false);
        return new InsuranceAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InsuranceAdapter.ViewHolder holder, final int position) {
        if (insuranceDataList == null) {
            holder.recyclerView.setVisibility(View.GONE);
            holder.noInsuranceHistory.setVisibility(View.VISIBLE);
            return;
        }
        InsuranceData insuranceData = insuranceDataList.get(position);
        if (insuranceData != null) {
            holder.recyclerView.setVisibility(View.VISIBLE);
            holder.noInsuranceHistory.setVisibility(View.GONE);
            holder.appAmount.setText(String.valueOf(insuranceData.getInsuranceAmt()));
            holder.appDate.setText(String.valueOf(insuranceData.getApplDate()));
            holder.insuranceId.setText(insuranceData.getId());
            holder.insuranceType.setText(insuranceData.getInsuranceType());
            holder.nominee.setText(insuranceData.getNominee());
        }
    }

    @Override
    public int getItemCount() {
        return insuranceDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView appDate;
        private final TextView insuranceType;
        private final TextView appAmount;
        private final TextView insuranceId;
        private final TextView nominee;
        private final TextView noInsuranceHistory;
        private final RecyclerView recyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            recyclerView = itemView.findViewById(R.id.recyclerView);
            noInsuranceHistory = itemView.findViewById(R.id.noInsuranceHistory);
            appDate = itemView.findViewById(R.id.appDate);
            insuranceType = itemView.findViewById(R.id.insuranceType);
            appAmount = itemView.findViewById(R.id.appAmount);
            insuranceId = itemView.findViewById(R.id.insuranceId);
            nominee = itemView.findViewById(R.id.nominee);
        }
    }
}