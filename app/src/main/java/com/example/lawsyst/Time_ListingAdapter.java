package com.example.lawsyst;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
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

import com.android.volley.Response;
import com.bumptech.glide.Glide;
import com.example.lawsyst.Time_Listing_Pojo;
import com.example.lawsyst.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ravi on 16/11/17.
 */

class Time_ListingAdapter extends RecyclerView.Adapter<Time_ListingAdapter.MyViewHolder> implements Filterable {
    private Context context;
    private List<Time_Listing_Pojo> contactList;
    private List<Time_Listing_Pojo> contactListFiltered;
    private ContactsAdapterListener listener;



    public void setDataList(ArrayList<Time_Listing_Pojo> contactList) {

        this.contactList = contactList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
//        public TextView name, phone;
//        public ImageView thumbnail;


        TextView listing_id;
        TextView mattername;
        TextView referenceno;
        TextView matternumber;
        TextView hourly_rate,resource;
        TextView hourlyrate;
        TextView item,bill_ref;
        ImageButton edit_tv,delete_tv,add_icon;

        CardView cardview1;


        public MyViewHolder(View view) {
            super(view);


                        mattername = itemView.findViewById(R.id.mattername);
            hourly_rate = itemView.findViewById(R.id.hourly_rate);
            resource = itemView.findViewById(R.id.resource);
            item = itemView.findViewById(R.id.item);
            bill_ref = itemView.findViewById(R.id.bill_ref);

            edit_tv = itemView.findViewById(R.id.edit_tv);
            add_icon = itemView.findViewById(R.id.add_icon);

            delete_tv = itemView.findViewById(R.id.delete_tv);



            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(contactListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }


    public Time_ListingAdapter(Context context, List<Time_Listing_Pojo> contactList, ContactsAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.contactList = contactList;
        this.contactListFiltered = contactList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.time_listing_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Time_Listing_Pojo contact = contactListFiltered.get(position);
//        holder.name.setText(contact.getName());
//        holder.phone.setText(contact.getPhone());





                holder.mattername.setText(contact.getMattername());
        holder.hourly_rate.setText(contact.getHourlyrate());
        holder.resource.setText(contact.getResource());
        holder.item.setText(contact.getItem());
        holder.bill_ref.setText(contact.getBillrefno());


        Log.d("MatterId" , contact.getId());




        if (contact.getMattername().equals("")) {
            holder.mattername.setText("N/A");

        } else {
            holder.mattername.setText(contact.getMattername());
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



        if (contact.getItem().equals("")) {
            holder.item.setText("N/A");

        } else {
            holder.item.setText(contact.getItem());
        }


        if (contact.getBillrefno().equals("")) {
            holder.bill_ref.setText("N/A");

        } else {
            holder.bill_ref.setText(contact.getBillrefno());
        }




        holder.edit_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(context , EditTimeEntry.class);
                intent.putExtra("matterid" , contact.getMatterid());
                intent.putExtra("userid" , contact.getId());

                Log.d("MatterIdUserId" , ""+contact.getMatterid()            +      "                        "         +      contact.getId());

                context.startActivity(intent);
            }
        });


        holder.add_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(context , AddTimeEntry.class);

                intent.putExtra("matterid" , contact.getMatterid());
                intent.putExtra("userid" , contact.getId());

                Log.d("MatterIdUserId2" , ""+contact.getMatterid()            +      "                        "         +      contact.getId());
                context.startActivity(intent);
            }
        });




        holder.delete_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Delete Api Integrated..!!
                Toast.makeText(context, "Delete Clicked", Toast.LENGTH_SHORT).show();
            }
        });





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
                    List<Time_Listing_Pojo> filteredList = new ArrayList<>();
                    for (Time_Listing_Pojo row : contactList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getMattername().toLowerCase().contains(charString.toLowerCase()) || row.getItem().contains(charSequence)) {
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
                contactListFiltered = (ArrayList<Time_Listing_Pojo>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ContactsAdapterListener {
        void onContactSelected(Time_Listing_Pojo contact);
    }
}
