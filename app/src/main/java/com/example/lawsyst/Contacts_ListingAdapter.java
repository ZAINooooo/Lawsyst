package com.example.lawsyst;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lawsyst.Bills_Pojo;
import com.example.lawsyst.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ravi on 16/11/17.
 */

class Contacts_ListingAdapter extends RecyclerView.Adapter<Contacts_ListingAdapter.MyViewHolder> implements Filterable {
    private Context context;
    private List<Contacts_Listing_Pojo> contactList;
    private List<Contacts_Listing_Pojo> contactListFiltered;
    private ContactsAdapterListener listener;

    public void setDataList(ArrayList<Contacts_Listing_Pojo> contactList) {

        this.contactList = contactList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
//        public TextView name, phone;
//        public ImageView thumbnail;


        TextView contact_person;
        TextView namelname;
        TextView mobile_number;
        TextView country_code;
        TextView email;



        public MyViewHolder(View view) {
            super(view);
            contact_person = view.findViewById(R.id.contact_person);
            mobile_number = view.findViewById(R.id.mobile_number);
            country_code = view.findViewById(R.id.country_code);
            email = view.findViewById(R.id.email);




            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(contactListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }


    public Contacts_ListingAdapter(Context context, List<Contacts_Listing_Pojo> contactList, ContactsAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.contactList = contactList;
        this.contactListFiltered = contactList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contacts_listing, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Contacts_Listing_Pojo contact = contactListFiltered.get(position);
//        holder.name.setText(contact.getName());
//        holder.phone.setText(contact.getPhone());





        holder.contact_person.setText(contact.getName());
        holder.mobile_number.setText(contact.getMobile_number());
        holder.country_code.setText(contact.getCountry_code_no());
        holder.email.setText(contact.getEmail());



//        Log.d("MatterId" , contact.getId());

        if (contact.getName().equals("")) {
            holder.contact_person.setText("N/A");

        } else {
            holder.contact_person.setText(contact.getName());
        }






        if (contact.getMobile_number().equals("") ) {
            holder.mobile_number.setText("N/A");

        } else {
            holder.mobile_number.setText(contact.getMobile_number());
        }


        if (contact.getEmail().equals("") ) {
            holder.email.setText("N/A");

        } else {
            holder.email.setText(contact.getEmail());
        }


        if (contact.getCountry_code_no().equals("") ) {
            holder.country_code.setText("N/A");

        } else {
            holder.country_code.setText(contact.getCountry_code_no());
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
                    List<Contacts_Listing_Pojo> filteredList = new ArrayList<>();
                    for (Contacts_Listing_Pojo row : contactList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getMobile_number().contains(charSequence)) {
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
                contactListFiltered = (ArrayList<Contacts_Listing_Pojo>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ContactsAdapterListener {
        void onContactSelected(Contacts_Listing_Pojo contact);
    }
}
