package com.example.baseprojectandroid.src.adapter.staft_adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baseprojectandroid.R;
import com.example.baseprojectandroid.models.callback.CallbackStaff;
import com.example.baseprojectandroid.models.staft_model.StaftModel;
import com.example.baseprojectandroid.src.dialog.fragment_dialog_edit.FragmentDialogEditStaff;
import com.example.baseprojectandroid.src.fragment.fragment_staft.FragmentStaft;
import com.example.baseprojectandroid.utils.Constain;

import java.util.ArrayList;

public class StaftAdapter extends RecyclerView.Adapter<StaftAdapter.ViewHolder> {
    private FragmentStaft mContext;
    private int layout;
    private ArrayList<StaftModel>mArrayStaft;

    public StaftAdapter(FragmentStaft mContext, int layout, ArrayList<StaftModel> mArrayStaft) {
        this.mContext = mContext;
        this.layout = layout;
        this.mArrayStaft = mArrayStaft;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext.getContext(),layout,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StaftModel staftModel = mArrayStaft.get(position);
        holder.mAccount.setText(staftModel.getmNameStaft());
        holder.mRole.setText(staftModel.getmRole());
        listenerOnclickedView(holder,position);
    }

    private void listenerOnclickedView(ViewHolder holder, final int position) {
        holder.mImageEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentDialogEditStaff editStaff = new FragmentDialogEditStaff();
                editStaff.show(mContext.getFragmentManager(), Constain.dialogStaffEdit);
                CallbackStaff callbackStaff = editStaff;
                callbackStaff.onGetStaff(mArrayStaft.get(position),position);
            }
        });

        holder.mImageRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.removeStaff(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArrayStaft.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mAccount,mRole;
        private ImageView mImageEdit,mImageRemove;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mAccount = itemView.findViewById(R.id.txt_account);
            mRole = itemView.findViewById(R.id.txt_role);
            mImageEdit = itemView.findViewById(R.id.img_edit_staff);
            mImageRemove = itemView.findViewById(R.id.img_delete);
        }
    }
}
