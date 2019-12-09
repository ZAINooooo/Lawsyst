package com.example.lawsyst;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MatterDetailsAdapter extends RecyclerView.Adapter<MatterDetailsAdapter.ViewHolder> {
    public Context mContext ;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView image;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

//            image = itemView.findViewById(R.id.detail_icon);
            name = itemView.findViewById(R.id.detail_name);
        }
    }
    public ArrayList<MatterDetail> matterDetailsList;

    public void  setMatterDetailsList(ArrayList<MatterDetail> matterDetailsList){
        this.matterDetailsList = matterDetailsList;
    }


    public MatterDetailsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MatterDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.matter_details_list_item, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MatterDetailsAdapter.ViewHolder viewHolder, int i) {
        MatterDetail matterDetailsItem = matterDetailsList.get(i);
        viewHolder.name.setText(matterDetailsItem.getName());
//        viewHolder.image.setImageResource(matterDetailsItem.getImage());


        viewHolder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(mContext, "Clicked This", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return matterDetailsList.size();
    }
}
