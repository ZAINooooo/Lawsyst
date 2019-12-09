package com.example.lawsyst;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lawsyst.Matters_Time_Entries_Pojo;
import com.example.lawsyst.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ravi on 16/11/17.
 */

class MattersTimeEntryAdapter extends RecyclerView.Adapter<MattersTimeEntryAdapter.MyViewHolder> implements Filterable {
    private Context context;
    private List<Matters_Time_Entries_Pojo> contactList;
    private List<Matters_Time_Entries_Pojo> contactListFiltered;
    private ContactsAdapterListener listener;

    public void setDataList(ArrayList<Matters_Time_Entries_Pojo> contactList) {

        this.contactList = contactList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
//        public TextView name, phone;
//        public ImageView thumbnail;



        TextView listing_id;
        TextView mattername;
        TextView time_entry_status;
        TextView matternumber;
        TextView hourly_rate;
        TextView resource;
        TextView item,bill_ref;
        CardView cardview1;
        TextView statusdescription;




        public MyViewHolder(View view) {
            super(view);




            //            listing_id = itemView.findViewById(R.id.listing_id);
            mattername = itemView.findViewById(R.id.mattername);


            time_entry_status = itemView.findViewById(R.id.time_entry_status);
            hourly_rate = itemView.findViewById(R.id.hourly_rate);
            resource = itemView.findViewById(R.id.resource);
            bill_ref = itemView.findViewById(R.id.bill_ref);




            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(contactListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }


    public MattersTimeEntryAdapter(Context context, List<Matters_Time_Entries_Pojo> contactList, ContactsAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.contactList = contactList;
        this.contactListFiltered = contactList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.matters_time_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Matters_Time_Entries_Pojo contact = contactListFiltered.get(position);
//        holder.name.setText(contact.getName());
//        holder.phone.setText(contact.getPhone());







        holder.mattername.setText(contact.getMattername());
        holder.time_entry_status.setText(contact.getTimeentry_status());
        holder.hourly_rate.setText(contact.getHourlyrate());
        holder.resource.setText(contact.getResource());
        holder.bill_ref.setText(contact.getBillrefno());

//        viewHolder.item.setText(contact.getItem());


        Log.d("MatterId" , contact.getId());

//        if (contact.getId().equals("")) {
//            viewHolder.listing_id.setText("N/A");
//
//        } else {
//            viewHolder.listing_id.setText(contact.getId());
//        }


        if (contact.getMattername().equals("")) {
            holder.mattername.setText("N/A");

        } else {
            holder.mattername.setText(contact.getMattername());
        }



        if (contact.getTimeentry_status().equals("")) {
            holder.time_entry_status.setText("N/A");

        } else {
            holder.time_entry_status.setText(contact.getTimeentry_status());
        }



        if (contact.getHourlyrate().equals("")) {
            holder.hourly_rate.setText("N/A");

        } else {
            holder.hourly_rate.setText(contact.getHourlyrate());
        }


        if (contact.getResource().equals("")) {
            holder.resource.setText("N/A");

        } else {
            holder.resource.setText(contact.getResource());
        }



//        if (contact.getItem().equals("")) {
//            viewHolder.item.setText("N/A");
//
//        } else {
//            viewHolder.item.setText(contact.getItem());
//        }


        if (contact.getBillrefno().equals("")) {
            holder.bill_ref.setText("N/A");

        } else {
            holder.bill_ref.setText(contact.getBillrefno());
        }








    }






    @Override
    public int getItemCount() {
        return contactListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    contactListFiltered = contactList;
                } else {
                    List<Matters_Time_Entries_Pojo> filteredList = new ArrayList<>();
                    for (Matters_Time_Entries_Pojo row : contactList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getMattername().toLowerCase().contains(charString.toLowerCase()) || row.getBillrefno().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    contactListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = contactListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                contactListFiltered = (ArrayList<Matters_Time_Entries_Pojo>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ContactsAdapterListener {
        void onContactSelected(Matters_Time_Entries_Pojo contact);
    }
}
