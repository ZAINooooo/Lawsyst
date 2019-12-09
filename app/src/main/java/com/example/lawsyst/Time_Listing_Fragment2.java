//package com.example.lawsyst;
//
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.support.v7.widget.DefaultItemAnimator;
//import android.support.v7.widget.DividerItemDecoration;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Objects;
//
//import cn.pedant.SweetAlert.SweetAlertDialog;
//
//
//public class Time_Listing_Fragment2 extends Fragment  {
//
//
//    public RecyclerView data_list;
//    public Time_ListingAdapter dataListAdapter;
//    public ArrayList<Time_Listing_Pojo> data_array_list;
//    private ArrayList<Time_Listing_Pojo> lstAnime2;
//
//    protected SweetAlertDialog pDialog;
//
//    private SwipeRefreshLayout mSwipeRefreshLayout;
//    android.support.design.widget.FloatingActionButton floatingActionButton;
//
//
//
//
//    public Time_Listing_Fragment2() {
//        // Required empty public constructor
//    }
//
//    public static Matters_Fragment newInstance(String param1, String param2) {
//        Matters_Fragment fragment = new Matters_Fragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view =  inflater.inflate(R.layout.fragment_time__listing_, container, false);
//
//
//
//        data_array_list = new ArrayList<>();
//        lstAnime2= new ArrayList<>();
//
//        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
//        data_list = view.findViewById(R.id.data_list);
//
//        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
//
//
////        loginTask();
//
//        listLoadTask();
//
//
//
//        // Set a Refresh Listener for the SwipeRefreshLayout
//        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                // Refresh the data
//                // Calls setRefreshing(false) when it is finish
//                updateOperation();
//            }
//        });
//
//
//
////        floatingActionButton= view.findViewById(R.id.fab);
////
////
////        floatingActionButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////
////                Intent intent = new Intent(getActivity() , AddTimeEntry.class);
//////                intent.putExtra("matterid" , );
//////                intent.putExtra("userid" ,MainActivity.value7 );
////                startActivity(intent);
////            }
////        });
//
//
//
//        return view;
//    }
//
//
//
//        private void updateOperation()
//        {
//            lstAnime2= new ArrayList<>();
//
//            RequestQueue queue = Volley.newRequestQueue(getActivity());
//            String url = "https://demo.lawsyst.com/mobile-app/json-call/json_timeentry_listing.php";
//            StringRequest stringRequest = new StringRequest(Request.Method.GET , url,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//
//                            if (getActivity() != null) {
//                                getActivity().runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        Utilss.hideSweetLoader(pDialog);
//                                    }
//                                });
//                            }
//
//
//                            Log.d("Json-response1", response);
//
//                            try {
//                                JSONObject respone = new JSONObject(response);
//                                JSONArray data = respone.getJSONArray("data");
//                                for (int i = 0; i < data.length(); i++) {
//                                    JSONObject jsonObject = new JSONObject(String.valueOf(data.get(i)));
//                                    Time_Listing_Pojo dataObject = new Time_Listing_Pojo();
//
//
//                                    dataObject.setId(jsonObject.getString("id"));
//                                    dataObject.setMatterid(jsonObject.getString("matterid"));
//                                    dataObject.setContactid(jsonObject.getString("contactid"));
//                                    dataObject.setItemid(jsonObject.getString("itemid"));
//                                    dataObject.setUserid(jsonObject.getString("userid"));
//                                    dataObject.setTimeentry_status(jsonObject.getString("timeentry_status"));
//                                    dataObject.setHourlyrate(jsonObject.getString("hourlyrate"));
//                                    dataObject.setMattername(jsonObject.getString("mattername"));
//                                    dataObject.setResource(jsonObject.getString("resource"));
//                                    dataObject.setItem(jsonObject.getString("item"));
//                                    dataObject.setBillrefno(jsonObject.getString("billrefno"));
//                                    lstAnime2.add(dataObject);
//                                    dataListAdapter.notifyDataSetChanged();
//
//
////                                Log.d("assigntoid",jsonObject.getString("assigntoid"));
//
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                            dataListAdapter = new Time_ListingAdapter(getActivity(),lstAnime2) ;
//                            data_list.setLayoutManager(new LinearLayoutManager(getActivity()));
//                            data_list.setAdapter(dataListAdapter);
//                        }
//
//
//
//                    }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//
//                    getActivity().runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Utilss.hideSweetLoader(pDialog);
//                        }
//                    });
//                }
//            })
//
//
//
//
//
//            {
//                @Override
//                public Map getHeaders() {
//                    HashMap headers = new HashMap();
//                    headers.put("token", access_token);
//                    return headers;
//                }
//            };
//            queue.add(stringRequest);
//
//            mSwipeRefreshLayout.setRefreshing(false);
//
//
//
//
//        }
//
//
//
//    public void listLoadTask()
//    {
//        pDialog = Utilss.showSweetLoader(getActivity(), SweetAlertDialog.PROGRESS_TYPE, "Submitting...");
//
//        RequestQueue queue = Volley.newRequestQueue(getActivity());
//        String url = "https://demo.lawsyst.com/mobile-app/json-call/json_timeentry_listing.php";
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//
//                        getActivity().runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Utilss.hideSweetLoader(pDialog);
//                            }
//                        });
//
//                        Log.d("Json-response4",response);
//
//
//
//
//                        try {
//                            JSONObject respone = new JSONObject(response);
//                            JSONArray data = respone.getJSONArray("data");
//                            for (int i = 0; i < data.length(); i++) {
//                                JSONObject jsonObject = new JSONObject(String.valueOf(data.get(i)));
//                                Time_Listing_Pojo dataObject = new Time_Listing_Pojo();
//
//
//                                dataObject.setId(jsonObject.getString("id"));
//                                dataObject.setMatterid(jsonObject.getString("matterid"));
//                                dataObject.setContactid(jsonObject.getString("contactid"));
//                                dataObject.setItemid(jsonObject.getString("itemid"));
//                                dataObject.setUserid(jsonObject.getString("userid"));
//                                dataObject.setTimeentry_status(jsonObject.getString("timeentry_status"));
//                                dataObject.setHourlyrate(jsonObject.getString("hourlyrate"));
//                                dataObject.setMattername(jsonObject.getString("mattername"));
//                                dataObject.setResource(jsonObject.getString("resource"));
//                                dataObject.setItem(jsonObject.getString("item"));
//                                dataObject.setBillrefno(jsonObject.getString("billrefno"));
//                                data_array_list.add(dataObject);
////                                dataListAdapter.notifyDataSetChanged();
//
//
////                                Log.d("assigntoid",jsonObject.getString("assigntoid"));
//
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                        setuprecyclerview(data_array_list);
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
////                Log.d("Json-response Error",error.getMessage());
//
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Utilss.hideSweetLoader(pDialog);
//                    }
//                });
//            }
//        }){
//            @Override
//            public Map getHeaders() throws AuthFailureError {
//                HashMap headers = new HashMap();
//                headers.put("token", access_token);
//                return headers;
//            }
//        };
//        queue.add(stringRequest);
//    }
//
//
//
//
//
//    private void setuprecyclerview(ArrayList<Time_Listing_Pojo> data_array_list) {
//
//        dataListAdapter = new Time_ListingAdapter(getActivity(),data_array_list) ;
//        data_list.setLayoutManager(new LinearLayoutManager(getActivity()));
//        data_list.setItemAnimator(new DefaultItemAnimator());
//        data_list.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getActivity()), DividerItemDecoration.VERTICAL));
//        data_list.setAdapter(dataListAdapter);
//        dataListAdapter.notifyDataSetChanged();
//        data_list.scheduleLayoutAnimation();
//    }
//}
