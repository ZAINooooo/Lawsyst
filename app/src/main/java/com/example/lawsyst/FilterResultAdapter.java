package com.example.lawsyst;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lawsyst.Matters_Invoice_Bills_Listing_Pojo;
import com.example.lawsyst.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ravi on 16/11/17.
 */

class FilterResultAdapter extends RecyclerView.Adapter<FilterResultAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<FilterResultPojo> contactList;
    private FilterResultAdapter.ContactsAdapterListener listener;


    public void setDataList(ArrayList<FilterResultPojo> contactList) {

        this.contactList = contactList;
    }


    public FilterResultAdapter(Context context, ArrayList<FilterResultPojo> contactList , FilterResultAdapter.ContactsAdapterListener listener) {
        this.context = context;
        this.contactList = contactList;
        this.listener = listener;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.filter_result_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final FilterResultPojo contact = contactList.get(position);


        holder.bill_ref.setText(contact.getClientname());

        Log.d("Client_Name" , ""+contact.getBillto());


        holder.entry_Date.setText(contact.getStatus());
//        holder. bill_total_amount.setText(""+contact.getPrice());


        if (contact.getPrice().equals("null")) {
            holder. bill_total_amount.setText("N/A");

        }


        else if (contact.getPrice().equals("")) {
            holder. bill_total_amount.setText("N/A");

        }

        else {
            holder. bill_total_amount.setText(contact.getPrice());
        }




//        holder.supplier.setText(contact.getPropertytype());

        if (contact.getPropertytype().equals("null")) {
            holder.supplier.setText("N/A");

        }


        else if (contact.getPropertytype().equals("")) {
            holder.supplier.setText("N/A");

        }

        else {
            holder.supplier.setText(contact.getPropertytype());
        }



//        holder.added_by.setText(""+contact.getBillto());


        if (contact.getBillto().equals("null"))
        {
            holder.added_by.setText("N/A");
        }


        else if (contact.getBillto().equals("")) {
            holder.added_by.setText("N/A");

        }

        else {
            holder.added_by.setText(""+contact.getBillto());
        }



//        holder.statuss.setText(contact.getAssignto());


        if (contact.getAssignto().equals("null")) {
            holder.statuss.setText("N/A");

        }


        else if (contact.getAssignto().equals("")) {
            holder.statuss.setText("N/A");

        }

        else {
            holder.statuss.setText(contact.getAssignto());
        }







        if (contact.getClientname().equals("null")) {
            holder.bill_ref.setText("N/A");

        }


        else if (contact.getClientname().equals("")) {
            holder.bill_ref.setText("N/A");

        }

        else {
            holder.bill_ref.setText(contact.getClientname());
        }
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }



    public interface ContactsAdapterListener {
        void onContactSelected(FilterResultPojo contact);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
//        public TextView name, phone;
//        public ImageView thumbnail;


        TextView bill_ref, entry_Date, bill_total_amount, supplier, added_by, statuss;

        public MyViewHolder(View view) {
            super(view);

            bill_ref = itemView.findViewById(R.id.bill_ref);
            entry_Date = itemView.findViewById(R.id.entry_Date);
            bill_total_amount = itemView.findViewById(R.id.bill_total_amount);
            supplier = itemView.findViewById(R.id.supplier);
            added_by = itemView.findViewById(R.id.added_by);
            statuss = itemView.findViewById(R.id.statuss);



        }


    }
}
