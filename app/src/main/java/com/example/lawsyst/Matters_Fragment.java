package com.example.lawsyst;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.content.Context.MODE_PRIVATE;


public class Matters_Fragment extends Fragment implements MattersAdapter.ContactsAdapterListener {


    public RecyclerView data_list;
    public MattersAdapter dataListAdapter;
    public ArrayList<Matters_Pojo> contactList;
    public ArrayList<Matters_Pojo> contactList2;
    protected SweetAlertDialog pDialog;
    TextView alter_txt;
    SearchView searchView;
    String token_value;
    SwipeRefreshLayout mSwipeRefreshLayout;
    Toolbar toolbar;
    android.support.design.widget.FloatingActionButton floatingActionButton, fab2;

    SharedPreferences sharedPreferences;
    String access_token;
    public Matters_Fragment() {
        // Required empty public constructor
    }

    public static Matters_Fragment newInstance() {
        Matters_Fragment fragment = new Matters_Fragment();
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
        View view = inflater.inflate(R.layout.fragment_matters_, container, false);

        sharedPreferences = getActivity().getSharedPreferences("DATA", MODE_PRIVATE);
        access_token = sharedPreferences.getString("token", "");

        contactList = new ArrayList<>();
        contactList2 = new ArrayList<>();

        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) view.findViewById(R.id.search);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        data_list = view.findViewById(R.id.data_list);
        dataListAdapter = new MattersAdapter(getActivity(), contactList, this);
        dataListAdapter.setDataList(contactList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        data_list.setLayoutManager(mLayoutManager);
        data_list.setItemAnimator(new DefaultItemAnimator());
        data_list.addItemDecoration(new MyDividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL, 36));
        data_list.setAdapter(dataListAdapter);

        floatingActionButton = view.findViewById(R.id.fab);

        fab2 = view.findViewById(R.id.fab2);



        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), MattersDetailsScreen.class);
                intent.putExtra("TokenIs", access_token);
                startActivity(intent);
            }
        });


        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Filter_Matter_Screen.class);
                intent.putExtra("TokenIs", access_token);
                startActivity(intent);
            }
        });


        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                listLoadTask();
            }
        });



        //mahnoor
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

        listLoadTask();
        return view;
    }
//-mahnoor


    public void listLoadTask() {

        pDialog = Utilss.showSweetLoader(getActivity(), SweetAlertDialog.PROGRESS_TYPE, "Fetching Data...");
        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = "https://demo.lawsyst.com/mobile-app/json-call/json_matter_listing.php";
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        if (getActivity() != null) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Utilss.hideSweetLoader(pDialog);

                                }
                            });
                        }


                        Log.d("Json-response1", response);

                        try {
                            JSONObject respone = new JSONObject(response);
                            JSONArray data = respone.getJSONArray("data");
                            contactList.clear();

                            for (int i = 0; i < data.length(); i++) {
                                JSONObject jsonObject = new JSONObject(String.valueOf(data.get(i)));
                                Matters_Pojo dataObject = new Matters_Pojo();
                                dataObject.setStatusdescription(jsonObject.getString("statusdescription"));
                                dataObject.setClientname(jsonObject.getString("clientname"));
                                dataObject.setMattername(jsonObject.getString("mattername"));
                                dataObject.setSTATUS(jsonObject.getString("status"));
                                dataObject.setAssignto(jsonObject.getString("assignto"));
                                dataObject.setMatternumber(jsonObject.getString("matternumber"));
//                                dataObject.setLandlord_fname(jsonObject.getString("landlord_fname"));
//                                dataObject.setLandlord_lname(jsonObject.getString("landlord_lname"));
                                dataObject.setId(jsonObject.getString("id"));
//                                dataObject.setLandlord_id(jsonObject.getString("landlord_id"));
                                dataObject.setAssigntoid(jsonObject.getString("assigntoid"));
                                contactList.add(dataObject);
                                dataListAdapter.notifyDataSetChanged();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }


                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        Utilss.hideSweetLoader(pDialog);
                        Utilss.hideSweetLoader(pDialog);

                    }
                });
            }
        }) {
            @Override
            public Map getHeaders() {
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
    public void onContactSelected(Matters_Pojo contact) {

    }
}
