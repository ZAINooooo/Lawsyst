package com.example.lawsyst;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.commonlibrary.logger.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Matters_Details extends BaseActivity {

    String matter_id,value,valu2,value3 ,value4,value5,value6,value7 ,value8 ,value9 ,value10 ,value11 ,value12 ,value13 ,value14 ,value15 ,value16,value17,value18 ,value19,value20,value21 ,value22 , value77,value23 ,value24 ,value25 ,value26 ,value27 ,value28,value29   ;
    OkHttpClient client;

    TextView client_name,law,case_type,details_client,assign_description,assign_tags,assign_originatory,assign_to,invoices_total
            ,bills_total,balance,fee_retainer_Amount,fee_legal_aid,fee_hourly_rate,fee_agree,
            dates_law,dates_limitation,details_case_type,details_law,opendate;

    ImageView iv_back;  SharedPreferences sharedPreferences;
    String access_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_matter__tab);

        sharedPreferences = getSharedPreferences("DATA", MODE_PRIVATE);
        access_token = sharedPreferences.getString("token", "");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() !=null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        }







        client_name = (TextView) findViewById(R.id.client_name);
        law = (TextView) findViewById(R.id.law);
        case_type = (TextView) findViewById(R.id.case_type);
        details_client = (TextView) findViewById(R.id.details_client);
        assign_description = (TextView) findViewById(R.id.assign_description);
//        assign_tags = (TextView) findViewById(R.id.assign_tags);
        assign_originatory = (TextView) findViewById(R.id.assign_originatory);
        assign_to = (TextView) findViewById(R.id.assign_to);
//        invoices_total = (TextView) findViewById(R.id.invoices_total);
//        bills_total = (TextView) findViewById(R.id.bills_total);
//        balance = (TextView) findViewById(R.id.balance);
        fee_retainer_Amount = (TextView) findViewById(R.id.fee_retainer_Amount);
//        fee_legal_aid = (TextView) findViewById(R.id.fee_legal_aid);
        fee_hourly_rate = (TextView) findViewById(R.id.fee_hourly_rate);
//        fee_agree = (TextView) findViewById(R.id.fee_agree);
        opendate = (TextView) findViewById(R.id.opendate);
        dates_limitation = (TextView) findViewById(R.id.dates_limitation);
//        details_case_type = (TextView) findViewById(R.id.details_case_type);
        details_law = (TextView) findViewById(R.id.details_law);


        matter_id = getIntent().getStringExtra("MatterId");
        client = new OkHttpClient();

        Log.d("MattersId" , matter_id);

        iv_back = (ImageView) findViewById(R.id.iv_back);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                                onBackPressed();
            }
        });


        getDetails();
    }



    private void getDetails()
    {

        pDialog = Utilss.showSweetLoader(Matters_Details.this, SweetAlertDialog.PROGRESS_TYPE, "Fetching Data...");

        RequestBody formBody = new FormBody.Builder().add("action", "getmatterdetails").add("matterid", matter_id).build();

        final Request request = new Request.Builder()
                .url("https://demo.lawsyst.com/mobile-app/json-call/json_matter_detail.php")
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
            public void onResponse(Call call, Response response) throws IOException {


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Utilss.hideSweetLoader(pDialog);
                    }
                });

                String responses = response.body().string();
                Log.e("response", "onResponse(): " + responses);

                if (response.code() == 404) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Utilss.hideSweetLoader(pDialog);
                        }
                    });
                }

                else if (response.code() == 500)
                {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Utilss.hideSweetLoader(pDialog);
                        }
                    });

                }


                else {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Utilss.hideSweetLoader(pDialog);
                        }
                    });

                    try {

                        JSONObject json = new JSONObject(responses);
                        JSONObject json2 = json.getJSONObject("data");


//                        JSONObject jsonObject = new JSONObject(String.valueOf(data.get(i)));

                         value = json2.getString("landlord_id");
                         Log.d("Value1" , value);

                         valu2 = json2.getString("userid");
                        Log.d("Value2" , valu2);

                        value3 = json2.getString("lkptlawid");
                        Log.d("Value3" , value3);


                        value4 = json2.getString("lkptcasetypeid");
                        Log.d("Value4" , value4);



                        value5 = json2.getString("mattername");
                        Log.d("Value5" , value5);

                        value6 = json2.getString("matternumber");
                        Log.d("Value6" , value6);



                        value7 = json2.getString("matternotes");
                        Log.d("Value7" , value7);



                        value77 = json2.getString("hourly");
                        Log.d("Value77" , value77);










//                        value8 = json2.getString("statueoflimitation");
//                        Log.d("Value8" , value8);



                        value9 = json2.getString("opendate");
                        Log.d("Value9" , value9);



////                         value10 = json2.getString("agreed_fee");
//                         value11 = json2.getString("hourlyrateamount");
//                        Log.d("Value11" , value11);




                        value12 = json2.getString("retainer_amount");
                        Log.d("Value12" , value12);

//                        value13 = json2.getString("balance");
//                        Log.d("Value13" , value13);


                        value14 = json2.getString("assigntoid");
                        Log.d("Value14" , value14);


                        value15 = json2.getString("originatingattorney");
                        Log.d("Value15" , value15);


                        value16 = json2.getString("tags");
                        Log.d("Value16" , value16);


                        value17 = json2.getString("statusid");
                        Log.d("Value17" ,  value17);



                        value18 = json2.getString("agency_id");
                        Log.d("Value18" , value18);



                        value19 = json2.getString("assignto_name");
                        Log.d("Value19" , value19);



                        value20 = json2.getString("assignto_lname");
                        Log.d("Value20" , value20);

                        value21 = json2.getString("originatingattorney_name");
                        Log.d("Value21" , value21);

                        value22 = json2.getString("originatingattorney_lname");
                        Log.d("Value22" , value22);


                        value23 = json2.getString("user_name");
                        Log.d("Value23" , value23);



                        value24 = json2.getString("user_lname");
                        Log.d("Value24" , value24);


                        value25 = json2.getString("lawdescription");
                        Log.d("Value25" , value25);

                        value26 = json2.getString("casetypedescription");
                        Log.d("Value26" , value26);

                        value27 = json2.getString("status_description");
                        Log.d("Value27" , value27);


                        value28 = json2.getString("landlord_fname");
                        Log.d("Value28" , value28);

                        value29 = json2.getString("landlord_lname");
                        Log.d("Value29" , value29);





                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {



                                client_name.setText(value5);
                                law.setText(value25);
                                case_type.setText(value26);
//
//
//
//
//
//                                //Matter Details
                                details_client.setText(value5);


                                if (value7==null)
                                {
                                    details_law.setText("null");
                                }
                                else
                                {
                                    details_law.setText(value7);

                                }


                                if (value8==null)
                                {
                                                                    dates_limitation.setText("null");

                                }

                                else
                                {
                                                                    dates_limitation.setText(value8);
                                }
//
//
//
//
//                                //Dates
//                                dates_limitation.setText(value8);
                                opendate.setText(value9);
//
//
//
//                                //Fees
////                                fee_agree.setText(value10);
                                fee_hourly_rate.setText(value77);
                                fee_retainer_Amount.setText(value12);
//                                balance.setText(value13);
//
//
//                        fee_legal_aid.setText("");
//
//
//
////                        bills_total.setText("");
////                        invoices_total.setText("");
//
//
//
//
//
//
//
//                                //Assign To
                                assign_to.setText(value14);
                                assign_originatory.setText(value21 +value22);

//                                assign_tags.setText("");
//
//
//                                if (value16.equals(""))
//                                {
//                                    assign_tags.setText("N/A");
//
//                                }
//                                else
//                                {
//                                    assign_tags.setText(value16);
//                                }
//
//
//
////                                invoices_total.setText("N/A");
//
//
//
//                                assign_tags.setText(value16);
                                assign_description.setText(value27);



                            }
                        });









//                        assign_description.setText(value7);
//
//
//
//                        assign_tags.setText(value8);
//                        assign_originatory.setText("");
//                        assign_to.setText("");
//                        invoices_total.setText("");
//                        bills_total.setText("");
//                        balance .setText("");
//                        fee_retainer_Amount.setText("");
//                        fee_legal_aid .setText("");
//                        fee_hourly_rate.setText("");
//                        fee_agree.setText("");
//                        dates_law.setText("");
//                        dates_limitation.setText("");
//                        details_case_type.setText("");
//





//                        value8 = jsonObject.getString("isApprover");

//                        value + valu2 + value3 + value4 + value5 + value6 + value7 + value8
                        Log.d("Value123", value5);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

//                    SendTokenParams();


                }
            }

        });





    }


//    @Override
//    public void onBackPressed() {
//        Intent i = new Intent(getApplicationContext(), MatterDetails.class);
//        startActivity(i);
//        finish();
//    }
}
