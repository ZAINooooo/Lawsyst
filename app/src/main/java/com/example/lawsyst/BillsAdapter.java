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

class BillsAdapter extends RecyclerView.Adapter<BillsAdapter.MyViewHolder> implements Filterable {
    private Context context;
    private List<Bills_Pojo> contactList;
    private List<Bills_Pojo> contactListFiltered;
    private ContactsAdapterListener listener;

    public void setDataList(ArrayList<Bills_Pojo> contactList) {

        this.contactList = contactList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
//        public TextView name, phone;
//        public ImageView thumbnail;


        TextView status;
        TextView received_date;
        TextView entry_Date;
        TextView due_Dates,supplier,matter_name,client_id,clients,bill_amount,currency,statuss,added_by,bill_total_amount,bill_ref;


        public MyViewHolder(View view) {
            super(view);
            bill_ref = itemView.findViewById(R.id.bill_ref);
            status = itemView.findViewById(R.id.statuss);
            entry_Date = itemView.findViewById(R.id.entry_Date);
            supplier= itemView.findViewById(R.id.supplier);
//            matter_name= itemView.findViewById(R.id.matter_name);
            statuss= itemView.findViewById(R.id.statuss);
            added_by= itemView.findViewById(R.id.added_by);
            bill_total_amount= itemView.findViewById(R.id.bill_total_amount);



            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(contactListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }


    public BillsAdapter(Context context, List<Bills_Pojo> contactList, ContactsAdapterListener listener) {
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
        final Bills_Pojo contact = contactListFiltered.get(position);
//        holder.name.setText(contact.getName());
//        holder.phone.setText(contact.getPhone());





        holder.bill_ref.setText(contact.getBillreference());
        holder.entry_Date.setText(contact.getEntrydate());
        holder.supplier.setText(contact.getSupplier());
        holder.statuss.setText(contact.getSTATUS());
        holder.added_by.setText(contact.getAddedby());
        holder.bill_total_amount.setText(contact.getBillamount());


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
                    List<Bills_Pojo> filteredList = new ArrayList<>();
                    for (Bills_Pojo row : contactList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getBillreference().toLowerCase().contains(charString.toLowerCase()) || row.getSTATUS().contains(charSequence)) {
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
                contactListFiltered = (ArrayList<Bills_Pojo>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ContactsAdapterListener {
        void onContactSelected(Bills_Pojo contact);
    }
}
