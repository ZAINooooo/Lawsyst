package com.example.lawsyst;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class MattersCommuncationAdapter extends RecyclerView.Adapter<MattersCommuncationAdapter.ViewHolder> {

    private Context mContext;
    public ArrayList<Matters_Communication_Pojo> dataList;

    ProgressDialog progress;


    public MattersCommuncationAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MattersCommuncationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.matters_communication_list_item, viewGroup, false);
        return new MattersCommuncationAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MattersCommuncationAdapter.ViewHolder viewHolder, int i) {


        final Matters_Communication_Pojo dataObject = dataList.get(i);

        viewHolder.communicationstatus.setText(dataObject.getCommunicationstatus());

        viewHolder.communicationstatus2.setText(dataObject.getDate());

        String path = dataObject.getDirectPath();
        Log.d("Direct_Path", path);

        Log.d("Draft222", dataObject.getCommunicationstatus());



        Uri file = Uri.fromFile(new File(path));
        String fileExt = MimeTypeMap.getFileExtensionFromUrl(file.toString());
        Log.d("Httpath22", fileExt);
//


        if (dataObject.getMattername().equals("") || dataObject.getMattername().length() == 0) {
            viewHolder.matter_name.setText("N/A");
        } else {
            viewHolder.matter_name.setText(dataObject.getMattername());
        }


        if (fileExt.equals("doc")) {
//            viewHolder.thumbnail.setImageResource(R.mipmap.imageicon);

            Glide.with(mContext).load(R.mipmap.word_icon).into(viewHolder.thumbnail);

        } else if (fileExt.equals("docx")) {
//            viewHolder.thumbnail.setImageResource(R.mipmap.imageicon);

            Glide.with(mContext).load(R.mipmap.word_icon).into(viewHolder.thumbnail);

        }





        else if (fileExt.equals("msg")) {
//            viewHolder.thumbnail.setImageResource(R.drawable.aar_ic_rec);

            Glide.with(mContext).load(R.mipmap.imageicon).into(viewHolder.thumbnail);

        } else if (fileExt.equals("txt")) {
//            viewHolder.thumbnail.setImageResource(R.drawable.aar_ic_rec);

            Glide.with(mContext).load(R.mipmap.notepad_icon).into(viewHolder.thumbnail);

        } else if (fileExt.equals("jpg")) {
//            viewHolder.thumbnail.setImageResource(R.drawable.aar_ic_rec);

            Glide.with(mContext).load(R.mipmap.jpg_icon).into(viewHolder.thumbnail);

        } else if (fileExt.equals("pdf")) {
//            viewHolder.thumbnail.setImageResource(R.drawable.ic_arrow_drop_down);

            Glide.with(mContext).load(R.mipmap.pdf_icon).into(viewHolder.thumbnail);

        } else if (fileExt.equals("Docx")) {
//            viewHolder.thumbnail.setImageResource(R.drawable.aar_ic_pause);
            Glide.with(mContext).load(R.mipmap.word_icon).into(viewHolder.thumbnail);
        } else if (fileExt.equals("wav")) {
//            viewHolder.thumbnail.setImageResource(R.drawable.aar_ic_pause);
            Glide.with(mContext).load(R.mipmap.wav_format).into(viewHolder.thumbnail);
        } else if (fileExt.equals("csv")) {
//            viewHolder.thumbnail.setImageResource(R.drawable.aar_ic_pause);
            Glide.with(mContext).load(R.mipmap.csv_format).into(viewHolder.thumbnail);
        } else if (fileExt.equals("rar")) {
//            viewHolder.thumbnail.setImageResource(R.drawable.aar_ic_pause);

            Glide.with(mContext).load(R.mipmap.rar_icon).into(viewHolder.thumbnail);

        }  else if (fileExt.equals("png")) {
//            viewHolder.thumbnail.setImageResource(R.drawable.aar_ic_pause);

            Glide.with(mContext).load(R.mipmap.png_icon).into(viewHolder.thumbnail);

        } else if (fileExt.equals("pptx")) {
//            viewHolder.thumbnail.setImageResource(R.drawable.aar_ic_pause);

            Glide.with(mContext).load(R.mipmap.powerpoint_icon).into(viewHolder.thumbnail);

        } else if (fileExt.equals("xlsx")) {
//            viewHolder.thumbnail.setImageResource(R.drawable.aar_ic_pause);


            Glide.with(mContext).load(R.mipmap.excel_icon).into(viewHolder.thumbnail);

        } else {
            Glide.with(mContext).load(R.mipmap.no_images).into(viewHolder.thumbnail);
//            Toast.makeText(mContext, "Here..!!!!", Toast.LENGTH_SHORT).show();
        }







        viewHolder.httpath.setOnClickListener(new View.OnClickListener() {



            @SuppressLint("SetJavaScriptEnabled")
            @Override
            public void onClick(View v) {

                viewHolder.card.setVisibility(View.VISIBLE);
                final ProgressDialog pd = ProgressDialog.show(mContext, "", "Please wait..", true);

                viewHolder.imgChargement.getSettings().setJavaScriptEnabled(true); // enable javascript
                viewHolder.imgChargement.getSettings().setLoadWithOverviewMode(true);
                viewHolder.imgChargement.getSettings().setUseWideViewPort(true);
                viewHolder.imgChargement.getSettings().setBuiltInZoomControls(true);

                viewHolder.imgChargement.setWebViewClient(new WebViewClient() {
                    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
//                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPageStarted(WebView view, String url, Bitmap favicon) {
                        pd.show();
                    }


                    @Override
                    public void onPageFinished(WebView view, String url) {
                        pd.dismiss();

                        String webUrl = viewHolder.imgChargement.getUrl();
                        Log.d("Clicked", webUrl);

                    }

                });

                if (dataObject.getHttppath().equals(""))
                {
//                    viewHolder.httpath.setFocusable(false);
//                    viewHolder.httpath.setClickable(false);

                    viewHolder.imgChargement.loadUrl("http://google.com");

                    pd.dismiss();
                }

                else
                {
                    viewHolder.httpath.setFocusable(false);
                    viewHolder.httpath.setClickable(false);
                    viewHolder.imgChargement.loadUrl(dataObject.getHttppath());

                    pd.dismiss();
                }






        }


        });
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void setDataList(ArrayList<Matters_Communication_Pojo> dataList){
        this.dataList = dataList;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
//        TextView from_name;
//        TextView date_time;
//        TextView receiver_name;
//        TextView comm_type;
//        TextView matter_communication;
//        TextView senderid;
//        TextView inoutward;
//        Button httppath;
        ImageView httpath;
        ProgressDialog progress;
        WebView imgChargement;
TextView communicationstatus2;
        CircularImageView thumbnail;
        TextView emp_id,matter_name,communicationstatus;
FrameLayout card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

//            emp_id = itemView.findViewById(R.id.emp_id);
            matter_name = itemView.findViewById(R.id.matter_name);
            communicationstatus = itemView.findViewById(R.id.communicationstatus);
            communicationstatus2 = itemView.findViewById(R.id.communicationstatus2);

            thumbnail = itemView.findViewById(R.id.thumbnail);

//            receiver_name = itemView.findViewById(R.id.receiver_name);
//            comm_type = itemView.findViewById(R.id.comm_type);
//            matter_communication = itemView.findViewById(R.id.matter_communication);
//            senderid = itemView.findViewById(R.id.senderid);
//            inoutward = itemView.findViewById(R.id.inoutward);

            //            from_name = itemView.findViewById(R.id.from_name);




            httpath = itemView.findViewById(R.id.httpath);

            card = itemView.findViewById(R.id.card);


            imgChargement = itemView.findViewById(R.id.web);


//            statusdescription = itemView.findViewById(R.id.statusdescription);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(v.getContext(),"Ouch",Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}





