package com.example.lawsyst;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Filter_Result extends BaseActivity implements FilterResultAdapter.ContactsAdapterListener {


    SharedPreferences sharedPreferences;
    String access_token;
    RecyclerView data_list;
    ImageView iv_back;
    TextView alter_txt;
    ArrayList<FilterResultPojo> contactList;
    FilterResultAdapter mAdapter;


    int v0,v1,v2,v3,v4;
    String start_dates,end_dates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter__result);

        contactList = new ArrayList<>();
        sharedPreferences = getSharedPreferences("DATA", MODE_PRIVATE);
        access_token = sharedPreferences.getString("token", "");



        start_dates =getIntent().getStringExtra("start_date");
        end_dates =getIntent().getStringExtra("end_date");


        v0 =getIntent().getIntExtra("status" , 0);
        v1 =getIntent().getIntExtra("Staffid" , 0);
        v2 =getIntent().getIntExtra("Billto" , 0);
        v3 =getIntent().getIntExtra("Law" , 0);
        v4 =getIntent().getIntExtra("Casetype" , 0);



        data_list = findViewById(R.id.data_list);



        mAdapter = new FilterResultAdapter(Filter_Result.this, contactList, this);
        mAdapter.setDataList(contactList);

        alter_txt = findViewById(R.id.tv_altText);



        fetchContacts();




        iv_back = (ImageView) findViewById(R.id.iv_back);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });



    }

    private void fetchContacts()
    {

        pDialog = Utilss.showSweetLoader(Filter_Result.this, SweetAlertDialog.PROGRESS_TYPE, "Fetching Data...");

        RequestQueue queue = Volley.newRequestQueue(Filter_Result.this);
        String url = "https://demo.lawsyst.com/mobile-app/json-call/json_matter_listing.php?start_date ="+start_dates+"&end_date ="+end_dates+"&status ="+v0+"&Staffid="+v1+"&Billto ="+v2+"&Law ="+v3+"&Casetype ="+v4+"&agencylocation_id ="+524368;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Utilss.hideSweetLoader(pDialog);
                            }
                        });

                        Log.d("Json-response10", response);

                        try {
                            JSONObject respone = new JSONObject(response);
                            JSONArray data = respone.getJSONArray("data");

                            if (data.length() == 0)
                            {
                                alter_txt.setVisibility(View.VISIBLE);
                            }


                            else
                            {

                                alter_txt.setVisibility(View.GONE);

                                for (int i = 0; i < data.length(); i++)
                                {
                                    JSONObject jsonObject = new JSONObject(String.valueOf(data.get(i)));
                                    FilterResultPojo dataObject = new FilterResultPojo();

                                    dataObject.setClientname(jsonObject.getString("clientname"));
                                    dataObject.setMatternumber(jsonObject.getString("matternumber"));
                                    dataObject.setOld_referencenum(jsonObject.getString("old_referencenum"));
                                    dataObject.setPropertytype(jsonObject.getString("propertyto"));
                                    dataObject.setBillto(jsonObject.getString("Billto"));
                                    dataObject.setPrice(jsonObject.getString("price"));
                                    dataObject.setAssignto(jsonObject.getString("assignto"));
                                    dataObject.setStatusdescription(jsonObject.getString("statusdescription"));
                                    dataObject.setStatus(jsonObject.getString("status"));
                                    contactList.add(dataObject);
                                }


                            }

                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                            data_list.setLayoutManager(mLayoutManager);
                            data_list.setItemAnimator(new DefaultItemAnimator());
                            data_list.addItemDecoration(new MyDividerItemDecoration(Filter_Result.this, DividerItemDecoration.VERTICAL, 36));
                            data_list.setAdapter(mAdapter);
                        }

                        catch (JSONException e) {
                            e.printStackTrace();

                            Log.d("Exception22222 " ,  ""+e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Utilss.hideSweetLoader(pDialog);
                    }
                });
            }
        }){
            @Override
            public Map getHeaders() {
                HashMap headers = new HashMap();
                headers.put("token", access_token);
                return headers;
            }
        };
        queue.add(stringRequest);
    }






    @Override
    public void onContactSelected(FilterResultPojo contact) {

    }
}
