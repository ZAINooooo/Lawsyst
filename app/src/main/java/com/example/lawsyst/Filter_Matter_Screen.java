package com.example.lawsyst;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

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

import static android.R.layout.simple_spinner_item;

public class Filter_Matter_Screen extends BaseActivity implements AdapterView.OnItemSelectedListener {


    Button filter_btn;

    SharedPreferences sharedPreferences;
    String access_token;
    Spinner spLeaveSubject436, spLeaveSubject4367, spLeaveSubject43678, spLeaveSubject436789, spLeaveSubject436780;
    public ArrayList<LegaStatusData> lstAnime5 = new ArrayList<>();
    private ArrayList<String> names5 = new ArrayList<String>();

    public ArrayList<LegalLawTypeData> lstAnime8 = new ArrayList<>();
    private ArrayList<String> names8 = new ArrayList<String>();


    int v11,v12,v13,v14,v15;

    public ArrayList<CaseType> lstAnime4 = new ArrayList<CaseType>();
    private ArrayList<String> names6 = new ArrayList<String>();

    public ArrayList<BillsData> lstAnime = new ArrayList<BillsData>();
    private ArrayList<String> names = new ArrayList<String>();

    public ArrayList<AssignTo> lstAnime6 = new ArrayList<>();
    private ArrayList<String> names9 = new ArrayList<String>();

    ImageView iv_back;


    static EditText days_leave4543,days_leave4544;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_main);


        sharedPreferences = getSharedPreferences("DATA", MODE_PRIVATE);
        access_token = sharedPreferences.getString("token", "");

        filter_btn = findViewById(R.id.filter_btn);

        days_leave4543 = findViewById(R.id.days_leave4);
        days_leave4543.setInputType(InputType.TYPE_NULL);

        days_leave4544 = findViewById(R.id.days_leave5);
        days_leave4544.setInputType(InputType.TYPE_NULL);



        spLeaveSubject436 = (Spinner) findViewById(R.id.spLeaveSubject436);
        spLeaveSubject436.setOnItemSelectedListener(Filter_Matter_Screen.this);

        spLeaveSubject4367 = (Spinner) findViewById(R.id.spLeaveSubject4367);
        spLeaveSubject4367.setOnItemSelectedListener(Filter_Matter_Screen.this);

        spLeaveSubject43678 = (Spinner) findViewById(R.id.spLeaveSubject43678);
        spLeaveSubject43678.setOnItemSelectedListener(Filter_Matter_Screen.this);


        spLeaveSubject436789 = (Spinner) findViewById(R.id.spLeaveSubject436789);
        spLeaveSubject436789.setOnItemSelectedListener(Filter_Matter_Screen.this);


        spLeaveSubject436780 = (Spinner) findViewById(R.id.spLeaveSubject436780);
        spLeaveSubject436780.setOnItemSelectedListener(Filter_Matter_Screen.this);



        days_leave4543.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment newFragment = new selected_date_fragment44();
                newFragment.show(getSupportFragmentManager(), "date picker");
            }
        });


        days_leave4544.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment newFragment = new selected_date_fragment45();
                newFragment.show(getSupportFragmentManager(), "date picker");
            }
        });


        iv_back = (ImageView) findViewById(R.id.iv_back);


        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });


        filter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked..!!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(  Filter_Matter_Screen.this , Filter_Result.class);

                intent.putExtra("start_date",days_leave4543.getText().toString());
                intent.putExtra("end_date",days_leave4544.getText().toString());
                intent.putExtra("status",v11);
                intent.putExtra("Staffid",v12);
                intent.putExtra("Billto",v13);
                intent.putExtra("Law",v14);
                intent.putExtra("Casetype",v15);
                startActivity(intent);
                Log.d("DataIsss" , v11 + "      "   + v12 + "             " +v13 + "         " + v14 + "               "+v15);
            }
        });

        getalldata();
    }




    private void getalldata()
    {
        retreiveStatus();
        retrieveBillsJSON();
        retreiveLawType();
        retreivelegalaid();  //Case Type..!!
        retreiveusers();
    }





    private void retreiveusers()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://demo.lawsyst.com/mobile-app/json-call/json_matter_detail.php?action=getusers",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);

                        try {

                            JSONObject obj = new JSONObject(response);
                            if(obj.optString("status").equals("success")){

                                lstAnime6 = new ArrayList<>();
                                JSONArray dataArray  = obj.getJSONArray("data");

                                for (int i = 0; i < dataArray.length(); i++) {

                                    AssignTo playerModel6= new AssignTo();
                                    JSONObject dataobj = dataArray.getJSONObject(i);

                                    playerModel6.setId(dataobj.getInt("id"));
                                    playerModel6.setValue(dataobj.getString("value"));

                                    lstAnime6.add(playerModel6);

                                }

                                for (int i = 0; i < lstAnime6.size(); i++){
                                    names9.add(lstAnime6.get(i).getValue());
                                }

                                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(Filter_Matter_Screen.this, simple_spinner_item,names9);
                                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                                spLeaveSubject4367.setAdapter(spinnerArrayAdapter);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })


        {
            @Override
            public Map getHeaders() {
                HashMap headers = new HashMap();
                headers.put("token", access_token);
                return headers;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }








    private void retreiveLawType()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://demo.lawsyst.com/mobile-app/json-call/json_matter_detail.php?action=getlawtypes",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);

                        try {

                            JSONObject obj = new JSONObject(response);
                            if(obj.optString("status").equals("success")){

                                lstAnime8 = new ArrayList<>();
                                JSONArray dataArray  = obj.getJSONArray("data");

                                for (int i = 0; i < dataArray.length(); i++) {

                                    LegalLawTypeData playerModel8= new LegalLawTypeData();
                                    JSONObject dataobj = dataArray.getJSONObject(i);

                                    playerModel8.setId(dataobj.getInt("id"));
                                    playerModel8.setValue(dataobj.getString("value"));

                                    lstAnime8.add(playerModel8);

                                }

                                for (int i = 0; i < lstAnime8.size(); i++){
                                    names8.add(lstAnime8.get(i).getValue());
                                }

                                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(Filter_Matter_Screen.this, simple_spinner_item,names8);
                                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                                spLeaveSubject436789.setAdapter(spinnerArrayAdapter);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("token", access_token);
                return headers;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }







    private void retreivelegalaid()
    {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://demo.lawsyst.com/mobile-app/json-call/json_matter_detail.php?action=getcasetypes",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);

                        try {

                            JSONObject obj = new JSONObject(response);
                            if(obj.optString("status").equals("success")){

                                lstAnime4 = new ArrayList<>();
                                JSONArray dataArray  = obj.getJSONArray("data");

                                for (int i = 0; i < dataArray.length(); i++) {

                                    CaseType playerModel4 = new CaseType();
                                    JSONObject dataobj = dataArray.getJSONObject(i);

                                    playerModel4.setId(dataobj.getInt("id"));
                                    playerModel4.setValue(dataobj.getString("value"));
                                    playerModel4.setLawid(dataobj.getString("lawid"));

                                    lstAnime4.add(playerModel4);

                                }

                                for (int i = 0; i < lstAnime4.size(); i++){
                                    names6.add(lstAnime4.get(i).getValue());
                                }

                                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(Filter_Matter_Screen.this, simple_spinner_item,names6);
                                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                                spLeaveSubject436780.setAdapter(spinnerArrayAdapter);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })


        {
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("token", access_token);
                return headers;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }




    private void retrieveBillsJSON() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://demo.lawsyst.com/mobile-app/json-call/json_matter_detail.php?action=getbillto",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);

                        try {

                            JSONObject obj = new JSONObject(response);
                            if(obj.optString("status").equals("success")){

                                lstAnime = new ArrayList<>();
                                JSONArray dataArray  = obj.getJSONArray("data");

                                for (int i = 0; i < dataArray.length(); i++) {

                                    BillsData playerModel = new BillsData();
                                    JSONObject dataobj = dataArray.getJSONObject(i);

                                    playerModel.setId(dataobj.getInt("id"));
                                    playerModel.setValue(dataobj.getString("value"));
                                    lstAnime.add(playerModel);
                                }

                                for (int i = 0; i < lstAnime.size(); i++){
                                    names.add(lstAnime.get(i).getValue());
                                }


                                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(Filter_Matter_Screen.this, simple_spinner_item,names);
                                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                                spLeaveSubject43678.setAdapter(spinnerArrayAdapter);


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })


        {
            @Override
            public Map getHeaders() {
                HashMap headers = new HashMap();
                headers.put("token", access_token);
                return headers;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }









    private void retreiveStatus()
    {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://demo.lawsyst.com/mobile-app/json-call/json_matter_detail.php?action=getstatuses",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Log.d("strrrrr", ">>" + response);

                            try {

                                JSONObject obj = new JSONObject(response);
                                if(obj.optString("status").equals("success")){

                                    lstAnime5 = new ArrayList<>();
                                    JSONArray dataArray  = obj.getJSONArray("data");

                                    for (int i = 0; i < dataArray.length(); i++) {

                                        LegaStatusData playerModel5= new LegaStatusData();
                                        JSONObject dataobj = dataArray.getJSONObject(i);

                                        playerModel5.setId(dataobj.getInt("id"));
                                        playerModel5.setValue(dataobj.getString("value"));

                                        lstAnime5.add(playerModel5);

                                    }

                                    for (int i = 0; i < lstAnime5.size(); i++){
                                        names5.add(lstAnime5.get(i).getValue());
                                    }
                                }

                                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(Filter_Matter_Screen.this, simple_spinner_item,names5);
                                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                                spLeaveSubject436.setAdapter(spinnerArrayAdapter);

//                                for (int i = 0; i < lstAnime.size(); i++)
//                                {
//                                    spLeaveSubject44.setSelection(value37);
//                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })


            {
                @Override
                public Map getHeaders() {
                    HashMap headers = new HashMap();
                    headers.put("token", access_token);
                    return headers;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }













    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        if (parent.getId()==R.id.spLeaveSubject436)
        {
            v11 = lstAnime5.get(position).getId();
            Log.d("spinnervalue11" ,""+v11);
        }


        if (parent.getId()==R.id.spLeaveSubject4367)
        {
             v12 = lstAnime6.get(position).getId();
             Log.d("spinnervalue12" ,""+v12);
        }
        if (parent.getId()==R.id.spLeaveSubject43678)
        {
             v13 = lstAnime.get(position).getId();
             Log.d("spinnervalue13" ,""+v13);
        }


        if (parent.getId()==R.id.spLeaveSubject436789)
        {
             v14= lstAnime8.get(position).getId();
             Log.d("spinnervalue14" ,""+v14);
        }

        if (parent.getId()==R.id.spLeaveSubject436780)
        {
             v15 = lstAnime4.get(position).getId();
             Log.d("spinnervalue15" ,""+v15);
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
