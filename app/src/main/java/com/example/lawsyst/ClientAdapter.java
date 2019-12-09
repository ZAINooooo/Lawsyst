package com.example.lawsyst;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ravi on 16/11/17.
 */

class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.MyViewHolder> implements Filterable {
    private Context context;

    private List<Client_Listing_Pojo> contactList;
    private List<Client_Listing_Pojo> contactListFiltered;
    private ContactsAdapterListener listener;

    public void setDataList(ArrayList<Client_Listing_Pojo> contactList) {

        this.contactList = contactList;
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
//        public TextView name, phone;
//        public ImageView thumbnail;


        TextView landlordid;
        TextView name;
        TextView CurrencyShortDesc;
        TextView referenceno;
        TextView country;
        TextView landlord_mobile;
        TextView landlord_email;
        TextView company_name;


        public MyViewHolder(View view) {
            super(view);
            name = itemView.findViewById(R.id.name);
            country = itemView.findViewById(R.id.country);
            landlord_mobile = itemView.findViewById(R.id.landlord_mobile);
            landlord_email = itemView.findViewById(R.id.landlord_email);
            company_name = itemView.findViewById(R.id.company_name);



            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(contactListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }


    public ClientAdapter(Context context, List<Client_Listing_Pojo> contactList, ContactsAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.contactList = contactList;
        this.contactListFiltered = contactList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.client_listing_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Client_Listing_Pojo contact = contactListFiltered.get(position);
//        holder.name.setText(contact.getName());
//        holder.phone.setText(contact.getPhone());

        holder.name.setText(contact.getName());
        holder.country.setText(contact.getCountry());
        holder.landlord_mobile.setText(contact.getLandlord_mobile());
        holder.landlord_email.setText(contact.getLandlord_email());
        holder.company_name.setText(contact.getCompany_name());

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
                    List<Client_Listing_Pojo> filteredList = new ArrayList<>();
                    for (Client_Listing_Pojo row : contactList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getCountry().contains(charSequence)) {
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
                contactListFiltered = (ArrayList<Client_Listing_Pojo>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ContactsAdapterListener {
        void onContactSelected(Client_Listing_Pojo contact);
    }
}
