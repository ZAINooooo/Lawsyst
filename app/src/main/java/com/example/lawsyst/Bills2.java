//package com.example.lawsyst;
//
//import android.graphics.Color;
//import android.os.Build;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.DefaultItemAnimator;
//import android.support.v7.widget.DividerItemDecoration;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.SearchView;
//import android.support.v7.widget.Toolbar;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
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
//import java.util.List;
//import java.util.Map;
//
//import cn.pedant.SweetAlert.SweetAlertDialog;
//
//
//public class Bills2 extends Fragment implements BillsAdapter.ContactsAdapterListener{
//
//
//    private RecyclerView recyclerView;
//    private List<Bills_Pojo> contactList;
//    private BillsAdapter mAdapter;
//    private SearchView searchView;
//    TextView alter_txt;
//
//
//    Toolbar toolbar;
//
//
//    public Bills2() {
//        // Required empty public constructor
//    }
//
//    public static Bills2 newInstance() {
//        Bills2 fragment = new Bills2();
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
//        View view =  inflater.inflate(R.layout.activity_bills_listing, container, false);
//
//        recyclerView = view.findViewById(R.id.data_list);
//        contactList = new ArrayList<>();
//
//        mAdapter = new BillsAdapter(getActivity(), contactList, this);
//        alter_txt = view.findViewById(R.id.tv_altText);
//        // white background notification bar
//        whiteNotificationBar(recyclerView);
//
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.addItemDecoration(new MyDividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL, 36));
//        recyclerView.setAdapter(mAdapter);
//
//        alter_txt = view.findViewById(R.id.tv_altText);
//
//
//        listLoadTask();
//
//
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                // filter recycler view when query submitted
//                dataListAdapter.getFilter().filter(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String query) {
//                // filter recycler view when text is changed
//                dataListAdapter.getFilter().filter(query);
//                return false;
//            }
//        });
//
//
//
//        return view;
//    }
//
//    public void listLoadTask()
//    {
//        pDialog = Utilss.showSweetLoader(getActivity(), SweetAlertDialog.PROGRESS_TYPE, "Submitting...");
//
//        if (getActivity() != null)
//        {
//            RequestQueue queue = Volley.newRequestQueue(getActivity());
//            String url = "https://demo.lawsyst.com/mobile-app/json-call/json_bills_listing.php";
//            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//
//
//                            if (getActivity() != null)
//                            {
//                                getActivity().runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        Utilss.hideSweetLoader(pDialog);
//                                    }
//                                });
//                            }
//
//
//                            Log.d("Json-response1",response);
//
//
//
//
//                            try {
//                                JSONObject respone = new JSONObject(response);
//                                JSONArray data = respone.getJSONArray("data");
//
//
//                                if (data.length() == 0) {
//                                    alter_txt.setVisibility(View.VISIBLE);
//                                } else {
//
//
//                                    for (int i = 0; i < data.length(); i++) {
//                                        JSONObject jsonObject = new JSONObject(String.valueOf(data.get(i)));
//                                        Bills_Pojo dataObject = new Bills_Pojo();
//
//                                        dataObject.setId(jsonObject.getString("id"));
//                                        dataObject.setMattername(jsonObject.getString("mattername"));
//                                        dataObject.setBillreference(jsonObject.getString("billrefno"));
//                                        dataObject.setBill_id(jsonObject.getString("bill_id"));
//                                        dataObject.setReceiveddate(jsonObject.getString("receiveddate"));
//                                        dataObject.setEntrydate(jsonObject.getString("entrydate"));
//                                        dataObject.setDuedate(jsonObject.getString("duedate"));
//                                        dataObject.setSupplier(jsonObject.getString("supplier"));
//                                        dataObject.setClientid(jsonObject.getString("clientid"));
//                                        dataObject.setCLIENT(jsonObject.getString("CLIENT"));
//                                        dataObject.setBillamount(jsonObject.getString("billamount"));
//                                        dataObject.setCurrency(jsonObject.getString("currency"));
//                                        dataObject.setSTATUS(jsonObject.getString("STATUS"));
//                                        dataObject.setAddedby(jsonObject.getString("addedby"));
//                                        dataObject.setBillamount(jsonObject.getString("billtotalamount"));
//
//
//                                        contactList.add(dataObject);
//                                        dataListAdapter.notifyDataSetChanged();
////                                    Log.d("assigntoid",jsonObject.getString("assigntoid"));
//
//                                    }
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
////                Log.d("Json-response Error",error.getMessage());
//
//                    getActivity().runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Utilss.hideSweetLoader(pDialog);
//                        }
//                    });
//                }
//            }){
//                @Override
//                public Map getHeaders() throws AuthFailureError {
//                    HashMap headers = new HashMap();
//                    headers.put("token", access_token);
//                    return headers;
//                }
//            };
//            queue.add(stringRequest);
//        }
//
//    }
//
//
//
//    private void whiteNotificationBar(View view) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            int flags = view.getSystemUiVisibility();
//            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
//            view.setSystemUiVisibility(flags);
//            getActivity().getWindow().setStatusBarColor(Color.WHITE);
//        }
//    }
//
//
//    @Override
//    public void onContactSelected(Bills_Pojo contact) {
//
//    }
//}
