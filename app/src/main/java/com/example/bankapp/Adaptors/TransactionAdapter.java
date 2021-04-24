package com.example.bankapp.Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bankapp.Database.DataBaseHelper;
import com.example.bankapp.Database.SharedPreferences.LoginPreferences;
import com.example.bankapp.Database.Transaction;
import com.example.bankapp.Database.User;
import com.example.bankapp.R;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<Transaction> transactions;



    public TransactionAdapter(Context context, ArrayList<Transaction> transactions) {
        this.context = context;
        this.transactions = transactions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        User user = DataBaseHelper.getInstance().getUserDAO().findByEmail(LoginPreferences.getInstance().getUserId());
        if (transactions.get(position) == null) {
            holder.recyclerView.setVisibility(View.GONE);
            holder.noTransaction.setVisibility(View.VISIBLE);
        }
        Transaction transaction = transactions.get(position);
        if (transaction != null) {
            holder.recyclerView.setVisibility(View.VISIBLE);
            holder.noTransaction.setVisibility(View.GONE);
            holder.transactionId.setText(transaction.getId());
            holder.sentAmount.setText(String.valueOf(transaction.getAmount()));
            holder.sentDate.setText(String.valueOf(transaction.getCreatedDate()));
            if (transaction.getReceiver().equals(user.getId())) {
                holder.toAccNo.setText(transaction.getSender());
                holder.sentOrReceived.setText("Received");
            } else {
                holder.toAccNo.setText(transaction.getReceiver());
                holder.sentOrReceived.setText("Sent");
            }
        }
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView sentAmount;
        private final TextView toAccNo;
        private final TextView transactionId;
        private final TextView sentDate;
        private final TextView sentOrReceived;
        private final RecyclerView recyclerView;
        private final TextView noTransaction;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            recyclerView = itemView.findViewById(R.id.recyclerView);
            noTransaction = itemView.findViewById(R.id.noTransaction);
            sentAmount = itemView.findViewById(R.id.sentAmount);
            toAccNo = itemView.findViewById(R.id.toAccNo);
            transactionId = itemView.findViewById(R.id.transactionId);
            sentDate = itemView.findViewById(R.id.sentDate);
            sentOrReceived = itemView.findViewById(R.id.sentOrReceived);
        }
    }
}