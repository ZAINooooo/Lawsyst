package com.example.lawsyst;

import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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


public class Client_Listing extends Fragment implements ClientAdapter.ContactsAdapterListener {


    public RecyclerView data_list;
    public ClientAdapter dataListAdapter;
    public ArrayList<Client_Listing_Pojo> contactList;
    protected SweetAlertDialog pDialog;
    TextView alter_txt;
    SearchView searchView;

    Toolbar toolbar;
    SharedPreferences sharedPreferences;
    String access_token;

    public Client_Listing() {
        // Required empty public constructor
    }

    public static Client_Listing newInstance() {
        Client_Listing fragment = new Client_Listing();
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
        View view =  inflater.inflate(R.layout.activity_client_listing, container, false);

        sharedPreferences = getActivity().getSharedPreferences("DATA", MODE_PRIVATE);
        access_token = sharedPreferences.getString("token", "");

        contactList = new ArrayList<>();

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) view.findViewById(R.id.search);
        searchView.setSearchableInfo(searchManager.getSearchableInfo( getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);


        data_list = view.findViewById(R.id.data_list);

        dataListAdapter = new ClientAdapter(getActivity(), contactList, this);
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



        return view;
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

        if (getActivity() != null)
        {
            RequestQueue queue = Volley.newRequestQueue(getActivity());
            String url = "https://demo.lawsyst.com/mobile-app/json-call/json_clients_listing.php?agency_id=524368";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            if (getActivity() != null)
                            {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Utilss.hideSweetLoader(pDialog);
                                    }
                                });
                            }


                            Log.d("Json-response1",response);




                            try {
                                JSONObject respone = new JSONObject(response);
                                JSONArray data = respone.getJSONArray("data");


                                if (data.length() == 0) {
                                    alter_txt.setVisibility(View.VISIBLE);
                                } else {


                                    for (int i = 0; i < data.length(); i++) {
                                        JSONObject jsonObject = new JSONObject(String.valueOf(data.get(i)));
                                        Client_Listing_Pojo dataObject = new Client_Listing_Pojo();
                                        dataObject.setLandlordid(jsonObject.getString("landlordid"));
                                        dataObject.setName(jsonObject.getString("name"));
                                        dataObject.setCurrencyShortDesc(jsonObject.getString("CurrencyShortDesc"));
                                        dataObject.setReferenceno(jsonObject.getString("referenceno"));
                                        dataObject.setCountry(jsonObject.getString("country"));
                                        dataObject.setLandlord_mobile(jsonObject.getString("landlord_mobile"));
                                        dataObject.setLandlord_email(jsonObject.getString("landlord_email"));
                                        dataObject.setCompany_name(jsonObject.getString("company_name"));
                                        contactList.add(dataObject);
                                        dataListAdapter.notifyDataSetChanged();
//                                    Log.d("assigntoid",jsonObject.getString("assigntoid"));

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

    }

    @Override
    public void onContactSelected(Client_Listing_Pojo contact) {

    }
}
