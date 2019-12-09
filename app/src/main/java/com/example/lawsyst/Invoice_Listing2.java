//package com.example.lawsyst;
//
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.DefaultItemAnimator;
//import android.support.v7.widget.DividerItemDecoration;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
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
//import java.util.Map;
//
//import cn.pedant.SweetAlert.SweetAlertDialog;
//
//
//public class Invoice_Listing2 extends Fragment {
//
//
//    public RecyclerView data_list;
//    public InvoiceAdapter dataListAdapter;
//    public ArrayList<Invoice_Listing_Pojo> data_array_list;
//    protected SweetAlertDialog pDialog;
//    TextView alter_txt;
//
//    Toolbar toolbar;
//
//
//    public Invoice_Listing2() {
//        // Required empty public constructor
//    }
//
//    public static Invoice_Listing2 newInstance(String param1, String param2) {
//        Invoice_Listing2 fragment = new Invoice_Listing2();
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
//        View view =  inflater.inflate(R.layout.activity_invoice_listing, container, false);
//
//
////        toolbar = (Toolbar) view.findViewById(R.id.toolbar_main);
//
//
//
//
//        data_array_list = new ArrayList<>();
//        data_list = view.findViewById(R.id.data_list);
//
//        dataListAdapter = new InvoiceAdapter(getContext());
//        dataListAdapter.setDataList(data_array_list);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
//        data_list.setLayoutManager(mLayoutManager);
//        data_list.setItemAnimator(new DefaultItemAnimator());
//        data_list.setAdapter(dataListAdapter);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(data_list.getContext(), DividerItemDecoration.VERTICAL);
//        data_list.addItemDecoration(dividerItemDecoration);
//
//        alter_txt = view.findViewById(R.id.tv_altText);
//
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
//            String url = "https://demo.lawsyst.com/mobile-app/json-call/json_invoice_listing.php";
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
//                                        Invoice_Listing_Pojo dataObject = new Invoice_Listing_Pojo();
//                                        dataObject.setInvoicenum(jsonObject.getString("invoicenum"));
//                                        dataObject.setInvoiceid(jsonObject.getString("invoiceid"));
//                                        dataObject.setIssued_date(jsonObject.getString("issued_date"));
//                                        dataObject.setDuedate(jsonObject.getString("duedate"));
//                                        dataObject.setSTATUS(jsonObject.getString("STATUS"));
//                                        dataObject.setInvoicetotalamount(jsonObject.getString("invoicetotalamount"));
//                                        dataObject.setInvoicerefno(jsonObject.getString("invoicerefno"));
//                                        dataObject.setLandlord_email(jsonObject.getString("landlord_email"));
//
//                                        dataObject.setLandlord_fname(jsonObject.getString("landlord_fname"));
//                                        dataObject.setInvoicefilename(jsonObject.getString("invoicefilename"));
//                                        dataObject.setMattername(jsonObject.getString("mattername"));
//
//
//                                        data_array_list.add(dataObject);
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
//}
