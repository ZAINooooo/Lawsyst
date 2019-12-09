package com.example.lawsyst;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.yarolegovich.lovelydialog.LovelyChoiceDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MainActivity extends BaseActivity {


    EditText editTextEmail, editTextPassword;
    String email, password;
    Button button;
    String value2, value3,value5;
     String value4,value7;
    OkHttpClient client;

    MaterialStyledDialog.Builder dialogHeader_3;

    SharedPreferences sharedPreferences;

//    private static final int ID_SINGLE_CHOICE_DIALOG = R.id.btn_single_choice_dialog;


//    MaterialStyledDialog.Builder dialogHeader_3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sharedPreferences = getSharedPreferences("DATA", MODE_PRIVATE);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        button = (Button) findViewById(R.id.btnLogin);

        client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS).build();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = editTextEmail.getText().toString();
                password = editTextPassword.getText().toString();


                if (isNetAvailable()) {

                    pDialog = Utilss.showSweetLoader(MainActivity.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");

                    if (email.equals("") && password.equals("")) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Utilss.hideSweetLoader(pDialog);
                            }
                        });

                        SweetAlertDialog pDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE);
                        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                        pDialog.setTitleText("Credentials Required..!!");
                        pDialog.setCancelable(true);
                        pDialog.show();

                    } else if (email.equals("") || password.equals("")) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Utilss.hideSweetLoader(pDialog);
                            }
                        });

                        SweetAlertDialog pDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE);
                        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                        pDialog.setTitleText("Credentials Required..!!");
                        pDialog.setCancelable(true);
                        pDialog.show();
                    }


                    else
                        {


                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("email", email);
                            jsonObject.put("pass", password);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        OkHttpClient client = new OkHttpClient();
                        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                        // put your json here
                        RequestBody body = RequestBody.create(JSON, jsonObject.toString());
                        Request request = new Request.Builder() .url("https://demo.lawsyst.com/mobile-app/json-call/json_login.php").post(body).build();

                        Call call = client.newCall(request);
                        call.enqueue(new Callback() {
                            @Override
                            public void onFailure(final Call call, final IOException e) {


                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Utilss.hideSweetLoader(pDialog);
                                    }
                                });


                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        new LovelyChoiceDialog(MainActivity.this)
                                                .setTopColorRes(R.color.darkGreen)
                                                .setTitle("Failed to Connect")
                                                .setIcon(R.drawable.ic_assignment_white_36dp)
                                                .setMessage("No Response From Server").show();


                                        Log.e("HttpService", "onFailure() Request was: " + call);
                                        e.printStackTrace();
                                    }
                                });

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {

                                String responses = response.body().string();
                                Log.e("response", "onResponse(): " + responses);

                                try {

                                    JSONObject json = new JSONObject(responses);
                                    value2 = json.getString("status");

                                    if (response.code() == 200 && value2.equals("success"))
                                    {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Utilss.hideSweetLoader(pDialog);
                                            }
                                        });

                                        JSONObject json2 = json.getJSONObject("data");
                                        value2 = json2.getString("name");
                                        value7 = json2.getString("id");
                                        value3 = json2.getString("voip_number");
                                        value4 = json2.getString("token");

                                        try {

//                                            SharedPreferences.Editor editor = getSharedPreferences("Token_Saved", MODE_PRIVATE).edit();


                                            SharedPreferences.Editor editor=sharedPreferences.edit();

                                            if (editor.equals(""))
                                            {
                                                SharedPreferences.Editor editor2 = sharedPreferences.edit();
                                                editor2.putString("token", value4);
                                                editor2.apply();


                                                Log.d("Added2" , "Added");

//                                                Toast.makeText(MainActivity.this, "Added..!!", Toast.LENGTH_SHORT).show();
                                            }

                                            else
                                            {
                                                editor.remove("token");
                                                editor.apply();


                                                SharedPreferences.Editor editor2 = sharedPreferences.edit();
                                                editor2.putString("token", value4);
                                                editor2.apply();

                                                Intent intent = new Intent(MainActivity.this, Home_Activity.class);
                                                intent.putExtra("Value", value2);
                                                intent.putExtra("Value2", value3);
                                                intent.putExtra("Value3", value4);
                                                startActivity(intent);

                                                Log.d("Added" , "Added And Deleted..!!");




                                            }









                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }





                                    }

                                    else  if (response.code() == 200 && value2.equals("error") )
                                    {

                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Utilss.hideSweetLoader(pDialog);
                                            }
                                        });


                                        value5 = json.getString("message");

                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {


                                                SweetAlertDialog pDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE);
                                                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                                                pDialog.setTitleText(value5);
                                                pDialog.setCancelable(true);
                                                pDialog.show();
                                                return;
                                            }
                                        });




                                    }


                                }

                                catch (JSONException e)
                                {

                                }







                            }



                        });
                    }


                }


                else
                {
                    new LovelyChoiceDialog(MainActivity.this)
                            .setTopColorRes(R.color.darkGreen)
                            .setTitle("Error Message")
                            .setIcon(R.drawable.ic_assignment_white_36dp)
                            .setMessage("No Internet Connected").show();

                }

            }
        });
    }


    public  boolean isNetAvailable()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo =connectivityManager.getActiveNetworkInfo();
        return networkInfo !=null && networkInfo.isConnected();
    }

    }
//}
