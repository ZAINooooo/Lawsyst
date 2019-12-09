//package com.example.lawsyst;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
////import com.commonlibrary.logger.Log;
//
//public class InvoiceAdapter2 extends RecyclerView.Adapter<InvoiceAdapter2.ViewHolder> {
//
//    public ArrayList<Invoice_Listing_Pojo> dataList;
//
//    private Context mContext ;
//
//
//    public class ViewHolder extends RecyclerView.ViewHolder  {
//        TextView reference_number;
//        TextView invoice_id;
//        TextView due_date;
//        TextView status;
//        TextView total_amount;
//        TextView supplier,issued_date;
//
//
//        TextView statusdescription;
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//
////            invoice_number = itemView.findViewById(R.id.invoice_number);
//
//            reference_number = itemView.findViewById(R.id.reference_number);
//            issued_date = itemView.findViewById(R.id.entry_Date);
//            due_date = itemView.findViewById(R.id.due_Date);
//            status = itemView.findViewById(R.id.statuss);
//            total_amount = itemView.findViewById(R.id.bill_total_amount);
//
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    Toast.makeText(v.getContext(),"Ouch",Toast.LENGTH_LONG).show();
//                }
//            });
//        }
//    }
//
//    public InvoiceAdapter2(Context mContexts) {
//        this.mContext = mContexts;
////        this.mData = mData;
////        this.alterText = alterText;
//    }
//
//
//
//    @NonNull
//    @Override
//    public InvoiceAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.invoice_listing_item, viewGroup, false);
//        return new ViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull final InvoiceAdapter2.ViewHolder viewHolder, final int i) {
//        final Invoice_Listing_Pojo dataObject = dataList.get(i);
////        GradientDrawable statusCircle = (GradientDrawable) viewHolder.STATUS.getBackground();
////        statusCircle.setColor(dataObject.getStatusColor());
//
//        viewHolder.reference_number.setText(dataObject.getInvoicerefno());
////        viewHolder.invoice_id.setText(dataObject.getInvoiceid());
//        viewHolder.issued_date.setText(dataObject.getIssued_date());
//        viewHolder.due_date.setText(dataObject.getDuedate());
//        viewHolder.status.setText(dataObject.getSTATUS());
//        viewHolder.total_amount.setText(dataObject.getInvoicetotalamount());
//
//
//
//        if (dataObject.getInvoicenum().equals("")) {
//            viewHolder.reference_number.setText("N/A");
//
//        } else {
//            viewHolder.reference_number.setText(dataObject.getInvoicerefno());
//        }
//
//
//
//
//
//
//
//        if (dataObject.getIssued_date().equals("")) {
//            viewHolder.issued_date.setText("N/A");
//
//        } else {
//            viewHolder.issued_date.setText(dataObject.getIssued_date());
//        }
//
//
//        if (dataObject.getDuedate().equals("")) {
//            viewHolder.due_date.setText("N/A");
//
//        } else {
//            viewHolder.due_date.setText(dataObject.getDuedate());
//        }
//
//
//
//
//        if (dataObject.getSTATUS().equals("")) {
//            viewHolder.status.setText("N/A");
//
//        } else {
//            viewHolder.status.setText(dataObject.getSTATUS());
//        }
//
//
//
//
//        if (dataObject.getInvoicetotalamount().equals("")) {
//            viewHolder.total_amount.setText("N/A");
//
//        } else {
//            viewHolder.total_amount.setText(dataObject.getInvoicetotalamount());
//        }
//    }
//
//
//
//    public void setDataList(ArrayList<Invoice_Listing_Pojo> dataList){
//        this.dataList = dataList;
//    }
//    @Override
//    public int getItemCount() {
//        return dataList.size();
//    }
//}
