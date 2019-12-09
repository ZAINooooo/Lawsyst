//package com.example.lawsyst;
//
//import android.content.Context;
//import android.content.Intent;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.CardView;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageButton;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//
////import com.commonlibrary.logger.Log;
//
//public class Time_ListingAdapter2 extends RecyclerView.Adapter<Time_ListingAdapter2.ViewHolder> {
//
//    public ArrayList<Time_Listing_Pojo> dataList;
//
//    private Context mContext ;
//
//    public class ViewHolder extends RecyclerView.ViewHolder  {
//        TextView listing_id;
//        TextView mattername;
//        TextView referenceno;
//        TextView matternumber;
//        TextView hourly_rate,resource;
//        TextView hourlyrate;
//        TextView item,bill_ref;
//        ImageButton edit_tv,delete_tv,add_icon;
//
//        CardView cardview1;
//
//
//
//
//
//
//        TextView statusdescription;
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            mattername = itemView.findViewById(R.id.mattername);
//            hourly_rate = itemView.findViewById(R.id.hourly_rate);
//            resource = itemView.findViewById(R.id.resource);
//            item = itemView.findViewById(R.id.item);
//            bill_ref = itemView.findViewById(R.id.bill_ref);
//
//            edit_tv = itemView.findViewById(R.id.edit_tv);
//            add_icon = itemView.findViewById(R.id.add_icon);
//
//            delete_tv = itemView.findViewById(R.id.delete_tv);
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    Toast.makeText(v.getContext(),"Ouch",Toast.LENGTH_LONG).show();
//                }
//            });
//        }
//    }
//
//    public Time_ListingAdapter2(Context mContexts, ArrayList<Time_Listing_Pojo> dataList) {
//        this.mContext = mContexts;
//        this.dataList = dataList;
////        this.mData = mData;
////        this.alterText = alterText;
//    }
//
//    @NonNull
//    @Override
//    public Time_ListingAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.time_listing_list_item, viewGroup, false);
//        return new ViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull final Time_ListingAdapter2.ViewHolder viewHolder, final int i) {
//        final Time_Listing_Pojo dataObject = dataList.get(i);
////        GradientDrawable statusCircle = (GradientDrawable) viewHolder.STATUS.getBackground();
////        statusCircle.setColor(dataObject.getStatusColor());
//
//
//
////        mattername = itemView.findViewById(R.id.mattername);
////        referenceno = itemView.findViewById(R.id.referenceno);
////        hourly_rate = itemView.findViewById(R.id.hourly_rate);
////        resource = itemView.findViewById(R.id.resource);
////        item = itemView.findViewById(R.id.item);
////        bill_ref = itemView.findViewById(R.id.bill_ref);
//
//
//        viewHolder.mattername.setText(dataObject.getMattername());
//        viewHolder.hourly_rate.setText(dataObject.getHourlyrate());
//        viewHolder.resource.setText(dataObject.getResource());
//        viewHolder.item.setText(dataObject.getItem());
//        viewHolder.bill_ref.setText(dataObject.getBillrefno());
//
//
//        Log.d("MatterId" , dataObject.getId());
//
//
//
//
//        if (dataObject.getMattername().equals("")) {
//            viewHolder.mattername.setText("N/A");
//
//        } else {
//            viewHolder.mattername.setText(dataObject.getMattername());
//        }
//
//
//
//
//
//        if (dataObject.getHourlyrate().equals("")) {
//            viewHolder.hourly_rate.setText("N/A");
//
//        } else {
//            viewHolder.hourly_rate.setText(dataObject.getHourlyrate());
//        }
//
//
//        if (dataObject.getResource().equals("")) {
//            viewHolder.resource.setText("N/A");
//
//        } else {
//            viewHolder.resource.setText(dataObject.getResource());
//        }
//
//
//
//        if (dataObject.getItem().equals("")) {
//            viewHolder.item.setText("N/A");
//
//        } else {
//            viewHolder.item.setText(dataObject.getItem());
//        }
//
//
//        if (dataObject.getBillrefno().equals("")) {
//            viewHolder.bill_ref.setText("N/A");
//
//        } else {
//            viewHolder.bill_ref.setText(dataObject.getBillrefno());
//        }
//
//
//
//
//        viewHolder.edit_tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent =new Intent(mContext , EditTimeEntry.class);
//                intent.putExtra("matterid" , dataObject.getMatterid());
//                intent.putExtra("userid" , dataObject.getId());
//
//                Log.d("MatterIdUserId" , ""+dataObject.getMatterid()            +      "                        "         +      dataObject.getId());
//
//                mContext.startActivity(intent);
//            }
//        });
//
//
//        viewHolder.add_icon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent =new Intent(mContext , AddTimeEntry.class);
//
//                intent.putExtra("matterid" , dataObject.getMatterid());
//                intent.putExtra("userid" , dataObject.getId());
//
//                Log.d("MatterIdUserId2" , ""+dataObject.getMatterid()            +      "                        "         +      dataObject.getId());
//                mContext.startActivity(intent);
//            }
//        });
//
//
//
//
//        viewHolder.delete_tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Delete Api Integrated..!!
//                Toast.makeText(mContext, "Delete Clicked", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//
//
//
////        viewHolder.cardview1.setOnClickListener(new View.OnClickListener() {
////
////            @Override
////            public void onClick(View v)
////            {
////                Toast.makeText(mContext, "Clicked..!!", Toast.LENGTH_SHORT).show();
////
////                Intent intent =new Intent(mContext , MatterDetails.class);
////                intent.putExtra("Matter_Name" , dataList.get(i).getMattername());
////                intent.putExtra("Matter_Number" , dataList.get(i).getMatternumber());
////
////                intent.putExtra("MatterId" , dataList.get(i).getId());
////
//////                intent.putExtra("Status" , dataList.get(i).getSTATUS());
////
////                mContext.startActivity(intent);
////            }
////        });
//    }
//
//
//
//    public void setDataList(ArrayList<Time_Listing_Pojo> dataList){
//        this.dataList = dataList;
//    }
//    @Override
//    public int getItemCount() {
//        return dataList.size();
//    }
//}
