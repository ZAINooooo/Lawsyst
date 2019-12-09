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
import com.example.lawsyst.Matters_Pojo;
import com.example.lawsyst.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ravi on 16/11/17.
 */

class MattersAdapter extends RecyclerView.Adapter<MattersAdapter.MyViewHolder> implements Filterable {
    private Context context;
    private List<Matters_Pojo> contactList;
    private List<Matters_Pojo> contactListFiltered;
    private ContactsAdapterListener listener;

    public void setDataList(ArrayList<Matters_Pojo> contactList) {

        this.contactList = contactList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
//        public TextView name, phone;
//        public ImageView thumbnail;



        TextView STATUS;
        TextView mattername;
        TextView client;
        TextView matternumber;
        TextView assignto;
        CardView cardview1;
        ImageButton edit_tv,delete_tv;
        TextView statusdescription;



        public MyViewHolder(View view) {
            super(view);




            mattername = itemView.findViewById(R.id.mattername);
            client = itemView.findViewById(R.id.client);
            STATUS = itemView.findViewById(R.id.STATUS);
            matternumber = itemView.findViewById(R.id.matternumber);
            assignto = itemView.findViewById(R.id.assignto);
            cardview1 = itemView.findViewById(R.id.cardview1);

            edit_tv = itemView.findViewById(R.id.edit_tv);
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


    public MattersAdapter(Context context, List<Matters_Pojo> contactList, ContactsAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.contactList = contactList;
        this.contactListFiltered = contactList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.matters_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Matters_Pojo contact = contactListFiltered.get(position);
//        holder.name.setText(contact.getName());
//        holder.phone.setText(contact.getPhone());







        holder.mattername.setText(contact.getMattername());
        holder.STATUS.setText(contact.getSTATUS());
        holder.matternumber.setText(contact.getMatternumber());
        holder.assignto.setText(contact.getAssigntoid());
        holder.client.setText(contact.getClientname());

        Log.d("MatterId" , contact.getId());

        if (contact.getClientname().equals("")) {
            holder.client.setText("N/A");

        } else {
            holder.client.setText(contact.getClientname());
        }





        holder.edit_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                pDialog = Utilss.showSweetLoader(context, SweetAlertDialog.PROGRESS_TYPE, "Getting Data...");
//                Toast.makeText(context, "Edit Clicked", Toast.LENGTH_SHORT).show();

                Intent intent =new Intent(context , EditMatter.class);
                intent.putExtra("Matter_Id" , contactList.get(position).getId());
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



        holder.cardview1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {

                final Matters_Pojo contact2 = contactListFiltered.get(position);
                Intent intent =new Intent(context , MatterDetails.class);
                intent.putExtra("Matter_Name" , contact2.getMattername());
                intent.putExtra("Matter_Number" , contact2.getMatternumber());
                intent.putExtra("MatterId" ,contact2.getId());

                context.startActivity(intent);
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
                    List<Matters_Pojo> filteredList = new ArrayList<>();
                    for (Matters_Pojo row : contactList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getMattername().toLowerCase().contains(charString.toLowerCase()) || row.getClientname().contains(charSequence)) {
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
                contactListFiltered = (ArrayList<Matters_Pojo>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ContactsAdapterListener {
        void onContactSelected(Matters_Pojo contact);
    }
}
