package com.example.lawsyst;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
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

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import static android.R.layout.simple_spinner_item;

public class EditTimeEntry extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    ImageView iv_back;
    Button calculate_btn;
    String matter_id , user_id;
    int value2,value3,value4,value5;
    int unitvalueis,unitvalueis2;
    TextView km33891;
    Spinner spLeaveSubject3,spLeaveSubject2;
    public ArrayList<WorkTypes> lstAnime3 = new ArrayList<>();
    private ArrayList<String> names3 = new ArrayList<String>();
    private ArrayList<String> names2 = new ArrayList<String>();
    EditText days_leave2,days_leave3   ,days_leave23,days_leave235,days_leave2356;
    float roundOff;
    String duration , units;
    SharedPreferences sharedPreferences;
    String access_token;
    public ArrayList<SupervisorData> lstAnime2 = new ArrayList<SupervisorData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_time_entry);


        sharedPreferences = getSharedPreferences("DATA", MODE_PRIVATE);
        access_token = sharedPreferences.getString("token", "");

        lstAnime3 = new ArrayList<>();
        lstAnime2 = new ArrayList<>();

        iv_back = (ImageView) findViewById(R.id.iv_back);
        days_leave2 = (EditText) findViewById(R.id.days_leave2);
        days_leave3 = (EditText) findViewById(R.id.days_leave3);
        days_leave235 = (EditText) findViewById(R.id.days_leave235);
        days_leave2356 = (EditText) findViewById(R.id.days_leave2356);

        km33891 = (TextView) findViewById(R.id.km33891);


        days_leave23= (EditText) findViewById(R.id.days_leave23);
        calculate_btn= (Button) findViewById(R.id.calculate_btn);
        spLeaveSubject3= (Spinner) findViewById(R.id.spLeaveSubject3);
        spLeaveSubject3.setOnItemSelectedListener(EditTimeEntry.this);

        spLeaveSubject2= (Spinner) findViewById(R.id.spLeaveSubject2);
        spLeaveSubject2.setOnItemSelectedListener(EditTimeEntry.this);

        matter_id = getIntent().getStringExtra("matterid" );
        user_id = getIntent().getStringExtra("userid");


        Log.d("MatterIdUserId" , ""+matter_id +""+user_id);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

        getworktype();
        getsupervisor();
        getcalculation();
        getcalculation2();


        days_leave2.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(!s.equals("") )
                {
//                    Toast.makeText(EditTimeEntry.this, "Clicked", Toast.LENGTH_SHORT).show();
                    Log.d("ValueDuration" , ""+s);


                    String ff = s.toString() ;
                    Log.d("ffss" , ff);



                    if (ff.equals("") || ff.length()==0)
                    {

                    }

                    else
                    {
//                        1295  6

                        unitvalueis = Integer.parseInt(ff);
                        Log.d("unitvalueis" , "" + unitvalueis);
                        Log.d("Value3IS" , ""+value3);

                        double fff= unitvalueis / value3;
                         roundOff = Math.round(Float.parseFloat(new DecimalFormat("##.###").format(fff)));

//                        double roundOff1 = Math.round(fff*10000)/10000.00;
//                        double roundOff2 = Math.round(roundOff1*1000)/1000.00;
//                        double roundOff = Math.round(roundOff2*100)/100.00;

                        Log.d("ValueOfUnitFinal" , ""+roundOff);
                        days_leave3.setText(""+roundOff);



                        int in = Integer.valueOf((ff));
                        int hours = (int) Math.floor(in/60);
                        Log.d("HourSS" , "" + hours);
                        km33891.setText(""+hours);
                    }









//                    unitvalueis = Integer.parseInt(ff);
//
//                    if (unitvalueis ==0)
//                    {
//
//                    }
//                    else
//                    {
//                        Log.d("unitvalueis" , "" + unitvalueis);
//                    }




//                     Log.d("Value3IS" , ""+value3);
//
//                     int fff= unitvalueis % value3;
////                    days_leave3.setText(unitvalueis);
//                    Log.d("ValueOfUnitFinal" , ""+fff);


                    //do your work here
                }
            }



            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void afterTextChanged(Editable s)
            {

            }
        });



        days_leave3.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(!s.equals("") )
                {
                  /*  Log.d("ValueDuration3" , ""+s);

                    String ff = s.toString() ;
                    Log.d("ffss" , ff);

                    if (ff.equals("") || ff.length()==0)
                    {

                    }

                    else
                    {
                        unitvalueis2 = Integer.parseInt(ff);
                        Log.d("durationvalueis" , "" + unitvalueis2);
                        Log.d("Value3IS" , ""+value3);

                        int fffs= unitvalueis2 * value3;
                        Log.d("ValueOfDurationFinal" , ""+fffs);
                        days_leave2.setText(""+fffs);
                    }*/



//                    days_leave2.setText("");
                    //do your work here
                }
            }



            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void afterTextChanged(Editable s)
            {

            }
        });





        calculate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float Perunitrate = value4 /10;
                Log.d("Perunitrate" ,""+Perunitrate );                   //25.0
//                days_leave23.setText(""+Perunitrate);



                            float net_amount = Perunitrate *roundOff ;
                            Log.d("net_amount" , "" +net_amount);
                             days_leave23.setText(""+net_amount);



                float values5 = (float)20/(float) 100;                    //20/100 =0.2
                Log.d("values5" , ""+values5);

//                Netamount = perunitrate * units;
                float tax_amount = net_amount *values5;
                Log.d("tax_amount" , "" +tax_amount);
                days_leave235.setText(""+tax_amount);




                float Totalamount = net_amount + tax_amount;
                Log.d("Totalamount" , "" +Totalamount);
                days_leave2356.setText(""+Totalamount);

            }
        });

    }

    private void getcalculation2()
    {
        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, "https://demo.lawsyst.com/common/ajax_save_time_entry.php?fetchunitandminutes=true&matterid="+matter_id+""+"&userid="+user_id+""+"&agency_id=524368", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("strrrrr", ">>" + response);

                try {

                    JSONObject obj = new JSONObject(response);
                    value4 = obj.getInt("hourlyrate");
                    value5 = obj.getInt("taxrate");

                    Log.d("Unitss22" , ""+value4           +           "           "          +                  value5);



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
        requestQueue.add(stringRequest2);
    }


    private void getcalculation()
    {

        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, "https://demo.lawsyst.com/common/ajax_save_time_entry.php?fetchunitandminutes=true&matterid="+matter_id+""+"&userid="+user_id+""+"&agency_id=524368", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("strrrrr", ">>" + response);

                try {

                    JSONObject obj = new JSONObject(response);

                    JSONObject json2 = obj.getJSONObject("invoicenotes");

                    value2 = json2.getInt("minbillingunit");
                    value3 = json2.getInt("billingunitminutes");

                    Log.d("Unitss" , ""+value2           +           "           "          +                  value3);



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
        requestQueue.add(stringRequest2);
    }










    private void getsupervisor()
    {
        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, "https://demo.lawsyst.com/mobile-app/json-call/json_matter_detail.php?action=getsupervisors", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("strrrrr", ">>" + response);

                try {

                    JSONObject obj = new JSONObject(response);
                    if(obj.optString("status").equals("success")){

                        lstAnime2 = new ArrayList<>();
                        JSONArray dataArray2  = obj.getJSONArray("data");

                        for (int i = 0; i < dataArray2.length(); i++) {

                            SupervisorData playerModel2 = new SupervisorData();
                            JSONObject dataobj = dataArray2.getJSONObject(i);
                            playerModel2.setId(dataobj.getInt("id"));
                            playerModel2.setValue(dataobj.getString("value"));

                            lstAnime2.add(playerModel2);

                        }

                        for (int i = 0; i < lstAnime2.size(); i++){
                            names2.add(lstAnime2.get(i).getValue());
                        }

                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(EditTimeEntry.this, simple_spinner_item,names2);
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                        spLeaveSubject2.setAdapter(spinnerArrayAdapter);
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
        requestQueue.add(stringRequest2);

    }





    private void getworktype()
    {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://demo.lawsyst.com/mobile-app/json-call/json_get_timeentry_amount.php?matter_id="+matter_id+""+"&userid="+user_id+""+"&resource="+user_id+""+"&agencyid=524368&get_worktypes=true",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);

                        try {

                            JSONObject obj = new JSONObject(response);

                            lstAnime3 = new ArrayList<>();
                            JSONArray dataArray  = obj.getJSONArray("worktypeopts");

                            for (int i = 0; i < dataArray.length(); i++) {

                                WorkTypes playerModel3 = new WorkTypes();
                                JSONObject dataobj = dataArray.getJSONObject(i);

                                playerModel3.setId(dataobj.getInt("id"));
                                playerModel3.setDescription(dataobj.getString("description"));
                                playerModel3.setSpreadsheet_string(dataobj.getString("spreadsheet_string"));
                                lstAnime3.add(playerModel3);

                            }

                            for (int i = 0; i < lstAnime3.size(); i++){
                                names3.add(lstAnime3.get(i).getSpreadsheet_string());
                            }

                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(EditTimeEntry.this, simple_spinner_item,names3);
                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                            spLeaveSubject3.setAdapter(spinnerArrayAdapter);




//                            if(obj.optString("status").equals("success")){
//
//
//
//
//
//                                editmatterforbranchdata();
//                            }

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






    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
