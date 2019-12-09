package com.example.lawsyst;

import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Matters_Invoices extends BaseActivity implements SearchView.OnQueryTextListener,SearchView.OnCloseListener {


    public RecyclerView recyclerView;
    public MattersInvoiceListingAdapter dataListAdapter;
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    String matterid;




    public ArrayList<Matters_Invoice_Matter_Listing_Pojo> lstAnime;
    private ArrayList<Matters_Invoice_Matter_Listing_Pojo> lstAnime2;
    private SearchView searchView;
    TextView alter_txt;
    ImageView iv_back;
    SharedPreferences sharedPreferences;
    String access_token;




    protected SweetAlertDialog pDialog;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    android.support.design.widget.FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matters__invoices);

        sharedPreferences = getSharedPreferences("DATA", MODE_PRIVATE);
        access_token = sharedPreferences.getString("token", "");


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() !=null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        }




        lstAnime = new ArrayList<>();
        lstAnime2= new ArrayList<>();

        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        recyclerView = findViewById(R.id.data_list);


        searchView = findViewById(R.id.search);
        searchView.setOnCloseListener(this);
        searchView.setOnQueryTextListener(this);

        matterid = getIntent().getStringExtra("MatterId");
        Log.d("Matter_Iddd" , matterid);

        alter_txt = findViewById(R.id.tv_altText);

        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

        jsonrequest();

        // Set a Refresh Listener for the SwipeRefreshLayout
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh the data
                // Calls setRefreshing(false) when it is finish
                updateOperation();
            }
        });
    }

    private void updateOperation()
    {
        lstAnime2= new ArrayList<>();

        RequestQueue queue = Volley.newRequestQueue(Matters_Invoices.this);
        String url = "https://demo.lawsyst.com/mobile-app/json-call/json_matter_invoice_listing.php?"+"matterid="+matterid;
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

                        Log.d("Json-response1", response);

                        try {
                            JSONObject respone = new JSONObject(response);
                            JSONArray data = respone.getJSONArray("data");

                            if (data.length() == 0)
                            {
                                alter_txt.setVisibility(View.VISIBLE);
                            }

                            else
                            {
                                for (int i = 0; i < data.length(); i++)
                                {
                                    JSONObject jsonObject = new JSONObject(String.valueOf(data.get(i)));
                                    Matters_Invoice_Matter_Listing_Pojo dataObject = new Matters_Invoice_Matter_Listing_Pojo();
                                    dataObject.setInvoicenum(jsonObject.getInt("invoicenum"));
                                    dataObject.setInvoiceid(jsonObject.getInt("invoiceid"));
                                    dataObject.setIssued_date(jsonObject.getString("issued_date"));
                                    dataObject.setDuedate(jsonObject.getString("duedate"));
                                    dataObject.setSTATUS(jsonObject.getString("STATUS"));
                                    dataObject.setInvoicetotalamount(jsonObject.getDouble("invoicetotalamount"));
                                    dataObject.setInvoicerefno(jsonObject.getString("invoicerefno"));
                                    dataObject.setLandlord_email(jsonObject.getString("landlord_email"));
                                    dataObject.setLandlord_fname(jsonObject.getString("landlord_fname"));
                                    dataObject.setInvoicepath(jsonObject.getString("invoicepath"));
                                    dataObject.setInvoicefilename(jsonObject.getString("invoicefilename"));
                                    dataObject.setCompletepath(jsonObject.getString("completepath"));
                                    dataObject.setMattername(jsonObject.getString("mattername"));
                                    lstAnime2.add(dataObject);
                                }


                            }

                            dataListAdapter = new MattersInvoiceListingAdapter(Matters_Invoices.this,lstAnime2) ;
                            recyclerView.setLayoutManager(new LinearLayoutManager(Matters_Invoices.this));
                            recyclerView.setAdapter(dataListAdapter);
                            }

                        catch (JSONException e) {
                            e.printStackTrace();
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
        mSwipeRefreshLayout.setRefreshing(false);
    }









    private void jsonrequest()
    {
        pDialog = Utilss.showSweetLoader(Matters_Invoices.this, SweetAlertDialog.PROGRESS_TYPE, "Fetching Data...");

        RequestQueue queue = Volley.newRequestQueue(Matters_Invoices.this);
        String url = "https://demo.lawsyst.com/mobile-app/json-call/json_matter_invoice_listing.php?"+"matterid="+matterid;
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


                        Log.d("Json-response1", response);

                        try {
                            JSONObject respone = new JSONObject(response);
                            JSONArray data = respone.getJSONArray("data");

                            if (data.length() == 0)
                            {
                                alter_txt.setVisibility(View.VISIBLE);
                            }


                            else
                            {
                                for (int i = 0; i < data.length(); i++)
                                {
                                    JSONObject jsonObject = new JSONObject(String.valueOf(data.get(i)));
                                    Matters_Invoice_Matter_Listing_Pojo dataObject = new Matters_Invoice_Matter_Listing_Pojo();
                                    dataObject.setInvoicenum(jsonObject.getInt("invoicenum"));
                                    dataObject.setInvoiceid(jsonObject.getInt("invoiceid"));
                                    dataObject.setIssued_date(jsonObject.getString("issued_date"));
                                    dataObject.setDuedate(jsonObject.getString("duedate"));
                                    dataObject.setSTATUS(jsonObject.getString("STATUS"));
                                    dataObject.setInvoicetotalamount(jsonObject.getDouble("invoicetotalamount"));
                                    dataObject.setInvoicerefno(jsonObject.getString("invoicerefno"));
                                    dataObject.setLandlord_email(jsonObject.getString("landlord_email"));
                                    dataObject.setLandlord_fname(jsonObject.getString("landlord_fname"));
                                    dataObject.setInvoicepath(jsonObject.getString("invoicepath"));
                                    dataObject.setInvoicefilename(jsonObject.getString("invoicefilename"));
                                    dataObject.setCompletepath(jsonObject.getString("completepath"));
                                    dataObject.setMattername(jsonObject.getString("mattername"));
                                    lstAnime.add(dataObject);
                                }


                            }

                            setuprecyclerview(lstAnime);
                            }

                        catch (JSONException e) {
                            e.printStackTrace();
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

//    private void setuprecyclerview(ArrayList<Matters_Invoice_Matter_Listing_Pojo> lstAnime) {
//    }

    private void setuprecyclerview(ArrayList<Matters_Invoice_Matter_Listing_Pojo> lstAnime) {

        dataListAdapter = new MattersInvoiceListingAdapter(Matters_Invoices.this,lstAnime) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(Matters_Invoices.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(Matters_Invoices.this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(dataListAdapter);
        dataListAdapter.notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }


    @Override
    public boolean onClose() {
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}


