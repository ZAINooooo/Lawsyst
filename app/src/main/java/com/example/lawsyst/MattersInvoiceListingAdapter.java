package com.example.lawsyst;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

class MattersInvoiceListingAdapter extends RecyclerView.Adapter<MattersInvoiceListingAdapter.ViewHolder> {

    public ArrayList<Matters_Invoice_Matter_Listing_Pojo> dataList;

    private Context mContext ;


    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView invoice_number,mattername,issued_date,due_date,total_amount,assignto,status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mattername = itemView.findViewById(R.id.mattername);
            invoice_number = itemView.findViewById(R.id.invoice_number);
            issued_date = itemView.findViewById(R.id.issued_date);
            due_date = itemView.findViewById(R.id.due_date);
            total_amount = itemView.findViewById(R.id.total_amount);
            assignto = itemView.findViewById(R.id.assignto);
            status = itemView.findViewById(R.id.status);







            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(v.getContext(),"Ouch",Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public MattersInvoiceListingAdapter(Context mContext, ArrayList<Matters_Invoice_Matter_Listing_Pojo> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }


    @NonNull
    @Override
    public MattersInvoiceListingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.matters_invoice_list_item, viewGroup, false);
        return new MattersInvoiceListingAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MattersInvoiceListingAdapter.ViewHolder viewHolder,final int i) {
        final Matters_Invoice_Matter_Listing_Pojo dataObject = dataList.get(i);
//        GradientDrawable statusCircle = (GradientDrawable) viewHolder.STATUS.getBackground();
//        statusCircle.setColor(dataObject.getStatusColor());


        viewHolder.mattername.setText(dataObject.getMattername());
        viewHolder.status.setText(dataObject.getSTATUS());
        viewHolder.invoice_number.setText(dataObject.getInvoicenum());
        viewHolder.issued_date.setText(dataObject.getIssued_date());
        viewHolder.due_date.setText(dataObject.getDuedate());
        viewHolder.total_amount.setText((int) dataObject.getInvoicetotalamount());


//        Log.d("MatterId" , dataObject.getId());

        if (dataObject.getMattername().equals("")) {
            viewHolder.mattername.setText("N/A");

        } else {
            viewHolder.mattername.setText(dataObject.getMattername());
        }







    }





    public void setDataList(ArrayList<Matters_Invoice_Matter_Listing_Pojo> dataList){
        this.dataList = dataList;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
