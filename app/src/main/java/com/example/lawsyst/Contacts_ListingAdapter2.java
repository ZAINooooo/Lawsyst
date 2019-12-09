//package com.example.lawsyst;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
////import com.commonlibrary.logger.Log;
//
//public class Contacts_ListingAdapter2 extends RecyclerView.Adapter<Contacts_ListingAdapter2.ViewHolder>  {
//
//    public ArrayList<Contacts_Listing_Pojo> dataList;
//    public ArrayList<Contacts_Listing_Pojo> dataListFiltered;
//
//    private Context mContext ;
//
//
//
//
//    public class ViewHolder extends RecyclerView.ViewHolder  {
//        TextView contact_person;
//        TextView namelname;
//        TextView mobile_number;
//        TextView country_code;
//        TextView email;
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
//            contact_person = itemView.findViewById(R.id.contact_person);
//            mobile_number = itemView.findViewById(R.id.mobile_number);
//            country_code = itemView.findViewById(R.id.country_code);
//            email = itemView.findViewById(R.id.email);
//
////            country_code_no = itemView.findViewById(R.id.country_code_no);
////            agency_name = itemView.findViewById(R.id.agency_name);
////            contacttypename = itemView.findViewById(R.id.contacttypename);
//
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    Toast.makeText(v.getContext(),"Ouch",Toast.LENGTH_LONG).show();
//                }
//            });
//
//
//
//
////            statusdescription = itemView.findViewById(R.id.statusdescription);
//        }
//    }
//
//    public Contacts_ListingAdapter2(Context mContexts) {
//        this.mContext = mContexts;
////        this.mData = mData;
////        this.alterText = alterText;
//    }
//
//
//
//    @NonNull
//    @Override
//    public Contacts_ListingAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.contacts_listing, viewGroup, false);
//        return new ViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull final Contacts_ListingAdapter2.ViewHolder viewHolder, final int i) {
//        final Contacts_Listing_Pojo dataObject = dataList.get(i);
////        GradientDrawable statusCircle = (GradientDrawable) viewHolder.STATUS.getBackground();
////        statusCircle.setColor(dataObject.getStatusColor());
//
//        viewHolder.contact_person.setText(dataObject.getName());
//        viewHolder.mobile_number.setText(dataObject.getMobile_number());
//        viewHolder.country_code.setText(dataObject.getCountry_code_no());
//        viewHolder.email.setText(dataObject.getEmail());
//
//
//
////        Log.d("MatterId" , dataObject.getId());
//
//        if (dataObject.getName().equals("")) {
//            viewHolder.contact_person.setText("N/A");
//
//        } else {
//            viewHolder.contact_person.setText(dataObject.getName());
//        }
//
//
//
//
//
//
//        if (dataObject.getMobile_number().equals("") ) {
//            viewHolder.mobile_number.setText("N/A");
//
//        } else {
//            viewHolder.mobile_number.setText(dataObject.getMobile_number());
//        }
//
//
//        if (dataObject.getEmail().equals("") ) {
//            viewHolder.email.setText("N/A");
//
//        } else {
//            viewHolder.email.setText(dataObject.getEmail());
//        }
//
//
//        if (dataObject.getCountry_code_no().equals("") ) {
//            viewHolder.country_code.setText("N/A");
//
//        } else {
//            viewHolder.country_code.setText(dataObject.getCountry_code_no());
//        }
//
//
//
//
//
//
//
//    }
//
//
//
//    public void setDataList(ArrayList<Contacts_Listing_Pojo> dataList){
//        this.dataList = dataList;
//    }
//    @Override
//    public int getItemCount() {
//        return dataList.size();
//    }
//
//
//
//
//
//
//}
