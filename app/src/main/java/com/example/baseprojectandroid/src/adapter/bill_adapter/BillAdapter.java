package com.example.baseprojectandroid.src.adapter.bill_adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baseprojectandroid.R;
import com.example.baseprojectandroid.models.bill_model.BillModel;

import java.util.ArrayList;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.ViewHolder> {
    private Context mContext;
    private int mLayout;
    private ArrayList<BillModel>mArrayBill;

    public BillAdapter(ArrayList<BillModel> mArrayBill, Context mContext, int mLayout) {
        this.mContext = mContext;
        this.mLayout = mLayout;
        this.mArrayBill = mArrayBill;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, mLayout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BillModel billModel = mArrayBill.get(position);
        holder.mCodeBill.setText(billModel.getmBillCode());
        holder.mDateBill.setText(billModel.getmDate());
        holder.mSumBill.setText(billModel.getmSumBill());

    }

    @Override
    public int getItemCount()    {
        return mArrayBill.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mCodeBill,mDateBill,mSumBill;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCodeBill = itemView.findViewById(R.id.txt_code_bill);
            mDateBill = itemView.findViewById(R.id.txt_date_bill);
            mSumBill = itemView.findViewById(R.id.txt_sum_bill);
        }
    }
}
