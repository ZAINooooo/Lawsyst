package com.example.lawsyst;

import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
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

public class Matters_Time_Entries extends AppCompatActivity implements MattersTimeEntryAdapter.ContactsAdapterListener{


//    public RecyclerView data_list;
//    public MattersTimeEntryAdapter dataListAdapter;
//    public ArrayList<Matters_Time_Entries_Pojo> data_array_list;
//
//    protected SweetAlertDialog pDialog;
//    TextView alter_txt;
//
//    String matter_id;
//    JSONObject respone;
//
    ImageView iv_back;

    public RecyclerView data_list;
    String matter_id;
    public MattersTimeEntryAdapter dataListAdapter;
    public ArrayList<Matters_Time_Entries_Pojo> contactList;
    public ArrayList<Matters_Time_Entries_Pojo> contactList2;
    protected SweetAlertDialog pDialog;
    TextView alter_txt;
    SearchView searchView;
    SharedPreferences sharedPreferences;
    String access_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matters__time__entries);

        sharedPreferences = getSharedPreferences("DATA", MODE_PRIVATE);
        access_token = sharedPreferences.getString("token", "");
        contactList = new ArrayList<>();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        }


        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) findViewById(R.id.search);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);


        data_list = findViewById(R.id.data_list);

        dataListAdapter = new MattersTimeEntryAdapter(getApplicationContext(), contactList, this);
        dataListAdapter.setDataList(contactList);


//        whiteNotificationBar(data_list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        data_list.setLayoutManager(mLayoutManager);
        data_list.setItemAnimator(new DefaultItemAnimator());
        data_list.addItemDecoration(new MyDividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL, 36));
        data_list.setAdapter(dataListAdapter);

        alter_txt = findViewById(R.id.tv_altText);
                matter_id = getIntent().getStringExtra("MatterId");


//        data_array_list = new ArrayList<>();
//        data_list = findViewById(R.id.data_list);
//
//        dataListAdapter = new MattersTimeEntryAdapter(Matters_Time_Entries.this);
//        dataListAdapter.setDataList(data_array_list);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(Matters_Time_Entries.this);
//        data_list.setLayoutManager(mLayoutManager);
//        data_list.setItemAnimator(new DefaultItemAnimator());
//        data_list.setAdapter(dataListAdapter);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(data_list.getContext(), DividerItemDecoration.VERTICAL);
//        data_list.addItemDecoration(dividerItemDecoration);
//
//        alter_txt = findViewById(R.id.tv_altText);
//
//        matter_id = getIntent().getStringExtra("MatterId");
//        Log.d("Matter_Ids", matter_id);
//



        iv_back = (ImageView) findViewById(R.id.iv_back);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

        listLoadTask();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                dataListAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                dataListAdapter.getFilter().filter(query);
                return false;
            }
        });

    }



    public void listLoadTask()
    {
        pDialog = Utilss.showSweetLoader(Matters_Time_Entries.this, SweetAlertDialog.PROGRESS_TYPE, "Fetching Data...");

        RequestQueue queue = Volley.newRequestQueue(Matters_Time_Entries.this);
        String url = "https://demo.lawsyst.com/mobile-app/json-call/json_matter_timeentry_listing.php?matterid="+matter_id;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Matters_Time_Entries.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Utilss.hideSweetLoader(pDialog);
                            }
                        });

                        Log.d("Json-response2", response);

                        try {
                            JSONObject respone = new JSONObject(response);
                            JSONArray data = respone.getJSONArray("data");

                            if (data.length() == 0) {
                                alter_txt.setVisibility(View.VISIBLE);
                            } else {


                                alter_txt.setVisibility(View.GONE);

                                for (int i = 0; i < data.length(); i++) {
                                    JSONObject jsonObject = new JSONObject(String.valueOf(data.get(i)));
                                    Matters_Time_Entries_Pojo dataObject = new Matters_Time_Entries_Pojo();
                                    dataObject.setId(jsonObject.getString("id"));
                                    dataObject.setMatterid(jsonObject.getString("matterid"));
                                    dataObject.setContactid(jsonObject.getString("contactid"));
                                    dataObject.setItemid(jsonObject.getString("itemid"));
                                    dataObject.setUserid(jsonObject.getString("userid"));
                                    dataObject.setTimeentry_status(jsonObject.getString("timeentry_status"));
                                    dataObject.setHourlyrate(jsonObject.getString("rate"));
                                    dataObject.setMattername(jsonObject.getString("mattername"));
                                    dataObject.setResource(jsonObject.getString("resource"));
                                    dataObject.setItem(jsonObject.getString("item"));
                                    dataObject.setBillrefno(jsonObject.getString("billrefno"));

                                    contactList.add(dataObject);
                                    dataListAdapter.notifyDataSetChanged();
                                }
                            }

                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Matters_Time_Entries.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Utilss.hideSweetLoader(pDialog);
                    }
                });
            }
        }){
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("token", access_token);
                return headers;
            }
        };
        queue.add(stringRequest);
    }


    @Override
    public void onContactSelected(Matters_Time_Entries_Pojo contact) {

    }
}
