//package com.example.lawsyst;
//
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.DefaultItemAnimator;
//import android.support.v7.widget.DividerItemDecoration;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.SearchView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.DefaultRetryPolicy;
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
//import java.util.Locale;
//import java.util.Map;
//
//import cn.pedant.SweetAlert.SweetAlertDialog;
//
//
//public class Contacts_Listing2 extends Fragment implements SearchView.OnQueryTextListener {
//
//
//    public RecyclerView data_list;
//    public Contacts_ListingAdapter dataListAdapter;
//
//
//    public  ArrayList<Contacts_Listing_Pojo> data_array_list;
//    public ArrayList<Contacts_Listing_Pojo> arraylist;
//
//
//    protected SweetAlertDialog pDialog;
//    private SearchView searchView;
//
//    public Contacts_Listing2() {
//        // Required empty public constructor
//    }
//
//    public static Contacts_Listing2 newInstance() {
//        Contacts_Listing2 fragment = new Contacts_Listing2();
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
//        View view =  inflater.inflate(R.layout.fragment_contacts__listing, container, false);
//
//
//
//
//        searchView = view.findViewById(R.id.search);
//        searchView.setOnQueryTextListener(this);
//        data_array_list = new ArrayList<>();
//        arraylist = new ArrayList<>();
//
//        data_list = view.findViewById(R.id.data_list);
//
//        Log.d("hjhjh", data_array_list.size() + "");
//
//        dataListAdapter = new Contacts_ListingAdapter(getContext());
//        dataListAdapter.setDataList(data_array_list);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
//        data_list.setLayoutManager(mLayoutManager);
//        data_list.setItemAnimator(new DefaultItemAnimator());
//        data_list.setAdapter(dataListAdapter);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(data_list.getContext(), DividerItemDecoration.VERTICAL);
//        data_list.addItemDecoration(dividerItemDecoration);
//
//        listLoadTask();
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
//            String url = "https://demo.lawsyst.com/mobile-app/json-call/json_contact_listing.php";
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
//                                for (int i = 0; i < data.length(); i++) {
//                                    JSONObject jsonObject = new JSONObject(String.valueOf(data.get(i)));
//                                    Contacts_Listing_Pojo dataObject = new Contacts_Listing_Pojo();
//
//
//                                    dataObject.setName(jsonObject.getString("name"));
//                                    dataObject.setMobile_number(jsonObject.getString("phoneno"));
//                                    dataObject.setEmail(jsonObject.getString("email"));
//                                    dataObject.setCountry_code_no(jsonObject.getString("country_code_no"));
//
//                                    data_array_list.add(dataObject);
//                                    dataListAdapter.notifyDataSetChanged();
//
//
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                Log.d("Json-responseError", error.toString());
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
//
//            //10000 is the time in milliseconds adn is equal to 10 sec
//            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
//                    10000,
//                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//
//
//            queue.add(stringRequest);
//        }
//
//    }
//
//
//    @Override
//    public boolean onQueryTextSubmit(String query) {
//
//        return false;
//    }
//
//    @Override
//    public boolean onQueryTextChange(String newText) {
//        String text = newText;
//        if (text == null || text.trim().isEmpty()) {
//            resetSearch();
//            return false;
//        }
//        else
//        {
//            filter(text);
//        }
//        return false;
//    }
//
//    public void filter(String charText) {
//        charText = charText.toLowerCase(Locale.getDefault());
//        data_array_list.clear();
//        if (charText.length() == 0) {
//            data_array_list.addAll(arraylist);
//        } else {
//            for (Contacts_Listing_Pojo wp : arraylist) {
//                if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
//                    data_array_list.add(wp);
//                }
//            }
//        }
//        dataListAdapter.notifyDataSetChanged();
//    }
//
//
//
//
//    public void resetSearch() {
//        dataListAdapter = new Contacts_ListingAdapter(getContext());
//        dataListAdapter.setDataList(data_array_list);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
//        data_list.setLayoutManager(mLayoutManager);
//        data_list.setItemAnimator(new DefaultItemAnimator());
//        data_list.setAdapter(dataListAdapter);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(data_list.getContext(), DividerItemDecoration.VERTICAL);
//        data_list.addItemDecoration(dividerItemDecoration);
//    }
//
//
//
//}
