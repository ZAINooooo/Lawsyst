package com.example.lawsyst;

import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import static android.content.Context.MODE_PRIVATE;


public class Time_Listing_Fragment extends Fragment implements Time_ListingAdapter.ContactsAdapterListener {


    public RecyclerView data_list;
    public Time_ListingAdapter dataListAdapter;
    public ArrayList<Time_Listing_Pojo> contactList;
    public ArrayList<Time_Listing_Pojo> contactList2;


    protected SweetAlertDialog pDialog;
    TextView alter_txt;
    SearchView searchView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    Toolbar toolbar;


    SharedPreferences sharedPreferences;
    String access_token;

    public Time_Listing_Fragment() {
        // Required empty public constructor
    }

    public static Time_Listing_Fragment newInstance() {
        Time_Listing_Fragment fragment = new Time_Listing_Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_time__listing_, container, false);

        sharedPreferences = getActivity().getSharedPreferences("DATA", MODE_PRIVATE);
        access_token = sharedPreferences.getString("token", "");

        contactList = new ArrayList<>();
        contactList2 = new ArrayList<>();


        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) view.findViewById(R.id.search);
        searchView.setSearchableInfo(searchManager.getSearchableInfo( getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        data_list = view.findViewById(R.id.data_list);

        dataListAdapter = new Time_ListingAdapter(getActivity(), contactList, this);
        dataListAdapter.setDataList(contactList);

//        whiteNotificationBar(data_list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        data_list.setLayoutManager(mLayoutManager);
        data_list.setItemAnimator(new DefaultItemAnimator());
        data_list.addItemDecoration(new MyDividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL, 36));
        data_list.setAdapter(dataListAdapter);

        alter_txt = view.findViewById(R.id.tv_altText);


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


        // Set a Refresh Listener for the SwipeRefreshLayout
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh the data
                // Calls setRefreshing(false) when it is finish
                updateOperation();
            }
        });



        return view;
    }

            private void updateOperation()
        {

            contactList2=new ArrayList<>();
            RequestQueue queue = Volley.newRequestQueue(getActivity());
            String url = "https://demo.lawsyst.com/mobile-app/json-call/json_timeentry_listing.php";
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Utilss.hideSweetLoader(pDialog);
                                }
                            });

                            Log.d("Json-response4",response);




                            try {
                                JSONObject respone = new JSONObject(response);
                                JSONArray data = respone.getJSONArray("data");
                                for (int i = 0; i < data.length(); i++) {
                                    JSONObject jsonObject = new JSONObject(String.valueOf(data.get(i)));
                                    Time_Listing_Pojo dataObject = new Time_Listing_Pojo();


                                    dataObject.setId(jsonObject.getString("id"));
                                    dataObject.setMatterid(jsonObject.getString("matterid"));
                                    dataObject.setContactid(jsonObject.getString("contactid"));
                                    dataObject.setItemid(jsonObject.getString("itemid"));
                                    dataObject.setUserid(jsonObject.getString("userid"));
                                    dataObject.setTimeentry_status(jsonObject.getString("timeentry_status"));
                                    dataObject.setHourlyrate(jsonObject.getString("hourlyrate"));
                                    dataObject.setMattername(jsonObject.getString("mattername"));
                                    dataObject.setResource(jsonObject.getString("resource"));
                                    dataObject.setItem(jsonObject.getString("item"));
                                    dataObject.setBillrefno(jsonObject.getString("billrefno"));
                                    contactList2.add(dataObject);
                                    dataListAdapter.notifyDataSetChanged();
//
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            setuprecyclerview(contactList2);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
//                Log.d("Json-response Error",error.getMessage());

                    getActivity().runOnUiThread(new Runnable() {
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
            mSwipeRefreshLayout.setRefreshing(false);


        }

    private void setuprecyclerview(ArrayList<Time_Listing_Pojo> contactList2)
    {
                            dataListAdapter = new Time_ListingAdapter(getActivity(),contactList2,this) ;
                            data_list.setLayoutManager(new LinearLayoutManager(getActivity()));
                            data_list.setAdapter(dataListAdapter);
    }


//    private void whiteNotificationBar(View view) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            int flags = view.getSystemUiVisibility();
//            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
//            view.setSystemUiVisibility(flags);
//            getActivity().getWindow().setStatusBarColor(Color.WHITE);
//        }
//    }



    public void listLoadTask()
    {
                pDialog = Utilss.showSweetLoader(getActivity(), SweetAlertDialog.PROGRESS_TYPE, "Fetching Data...");

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = "https://demo.lawsyst.com/mobile-app/json-call/json_timeentry_listing.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Utilss.hideSweetLoader(pDialog);
                            }
                        });

                        Log.d("Json-response4",response);




                        try {
                            JSONObject respone = new JSONObject(response);
                            JSONArray data = respone.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject jsonObject = new JSONObject(String.valueOf(data.get(i)));
                                Time_Listing_Pojo dataObject = new Time_Listing_Pojo();


                                dataObject.setId(jsonObject.getString("id"));
                                dataObject.setMatterid(jsonObject.getString("matterid"));
                                dataObject.setContactid(jsonObject.getString("contactid"));
                                dataObject.setItemid(jsonObject.getString("itemid"));
                                dataObject.setUserid(jsonObject.getString("userid"));
                                dataObject.setTimeentry_status(jsonObject.getString("timeentry_status"));
                                dataObject.setHourlyrate(jsonObject.getString("hourlyrate"));
                                dataObject.setMattername(jsonObject.getString("mattername"));
                                dataObject.setResource(jsonObject.getString("resource"));
                                dataObject.setItem(jsonObject.getString("item"));
                                dataObject.setBillrefno(jsonObject.getString("billrefno"));
                                contactList.add(dataObject);
                                dataListAdapter.notifyDataSetChanged();
//                                dataListAdapter.notifyDataSetChanged();


//                                Log.d("assigntoid",jsonObject.getString("assigntoid"));

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Log.d("Json-response Error",error.getMessage());

                getActivity().runOnUiThread(new Runnable() {
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
    public void onContactSelected(Time_Listing_Pojo contact) {

    }
}
