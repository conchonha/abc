package com.example.baseprojectandroid.src.adapter.food_adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baseprojectandroid.R;
import com.example.baseprojectandroid.models.food_model.FoodModel;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolderFood> {
    private ArrayList<FoodModel>mArrayFood;
    private int mLayout;
    private Context mContext;

    public FoodAdapter(ArrayList<FoodModel> mArrayFood, int mLayout, Context mContext) {
        this.mArrayFood = mArrayFood;
        this.mLayout = mLayout;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolderFood onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext,mLayout,null);
        return new ViewHolderFood(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderFood holder, int position) {
        FoodModel foodModel = mArrayFood.get(position);
        holder.mTxtName.setText(foodModel.getmName());
        holder.mTxtPrice.setText(foodModel.getmPrice()+"");
        holder.mImgPree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = Integer.parseInt(holder.mTxtNumber.getText().toString());
                if(number > 1){
                    --number;
                }
                holder.mTxtNumber.setText(number+"");
            }
        });

        holder.mImgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = Integer.parseInt(holder.mTxtNumber.getText().toString());
                number++;
                holder.mTxtNumber.setText(number+"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArrayFood.size();
    }

    public class ViewHolderFood extends RecyclerView.ViewHolder{
        private TextView mTxtName,mTxtPrice,mTxtNumber;
        private ImageView mImgPree,mImgNext,mImageFood;

        public ViewHolderFood(@NonNull View itemView) {
            super(itemView);
            mTxtName = itemView.findViewById(R.id.txt_name_food);
            mTxtPrice = itemView.findViewById(R.id.txt_price_food);
            mTxtNumber = itemView.findViewById(R.id.txt_number_food);
            mImgPree = itemView.findViewById(R.id.img_pree);
            mImgNext = itemView.findViewById(R.id.img_next);
            mImageFood = itemView.findViewById(R.id.img_food);
        }
    }
}
