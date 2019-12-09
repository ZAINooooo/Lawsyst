package com.example.lawsyst;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

import static android.R.layout.simple_spinner_item;

public class EditMatter2 extends BaseActivity implements AdapterView.OnItemSelectedListener {


    CheckBox cbx_serveform_selectionbox2, cbx_serveform_selectionbox, cbx_serveform_selectionbox22, cbx_serveform_selectionbox222;

    int v77, v900;
    OkHttpClient client;
    ImageView iv_back;
    String matters_id;

    double value440,value441,value442,value443,value444;
    int valuefee=0,value452,value454,value457,value44,value45,value581,value47,value37, value48;
    String value, value2,value3,value57,value58,value4,value46, value5, value6, value7, value8, value9, value10, value11, value12, value13, v90,value580;
//,calculate_btn2
SharedPreferences sharedPreferences;
    String access_token;
    
    
    Button calculate_btn;
    public ArrayList<BillsData> lstAnime = new ArrayList<BillsData>();
    public ArrayList<SupervisorData> lstAnime2 = new ArrayList<SupervisorData>();

    public ArrayList<BranchesData> lstAnime3 = new ArrayList<BranchesData>();

    public ArrayList<CaseType> lstAnime4 = new ArrayList<CaseType>();

    public ArrayList<LegaStatusData> lstAnime5 = new ArrayList<>();
    public ArrayList<AssignTo> lstAnime6 = new ArrayList<>();
    public ArrayList<LegalClientsData> lstAnime7 = new ArrayList<>();
    public ArrayList<LegalLawTypeData> lstAnime8 = new ArrayList<>();


    private JsonArrayRequest request;
//    private JsonArrayRequest request2;

    private RequestQueue requestQueue;
    JSONObject jsonObject;
    //    CustomAdapter adapter;
    Spinner spLeaveSubject44, spLeaveSubject446, spLeaveSubject4362, spLeaveSubject4, spLeaveSubject436, spLeaveSubject4433, spLeaveSubject2, spLeaveSubject3, spLeaveSubject445;
    String v1, v2, v3, v4, v5, v6, v7, v8;
    String ki, sk;
    int v55,v88,v11,v33,v44,v22;
    long demage_value;
//    , days_leave445,
    EditText days_leave44, days_leave46, days_leave56, days_leave3,days_leave47, days_leave58, days_leave49, days_leave,days_leave2;
    public  static  EditText days_leave467, days_leave4657;
    EditText days_leave43;
    TextView mkmn44, km333, mkmn46, mkmn56, mkmn47, mkmn57, mkmn48;

//, mkmn445
    private ArrayList<String> names = new ArrayList<String>();
    private ArrayList<String> names2 = new ArrayList<String>();
    private ArrayList<String> names3 = new ArrayList<String>();
    private ArrayList<String> names4 = new ArrayList<String>();
    private ArrayList<String> names5 = new ArrayList<String>();
    private ArrayList<String> names6 = new ArrayList<>();
    private ArrayList<String> names7 = new ArrayList<String>();
    private ArrayList<String> names8 = new ArrayList<String>();
    private ArrayList<String> names9 = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_matter);

        sharedPreferences = getSharedPreferences("DATA", MODE_PRIVATE);
        access_token = sharedPreferences.getString("token", "");
        client = new OkHttpClient();

        lstAnime = new ArrayList<>();
        lstAnime2 = new ArrayList<>();
        lstAnime3 = new ArrayList<>();
        lstAnime4 = new ArrayList<>();
        lstAnime5 = new ArrayList<>();
        lstAnime6 = new ArrayList<>();
        lstAnime7 = new ArrayList<>();
        lstAnime8 = new ArrayList<>();


        cbx_serveform_selectionbox = (CheckBox) findViewById(R.id.cbx_serveform_selectionbox);
        cbx_serveform_selectionbox2 = (CheckBox) findViewById(R.id.cbx_serveform_selectionbox2);
        cbx_serveform_selectionbox22 = (CheckBox) findViewById(R.id.cbx_serveform_selectionbox22);
        cbx_serveform_selectionbox222 = (CheckBox) findViewById(R.id.cbx_serveform_selectionbox222);

        days_leave44 = (EditText) findViewById(R.id.days_leave44);

        days_leave = (EditText) findViewById(R.id.days_leave);
        days_leave2 = (EditText) findViewById(R.id.days_leave2);

        days_leave467 = (EditText) findViewById(R.id.days_leave4);
        days_leave467.setInputType(InputType.TYPE_NULL);

        iv_back = (ImageView) findViewById(R.id.iv_back);


        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });


        days_leave467.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment newFragment = new selected_date_fragment3();
                newFragment.show(getSupportFragmentManager(), "date picker");
            }
        });




        days_leave4657 = (EditText) findViewById(R.id.days_leave5);
        days_leave4657.setInputType(InputType.TYPE_NULL);


        days_leave4657.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment newFragment = new selected_date_fragment4();
                newFragment.show(getSupportFragmentManager(), "date picker");
            }
        });



        mkmn44 = (TextView) findViewById(R.id.mkmn44);
//        days_leave445 = (EditText) findViewById(R.id.days_leave445);
//        mkmn445 = (TextView) findViewById(R.id.mkmn445);

        days_leave46 = (EditText) findViewById(R.id.days_leave46);
        mkmn46 = (TextView) findViewById(R.id.mkmn46);

        days_leave56 = (EditText) findViewById(R.id.days_leave56);
        days_leave43 = (EditText) findViewById(R.id.days_leave43);

        mkmn56 = (TextView) findViewById(R.id.mkmn56);

        days_leave47 = (EditText) findViewById(R.id.days_leave47);
        mkmn47 = (TextView) findViewById(R.id.mkmn47);

        days_leave3= (EditText) findViewById(R.id.days_leave3);
        days_leave58 = (EditText) findViewById(R.id.days_leave58);
        mkmn57 = (TextView) findViewById(R.id.mkmn57);


        days_leave49 = (EditText) findViewById(R.id.days_leave49);

        mkmn48 = (TextView) findViewById(R.id.mkmn48);

        km333 = (TextView) findViewById(R.id.km333);
//
        cbx_serveform_selectionbox.setChecked(false);
        cbx_serveform_selectionbox2.setChecked(false);
        cbx_serveform_selectionbox22.setChecked(false);
        cbx_serveform_selectionbox222.setChecked(false);


//        sk = getIntent().getStringExtra("TokenIs");
//        Log.d("TokenIs", sk);
        spLeaveSubject44 = (Spinner) findViewById(R.id.spLeaveSubject44);
        spLeaveSubject44.setOnItemSelectedListener(EditMatter2.this);


        spLeaveSubject446 = (Spinner) findViewById(R.id.spLeaveSubject446);
        spLeaveSubject446.setOnItemSelectedListener(EditMatter2.this);


        spLeaveSubject4362 = (Spinner) findViewById(R.id.spLeaveSubject4362);
        spLeaveSubject4362.setOnItemSelectedListener(EditMatter2.this);


        spLeaveSubject4 = (Spinner) findViewById(R.id.spLeaveSubject4);
        spLeaveSubject4.setOnItemSelectedListener(EditMatter2.this);


        spLeaveSubject436 = (Spinner) findViewById(R.id.spLeaveSubject436);
        spLeaveSubject436.setOnItemSelectedListener(EditMatter2.this);


        spLeaveSubject4433 = (Spinner) findViewById(R.id.spLeaveSubject4433);
        spLeaveSubject4433.setOnItemSelectedListener(EditMatter2.this);


        spLeaveSubject2 = (Spinner) findViewById(R.id.spLeaveSubject2);
        spLeaveSubject2.setOnItemSelectedListener(EditMatter2.this);


        spLeaveSubject3 = (Spinner) findViewById(R.id.spLeaveSubject3);
        spLeaveSubject3.setOnItemSelectedListener(EditMatter2.this);

        spLeaveSubject445 = (Spinner) findViewById(R.id.spLeaveSubject445);
        spLeaveSubject445.setOnItemSelectedListener(EditMatter2.this);


        matters_id = getIntent().getStringExtra("Matter_Id");
        Log.d("Matter_Ids1", matters_id);




        getalldata();


        calculate_btn = (Button) findViewById(R.id.calculate_btn);
//        calculate_btn2 = (Button) findViewById(R.id.calculate_btn2);




        calculate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (days_leave.getText().toString().equals("") || days_leave.getText().toString().length() == 0)
                {

//                    calculate_btn2.setClickable(false);
//                    calculate_btn2.setEnabled(false);

                    days_leave.setError("Matter Name Cant be Null");
                    return;
                }

                else
                {
//                    calculate_btn2.setClickable(true);
//                    calculate_btn2.setEnabled(true);
                }
            }
        });





        calculate_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                if (days_leave.getText().toString().equals("") || days_leave.getText().toString().length() == 0)
                {
                    calculate_btn.setClickable(false);
                    calculate_btn.setEnabled(false);

                    days_leave.setError("Matter Name Is Required..!!");

                    return;
                }


                else
                {
                    calculate_btn.setClickable(true);
                    calculate_btn.setEnabled(true);



                    updateMatter();

//                    pDialog = Utilss.showSweetLoader(EditMatter.this, SweetAlertDialog.PROGRESS_TYPE, "Generating Matter Number...");
//
//
//                    RequestBody formBody = new FormBody.Builder().add("action", "generatenumber").build();
//
//
//                    final okhttp3.Request request = new okhttp3.Request.Builder()
//                            .url("https://demo.lawsyst.com/mobile-app/json-call/json_save_matter.php")
//                            .post(formBody).addHeader("Cache-Control", "no-cache")
//                            .addHeader("Postman-Token", "7e2aa8d6-b994-49cd-bc85-a46f9e35ad1f")
//                            .addHeader("token", access_token)
//                            .build();
//
//
//                    Call call = client.newCall(request);
//                    call.enqueue(new Callback() {
//                        @Override
//                        public void onFailure(Call call, IOException e) {
//
//
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    Utilss.hideSweetLoader(pDialog);
//                                }
//                            });
//
//                            Log.e("HttpService", "onFailure() Request was: " + call);
//                            e.printStackTrace();
//
//                        }
//
//                        @Override
//                        public void onResponse(Call call, okhttp3.Response response) throws IOException {
//
//
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    Utilss.hideSweetLoader(pDialog);
//                                }
//                            });
//
//                            String responses = response.body().string();
//                            Log.e("response", "onResponse(): " + responses);
//
//
//                            if (response.code() == 200) {
//
//                                try {
//
//                                    JSONObject json = new JSONObject(responses);
//                                    value = json.getString("status");
//                                    value2 = json.getString("matternumber");
//
//                                    if (value2.equals("") && !(value.equals("success"))) {
//
//                                    } else {
//                                        Log.d("MatterNumber111", value2);
//                                        //Got Matter Number
//
//                                        updateMatter();
//
//                                    }
//
//
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }
//
//                    });


















                    Log.d("ValueOfAllSpinner", 1 + "" + v1 + 2 + "" + v2 + 3 + " " + v3);
                }


            }
        });






/*
        days_leave4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DialogFragment newFragment = new selected_date_fragment1();
                newFragment.show(getSupportFragmentManager(), "date picker");
            }

        });


        days_leave5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DialogFragment newFragment = new selected_date_fragment2();
                newFragment.show(getSupportFragmentManager(), "date picker");
            }

        });*/







        cbx_serveform_selectionbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    valuefee=1;

                    Toast.makeText(EditMatter2.this, "1st Clicked...!!", Toast.LENGTH_SHORT).show();
                    Log.d("ValueOfFeeIs" , ""+valuefee);

                    cbx_serveform_selectionbox2.setChecked(false);
                    cbx_serveform_selectionbox22.setChecked(false);
                    cbx_serveform_selectionbox222.setChecked(false);

                    cbx_serveform_selectionbox.setChecked(true);


                    mkmn44.setVisibility(View.VISIBLE);
                    days_leave44.setVisibility(View.VISIBLE);


                    mkmn56.setVisibility(View.VISIBLE);
                    days_leave56.setVisibility(View.VISIBLE);

                } else {

//                    cbx_serveform_selectionbox2.setChecked(true);
                    cbx_serveform_selectionbox2.setChecked(false);
                    cbx_serveform_selectionbox22.setChecked(false);
                    cbx_serveform_selectionbox222.setChecked(false);
                    cbx_serveform_selectionbox.setChecked(false);

                    Toast.makeText(EditMatter2.this, "1st Is Closed ...!!", Toast.LENGTH_SHORT).show();

                    mkmn44.setVisibility(View.GONE);
                    days_leave44.setVisibility(View.GONE);

                    mkmn56.setVisibility(View.GONE);
                    days_leave56.setVisibility(View.GONE);



                }
            }
        });














        cbx_serveform_selectionbox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {



                    valuefee=2;
                    Log.d("ValueOfFeeIs" , ""+valuefee);



                  /*  Toast.makeText(EditMatter.this, "2nd Clicked...!!", Toast.LENGTH_SHORT).show();
                    cbx_serveform_selectionbox.setChecked(false);
                    cbx_serveform_selectionbox22.setChecked(false);*/


                    Toast.makeText(EditMatter2.this, "2st Clicked...!!", Toast.LENGTH_SHORT).show();


                    cbx_serveform_selectionbox.setChecked(false);
                    cbx_serveform_selectionbox22.setChecked(false);
                    cbx_serveform_selectionbox222.setChecked(false);
                    cbx_serveform_selectionbox2.setChecked(true);

                    mkmn44.setVisibility(View.VISIBLE);
                    days_leave44.setVisibility(View.VISIBLE);

                    mkmn56.setVisibility(View.VISIBLE);
                    days_leave56.setVisibility(View.VISIBLE);




                } else {

                    mkmn44.setVisibility(View.GONE);
                    days_leave44.setVisibility(View.GONE);

                    mkmn56.setVisibility(View.GONE);
                    days_leave56.setVisibility(View.GONE);


                    cbx_serveform_selectionbox.setChecked(false);
                    cbx_serveform_selectionbox22.setChecked(false);
                    cbx_serveform_selectionbox222.setChecked(false);
                    cbx_serveform_selectionbox2.setChecked(false);

                    Toast.makeText(EditMatter2.this, "2st Is Closed ...!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cbx_serveform_selectionbox22.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {



                    valuefee=3;
                    Log.d("ValueOfFeeIs" , ""+valuefee);


                    spLeaveSubject4433.setVisibility(View.VISIBLE);
                    km333.setVisibility(View.VISIBLE);

                    mkmn46.setVisibility(View.VISIBLE);
                    days_leave46.setVisibility(View.VISIBLE);

                    mkmn56.setVisibility(View.VISIBLE);
                    days_leave56.setVisibility(View.VISIBLE);

                    cbx_serveform_selectionbox.setChecked(false);
                    cbx_serveform_selectionbox2.setChecked(false);
                    cbx_serveform_selectionbox222.setChecked(false);

                    cbx_serveform_selectionbox22.setChecked(true);


                    Toast.makeText(EditMatter2.this, "2st Is Closed ...!!", Toast.LENGTH_SHORT).show();




                } else {



                    cbx_serveform_selectionbox22.setChecked(false);
                    cbx_serveform_selectionbox.setChecked(false);
                    cbx_serveform_selectionbox2.setChecked(false);
                    cbx_serveform_selectionbox222.setChecked(false);


                    spLeaveSubject4433.setVisibility(View.GONE);
                    km333.setVisibility(View.GONE);

                    mkmn46.setVisibility(View.GONE);
                    days_leave46.setVisibility(View.GONE);


                    mkmn56.setVisibility(View.GONE);
                    days_leave56.setVisibility(View.GONE);



                }
            }
        });

        cbx_serveform_selectionbox222.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {



                    valuefee=4;
                    Log.d("ValueOfFeeIs" , ""+valuefee);


                    days_leave47.setVisibility(View.VISIBLE);
                    mkmn47.setVisibility(View.VISIBLE);

                    days_leave58.setVisibility(View.VISIBLE);
                    mkmn57.setVisibility(View.VISIBLE);

                    days_leave49.setVisibility(View.VISIBLE);
                    mkmn48.setVisibility(View.VISIBLE);



                    cbx_serveform_selectionbox.setChecked(false);
                    cbx_serveform_selectionbox2.setChecked(false);
                    cbx_serveform_selectionbox22.setChecked(false);
                    cbx_serveform_selectionbox222.setChecked(true);





                } else {


                    days_leave47.setVisibility(View.GONE);
                    mkmn47.setVisibility(View.GONE);

                    days_leave58.setVisibility(View.GONE);
                    mkmn57.setVisibility(View.GONE);

                    days_leave49.setVisibility(View.GONE);
                    mkmn48.setVisibility(View.GONE);



                    cbx_serveform_selectionbox222.setChecked(false);
                    cbx_serveform_selectionbox.setChecked(false);
                    cbx_serveform_selectionbox2.setChecked(false);
                    cbx_serveform_selectionbox22.setChecked(false);



//                    cbx_serveform_selectionbox2.setChecked(true);
//                    cbx_serveform_selectionbox22.setChecked(true);
//                    cbx_serveform_selectionbox222.setChecked(true);

//                    cbx_serveform_selectionbox.setChecked(false);
                }
            }
        });
    }



    public void getalldata()
    {
//        pDialog = Utilss.showSweetLoader(EditMatter.this, SweetAlertDialog.PROGRESS_TYPE, "Getting Data...");

        editmatter();
        retreivebranches();
        retreivelegalaid();     //Case Type (in Ui)
        retrieveSupervisorJSON();
        retrieveBillsJSON();
        retreivecLients();
        retreiveStatus();
//        retreiveusers();


        retreiveLawType();
        retreiveusers();

//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//
//                Utilss.hideSweetLoader(pDialog);
//            }
//        });
    }





    private void editmatter()
    {

        pDialog = Utilss.showSweetLoader(EditMatter2.this, SweetAlertDialog.PROGRESS_TYPE, "Getting Data...");
        RequestBody formBody = new FormBody.Builder().add("matterid", matters_id).add("action", "editmatter").build();

        final okhttp3.Request request = new okhttp3.Request.Builder()
                .url("https://demo.lawsyst.com/mobile-app/json-call/json_save_matter.php")
                .post(formBody).addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "7e2aa8d6-b994-49cd-bc85-a46f9e35ad1f")
                .addHeader("token", access_token)
                .build();


        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {



                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        Utilss.hideSweetLoader(pDialog);
                    }
                });


                Log.e("HttpService", "onFailure() Request was: " + call);
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {






                String responses = response.body().string();

                if (response.code() == 200)
                {
                    Log.e("response", "onResponse(): " + responses);


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {


                            Utilss.hideSweetLoader(pDialog);
                        }
                    });


                    try {

                        JSONObject json = new JSONObject(responses);
                        value2 = json.getString("status");

                        if (value2.equals("success"))
                        {


                            JSONObject json2 = json.getJSONObject("data");


                            value581 = json2.getInt("fee_type");
                            value581 = json2.getInt("fee_type");

                            value441 = json2.getInt("demage");
                            value442 = json2.getInt("success");

                            value444 = json2.getInt("amount");

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    days_leave44.setText("" +value444 );
                                }
                            });



                            value443 = json2.getInt("residue");
                            Log.d("Residue" , ""+value443);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    days_leave56.setText("" +value443);
                                }
                            });


runOnUiThread(new Runnable() {
    @Override
    public void run() {

        days_leave58.setText("" +value442);

    }
});

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    days_leave49.setText(""+value441);
                                }
                            });




                            Log.d("Fee_Type" , ""+value581);


                            if (value581==0) {

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        cbx_serveform_selectionbox.setChecked(false);
                                        cbx_serveform_selectionbox2.setChecked(false);
                                        cbx_serveform_selectionbox22.setChecked(false);
                                        cbx_serveform_selectionbox222.setChecked(false);
                                    }
                                });
                            }






                           else if (value581==1)
                            {

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        cbx_serveform_selectionbox.setChecked(true);

                                        cbx_serveform_selectionbox2.setChecked(false);
                                        cbx_serveform_selectionbox22.setChecked(false);
                                        cbx_serveform_selectionbox222.setChecked(false);

                                        mkmn44.setVisibility(View.VISIBLE);
                                        days_leave44.setVisibility(View.VISIBLE);
                                        mkmn56.setVisibility(View.VISIBLE);
                                        days_leave56.setVisibility(View.VISIBLE);
                                    }
                                });



                            }
                            else if (value581==2)
                            {


                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        cbx_serveform_selectionbox2.setChecked(true);

                                        cbx_serveform_selectionbox.setChecked(false);
                                        cbx_serveform_selectionbox22.setChecked(false);
                                        cbx_serveform_selectionbox222.setChecked(false);

                                        mkmn44.setVisibility(View.VISIBLE);
                                        days_leave44.setVisibility(View.VISIBLE);

                                        mkmn56.setVisibility(View.VISIBLE);
                                        days_leave56.setVisibility(View.VISIBLE);


                                    }
                                });
                            }

                            else if (value581==3)
                            {

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        cbx_serveform_selectionbox22.setChecked(true);

                                        spLeaveSubject4433.setVisibility(View.VISIBLE);
                                        km333.setVisibility(View.VISIBLE);

                                        mkmn46.setVisibility(View.VISIBLE);
                                        days_leave46.setVisibility(View.VISIBLE);

                                        mkmn56.setVisibility(View.VISIBLE);
                                        days_leave56.setVisibility(View.VISIBLE);

                                        cbx_serveform_selectionbox.setChecked(false);
                                        cbx_serveform_selectionbox2.setChecked(false);
                                        cbx_serveform_selectionbox222.setChecked(false);


                                    }
                                });


                            }
                            else if (value581==4)
                            {

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        cbx_serveform_selectionbox222.setChecked(true);

                                        cbx_serveform_selectionbox.setChecked(false);
                                        cbx_serveform_selectionbox2.setChecked(false);
                                        cbx_serveform_selectionbox22.setChecked(false);


                                        days_leave47.setVisibility(View.VISIBLE);
                                        mkmn47.setVisibility(View.VISIBLE);

                                        days_leave58.setVisibility(View.VISIBLE);
                                        mkmn57.setVisibility(View.VISIBLE);

                                        days_leave49.setVisibility(View.VISIBLE);
                                        mkmn48.setVisibility(View.VISIBLE);

                                    }
                                });
                            }











                            value580 = json2.getString("tags");
                            Log.d("Tags" , value580);



                            value4 = json2.getString("mattername");
                            Log.d("Matter_Name" , value4);

                            if (value4.equals(""))
                            {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        days_leave.setText("N/A");
                                    }
                                });
                            }

                            else
                            {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        days_leave.setText(value4);
                                        Log.d("Matter_Name22" ,value4);
                                    }
                                });
                            }



                            if (value580.equals(""))
                            {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        days_leave43.setText("N/A");
                                    }
                                });
                            }

                            else
                            {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        days_leave43.setText(value580);
                                    }
                                });
                            }












                            value46 = json2.getString("matternotes");
                            Log.d("Matter_Notes" , value46);


                            if (value46.equals(""))
                            {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        days_leave2.setText("N/A");
                                    }
                                });
                            }

                            else
                            {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        days_leave2.setText(value46);
                                    }
                                });
                            }




                            value47 = json2.getInt("matternumber");
                            Log.d("Matter_Number" , ""+value47);


                            value57 = json2.getString("opendate");
                            value58 = json2.getString("closeddate");
                            Log.d("OpenDateCloseDate" ,""+value57 + value58);



                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    days_leave467.setText(value57);
                                }
                            });



                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    days_leave4657.setText(value58);
                                }
                            });






                            if (value47!=0)
                            {

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        days_leave3.setText(""+value47);
                                    }
                                });
                            }




                            value440= json2.getInt("percentage_amount");


                            if (value440!=0)
                            {

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        days_leave47.setText(""+value440);

                                    }
                                });
                            }


























//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//
//                                    days_leave3.setText(""+value56);
//                                }
//                            });












//                            value5 = json2.getInt("STATUS");
//                            value6 = json2.getString("assignto");
//                            value7 = json2.getInt("matternumber");
                        }

                    }

                    catch (JSONException e)
                    {

                    }

















                }

            }


        });














    }


//    private void insertMatter() {
//
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//
//                pDialog = Utilss.showSweetLoader(EditMatter.this, SweetAlertDialog.PROGRESS_TYPE, "Saving Matter...");
//            }
//        });
//
//
////        RequestBody formBody = new FormBody.Builder().add("action", "insertmatter")
////
////                .add("username", "")
////                .add("password", "")
////                .build();
//
//       /* final okhttp3.Request request = new okhttp3.Request.Builder()
//                .url("https://demo.lawsyst.com/mobile-app/json-call/json_save_matter.php?action=insertmatter")
//                .post(formBody).addHeader("Cache-Control", "no-cache")
//                .addHeader("Postman-Token", "7e2aa8d6-b994-49cd-bc85-a46f9e35ad1f")
//                .addHeader("token",sk)
//                .build();*/
//
//
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("mattername", email);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
//        OkHttpClient client = new OkHttpClient();
//        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//        // put your json here
//        RequestBody body = RequestBody.create(JSON, jsonObject.toString());
//        okhttp3.Request request = new okhttp3.Request.Builder()  .url("https://demo.lawsyst.com/mobile-app/json-call/json_save_matter.php?action=insertmatter").post(body).build();
//
//
//        Call call = client.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, okhttp3.Response response) throws IOException {
//
//            }
//        });
//
//    }


    //After Generate Matter Number , Perform the Save Matter Work..!!
    private void updateMatter() {




        pDialog = Utilss.showSweetLoader(EditMatter2.this, SweetAlertDialog.PROGRESS_TYPE, "Updating Matter...");


        Log.d("Value2Is" , value2);
        JSONObject jsonObject3 = new JSONObject();
        try {


            jsonObject3.put("landlordid", v77 );
            jsonObject3.put("lkptlawid", v88 );
            jsonObject3.put("lkptcasetypeid", v11 );

            jsonObject3.put("mattername", days_leave.getText().toString() );
            jsonObject3.put("matternotes", days_leave2.getText().toString() );
            jsonObject3.put("matterreference",value2 );

            jsonObject3.put("opendate",days_leave467.getText().toString() );
            jsonObject3.put("closeddate",days_leave4657.getText().toString());
            jsonObject3.put("assigntoid",v900 );
            jsonObject3.put("supervisorid",v33 );
            jsonObject3.put("statusid",v55);
            jsonObject3.put("tags",days_leave43.getText().toString());
            jsonObject3.put("branchid",v44);

            jsonObject3.put("feescheme",valuefee);

            jsonObject3.put("billto_id",v22);
            jsonObject3.put("amount",days_leave44.getText().toString());
            jsonObject3.put("percentage_amount",days_leave47.getText().toString());
            jsonObject3.put("demage",days_leave49.getText().toString());
            jsonObject3.put("success",days_leave58.getText().toString());


        } catch (JSONException e) {
            e.printStackTrace();
        }


        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        // put your json here

        RequestBody body = RequestBody.create(JSON, jsonObject3.toString());
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url("https://demo.lawsyst.com/mobile-app/json-call/json_save_matter.php?matterid="+matters_id+"&action=updatematter")
                .addHeader("token",access_token)
                .post(body).build();

        Call call = client.newCall(request);

        call.enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e)
            {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Utilss.hideSweetLoader(pDialog);
                    }
                });

                Log.e("HttpService", "onFailure() Request was: " + call);
                e.printStackTrace();


            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException
            {

                String responses = response.body().string();
                Log.e("responsessss", "onResponse(): " + responses);



                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Utilss.hideSweetLoader(pDialog);
                    }
                });
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        SweetAlertDialog pDialog = new SweetAlertDialog(EditMatter2.this, SweetAlertDialog.SUCCESS_TYPE);
                        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                        pDialog.setTitleText("Matter Updated");
                        pDialog.setCancelable(true);
                        pDialog.show();

                    }
                });


            }
        });




















//        Log.d("Value2Is" , value2);
//        JSONObject jsonObject3 = new JSONObject();
//        try {
//
////            jsonObject3.put("mattername", days_leave.getText().toString() );
////            jsonObject3.put("landlordid",v77);
////            jsonObject3.put("statusid",v55);
////            jsonObject3.put("matterreference",value2 );
////            jsonObject3.put("assigntoid",v900 );
//
//            jsonObject3.put("landlordid", v77 );
//            jsonObject3.put("lkptlawid", v88 );
//            jsonObject3.put("lkptcasetypeid", v11 );
//
//            jsonObject3.put("mattername", days_leave.getText().toString() );
//            jsonObject3.put("matternotes", days_leave2.getText().toString() );
//            jsonObject3.put("matterreference",value2 );
//
//            jsonObject3.put("opendate",days_leave4.getText().toString() );
//            jsonObject3.put("closeddate",days_leave5.getText().toString());
//            jsonObject3.put("assigntoid",v900 );
//            jsonObject3.put("supervisorid",v33 );
//            jsonObject3.put("statusid",v55);
//            jsonObject3.put("tags",days_leave43.getText().toString());
//            jsonObject3.put("branchid",v44);
//
//            jsonObject3.put("feescheme",valuefee);
//
//            jsonObject3.put("billto_id",v22);
//
//            jsonObject3.put("amount",days_leave44.getText().toString());
//            jsonObject3.put("percentage_amount",days_leave47.getText().toString());
//            jsonObject3.put("demage",days_leave49.getText().toString());
//            jsonObject3.put("success",days_leave58.getText().toString());
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
//        OkHttpClient client = new OkHttpClient();
//        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//
//        // put your json here
//
//        RequestBody body = RequestBody.create(JSON, jsonObject3.toString());
//        okhttp3.Request request = new okhttp3.Request.Builder()
//                .url("https://demo.lawsyst.com/mobile-app/json-call/json_save_matter.php?action=insertmatter")
//                .addHeader("token",access_token)
//                .post(body).build();
//
//        Call call = client.newCall(request);
//
//        call.enqueue(new Callback()
//        {
//            @Override
//            public void onFailure(Call call, IOException e)
//            {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Utilss.hideSweetLoader(pDialog);
//                    }
//                });
//
//                Log.e("HttpService", "onFailure() Request was: " + call);
//                e.printStackTrace();
//
//
//            }
//
//            @Override
//            public void onResponse(Call call, okhttp3.Response response) throws IOException
//            {
//
//                String responses = response.body().string();
//                Log.e("responsessss", "onResponse(): " + responses);
//
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//
//                        SweetAlertDialog pDialog = new SweetAlertDialog(EditMatter.this, SweetAlertDialog.SUCCESS_TYPE);
//                        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
//                        pDialog.setTitleText("Matter Added");
//                        pDialog.setCancelable(true);
//                        pDialog.show();
//
//                    }
//                });
//
//
//            }
//        });
    }























//    private void retreiveAssignTo()
//    {
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://demo.lawsyst.com/mobile-app/json-call/json_matter_detail.php?action=getusers",
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        Log.d("strrrrr", ">>" + response);
//
//                        try {
//
//                            JSONObject obj = new JSONObject(response);
//                            if(obj.optString("status").equals("success")){
//
//                                lstAnime7 = new ArrayList<>();
//                                JSONArray dataArray  = obj.getJSONArray("data");
//
//                                for (int i = 0; i < dataArray.length(); i++) {
//
//                                    LegalLawTypeData playerModel8= new LegalLawTypeData();
//                                    JSONObject dataobj = dataArray.getJSONObject(i);
//
//                                    playerModel8.setId(dataobj.getInt("id"));
//                                    playerModel8.setValue(dataobj.getString("value"));
//
//                                    lstAnime8.add(playerModel8);
//
//                                }
//
//                                for (int i = 0; i < lstAnime8.size(); i++){
//                                    names8.add(lstAnime8.get(i).getValue());
//                                }
//
//                                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(EditMatter.this, simple_spinner_item,names8);
//                                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
//                                spLeaveSubject445.setAdapter(spinnerArrayAdapter);
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        //displaying the error in toast if occurrs
//                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                })
//
//
//        {
//            @Override
//            public Map getHeaders() throws AuthFailureError {
//                HashMap headers = new HashMap();
//                headers.put("token", sk);
//                return headers;
//            }
//        };
//
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
//    }
//










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

                                     editmatterforlawtypes();
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




    private void retreivecLients()
    {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://demo.lawsyst.com/mobile-app/json-call/json_matter_detail.php?action=getclients",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);

                        try {

                            JSONObject obj = new JSONObject(response);
                            if(obj.optString("status").equals("success")){

                                lstAnime7 = new ArrayList<>();
                                JSONArray dataArray  = obj.getJSONArray("data");

                                for (int i = 0; i < dataArray.length(); i++) {

                                    LegalClientsData playerModel7= new LegalClientsData();
                                    JSONObject dataobj = dataArray.getJSONObject(i);

                                    playerModel7.setId(dataobj.getInt("id"));
                                    playerModel7.setValue(dataobj.getString("value"));
                                    lstAnime7.add(playerModel7);

                                }

                                for (int i = 0; i < lstAnime7.size(); i++){
                                    names7.add(lstAnime7.get(i).getValue());
                                }

//                                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(EditMatter.this, simple_spinner_item, names7);
//                                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
//                                spLeaveSubject2.setAdapter(spinnerArrayAdapter);
                                editmatterforclient();
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




    private void editmatterforsupervisor()
    {


        RequestBody formBody = new FormBody.Builder().add("matterid", matters_id).add("action", "editmatter").build();

        final okhttp3.Request request = new okhttp3.Request.Builder()
                .url("https://demo.lawsyst.com/mobile-app/json-call/json_save_matter.php")
                .post(formBody).addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "7e2aa8d6-b994-49cd-bc85-a46f9e35ad1f")
                .addHeader("token", access_token)
                .build();


        Call call = client.newCall(request);


        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                Log.e("HttpService", "onFailure() Request was: " + call);
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {

                String responses = response.body().string();

                if (response.code() == 200)
                {
                    Log.e("response", "onResponse(): " + responses);

                    try {

                        JSONObject json = new JSONObject(responses);
                        value2 = json.getString("status");

                        if (value2.equals("success"))
                        {

                            JSONObject json2 = json.getJSONObject("data");
                            value457 = json2.getInt("supervisorid");

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    Log.d("ValueOf35012" , ""+value457);

                                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(EditMatter2.this, android.R.layout.simple_spinner_dropdown_item,names2);
                                    spLeaveSubject446.setAdapter(spinnerArrayAdapter);
                                }
                            });
                        }
                    }

                    catch (JSONException e)
                    {

                    }

                }
            }
        });
    }



    private void editmatterforstatus()
    {

        RequestBody formBody = new FormBody.Builder().add("matterid", matters_id).add("action", "editmatter").build();

        final okhttp3.Request request = new okhttp3.Request.Builder()
                .url("https://demo.lawsyst.com/mobile-app/json-call/json_save_matter.php")
                .post(formBody).addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "7e2aa8d6-b994-49cd-bc85-a46f9e35ad1f")
                .addHeader("token", access_token)
                .build();


        Call call = client.newCall(request);


        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                Log.e("HttpService", "onFailure() Request was: " + call);
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {

                String responses = response.body().string();

                if (response.code() == 200)
                {
                    Log.e("response", "onResponse(): " + responses);

                    try {

                        JSONObject json = new JSONObject(responses);
                        value2 = json.getString("status");

                        if (value2.equals("success"))
                        {

                            JSONObject json2 = json.getJSONObject("data");
                            value454 = json2.getInt("STATUS");

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    Log.d("ValueOf35077" , ""+value454);

                                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(EditMatter2.this, simple_spinner_item,names5);
                                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                                    spLeaveSubject436.setAdapter(spinnerArrayAdapter);


                                }
                            });
                        }
                    }

                    catch (JSONException e)
                    {

                    }

                }
            }
        });
    }




    private void editmatterforbranchdata()
    {



        RequestBody formBody = new FormBody.Builder().add("matterid", matters_id).add("action", "editmatter").build();

        final okhttp3.Request request = new okhttp3.Request.Builder()
                .url("https://demo.lawsyst.com/mobile-app/json-call/json_save_matter.php")
                .post(formBody).addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "7e2aa8d6-b994-49cd-bc85-a46f9e35ad1f")
                .addHeader("token", access_token)
                .build();


        Call call = client.newCall(request);


        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                Log.e("HttpService", "onFailure() Request was: " + call);
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {

                String responses = response.body().string();

                if (response.code() == 200)
                {
                    Log.e("response", "onResponse(): " + responses);

                    try {

                        JSONObject json = new JSONObject(responses);
                        value2 = json.getString("status");

                        if (value2.equals("success"))
                        {

                            JSONObject json2 = json.getJSONObject("data");
                            value452 = json2.getInt("branchid");

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    Log.d("ValueOf350" , ""+value452);

                                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(EditMatter2.this, simple_spinner_item,names3);
                                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                                    spLeaveSubject4362.setAdapter(spinnerArrayAdapter);

                                }
                            });
                        }
                    }

                    catch (JSONException e)
                    {

                    }

                }
            }
        });



    }




    private void editmatterforassignto()
    {

        RequestBody formBody = new FormBody.Builder().add("matterid", matters_id).add("action", "editmatter").build();

        final okhttp3.Request request = new okhttp3.Request.Builder()
                .url("https://demo.lawsyst.com/mobile-app/json-call/json_save_matter.php")
                .post(formBody).addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "7e2aa8d6-b994-49cd-bc85-a46f9e35ad1f")
                .addHeader("token", access_token)
                .build();


        Call call = client.newCall(request);


        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                Log.e("HttpService", "onFailure() Request was: " + call);
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {

                String responses = response.body().string();

                if (response.code() == 200)
                {
                    Log.e("response", "onResponse(): " + responses);

                    try {

                        JSONObject json = new JSONObject(responses);
                        value2 = json.getString("status");

                        if (value2.equals("success"))
                        {

                            JSONObject json2 = json.getJSONObject("data");
                            value45 = json2.getInt("assigntoid");

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    Log.d("ValueOf350" , ""+value45);

                                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(EditMatter2.this, simple_spinner_item,names9);
                                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                                    spLeaveSubject445.setAdapter(spinnerArrayAdapter);

//                                    String xuas= Objects.requireNonNull(spinnerArrayAdapter.getItem(value45));
//                                    Log.d("ValueOf344450" , ""+xuas);
                                }
                            });
                        }
                    }

                    catch (JSONException e)
                    {

                    }

                }
            }
        });
    }




    private void editmatterforlegalaid()
    {


        RequestBody formBody = new FormBody.Builder().add("matterid", matters_id).add("action", "editmatter").build();

        final okhttp3.Request request = new okhttp3.Request.Builder()
                .url("https://demo.lawsyst.com/mobile-app/json-call/json_save_matter.php")
                .post(formBody).addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "7e2aa8d6-b994-49cd-bc85-a46f9e35ad1f")
                .addHeader("token", access_token)
                .build();


        Call call = client.newCall(request);


        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                Log.e("HttpService", "onFailure() Request was: " + call);
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {

                String responses = response.body().string();

                if (response.code() == 200)
                {
                    Log.e("response", "onResponse(): " + responses);

                    try {

                        JSONObject json = new JSONObject(responses);
                        value2 = json.getString("status");

                        if (value2.equals("success"))
                        {

                            JSONObject json2 = json.getJSONObject("data");
                            value45 = json2.getInt("lkptcasetypeid");

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    Log.d("ValueOf35" , ""+value45);


                                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(EditMatter2.this, simple_spinner_item,names6);
                                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                                    spLeaveSubject4.setAdapter(spinnerArrayAdapter);

//                                    String xuas= Objects.requireNonNull(spinnerArrayAdapter.getItem(value45));
//                                    Log.d("ValueOf34445" , ""+xuas);
                                }
                            });
                        }
                    }

                    catch (JSONException e)
                    {

                    }

                }
            }
        });
    }




    private void editmatterforlawtypes()
    {

        RequestBody formBody = new FormBody.Builder().add("matterid", matters_id).add("action", "editmatter").build();

        final okhttp3.Request request = new okhttp3.Request.Builder()
                .url("https://demo.lawsyst.com/mobile-app/json-call/json_save_matter.php")
                .post(formBody).addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "7e2aa8d6-b994-49cd-bc85-a46f9e35ad1f")
                .addHeader("token", access_token)
                .build();


        Call call = client.newCall(request);


        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {


//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Utilss.hideSweetLoader(pDialog);
//                    }
//                });

                Log.e("HttpService", "onFailure() Request was: " + call);
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {


//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Utilss.hideSweetLoader(pDialog);
//                    }
//                });

                String responses = response.body().string();

                if (response.code() == 200)
                {
                    Log.e("response", "onResponse(): " + responses);

                    try {

                        JSONObject json = new JSONObject(responses);
                        value2 = json.getString("status");

                        if (value2.equals("success"))
                        {
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    Utilss.hideSweetLoader(pDialog);
//                                }
//                            });

                            JSONObject json2 = json.getJSONObject("data");
                            value44 = json2.getInt("lkptlawid");
//                            value3 = json2.getString("clientname");


                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    Log.d("ValueOf3" , ""+value44);
//                                    spLeaveSubject3.get;


                                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(EditMatter2.this, simple_spinner_item,names8);
                                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                                    spLeaveSubject3.setAdapter(spinnerArrayAdapter);

//                                    String xuas= Objects.requireNonNull(spinnerArrayAdapter.getItem(value44));
//                                    Log.d("ValueOf3444" , ""+xuas);
                                }
                            });




//                            if (value3.equals("null"))
//                            {
//
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//
//                                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(EditMatter.this, simple_spinner_item,names8);
//                                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
//                                        spLeaveSubject3.setAdapter(spinnerArrayAdapter);
//                                    }
//                                });
//
//                            }
//                            else
//                            {
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//
//
//                                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(EditMatter.this, simple_spinner_item,names8);
//                                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
//                                        spLeaveSubject3.setAdapter(spinnerArrayAdapter);
//
//                                        int selectionPosition= spinnerArrayAdapter.getPosition(value3);
//                                        spLeaveSubject3.setSelection(selectionPosition);
//                                    }
//                                });
//                            }
                        }
                    }

                    catch (JSONException e)
                    {

                    }

                }
            }
        });


    }




    private void editmatterforbilldata()
    {
        RequestBody formBody = new FormBody.Builder().add("matterid", matters_id).add("action", "editmatter").build();


        final okhttp3.Request request = new okhttp3.Request.Builder()
                .url("https://demo.lawsyst.com/mobile-app/json-call/json_save_matter.php")
                .post(formBody).addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "7e2aa8d6-b994-49cd-bc85-a46f9e35ad1f")
                .addHeader("token", access_token)
                .build();


        Call call = client.newCall(request);


        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {


//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Utilss.hideSweetLoader(pDialog);
//                    }
//                });

                Log.e("HttpService", "onFailure() Request was: " + call);
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {


//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Utilss.hideSweetLoader(pDialog);
//                    }
//                });

                String responses = response.body().string();

                if (response.code() == 200)
                {
                    Log.e("response", "onResponse(): " + responses);

                    try {

                        JSONObject json = new JSONObject(responses);
                        value2 = json.getString("status");

                        if (value2.equals("success"))
                        {
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    Utilss.hideSweetLoader(pDialog);
//                                }
//                            });

                            JSONObject json2 = json.getJSONObject("data");
                            value37 = json2.getInt("billto_id");



                            if (value37 == 0) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

//                                    spLeaveSubject3.get;


                                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(EditMatter2.this, simple_spinner_item,names);
                                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                                        spLeaveSubject44.setAdapter(spinnerArrayAdapter);

                                    }
                                });


                            }


                            else
                            {


                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        Log.d("ValueOf39", "" + value37);

                                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(EditMatter2.this, simple_spinner_item,names);
                                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                                        spLeaveSubject44.setAdapter(spinnerArrayAdapter);

//                                        String xuas = Objects.requireNonNull(spinnerArrayAdapter.getItem(value37));
//                                        Log.d("ValueOf34449", "" + xuas);
                                    }
                                });

                            }
                        }
                    }

                    catch (JSONException e)
                    {

                    }

                }
            }
        });
    }








    private void editmatterforclient()
    {
//        pDialog = Utilss.showSweetLoader(EditMatter.this, SweetAlertDialog.PROGRESS_TYPE, "Getting Data...");
        RequestBody formBody = new FormBody.Builder().add("matterid", matters_id).add("action", "editmatter").build();


        final okhttp3.Request request = new okhttp3.Request.Builder()
                .url("https://demo.lawsyst.com/mobile-app/json-call/json_save_matter.php")
                .post(formBody).addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "7e2aa8d6-b994-49cd-bc85-a46f9e35ad1f")
                .addHeader("token", access_token)
                .build();


        Call call = client.newCall(request);


        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {


//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Utilss.hideSweetLoader(pDialog);
//                    }
//                });

                Log.e("HttpService", "onFailure() Request was: " + call);
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {


//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Utilss.hideSweetLoader(pDialog);
//                    }
//                });

                String responses = response.body().string();

                if (response.code() == 200)
                {
                    Log.e("response", "onResponse(): " + responses);

                    try {

                        JSONObject json = new JSONObject(responses);
                        value2 = json.getString("status");

                        if (value2.equals("success"))
                        {
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    Utilss.hideSweetLoader(pDialog);
//                                }
//                            });

                            JSONObject json2 = json.getJSONObject("data");
                            value3 = json2.getString("clientname");


                            if (value3.equals("null"))
                            {

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(EditMatter2.this, simple_spinner_item, names7);
                                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                                        spLeaveSubject2.setAdapter(spinnerArrayAdapter);
                                    }
                                });
                            }


                            else
                            {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {


                                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(EditMatter2.this, simple_spinner_item, names7);
                                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                                        spLeaveSubject2.setAdapter(spinnerArrayAdapter);

                                        int selectionPosition= spinnerArrayAdapter.getPosition(value3);
                                        spLeaveSubject2.setSelection(selectionPosition);
                                    }
                                });
                            }
                        }
                    }

                    catch (JSONException e)
                    {

                    }

                }
            }
        });
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

                               editmatterforassignto();
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

                              editmatterforstatus();
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































    private void retreivebranches()
    {
//        pDialog = Utilss.showSweetLoader(EditMatter.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://demo.lawsyst.com/mobile-app/json-call/json_matter_detail.php?action=getbranches",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);

                        try {

                            JSONObject obj = new JSONObject(response);
                            if(obj.optString("status").equals("success")){

                                lstAnime3 = new ArrayList<>();
                                JSONArray dataArray  = obj.getJSONArray("data");

                                for (int i = 0; i < dataArray.length(); i++) {

                                    BranchesData playerModel3 = new BranchesData();
                                    JSONObject dataobj = dataArray.getJSONObject(i);

                                    playerModel3.setId(dataobj.getInt("id"));
                                    playerModel3.setValue(dataobj.getString("value"));

                                    lstAnime3.add(playerModel3);

                                }

                                for (int i = 0; i < lstAnime3.size(); i++){
                                    names3.add(lstAnime3.get(i).getValue());
                                }




                                editmatterforbranchdata();
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


                                editmatterforlegalaid();

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

                             editmatterforbilldata();
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




    private void retrieveSupervisorJSON() {

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

                       editmatterforsupervisor();
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

//        int socketTimeout = 3000;//3 seconds - change to what you want
//        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//        request2.setRetryPolicy(policy);
//
//
//        // request queue
//        requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(request2);




































//    private void retrieveJSON()
//    {
//
//
//        request = new JsonArrayRequest("https://demo.lawsyst.com/mobile-app/json-call/json_matter_detail.php?action=getbillto", new com.android.volley.Response.Listener<JSONArray>()
//        {
//            @Override
//            public void onResponse(JSONArray response)
//            {
//
//                try {
//                    JSONObject object= new JSONObject(response.toString());
//                    JSONArray jsonArray = jsonObject.getJSONArray("data");
//
//                    for (int i = 0; i < jsonArray.length(); i++)
//                    {
//
//                        try {
//                            jsonObject = response.getJSONObject(i);
//
//                            String levName=jsonObject.getString("leaveName");
//                            String levCode=jsonObject.getString("leaveCode");
//
//                            Log.d("LeaveNamee" , levName);
//                            Log.d("LeaveCode2" , levCode);
//
//                            lstAnime.add(new BillsData(levCode,levName));
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                        adapter = new CustomAdapter(EditMatter.this, R.layout.spinner_rows,lstAnime );
//                        spLeaveSubject44.setAdapter(adapter);
//                    }
//
//
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//
//
//            }
//        }, new com.android.volley.Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                Log.d("Error12", error.toString());
//
//
//
//
//            }
//        })
//
//        {
//                        @Override
//            public Map getHeaders() throws AuthFailureError {
//                HashMap headers = new HashMap();
//                headers.put("token", sk);
//                return headers;
//            }
////
//        };
//
//
//        int socketTimeout = 3000;//3 seconds - change to what you want
//        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//        request.setRetryPolicy(policy);
//
//        requestQueue = Volley.newRequestQueue(EditMatter.this);
//        requestQueue.add(request);
//
//    }



















//    private void retrieveJSON() {
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://demo.lawsyst.com/mobile-app/json-call/json_matter_detail.php?action=getbillto", new com.android.volley.Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//
//                for (int i = 0; i < response.length(); i++)
//                {
//
//                    try {
//                        jsonObject = response.getJSONObject(i);
//                        JSONArray jsonArray = jsonObject.getJSONArray("data");
//                        jsonObject = jsonArray.getJSONObject(i);
//
//
//
//
//                        String levName=jsonObject.getString("leaveName");
//                        String levCode=jsonObject.getString("leaveCode");
//
//                        Log.d("LeaveNamee" , levName);
//                        Log.d("LeaveCode2" , levCode);
//
//                        lstAnime.add(new BillsData(levCode,levName));
//
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                    adapter = new CustomAdapter(EditMatter.this, R.layout.spinner_rows,lstAnime );
//                    spLeaveSubject44.setAdapter(adapter);
//                }
//            }
//
//
//
//
//
//
//            @Override
//            public void onResponse(String response) {
//                try {
////                    JSONObject jsonObject = new JSONObject(response);
////                    if(jsonObject.getString("status").equals("success"))
////                    {
//
//
//
//                    for (int i = 0; i < response.length(); i++)
//                    {
//                        JSONObject jsonObject = new JSONObject(response);
//                        JSONArray jsonArray = jsonObject.getJSONArray("data");
//                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//
////                        String levName = jsonObject1.getString("id");
//                        String levCode = jsonObject1.getString("value");
//
////                        Log.d("LeaveNamee", levName);
//                        Log.d("LeaveCode2", levCode);
//                        lstAnime.add(new BillsData(levCode));
//                    }
//                    //}
//
////                    spLeaveSubject44.setAdapter(new ArrayAdapter<String>(EditMatter.this, android.R.layout.simple_spinner_dropdown_item, CountryName));
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    Log.d("ErrorIss", e.getMessage());
//                }
//
//                adapter = new CustomAdapter(EditMatter.this, R.layout.spinner_rows, lstAnime);
//                spLeaveSubject44.setAdapter(adapter);
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//                Log.d("Error12", error.toString());
//            }
//        }) {
//            @Override
//            public Map getHeaders() throws AuthFailureError {
//                HashMap headers = new HashMap();
//                headers.put("token", sk);
//                return headers;
//            }
//        };
//        requestQueue.add(stringRequest);
//
//
////        {
////            @Override
////            public Map<String, String> getHeaders() {
////                Map<String, String> params = new HashMap<>();
////                params.put("Authorization", " Bearer " + access_token);
////                params.put("Cache-Control", "no-cache");
////                params.put("Postman-Token", "afbf7fff-330c-4e4b-a6d1-9bb49be91eb8");
////                Log.d("AuthKey", access_token);
////
////                return params;
////            }
////        };
//
//        ;
//
//
//
//        int socketTimeout = 30000;
//        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//        stringRequest.setRetryPolicy(policy);
//        requestQueue.add(stringRequest);
//    }









//    private void retrieveJSON() {
//
//
//        request = new JsonArrayRequest("https://demo.lawsyst.com/mobile-app/json-call/json_matter_detail.php?action=getbillto", new com.android.volley.Response.Listener<JSONArray>()
//        {
//            @Override
//            public void onResponse(JSONArray response)
//            {
//
//
//
//                try
//                {
//                    JSONObject jsonObject = new JSONObject(response.toString());
//                    JSONArray jsonArray = jsonObject.getJSONArray("data");
//
//                    for (int i = 0; i < jsonArray.length(); i++)
//                    {
//                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//
//                        String levName = jsonObject1.getString("id");
//                        String levCode = jsonObject1.getString("value");
//
//                        Log.d("LeaveNamee", levName);
//                        Log.d("LeaveCode2", levCode);
//                        lstAnime.add(new BillsData(levCode, levName));
//                    }
//
//                }
//
//                catch (JSONException e)
//                {
//                    e.printStackTrace();
//                }
//
//
//                adapter = new CustomAdapter(getApplicationContext(), R.layout.spinner_rows,lstAnime );
//                spLeaveSubject44.setAdapter(adapter);
//
//
//
//
////                for (int i = 0; i < response.length(); i++)
////                {
////
////                    try {
////                        jsonObject = response.getJSONObject(i);
////
////                        String levName=jsonObject.getString("leaveName");
////                        String levCode=jsonObject.getString("leaveCode");
////
////                        Log.d("LeaveNamee" , levName);
////                        Log.d("LeaveCode2" , levCode);
////
////                        lstAnime.add(new BillsData(levCode,levName));
////
////
////                    } catch (JSONException e) {
////                        e.printStackTrace();
////                    }
//
//
//
//
//
//
//
//
//
//
//
//
//
////                }
//
//
//
//
//
//
////                for (int ja = 0; ja < response.length(); ja++)
////                {
////
////                    try {
////                        jsonObject = response.getJSONObject(ja);
////
////
////
////                        String levName=jsonObject.getString("id");
////                        String levCode=jsonObject.getString("value");
////
////                        Log.d("LeaveNamee" , levName);
////                        Log.d("LeaveCode2" , levCode);
////
////                        lstAnime.add(new BillsData(levCode,levName));
////
////
////                    } catch (JSONException e) {
////                        e.printStackTrace();
////                    }
////
////                    adapter = new CustomAdapter(getApplicationContext(), R.layout.spinner_rows,lstAnime );
////                    spLeaveSubject44.setAdapter(adapter);
////                }
//
//            }
//        }, new com.android.volley.Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                Log.d("Error12", error.toString());
//
////                runOnUiThread(new Runnable() {
////                    @Override
////                    public void run() {
////
////                        dialogHeader_3 = new MaterialStyledDialog.Builder(getActivity())
////                                .setHeaderDrawable(R.drawable.header)
////                                .setIcon(new IconicsDrawable(getActivity()).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
////                                .withDialogAnimation(true)
////                                .setTitle("Error Message")
////                                .setDescription("Cant Connect To Server")
////                                .setPositiveText("OK")
////                                .onPositive(new MaterialDialog.SingleButtonCallback() {
////                                    @Override
////                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////
////
//////                                        Intent intent = new Intent(getActivity() , LoginActivity.class);
//////                                        startActivity(intent);
////                                    }
////                                });
////                        dialogHeader_3.show();
////                    }
////                });
//
//
//            }
//        })
//
//        {
//            @Override
//            public Map<String, String> getHeaders() {
//                Map<String, String> params = new HashMap<>();
//                params.put("Authorization", " Bearer " + sk);
//                params.put("Cache-Control", "no-cache");
//                params.put("Postman-Token", "afbf7fff-330c-4e4b-a6d1-9bb49be91eb8");
//                Log.d("AuthKey", sk);
//
//                return params;
//            }
//        };
//
//
//        int socketTimeout = 3000;//3 seconds - change to what you want
//        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//        request.setRetryPolicy(policy);
//
//        requestQueue = Volley.newRequestQueue(EditMatter.this);
//        requestQueue.add(request);
//
//    }






    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {

        if (parent.getId()==R.id.spLeaveSubject4)
        {
            v1 = String.valueOf(spLeaveSubject4.getSelectedItem());
            v11 = lstAnime4.get(position).getId();
            Log.d("spinnervalue1" ,v1);
            Log.d("spinnervalue11" ,""+v11);

        }


        if (parent.getId()==R.id.spLeaveSubject44)
        {
            v2 = String.valueOf(spLeaveSubject44.getSelectedItem());
            v22 = lstAnime.get(position).getId();

            Log.d("spinnervalue2" ,v2);
            Log.d("spinnervalue22" ,""+v22);

        }




        if (parent.getId()==R.id.spLeaveSubject446)
        {
            v3 = String.valueOf(spLeaveSubject446.getSelectedItem());
            v33 = lstAnime2.get(position).getId();

            Log.d("spinnervalue3" ,v3);
            Log.d("spinnervalue33" ,""+v33);
        }


        if (parent.getId()==R.id.spLeaveSubject4362)
        {
            v4 = String.valueOf(spLeaveSubject4362.getSelectedItem());
            v44 = lstAnime3.get(position).getId();

            Log.d("spinnervalue4" ,v4);
            Log.d("spinnervalue44" ,""+v44);

        }




        if (parent.getId()==R.id.spLeaveSubject436)
        {
            v5 = String.valueOf(spLeaveSubject436.getSelectedItem());

            v55= lstAnime5.get(position).getId();

            Log.d("spinnervalue5" ,""+v55);
        }





        if (parent.getId()==R.id.spLeaveSubject4433)
        {
            v6 = String.valueOf(spLeaveSubject4433.getSelectedItem());
            Log.d("spinnervalue6" ,v6);
        }


        if (parent.getId()==R.id.spLeaveSubject2)
        {
            v7 = String.valueOf(spLeaveSubject2.getSelectedItem());

            v77 = lstAnime7.get(position).getId();
            Log.d("spinnervalue7" ,v7);
            Log.d("spinnervalue77" ,""+v77);
        }




        if (parent.getId()==R.id.spLeaveSubject445)
        {
            v90 = String.valueOf(spLeaveSubject445.getSelectedItem());
            v900 = lstAnime6.get(position).getId();

            Log.d("spinnervalue7" ,v90);
            Log.d("spinnervalue900" ,""+v900);
        }




        if (parent.getId()==R.id.spLeaveSubject3)
        {
            v8 = String.valueOf(spLeaveSubject3.getSelectedItem());
            v88 = lstAnime8.get(position).getId();

            Log.d("spinnervalue8" ,v8);
            Log.d("spinnervalue88" ,""+v88);


        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
