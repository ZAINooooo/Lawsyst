package com.example.lawsyst;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

//import com.commonlibrary.util.Utils;
import com.github.angads25.toggle.LabeledSwitch;
import com.github.angads25.toggle.interfaces.OnToggledListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cafe.adriel.androidaudiorecorder.AndroidAudioRecorder;
import cafe.adriel.androidaudiorecorder.model.AudioChannel;
import cafe.adriel.androidaudiorecorder.model.AudioSampleRate;
import cafe.adriel.androidaudiorecorder.model.AudioSource;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Home_Activity extends BaseActivity implements DrawerLayout.DrawerListener{

    private String TAG = "Home_Activity";
    CircleImageView img_navHeader;
    private NavigationView navView;
    private TextView tvUserName,user_code;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    SharedPreferences sharedPreferences;

    private SharedPreferences app_prefs;

    private Fragment fragment;

    private boolean clicked = false;
    private boolean clicked2 = false;


//    boolean status = false;

    private LinearLayout llDebugContainer2;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    List<Integer> icons;
    Toolbar toolbar;

    public  TextView tvToolbarTitle;
    String value_is,value_is2,value_is3,lo;

    private static final int REQUEST_RECORD_AUDIO = 0;
    private static final String AUDIO_FILE_PATH =
            Environment.getExternalStorageDirectory().getPath() + "/recorded_audio.wav";

RelativeLayout rel_click,rel_click2;

    final int[] prevExpandPosition = {-1};
    DrawerLayout drawer;
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle drawerToggle;

    private static final String DASHBOARD = "Home";
    private static final String LEGAL_AID = "Legal Aid";
    private static final String BILLS = "Disbursements";
    private static final String INVOICES = "Purchase Invoices";


    private static final String PROFILE = "Profile";
    private static final String CHANGE_PASSWORD = "Change Password";
    private static final String ACCOUNT_SETTING = "Account Setting";
    private static final String LOGOUT = "Logout";

    private static final String CONTACTS = "Contacts";
    private static final String MATTERS = "Matters";
    private static final String TIME_ENTRY = "Time Entry";
    private static final String CLIENTS = "Clients";

    private static final String LEAD_MANAGEMENT = "Lead Management";
    private static final String NEW_ENQUIRY = "New Enquiries";
    private static final String TRANSCRIBER = "Transcriber";
    private static final String TIME_RECORDER = "Time Recorder";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sharedPreferences = getSharedPreferences("DATA", MODE_PRIVATE);

        navView = findViewById(R.id.navView_main);
        img_navHeader = findViewById(R.id.img_navHeader);
        rel_click = findViewById(R.id.rel_click);
        rel_click2 = findViewById(R.id.rel_click2);

//        expand2= findViewById(R.id.expand2);


        tvUserName = findViewById(R.id.tv_navHeader_username);
        user_code= findViewById(R.id.user_code);
         value_is = getIntent().getStringExtra("Value");
         value_is2 = getIntent().getStringExtra("Value2");

         value_is3 = getIntent().getStringExtra("Value3");
         Log.d("Token_Value" , value_is3);

        tvUserName.setText(value_is);
        user_code.setText(value_is2);

        toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);


        if (getSupportActionBar() !=null)
        {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            tvToolbarTitle = toolbar.findViewById(R.id.tv_toolbar_title);
            init();
            initToolbar();
        }

        launchFragment(Home_Fragment.class, "home");

        Util.requestPermission(this, Manifest.permission.RECORD_AUDIO);
        Util.requestPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        tempMethodForSideDrawer();

img_navHeader.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

    }
});

        rel_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(clicked)
                {
                    tempMethodForSideDrawer();
                    clicked =false;

                    rel_click2.setVisibility(View.VISIBLE);
                    rel_click.setVisibility(View.GONE);


//                    Toast.makeText(Home_Activity.this, "1", Toast.LENGTH_SHORT).show();


                }

                else
                {
                    tempMethodForSideDrawer2();
                    clicked =false;
                    rel_click2.setVisibility(View.VISIBLE);
                    rel_click.setVisibility(View.GONE);

//                    Toast.makeText(Home_Activity.this, "2", Toast.LENGTH_SHORT).show();

                }
            }
        });


        rel_click2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(clicked2)
                {
                    tempMethodForSideDrawer2();
                    clicked2 =false;

                    rel_click2.setVisibility(View.GONE);
                    rel_click.setVisibility(View.VISIBLE);

//                    Toast.makeText(Home_Activity.this, "3", Toast.LENGTH_SHORT).show();


                }

                else
                {
                    tempMethodForSideDrawer();
                    clicked2 =false;
                    rel_click2.setVisibility(View.GONE);
                    rel_click.setVisibility(View.VISIBLE);


//                    Toast.makeText(Home_Activity.this, "4", Toast.LENGTH_SHORT).show();

                }

            }
        });





    }

    private void launchFragment(Class fragmentClass, String tag) {
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fl_MainContainer, fragment, tag);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_RECORD_AUDIO) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Audio recorded successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Home_Activity.this , Home_Activity.class);
                startActivity(intent);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Audio was not recorded", Toast.LENGTH_SHORT).show();
            }
        }
    }



    private void initToolbar() {

        //drawer view
        mDrawer = findViewById(R.id.dl_main);
        mDrawer.setDrawerListener(this);


        //Navigation view
        navView = findViewById(R.id.navView_main);

        drawerToggle = setupDrawerToggle();
        mDrawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);
    }


    private void init() {


        drawer = findViewById(R.id.dl_main);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        img_navHeader = findViewById(R.id.img_navHeader);

        tvUserName = findViewById(R.id.tv_navHeader_username);
        }

    private void tempMethodForSideDrawer() {
        // get the list view
        expListView = findViewById(R.id.lvExp);

        prepareListData();
        listAdapter = new ExpandableListAdapters(this, listDataHeader, listDataChild, icons);
        // setting list adapter
        expListView.setAdapter(listAdapter);
//
//        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                String str = listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);
                getRequiredFragment(str);

                return false;
            }
        });

        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {

                if (prevExpandPosition[0] >= 0 && prevExpandPosition[0] != groupPosition) {
                    expListView.collapseGroup(prevExpandPosition[0]);
                }
                prevExpandPosition[0] = groupPosition;

                try {
                    getRequiredFragment(listDataHeader.get(groupPosition));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {

            }
        });
    }





    private void tempMethodForSideDrawer2() {
        // get the list view
        expListView = findViewById(R.id.lvExp);

        prepareListData2();
        listAdapter = new ExpandableListAdapters2(this, listDataHeader, listDataChild, icons);
        // setting list adapter
        expListView.setAdapter(listAdapter);


        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {

                if (prevExpandPosition[0] >= 0 && prevExpandPosition[0] != groupPosition) {
                    expListView.collapseGroup(prevExpandPosition[0]);
                }
                prevExpandPosition[0] = groupPosition;

                try {
                    getRequiredFragment2(listDataHeader.get(groupPosition));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {

            }
        });
    }

    private void getRequiredFragment(String itemString) {
        Bundle bundle;
        Fragment frag = null;
        boolean status = false;

        Log.i("nav", itemString + " kjdgfqwjh");


        switch (itemString) {

            case DASHBOARD:
                status = true;
                tvToolbarTitle.setText(getStringWithResource("Home"));
                frag = new Home_Fragment();
                bundle = new Bundle();
                frag.setArguments(bundle);
                break;


            case LEGAL_AID:
                status = true;
                tvToolbarTitle.setText(getStringWithResource("Legal Aid"));
                frag = new Legal_Aid_Fragment();
                bundle = new Bundle();
                frag.setArguments(bundle);
                break;



            case BILLS:
                status = true;
                tvToolbarTitle.setText(getStringWithResource("Bills"));
                frag = new Bills();
                bundle = new Bundle();
                frag.setArguments(bundle);

                break;



            case INVOICES:
                status = true;
                tvToolbarTitle.setText(getStringWithResource("Invoices "));
                frag = new Invoice_Listing();
                bundle = new Bundle();
                frag.setArguments(bundle);

                break;


            case CONTACTS:
                status = true;
                tvToolbarTitle.setText(getStringWithResource("Contact "));
                frag = new Contacts_Listing();
                bundle = new Bundle();
                frag.setArguments(bundle);

                clicked=false;
                break;


            case MATTERS:
                status = true;
                tvToolbarTitle.setText(getStringWithResource("Client Matter "));
                frag = new Matters_Fragment();
                bundle = new Bundle();
                frag.setArguments(bundle);

                break;



            case TIME_ENTRY:
                status = true;
                tvToolbarTitle.setText(getStringWithResource("Time Entry "));
                frag = new Time_Listing_Fragment();
                bundle = new Bundle();
                frag.setArguments(bundle);

                break;



            case CLIENTS:
                status = true;
                tvToolbarTitle.setText(getStringWithResource("Client "));

                frag = new Client_Listing();
                bundle = new Bundle();
                frag.setArguments(bundle);

                break;


            case LEAD_MANAGEMENT:
                status = true;
                tvToolbarTitle.setText(getStringWithResource("Lead Management "));
                frag = new Home_Fragment();
                bundle = new Bundle();
                frag.setArguments(bundle);

                break;


            case NEW_ENQUIRY:
                status = true;
                tvToolbarTitle.setText(getStringWithResource("New Enquiry "));
                frag = new Home_Fragment();
                bundle = new Bundle();
                frag.setArguments(bundle);

                break;

            case  TRANSCRIBER :
                status = true;
                tvToolbarTitle.setText(getStringWithResource("Transcriber "));

                AndroidAudioRecorder.with(Home_Activity.this)
                        // Required
                        .setFilePath(AUDIO_FILE_PATH)
                        .setColor(ContextCompat.getColor(Home_Activity.this, R.color.recorder_bg))
                        .setRequestCode(REQUEST_RECORD_AUDIO)

                        .setSource(AudioSource.MIC)
                        .setChannel(AudioChannel.STEREO)
                        .setSampleRate(AudioSampleRate.HZ_48000)
                        .setAutoStart(false)
                        .setKeepDisplayOn(true)

                        .record();

                break;


            case  TIME_RECORDER :
                status = true;
                tvToolbarTitle.setText(getStringWithResource("Time Recorder "));
                frag = new Time_Recorder();
                bundle = new Bundle();
                frag.setArguments(bundle);

                break;

            default:
                break;
        }

        final Fragment finalFrag = frag;
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (finalFrag != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_MainContainer, finalFrag).commit();
                }
            }
        }, 400);


        if (drawer != null && status) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    private void getRequiredFragment2(String itemString) {
        Bundle bundle;
        Fragment frag = null;
//        boolean status = false;

        clicked=false;

        Log.i("nav", itemString + " kjdgfqwjh");


        switch (itemString) {


            case PROFILE:
                clicked = true;

                frag = new Custom_header();
                bundle = new Bundle();
                frag.setArguments(bundle);
                tvToolbarTitle.setText(getStringWithResource("Profile Screen"));

                break;




            case CHANGE_PASSWORD:

                clicked = true;

                frag = new Home_Fragment();
                bundle = new Bundle();
                frag.setArguments(bundle);
                tvToolbarTitle.setText(getStringWithResource("Change Password"));

                break;

            case ACCOUNT_SETTING:

                clicked = true;
                tvToolbarTitle.setText(getStringWithResource("Accounts Settings"));
                frag = new Home_Fragment();
                bundle = new Bundle();
                frag.setArguments(bundle);
                break;

            case LOGOUT:
                clicked = true;
                showLogoutDailog();

            break;

            default:
                break;
        }

        final Fragment finalFrag = frag;
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (finalFrag != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_MainContainer, finalFrag).commit();
                }
            }
        }, 400);


        if (drawer != null && clicked) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    private void showLogoutDailog() {

        final Dialog dialog = new Dialog(Home_Activity.this, R.style.DialogSlideAnim_leftright);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.logout_dialog);

        TextView btn_logout_yes = (TextView) dialog.findViewById(R.id.btn_logout_yes);
        TextView btn_logout_no = (TextView) dialog.findViewById(R.id.btn_logout_no);


        btn_logout_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

                pDialog = Utilss.showSweetLoader(Home_Activity.this, SweetAlertDialog.PROGRESS_TYPE, "Loading...");


//                SharedPreferences pref = getSharedPreferences("Token_Saved", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = pref.edit();
//                editor.remove("Token_Saved");
//                editor.clear();
//                editor.apply();

                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.remove("token");
//                editor.putBoolean("isLogin",false);
                editor.apply();
//                smartDialog.dismiss();
//                startActivity(new Intent(Home_Screen.this , LoginActivity.class));
//                finish();

//                Toast.makeText(Home_Activity.this, "Deleted..!!", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(Home_Activity.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                finish();
                startActivity(i);

            }
        });

        btn_logout_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    private String getStringWithResource(String name) {
        return String.format(getResources().getString(R.string.fragment_headers), name);
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();
        icons = new ArrayList<>();

        // Adding icons
        icons.add(R.drawable.icon_1);
        icons.add(R.drawable.icon_2);
        icons.add(R.drawable.icon_3);
        icons.add(R.drawable.icon_4);
        icons.add(R.drawable.icon_5);
        icons.add(R.drawable.icon_6);
        icons.add(R.drawable.icon_7);
        icons.add(R.drawable.icon_8);
        icons.add(R.drawable.icon_9);
        icons.add(R.drawable.ic_book);
        icons.add(R.drawable.ic_logout);

        // Adding child data
        listDataHeader.add("Home");                //0
        listDataHeader.add("Accounts");                //1
        listDataHeader.add("Client And Matters");   //2
        listDataHeader.add("CRM");         //3
        listDataHeader.add("Transcriber");         //4
        listDataHeader.add("Time Recorder");         //5



        // Adding child data
        List<String> portfolio = new ArrayList<>();
        portfolio.add("Legal Aid");
        portfolio.add("Disbursements");
        portfolio.add("Purchase Invoices");

        List<String> performanceManagment = new ArrayList<>();
        performanceManagment.add("Contacts");
        performanceManagment.add("Matters");
        performanceManagment.add("Time Entry");
        performanceManagment.add("Clients");

        List<String> salesManagement = new ArrayList<>();
        salesManagement.add("Lead Management");
        salesManagement.add("New Enquiries");
        listDataChild.put(listDataHeader.get(1), portfolio); // Header, Child data
        listDataChild.put(listDataHeader.get(2), performanceManagment);
        listDataChild.put(listDataHeader.get(3), salesManagement);
    }





    private void prepareListData2() {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();
        icons = new ArrayList<>();

        // Adding icons
        icons.add(R.drawable.icon_1);
        icons.add(R.drawable.icon_2);
        icons.add(R.drawable.icon_3);
        icons.add(R.drawable.icon_4);


        // Adding child data
        listDataHeader.add("Profile");                //0
        listDataHeader.add("Change Password");                //1
        listDataHeader.add("Account Setting");   //2
        listDataHeader.add("Logout");         //3

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onDrawerSlide(@NonNull View view, float v) {

    }

    @Override
    public void onDrawerOpened(@NonNull View view) {

    }

    @Override
    public void onDrawerClosed(@NonNull View view) {

    }

    @Override
    public void onDrawerStateChanged(int i) {

    }


    @Override
    public void onBackPressed() {
        backButtonHandler();
    }


    public void backButtonHandler() {

        final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(Home_Activity.this);
        builder.setTitle("Warning Message");
        builder.setMessage("Are you sure you want to leave the application?");
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which)
                    {
//                        dialog.dismiss();
//                       finish();

                        dialog.dismiss();
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }
                });
        builder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }



}
