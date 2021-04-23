package com.example.bankapp.Activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bankapp.R;

class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private final Context context;
    private final Fragment fragment;


    public CartAdapter(Context context, Fragment fragment) {
        this.context = context;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

//    public void setItems(ArrayList<> items) {
//        this.items = items;
//        calcTotalPrice();
//        notifyDataSetChanged();
//    }

//    public interface DeleteItem {
//        void onDeleteResult(GroceryItem item);
//    }

//    public interface TotalPrice {
//        void getTotalPrice(double price);
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView sentAmount;
        private final TextView toAccNo;
        private final TextView transactionId;
        private final TextView sentDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            sentAmount = itemView.findViewById(R.id.sentAmount);
            toAccNo = itemView.findViewById(R.id.toAccNo);
            transactionId = itemView.findViewById(R.id.transactionId);
            sentDate = itemView.findViewById(R.id.sentDate);
        }
    }
}