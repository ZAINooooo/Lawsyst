package com.example.lawsyst;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

//import com.commonlibrary.logger.Log;

import java.util.ArrayList;

public class MatterDetails extends AppCompatActivity {

ImageView iv_back;
    String matter_name, matter_number, status;
    String matter_id;
    TextView matternames, matternumbers, statuss;
    LinearLayout matter_Details,matter_contacts,matter_communication,time_entries,bills,invoices,matter_ledger,tasks,notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matter_details);

        matternames = (TextView) findViewById(R.id.mattername);
        matternumbers = (TextView) findViewById(R.id.matternumber);

        matter_Details = (LinearLayout) findViewById(R.id.matter_Details);
        matter_contacts = (LinearLayout) findViewById(R.id.matter_contacts);
        matter_communication = (LinearLayout) findViewById(R.id.matter_communication);
        time_entries = (LinearLayout) findViewById(R.id.time_entries);
        bills = (LinearLayout) findViewById(R.id.bills);
        invoices = (LinearLayout) findViewById(R.id.invoices);
        matter_ledger = (LinearLayout) findViewById(R.id.matter_ledger);
        tasks = (LinearLayout) findViewById(R.id.tasks);
        notes = (LinearLayout) findViewById(R.id.notes);



//        lay_add_person= (CardView) findViewById(R.id.lay_add_person);
        matter_name = getIntent().getStringExtra("Matter_Name");
        matter_number = getIntent().getStringExtra("Matter_Number");

        matter_id = getIntent().getStringExtra("MatterId");

        Log.d("MatterId" , ""+matter_id);


        matternames.setText(matter_name);
        matternumbers.setText(matter_number);
//        statuss.setText(status);

        Log.d("Matter_Number", matter_name + matter_number);




        iv_back = (ImageView) findViewById(R.id.iv_back);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

            }
        });



        matter_Details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(MatterDetails.this , Matters_Details.class);
                intent.putExtra("MatterId" , matter_id);
                startActivity(intent);
            }
        });



        matter_contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MatterDetails.this , Matters_Contacts.class);
                startActivity(intent);
            }
        });


        matter_communication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MatterDetails.this , Matters_Communication.class);
                intent.putExtra("MatterId" , matter_id);
                startActivity(intent);
            }
        });



        time_entries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MatterDetails.this , Matters_Time_Entries.class);
                intent.putExtra("MatterId" , matter_id);
                startActivity(intent);
            }
        });



        bills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MatterDetails.this , Matters_Bills.class);
                intent.putExtra("MatterId" , matter_id);
                startActivity(intent);
            }
        });




        invoices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MatterDetails.this , Matters_Invoices.class);
                intent.putExtra("MatterId" , matter_id);
                startActivity(intent);
            }
        });



        matter_ledger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MatterDetails.this , Matters_Ledger.class);
                startActivity(intent);
            }
        });



        tasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MatterDetails.this , Matters_Tasks.class);
                startActivity(intent);
            }
        });




        notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MatterDetails.this , Matters_Notes.class);
                startActivity(intent);
            }
        });
    }
}
