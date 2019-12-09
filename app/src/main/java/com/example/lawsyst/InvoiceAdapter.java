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
import com.example.lawsyst.Invoice_Listing_Pojo;
import com.example.lawsyst.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ravi on 16/11/17.
 */

class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.MyViewHolder> implements Filterable {
    private Context context;
    private List<Invoice_Listing_Pojo> contactList;
    private List<Invoice_Listing_Pojo> contactListFiltered;
    private ContactsAdapterListener listener;

    public void setDataList(ArrayList<Invoice_Listing_Pojo> contactList) {

        this.contactList = contactList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
//        public TextView name, phone;
//        public ImageView thumbnail;


                TextView reference_number;
        TextView invoice_id;
        TextView due_date;
        TextView status;
        TextView total_amount;
        TextView supplier,issued_date;


        public MyViewHolder(View view) {
            super(view);;


            reference_number = itemView.findViewById(R.id.reference_number);
            issued_date = itemView.findViewById(R.id.entry_Date);
            due_date = itemView.findViewById(R.id.due_Date);
            status = itemView.findViewById(R.id.statuss);
            total_amount = itemView.findViewById(R.id.bill_total_amount);
            
            
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(contactListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }


    public InvoiceAdapter(Context context, List<Invoice_Listing_Pojo> contactList, ContactsAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.contactList = contactList;
        this.contactListFiltered = contactList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.invoice_listing_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Invoice_Listing_Pojo contact = contactListFiltered.get(position);
//        holder.name.setText(contact.getName());
//        holder.phone.setText(contact.getPhone());





                holder.reference_number.setText(contact.getInvoicerefno());
//        holder.invoice_id.setText(contact.getInvoiceid());
        holder.issued_date.setText(contact.getIssued_date());
        holder.due_date.setText(contact.getDuedate());
        holder.status.setText(contact.getSTATUS());
        holder.total_amount.setText(contact.getInvoicetotalamount());



        if (contact.getInvoicenum().equals("")) {
            holder.reference_number.setText("N/A");

        } else {
            holder.reference_number.setText(contact.getInvoicerefno());
        }







        if (contact.getIssued_date().equals("")) {
            holder.issued_date.setText("N/A");

        } else {
            holder.issued_date.setText(contact.getIssued_date());
        }


        if (contact.getDuedate().equals("")) {
            holder.due_date.setText("N/A");

        } else {
            holder.due_date.setText(contact.getDuedate());
        }




        if (contact.getSTATUS().equals("")) {
            holder.status.setText("N/A");

        } else {
            holder.status.setText(contact.getSTATUS());
        }




        if (contact.getInvoicetotalamount().equals("")) {
            holder.total_amount.setText("N/A");

        } else {
            holder.total_amount.setText(contact.getInvoicetotalamount());
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
                    List<Invoice_Listing_Pojo> filteredList = new ArrayList<>();
                    for (Invoice_Listing_Pojo row : contactList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getInvoicerefno().toLowerCase().contains(charString.toLowerCase()) || row.getSTATUS().contains(charSequence)) {
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
                contactListFiltered = (ArrayList<Invoice_Listing_Pojo>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ContactsAdapterListener {
        void onContactSelected(Invoice_Listing_Pojo contact);
    }
}
