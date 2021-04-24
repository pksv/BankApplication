package com.example.bankapp.Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bankapp.Database.LoanData;
import com.example.bankapp.R;

import java.util.ArrayList;

public class LoanAdapter extends RecyclerView.Adapter<LoanAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<LoanData> loanDataList;


    public LoanAdapter(Context context, ArrayList<LoanData> loanDataList) {
        this.context = context;
        this.loanDataList = loanDataList;
    }

    @NonNull
    @Override
    public LoanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loan_item, parent, false);
        return new LoanAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoanAdapter.ViewHolder holder, final int position) {
        if (loanDataList == null) {
            holder.recyclerView.setVisibility(View.GONE);
            holder.noLoanHistory.setVisibility(View.VISIBLE);
            return;
        }
        LoanData loanData = loanDataList.get(position);
        if (loanData != null) {
            holder.appAmount.setText(String.valueOf(loanData.getLoanAmt()));
            holder.appDate.setText(String.valueOf(loanData.getApplDate()));
            holder.loanId.setText(loanData.getId());
            holder.loanType.setText(loanData.getLoanType());
            holder.period.setText(String.valueOf(loanData.getPeriod()));
        }
    }

    @Override
    public int getItemCount() {
        return loanDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView appDate;
        private final TextView loanType;
        private final TextView appAmount;
        private final TextView loanId;
        private final TextView period;
        private final TextView noLoanHistory;
        private final RecyclerView recyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            recyclerView = itemView.findViewById(R.id.recyclerView);
            noLoanHistory = itemView.findViewById(R.id.noLoanHistory);
            appDate = itemView.findViewById(R.id.appDate);
            loanType = itemView.findViewById(R.id.loanType);
            appAmount = itemView.findViewById(R.id.appAmount);
            loanId = itemView.findViewById(R.id.loanId);
            period = itemView.findViewById(R.id.period);
        }
    }
}
