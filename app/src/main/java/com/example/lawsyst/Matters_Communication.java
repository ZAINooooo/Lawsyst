package com.example.lawsyst;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;


public class Matters_Communication extends AppCompatActivity {


    public RecyclerView data_list;
    public MattersCommuncationAdapter dataListAdapter;
    public ArrayList<Matters_Communication_Pojo> data_array_list;
    public ArrayList<Matters_Communication_Pojo> data_array_list2;

    OkHttpClient client;
    SharedPreferences sharedPreferences;
    String access_token;
    protected SweetAlertDialog pDialog;
    TextView alter_txt;
    Toolbar toolbar;

    ImageView im_payment_type;

String matter_id,fileName,fileNameSingle;
ImageView iv_back;

FloatingActionButton floatingActionButton;
    SwipeRefreshLayout mSwipeRefreshLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matters__communication);


        sharedPreferences = getSharedPreferences("DATA", MODE_PRIVATE);
        access_token = sharedPreferences.getString("token", "");


        data_array_list = new ArrayList<>();
        data_array_list2 = new ArrayList<>();

        data_list = findViewById(R.id.data_list);

        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);


        dataListAdapter = new MattersCommuncationAdapter(Matters_Communication.this);
        dataListAdapter.setDataList(data_array_list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(Matters_Communication.this);
        data_list.setLayoutManager(mLayoutManager);
        data_list.setItemAnimator(new DefaultItemAnimator());
        data_list.setAdapter(dataListAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(data_list.getContext(), DividerItemDecoration.VERTICAL);
        data_list.addItemDecoration(dividerItemDecoration);

        alter_txt = findViewById(R.id.tv_altText);






        matter_id = getIntent().getStringExtra("MatterId");
        Log.d("Matter_Ids" , matter_id);

        client = new OkHttpClient();
        floatingActionButton= findViewById(R.id.fab);


        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateLoadTask();
            }
        });


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setType("*/*");
                startActivityForResult(intent, 7);


//                addnewcommunication();
            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() !=null)
        {
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        }




        iv_back = (ImageView) findViewById(R.id.iv_back);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });




        listLoadTask();



    }

    private void updateLoadTask()
    {

//        pDialog = Utilss.showSweetLoader(Matters_Communication.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");

        RequestQueue queue = Volley.newRequestQueue(Matters_Communication.this);
        String url = "https://demo.lawsyst.com/mobile-app/json-call/json_matter_history.php?action=gethistory&matterid="+matter_id;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//                                Utilss.hideSweetLoader(pDialog);
                            }
                        });

                        Log.d("Json-response2",response);

                        try {
                            JSONObject respone = new JSONObject(response);


                            if(response.length()== 0)

                            {
                                alter_txt.setVisibility(View.VISIBLE);
                            }

                            else
                            {

                                alter_txt.setVisibility(View.GONE);

                                JSONArray data = respone.getJSONArray("data");
                                data_array_list.clear();


                                for (int i = 0; i < data.length(); i++)
                                {
                                    JSONObject jsonObject = new JSONObject(String.valueOf(data.get(i)));
                                    Matters_Communication_Pojo dataObject = new Matters_Communication_Pojo();
                                    dataObject.setId(jsonObject.getString("id"));
                                    dataObject.setMatterid(jsonObject.getString("matterid"));
                                    dataObject.setFromname(jsonObject.getString("fromname"));
                                    dataObject.setDate(jsonObject.getString("datetime"));
                                    dataObject.setReceivername(jsonObject.getString("receivername"));
                                    dataObject.setCommtype(jsonObject.getString("commtype"));
                                    dataObject.setCommunicationstatus(jsonObject.getString("communicationstatus"));
                                    dataObject.setMattername(jsonObject.getString("mattername"));
                                    dataObject.setHttppath(jsonObject.getString("httppath"));
                                    dataObject.setDirectPath(jsonObject.getString("directpath"));
                                    dataObject.setSenderid(jsonObject.getString("senderid"));
                                    dataObject.setInoutward(jsonObject.getString("inoutward"));
                                    dataObject.setFile_name(jsonObject.getString("file_name"));
//                                    dataObject.setDate(jsonObject.getString("datetime"));

                                    data_array_list.add(dataObject);
                                    dataListAdapter.notifyDataSetChanged();

                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Log.d("Json-response Error",error.getMessage());

                Matters_Communication.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        Utilss.hideSweetLoader(pDialog);
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

        if(mSwipeRefreshLayout.isRefreshing()){
            mSwipeRefreshLayout.setRefreshing(false);
        }



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub

        switch (requestCode) {
            case 7:
                if (resultCode == RESULT_OK) {
//                    Uri PathHolder = data.getData();

                    if(data.getClipData() != null) {
                        int count = data.getClipData().getItemCount(); //evaluate the count before the for loop --- otherwise, the count is evaluated every loop.
                        for(int i = 0; i < count; i++)
                        {

                            Uri imageUri = data.getClipData().getItemAt(i).getUri();

                           String[] filePathColumn = {MediaStore.Images.Media.DATA, MediaStore.Images.Media.DISPLAY_NAME};

                            Cursor cursor = getContentResolver().query(imageUri, filePathColumn, null, null, null);
                            if (cursor.moveToFirst())
                            {
                                int fileNameIndex = cursor.getColumnIndex(filePathColumn[1]);
                                fileName = cursor.getString(fileNameIndex);

                                if (fileName.length() >1)
                                {
                                    Toast.makeText(this, "More then 1 result", Toast.LENGTH_SHORT).show();
                                }
                            }
                            cursor.close();

                            Log.d("ImageA" , ""+fileName);




                            //do something with the image (save it to some directory or whatever you need to do with it here)
                    }
                } else if(data.getData() != null) {
//                    String imagePath = data.getData().getPath();
                        Uri imagePath = data.getData();

                        String[] filePathColumn = {MediaStore.Images.Media.DATA, MediaStore.Images.Media.DISPLAY_NAME};
                        Cursor cursor = getContentResolver().query(imagePath, filePathColumn, null, null, null);

                        if (cursor.moveToFirst())
                        {
                            int fileNameIndex = cursor.getColumnIndex(filePathColumn[1]);
                            fileName = cursor.getString(fileNameIndex);

                            if (fileName.length() <1)
                            {
                                Toast.makeText(this, "1 result", Toast.LENGTH_SHORT).show();
                            }
                        }

                        cursor.close();


                        Log.d("ImageA" , ""+fileName);



                            pDialog = Utilss.showSweetLoader(Matters_Communication.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");

                            RequestBody formBody = new FormBody.Builder().add("action", "insertmattercommunication").add("matterid", matter_id)
                                    .add("file", fileName).build();

                            final okhttp3.Request request = new okhttp3.Request.Builder()
                                    .url("https://demo.lawsyst.com/mobile-app/json-call/json_save_matter_communication_history.php")
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


                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {

                                            SweetAlertDialog pDialog = new SweetAlertDialog(Matters_Communication.this, SweetAlertDialog.ERROR_TYPE);
                                            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                                            pDialog.setTitleText("Error In Uploading..!!");
                                            pDialog.setCancelable(true);
                                            pDialog.show();
                                            return;
                                        }
                                    });




                                }

                                @Override
                                public void onResponse(Call call, okhttp3.Response response) throws IOException {


                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Utilss.hideSweetLoader(pDialog);
                                        }
                                    });

                                    String responses = response.body().string();
                                    Log.e("response", "onResponse(): " + responses);

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {

                                            SweetAlertDialog pDialog = new SweetAlertDialog(Matters_Communication.this, SweetAlertDialog.NORMAL_TYPE);
                                            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                                            pDialog.setTitleText("Matter Added");
                                            pDialog.setCancelable(true);
                                            pDialog.show();
                                            return;

                                        }
                                    });
                                }
                            });
                }
                }
                break;
        }
    }

    public void listLoadTask()
    {
        pDialog = Utilss.showSweetLoader(Matters_Communication.this, SweetAlertDialog.PROGRESS_TYPE, "Fetching Data...");

        RequestQueue queue = Volley.newRequestQueue(Matters_Communication.this);
        String url = "https://demo.lawsyst.com/mobile-app/json-call/json_matter_history.php?action=gethistory&matterid="+matter_id;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Utilss.hideSweetLoader(pDialog);
                            }
                        });

                        Log.d("Json-response",response);

                        try {
                            JSONObject respone = new JSONObject(response);


                            if(response.length()== 0)

                            {
                                alter_txt.setVisibility(View.VISIBLE);
                            }

                            else
                            {

                                alter_txt.setVisibility(View.GONE);

                            JSONArray data = respone.getJSONArray("data");
                            data_array_list.clear();


                            for (int i = 0; i < data.length(); i++)
                            {
                                JSONObject jsonObject2 = new JSONObject(String.valueOf(data.get(i)));
                                Matters_Communication_Pojo dataObject = new Matters_Communication_Pojo();
                                dataObject.setId(jsonObject2.getString("id"));
                                dataObject.setMatterid(jsonObject2.getString("matterid"));
                                dataObject.setFromname(jsonObject2.getString("fromname"));
                                dataObject.setDate(jsonObject2.getString("datetime"));
                                dataObject.setReceivername(jsonObject2.getString("receivername"));
                                dataObject.setCommtype(jsonObject2.getString("commtype"));
                                dataObject.setCommunicationstatus(jsonObject2.getString("communicationstatus"));
                                dataObject.setMattername(jsonObject2.getString("mattername"));
                                dataObject.setHttppath(jsonObject2.getString("httppath"));
                                dataObject.setDirectPath(jsonObject2.getString("directpath"));
                                dataObject.setSenderid(jsonObject2.getString("senderid"));
                                dataObject.setInoutward(jsonObject2.getString("inoutward"));
                                dataObject.setFile_name(jsonObject2.getString("file_name"));
//                                    dataObject.setDate(jsonObject.getString("datetime"));d"));

                                data_array_list.add(dataObject);
                                dataListAdapter.notifyDataSetChanged();

                            }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Log.d("Json-response Error",error.getMessage());

                Matters_Communication.this.runOnUiThread(new Runnable() {
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

//        if(mSwipeRefreshLayout.isRefreshing()){
//            mSwipeRefreshLayout.setRefreshing(false);
//        }

    }















}
