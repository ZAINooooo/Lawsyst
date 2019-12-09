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
import com.example.lawsyst.Matters_Invoice_Bills_Listing_Pojo;
import com.example.lawsyst.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ravi on 16/11/17.
 */

class MattersBillsListingAdapter extends RecyclerView.Adapter<MattersBillsListingAdapter.MyViewHolder> implements Filterable {
    private Context context;
    private List<Matters_Invoice_Bills_Listing_Pojo> contactList;
    private List<Matters_Invoice_Bills_Listing_Pojo> contactListFiltered;
    private ContactsAdapterListener listener;

    public void setDataList(ArrayList<Matters_Invoice_Bills_Listing_Pojo> contactList) {

        this.contactList = contactList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
//        public TextView name, phone;
//        public ImageView thumbnail;




        TextView bills_reference,mattername,entry_date,due_date,total_amount,tax,status,type;
        public MyViewHolder(View view) {
            super(view);

            mattername = itemView.findViewById(R.id.mattername);
            bills_reference = itemView.findViewById(R.id.invoice_number);
            entry_date = itemView.findViewById(R.id.entry_date);
            due_date = itemView.findViewById(R.id.due_date);
            total_amount = itemView.findViewById(R.id.total_amount);
            tax = itemView.findViewById(R.id.tax);
            status = itemView.findViewById(R.id.status);
            type = itemView.findViewById(R.id.type);




            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(contactListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }


    public MattersBillsListingAdapter(Context context, List<Matters_Invoice_Bills_Listing_Pojo> contactList, ContactsAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.contactList = contactList;
        this.contactListFiltered = contactList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bills_listing_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Matters_Invoice_Bills_Listing_Pojo contact = contactListFiltered.get(position);


        holder.mattername.setText(contact.getMatter_name());
        holder.status.setText(contact.getSTATUS());
        holder.bills_reference.setText(contact.getBillrefno());


        holder.entry_date.setText(contact.getEntrydate());
        holder.due_date.setText(contact.getDue_date());
        holder.total_amount.setText((int) contact.getBilltaxamount());
        holder.tax.setText((int) contact.getBilltaxamount());
        holder.type.setText(contact.getTYPE());


//        Log.d("MatterId" , dataObject.getId());

        if (contact.getMatter_name().equals("")) {
            holder.mattername.setText("N/A");

        } else {
            holder.mattername.setText(contact.getMatter_name());
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
                    List<Matters_Invoice_Bills_Listing_Pojo> filteredList = new ArrayList<>();
                    for (Matters_Invoice_Bills_Listing_Pojo row : contactList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getMatter_name().toLowerCase().contains(charString.toLowerCase()) || row.getSTATUS().contains(charSequence)) {
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
                contactListFiltered = (ArrayList<Matters_Invoice_Bills_Listing_Pojo>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ContactsAdapterListener {
        void onContactSelected(Matters_Invoice_Bills_Listing_Pojo contact);
    }
}
