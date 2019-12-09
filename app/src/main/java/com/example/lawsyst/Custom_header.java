package com.example.lawsyst;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatDelegate;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import static android.app.Activity.RESULT_OK;

public class Custom_header extends Fragment {
    Context c;
    TextView username, designation, email, report_to, phone;
    String Username, Report_To, Designation, Email, Phone;
    Button editBtn;
    String userId;

    private ProgressDialog progressDialog;

    CircleImageView imag_view, navImageView;
    Uri tempUri;


    Spinner sp_country_reg, dpinner2,dpinner09,dpinner19;

    private ArrayList<String> countryCodes, countryCodesIso;


    static
    {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }



    public Custom_header() {

    }


    public static Custom_header newInstance() {
        Custom_header fragment = new Custom_header();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_custom_header, container, false);



//
        sp_country_reg = (Spinner) view.findViewById(R.id.dpinner5);
//
        countryCodes = new ArrayList<>();
        countryCodesIso = new ArrayList<>();
//
        dpinner09 = (Spinner) view.findViewById(R.id.dpinner09);
        dpinner19 = (Spinner) view.findViewById(R.id.dpinner19);
//
        setSpinners();
//
//
//
//
//
        dpinner2 = (Spinner) view.findViewById(R.id.dpinner2);
        dpinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener());
//
//
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, parseCountry());
        sp_country_reg.setAdapter(adapter);


        return view;

    }
//
//
    private void setSpinners() {
        ArrayAdapter<String> countryCodeAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, parseCountryCodes());
        dpinner09.setAdapter(countryCodeAdapter);
        dpinner19.setAdapter(countryCodeAdapter);
        TelephonyManager tm = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String countryCodeValue = tm.getNetworkCountryIso();
        for (int i = 0; i < countryCodesIso.size(); i++) {
            if (countryCodesIso.get(i).equalsIgnoreCase(countryCodeValue)) {
                dpinner09.setSelection(i);
                dpinner19.setSelection(i);
            }
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, parseCountry());
        sp_country_reg.setAdapter(adapter);

//        String[] lst_currency = getResources().getStringArray(R.array.currency);
//        Integer[] currency_imageArray = {R.drawable.us, R.drawable.ic_india};
//
//        SpinnerAdapter adapter_currencey = new SpinnerAdapter(getActivity(), R.layout.spinner_value_layout, lst_currency, currency_imageArray);
//        sp_curency_reg.setAdapter(adapter_currencey);

    }

    public ArrayList<String> parseCountryCodes() {
        String response = "";
        ArrayList<String> list = new ArrayList<String>();
        try {
            response = ReadFiles.readRawFileAsString(getActivity(), R.raw.countrycodes);

            JSONArray array = new JSONArray(response);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                list.add(object.getString("alpha-2") + " (" + object.getString("phone-code") + ")");
                countryCodes.add(object.getString("phone-code"));
                countryCodesIso.add(object.getString("alpha-2"));
            }

            Collections.sort(list);
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return list;
    }


    public ArrayList<String> parseCountry() {
        String response = "";
        ArrayList<String> list = new ArrayList<String>();
        try {
            response = ReadFiles.readRawFileAsString(getActivity(), R.raw.countrycodes);

            JSONArray array = new JSONArray(response);
            Log.d("mahi", "countries" + response);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                list.add(object.getString("name"));
            }

            Collections.sort(list);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
//
//
    public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
        {
//            Toast.makeText(parent.getContext(),
//                    "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
//                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }
    }
}




//
//
//
//
//        editBtn = view.findViewById(R.id.editBtn);
//
//        progressDialog = new ProgressDialog(getActivity());
//        progressDialog.setMessage("Please wait...");
//        progressDialog.setCancelable(false);
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//
//        inflater = this.getLayoutInflater();
//
////        @SuppressLint("InflateParams") View dialogView = inflater.inflate(R.layout.custom_dialog, null);
//
////        builder.setView(dialogView);
////
////        Button imageSelector = dialogView.findViewById(R.id.gallery);
////        Button camera = dialogView.findViewById(R.id.camera);
////        final AlertDialog dialog = builder.create();
//
////        imageSelector.setOnClickListener(v -> {
////            System.out.println("**** CAMERA IS CLICKED ");
////            galleryIntent();
////            dialog.dismiss();
////        });
////
////
////        camera.setOnClickListener(v -> {
////            System.out.println("**** CAMERA IS CLICKED **");
////            dispatchTakePictureIntent();
////            dialog.dismiss();
////        });
//
//
//        imag_view = view.findViewById(R.id.imag_view);
//
//
////        if (tempUri != null)
////        {
////            Glide.with(getActivity()).load(tempUri.toString()).into(imag_view);
////            navImageView = getActivity().findViewById(R.id.img_navHeader);
////            Glide.with(getActivity()).load(tempUri.toString()).into(navImageView);
////        }
////
////        else
////            {
////
////            Glide.with(getActivity()).load(dbHelper.getUser().getProfileImg()).into(imag_view);
////            navImageView = getActivity().findViewById(R.id.img_navHeader);
////            Glide.with(getActivity()).load(dbHelper.getUser().getProfileImg()).into(navImageView);
////        }
////
////        imag_view.setOnClickListener(view1 ->
////        {
////            dialog.show();
////        });
//
//
//
//
//        editBtn.setOnClickListener(view12 ->
//        {
//            if (progressDialog != null && !progressDialog.isShowing())
//            {
//
//                progressDialog.show();
//            }
//
//
//
//            if (!Utils.isNetworkConnected(getActivity()))        //internet not connected..!!
//            {
//                if(dbHelper !=null)
//                {
//
//                    ImagePathSave bean = new ImagePathSave();
//                    bean.setImagePath(tempUri != null ? tempUri.toString() : "");
//                    dbHelper.updateImage(tempUri.toString());
//
//                    MainActivity.tempUri = tempUri;
//
//                    dbHelper.insertImagePath(bean);
//                }
//            }
//
//            else
//
//            if (Utils.isNetworkConnected(getActivity()))
//            {
//
//                if (tempUri !=null)
//                {
//
//                    ImagePathSave bean = new ImagePathSave();
//                    bean.setImagePath(tempUri != null ? tempUri.toString() : "");
//
//                    ImageLists lists = new ImageLists();
//
//                    lists.setFile(Utils.prepareFilePart(getActivity(), "profile_picutre[]", tempUri));
//
//                    RequestBody action = RequestBody.create(MediaType.parse("text/plain"), "update_user_profile_picture");
//                    RequestBody userid = RequestBody.create(MediaType.parse("text/plain"), dbHelper.getUserId());
//
////                        RequestBody actionn = RequestBody.create(MediaType.parse("text/plain"), "update_user_profile_picture");
//
//                    Call<WebResponse<Object>> call = WebServiceFactory.getInstance().update_user_profile_picture(action, userid, lists.getFile());
//
//                    call.enqueue(new Callback<WebResponse<Object>>() {
//                        @Override
//
//                        public void onResponse(@NonNull Call<WebResponse<Object>> call, @NonNull Response<WebResponse<Object>> response)
//                        {
//
//                            if (response.body() == null) {
//
//                                Glide.with(Custom_header.this).load(tempUri.toString()).into(imag_view);
//
//                                Toast.makeText(getActivity(), "found null", Toast.LENGTH_SHORT).show();
//                            } else if (Objects.requireNonNull(response.body()).getHeader().success.equals("1")) {
//
//                                if (progressDialog != null && progressDialog.isShowing())
//                                {
//                                    progressDialog.dismiss();
//
//                                }
//
//                                MainActivity.tempUri = tempUri;
//
//                                ToastUtil.showShortToast(getActivity(), "Submitted Successfully");
//                                Log.e("OnErrorResponse", "onError " + response.body().getMessage());
//
//                                try {
//
////                                    Toast.makeText(getActivity(), response.body().getHeader().toString(), Toast.LENGTH_SHORT).show();
////                                    Toast.makeText(getActivity(), response.body().getBody().toString(), Toast.LENGTH_SHORT).show();
//
//                                    Users dbUser = dbHelper.getUser();
//                                    JSONObject jsonObject = new JSONObject(response.body().toString());
//                                    String userNewUserProfilePath = jsonObject.getJSONObject("body").getString("browseable_path");
//                                    dbUser.setProfileImg(userNewUserProfilePath);
//                                    Glide.with(getActivity()).load(userNewUserProfilePath).into(imag_view);
//                                    Glide.with(getActivity()).load(userNewUserProfilePath).into(navImageView);
//
//                                    dbHelper.updateUser(dbUser.getId(), dbUser.getProfileImg());
//
////                                    Toast.makeText(getActivity(), userNewUserProfilePath, Toast.LENGTH_SHORT).show();
//
//
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//
//
//                            } else {
//
//                                Log.e("error_print1", "onError " + response.body().getMessage());
//
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<WebResponse<Object>> call, Throwable t) {
//                            //                            dismissDialog();
//
//                            if (progressDialog != null && !progressDialog.isShowing())
//                            {
//                                progressDialog.dismiss();
//                            }
//
//                            Toast.makeText(getActivity(), "Internet not available Please try again.", Toast.LENGTH_SHORT).show();
//                            LogUtil.e("failure_print", "onFailure " + t.getMessage());
//                        }
//                    });
//                }
//
//            }
//        });
//
//        username = view.findViewById(R.id.username);
//
//        report_to= view.findViewById(R.id.report_to);
//        phone= view.findViewById(R.id.phone);
//
//
//        designation = view.findViewById(R.id.designation);
//        email = view.findViewById(R.id.email);
//
//        if (dbHelper != null)
//        {
//            Username = dbHelper.getUser().getFirstName();
//
//            Report_To = dbHelper.getUser().getReport_to();
//
//
//            userId = dbHelper.getUser().getUserId();
//
//            String userRole = "";
//            switch (dbHelper.getUser().getUserType()) {
//                case "32":
//                    userRole = "BDO";
//                    break;
//
//                case "16":
//                    userRole = "LEAD";
//                    break;
//
//                case "8":
//                    userRole = "ASM";
//                    break;
//
//                case "4":
//                    userRole = "RSM";
//                    break;
//
//                case "2":
//                    userRole = "NSM";
//                    break;
//            }
//
//            Designation = String.format(getResources().getString(R.string.user_name), userRole, dbHelper.getUser().getLoginId());
//            email = dbHelper.getUser().getEmailAddress();
//            Phone = dbHelper.getUser().getPhoneNumber();
//
//            username.setText(Username);
//            username.setEnabled(false);
//
//            phone.setText(Phone);
//            designation.setText(Designation);
//            designation.setEnabled(false);
//            email.setText(email);
//            email.setEnabled(false);
//
//            report_to.setText(Report_To);
//            report_to.setEnabled(false);
//        }
//
//        c= getContext();
//        return view;
//    }
//
//    public void galleryIntent() {
//        Intent pickPhoto = new Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        pickPhoto.setType("image/*");
//        startActivityForResult(pickPhoto, 0);//one can be replaced with any action code
//    }
//
//
//
//    String mCurrentPhotoPath;
//    static final int REQUEST_TAKE_PHOTO = 1;
//    static final int REQUEST_IMAGE_GALLAREY = 0;
//
//    private File createImageFile() throws IOException {
//        // Create an image file name
//        @SuppressLint("SimpleDateFormat")
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String imageFileName = "JPEG_" + timeStamp + "_";
//        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        File image = File.createTempFile(
//                imageFileName,  /* prefix */
//                ".jpg",         /* suffix */
//                storageDir      /* directory */
//        );
//
//        // Save a file: path for use with ACTION_VIEW intents
//        mCurrentPhotoPath = image.getAbsolutePath();
//        return image;
//    }
//
//    public void dispatchTakePictureIntent() {
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        // Ensure that there's a camera activity to handle the intent
//        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
//            // Create the File where the photo should go
//            File photoFile = null;
//            try {
//                photoFile = createImageFile();
//            } catch (IOException ex) {
//                // Error occurred while creating the File
//                System.out.println("File Couldn't be able to create: " + ex.getLocalizedMessage());
//            }
//            // Continue only if the File was successfully created
//            if (photoFile != null) {
//                Uri photoURI = FileProvider.getUriForFile(getActivity(), "com.meezandev.uhfsolution.fileprovider", photoFile);
//                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
//                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
//            }
//        }
//    }
//
//    @SuppressLint("StaticFieldLeak")
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
//
////        For Camera option
//        if (requestCode == REQUEST_TAKE_PHOTO) {
//            if (resultCode == RESULT_OK) {
//                Log.i("onActivityResult", "in camera activity");
//                final Bitmap[] myBitmap = new Bitmap[1];
//                new AsyncTask<Void, Void, Void>() {
//
//                    @Override
//                    protected Void doInBackground(Void... voids) {
//
//                        File imgFile = new File(mCurrentPhotoPath);
//
//                        if (imgFile.exists()) {
//
//                            myBitmap[0] = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//                            tempUri = Utils.getImageUri(getActivity(), myBitmap[0]);
//
//                        }
//                        return null;
//                    }
//
//                    @Override
//                    protected void onPostExecute(Void aVoid) {
//                        imag_view.setImageBitmap(myBitmap[0]);
//                        navImageView.setImageBitmap(myBitmap[0]);
//
//                        super.onPostExecute(aVoid);
//                    }
//                }.execute();
//            }
//        }
//        Log.i("selector", "before result");
//
//
//        if (requestCode == REQUEST_IMAGE_GALLAREY) {
//            if (resultCode == RESULT_OK) {
//                Log.i("selector", "in result");
//                Log.i("onActivityResult","in gallery activity");
//                Uri selectedImage = data.getData();
//
//                Log.d("SelectedPath" , selectedImage.toString());
//                try {
//
//                    if (selectedImage != null)
//                    {
//                        getActivity().getContentResolver().takePersistableUriPermission(selectedImage, Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//                    }
//
//                    Bitmap myBitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
//                    tempUri = selectedImage;
//                    imag_view.setImageBitmap(myBitmap);
//                    navImageView.setImageBitmap(myBitmap);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        }
//
//
//   /* public String getPathFromURI(Uri contentUri) {
//        String res = null;
//        String[] proj = {MediaStore.Images.Media.DATA};
//        Cursor cursor = getActivity().getContentResolver().query(contentUri, proj, null, null, null);
//        if (cursor.moveToFirst()) {
//            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//            res = cursor.getString(column_index);
//        }
//        cursor.close();
//        return res;
//    }*/
//
//
//    public MultipartBody.Part[] getpart(List<Uri> files) {
//        //List<Uri> files ; //These are the uris for the files to be uploaded
//        MediaType mediaType = MediaType.parse("multipart/form-data");//Based on the Postman logs,it's not specifying Content-Type, this is why I've made this empty content/mediaType
//        MultipartBody.Part[] fileParts = new MultipartBody.Part[files.size()];
//        for (int i = 0; i < files.size(); i++) {
//            new File(files.get(i).getPath());
//            //Setting the file name as an empty string here causes the same issue, which is sending the request successfully without saving the files in the backend, so don't neglect the file name parameter.
//            fileParts[i] = Utils.prepareFilePart(getActivity(), "profile_picutre", tempUri);//MultipartBody.Part.createFormData(String.format(Locale.ENGLISH, "proof_of_payment", i), file.getName(), fileBody);
//        }
//        return fileParts;
//    }
//
//
//}
//














//package com.meezandev.uhfsolution.Fragments;
//
//import android.annotation.SuppressLint;
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.net.Uri;
//import android.os.AsyncTask;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Environment;
//import android.provider.MediaStore;
//import android.support.annotation.NonNull;
//import android.support.v4.app.Fragment;
//import android.support.v4.content.FileProvider;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.bumptech.glide.Glide;
//import com.meezandev.uhfsolution.Constants.Constants;
//import com.meezandev.uhfsolution.Pojo.ImageLists;
//import com.meezandev.uhfsolution.Pojo.Users;
//import com.meezandev.uhfsolution.R;
//import com.meezandev.uhfsolution.SQLite.DBHelper;
//import com.meezandev.uhfsolution.ServiceModel.WebResponse;
//import com.meezandev.uhfsolution.ServiceModel.WebServiceFactory;
//import com.meezandev.uhfsolution.Utils.ImageUtil;
//import com.meezandev.uhfsolution.Utils.LogUtil;
//import com.meezandev.uhfsolution.Utils.ToastUtil;
//import com.meezandev.uhfsolution.Utils.Utils;
//
//import java.io.File;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//import java.util.Objects;
//
//import de.hdodenhof.circleimageview.CircleImageView;
//import okhttp3.MediaType;
//import okhttp3.MultipartBody;
//import okhttp3.RequestBody;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//import static android.app.Activity.RESULT_OK;
//
//public class Custom_header extends Fragment
//{
//    Context c;
//    DBHelper dbHelper;
//    TextView username , designation , email ,   report_to;
//    String Username ,Report_To, Designation , email , Phone ;
//    Button editBtn;
//    String userId;
//
//    CircleImageView imag_view;
//    Uri tempUri;
//
//
//
//    public Custom_header() {
//
//    }
//
//
//    public static Custom_header newInstance() {
//        Custom_header fragment = new Custom_header();
//
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
//    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
//    {
//
//        View view = inflater.inflate(R.layout.fragment_custom_header, container, false);
//
//        imag_view = view.findViewById(R.id.imag_view);
//        dbHelper = DBHelper.getInstance(getActivity());
//
//        updateFields();
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//
//        inflater = this.getLayoutInflater();
//
//        @SuppressLint("InflateParams") View dialogView = inflater.inflate(R.layout.custom_dialog, null);
//
//        builder.setView(dialogView);
////        builder.setTitle("Choose");
//
//
//        Button imageSelector = dialogView.findViewById(R.id.gallery);
//        Button camera = dialogView.findViewById(R.id.camera);
//        final AlertDialog dialog = builder.create();
//
//        imageSelector.setOnClickListener(v -> {
//            System.out.println("**** CAMERA IS CLICKED ");
//            galleryIntent();
//            dialog.dismiss();
//        });
//
//
//        camera.setOnClickListener(v -> {
//            System.out.println("**** CAMERA IS CLICKED **");
//            dispatchTakePictureIntent();
//            dialog.dismiss();
//        });
//
//
//
//
//        Glide.with(getActivity()).load(dbHelper.getUser().getProfileImg()).into(imag_view);
//
////        Glide.with(getActivity()).load(tempUri).into(imag_view);
//
////        Glide.with(getActivity()).load(dbHelper.getUser().getProfileImg()).into(imag_view);
//
//
////        DBHelper.getInstance(getActivity()).setImagePath(Integer.parseInt(dbHelper.getUser().getProfileImg()));
////
////        Toast.makeText(getActivity(), "Inserted..!!", Toast.LENGTH_SHORT).show();
//
//////        imag_view.setImageResource(Integer.parseInt(dbHelper.getUser().getProfileImg())
///// );
////
////        if (dbHelper !=null)
////        {
////            String result = dbHelper.getUser().getProfileImg();
////            imag_view.setImageResource(Integer.parseInt(result));
////
////        }
//
//
////  Picasso.with(context).load(rsc).placeholder(R.drawable.loading_placeholder) //todo update placeholder image.error(R.drawable.place_holder) //todo update loading image.into(imageView);
//
//
//
//
//        imag_view.setOnClickListener(view1 ->
//        {
////            dbHelper.getUser().getReport_to();
//
//            dialog.show();
//        });
//
//        editBtn = view.findViewById(R.id.editBtn);
//
//
//        editBtn.setOnClickListener(view12 ->
//        {
//
//            if (!Utils.isNetworkConnected(Objects.requireNonNull(getActivity())))        //internet not connected..!!
//            {
//                if(dbHelper !=null)
//                {
//
//                    ImagePathSave bean = new ImagePathSave();
//                    bean.setImagePath(tempUri != null ? tempUri.toString() : "");
////                            dbHelper.updateImage(tempUri.toString());
//
////                            Glide.with(getActivity()).load(dbHelper.getUser().getProfileImg()).into(imag_view);
//
//                    dbHelper.insertImagePath(bean);
//
//                    Utils.showShortToastInCenter(getActivity(), "Successfully updated on local db..!!");
//
//                    //  emptyFields();
//                }
//            }
//
////                    else
//
//            if (Utils.isNetworkConnected(getActivity()))
//            {
//
//                if (tempUri !=null)
//                {
//
//                    ImagePathSave bean = new ImagePathSave();
//                    bean.setImagePath(tempUri != null ? tempUri.toString() : "");
//
//                    ImageLists lists = new ImageLists();
//
//                    lists.setFile(Utils.prepareFilePart(getActivity(), "profile_picutre[]", tempUri));
//
//                    RequestBody action = RequestBody.create(MediaType.parse("text/plain"), "update_user_profile_picture");
//                    RequestBody userid = RequestBody.create(MediaType.parse("text/plain"), dbHelper.getUserId());
//
////                        RequestBody actionn = RequestBody.create(MediaType.parse("text/plain"), "update_user_profile_picture");
//
//                    Call<WebResponse<Object>> call = WebServiceFactory.getInstance().update_user_profile_picture(action, userid, lists.getFile());
//
//                    call.enqueue(new Callback<WebResponse<Object>>() {
//                        @Override
//
//                        public void onResponse(@NonNull Call<WebResponse<Object>> call, @NonNull Response<WebResponse<Object>> response)
//                        {
//
//                            if (response.body() == null) {
//
////                                    Glide.with(getActivity()).load(tempUri).into(imag_view);
//
//                                LogUtil.e("zainsubmitted", "SubmitRecoveryPersonCheckin null response ");
//                                Toast.makeText(getActivity(), Constants.kNullRespone, Toast.LENGTH_SHORT).show();
//                            }
//
//                            else if (Objects.requireNonNull(response.body()).getHeader().success.equals("1"))
//                            {
//
//                                ImagePathSave bean = new ImagePathSave();
//                                bean.setImagePath(tempUri != null ? tempUri.toString() : "");
//
//                                ToastUtil.showShortToast(getActivity(), "Submitted Successfully");
//                                Log.e("OnErrorResponse", "onError " + response.body().getMessage());
//
//
////                                    Bundle bundle = new Bundle();
////                                    Fragment newFragment = new Dashboard_Fragment();
////                                    newFragment.setArguments(bundle);
//
//
//                            } else {
//
//                                Log.e("error_print1", "onError " + response.body().getMessage());
//
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<WebResponse<Object>> call, Throwable t) {
//                            //                            dismissDialog();
//                            Toast.makeText(getActivity(), "Internet not available Please try again.", Toast.LENGTH_SHORT).show();
//                            LogUtil.e("failure_print", "onFailure " + t.getMessage());
//                        }
//                    });
//                }
//
//            }
//        });
//
//        username = view.findViewById(R.id.username);
//
//        report_to= view.findViewById(R.id.report_to);
//
//        designation = view.findViewById(R.id.designation);
//        email = view.findViewById(R.id.email);
//
//        if (dbHelper != null)
//        {
//            Username = dbHelper.getUser().getFirstName();
//
//            Report_To = dbHelper.getUser().getReport_to();
//
//
//            userId = dbHelper.getUser().getUserId();
//
//            String userRole = "";
//            switch (dbHelper.getUser().getUserType()) {
//                case "32":
//                    userRole = "BDO";
//                    break;
//
//                case "16":
//                    userRole = "LEAD";
//                    break;
//
//                case "8":
//                    userRole = "ASM";
//                    break;
//
//                case "4":
//                    userRole = "RSM";
//                    break;
//
//                case "2":
//                    userRole = "NSM";
//                    break;
//            }
//
//            Designation = String.format(getResources().getString(R.string.user_name), userRole, dbHelper.getUser().getLoginId());
//            email = dbHelper.getUser().getEmailAddress();
//            Phone = dbHelper.getUser().getPhoneNumber();
//
//            username.setText(Username);
//            username.setEnabled(false);
//
//
//            designation.setText(Designation);
//            designation.setEnabled(false);
//            email.setText(email);
//            email.setEnabled(false);
//
//            report_to.setText(Report_To);
//            report_to.setEnabled(false);
//        }
//
//        c= getContext();
//        return view;
//    }
//
//    private void updateFields()
//    {
//        ImageUtil.setImageResourceWithGlide(getActivity(), imag_view, dbHelper.getUser().getProfileImg());
//    }
//
////    private void emptyFields()
////    {
////        try
////        {
////            tempUri = null;
////            tempUri = Uri.EMPTY;
////        }
////
////        catch (Resources.NotFoundException e)
////        {
////            e.printStackTrace();
////        }
////
////    }
//
//    public void galleryIntent() {
//        Intent pickPhoto = new Intent(Intent.ACTION_OPEN_DOCUMENT, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        pickPhoto.setType("image/*");
//        startActivityForResult(pickPhoto, 0);//one can be replaced with any action code
//    }
//
//
//
//    String mCurrentPhotoPath;
//    static final int REQUEST_TAKE_PHOTO = 1;
//    static final int REQUEST_IMAGE_GALLAREY = 0;
//
//    private File createImageFile() throws IOException {
//        // Create an image file name
//        @SuppressLint("SimpleDateFormat")
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String imageFileName = "JPEG_" + timeStamp + "_";
//        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        File image = File.createTempFile(
//                imageFileName,  /* prefix */
//                ".jpg",         /* suffix */
//                storageDir      /* directory */
//        );
//
//        // Save a file: path for use with ACTION_VIEW intents
//        mCurrentPhotoPath = image.getAbsolutePath();
//        return image;
//    }
//
//    public void dispatchTakePictureIntent() {
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        // Ensure that there's a camera activity to handle the intent
//        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
//            // Create the File where the photo should go
//            File photoFile = null;
//            try {
//                photoFile = createImageFile();
//            } catch (IOException ex) {
//                // Error occurred while creating the File
//                System.out.println("File Couldn't be able to create: " + ex.getLocalizedMessage());
//            }
//            // Continue only if the File was successfully created
//            if (photoFile != null) {
//                Uri photoURI = FileProvider.getUriForFile(getActivity(), "com.meezandev.uhfsolution.fileprovider", photoFile);
//                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
//                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
//            }
//        }
//    }
//
//    @SuppressLint("StaticFieldLeak")
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
//
////        For Camera option
//        if (requestCode == REQUEST_TAKE_PHOTO) {
//            if (resultCode == RESULT_OK) {
//                Log.i("onActivityResult","in camera activity");
//                final Bitmap[] myBitmap = new Bitmap[1];
//                new AsyncTask<Void, Void, Void>(){
//
//                    @Override
//                    protected Void doInBackground(Void... voids) {
//
//                        File imgFile = new File(mCurrentPhotoPath);
//
//                        if (imgFile.exists()) {
//
//                            myBitmap[0] = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//                            tempUri = Utils.getImageUri(getActivity(), myBitmap[0]);
//
//                        }
//                        return null;
//                    }
//
//                    @Override
//                    protected void onPostExecute(Void aVoid) {
//                        imag_view.setImageBitmap(myBitmap[0]);
//                        super.onPostExecute(aVoid);
//                    }
//                }.execute();
//            }
//        }
//        Log.i("selector", "before result");
//
//
//
//
//
//        if (requestCode == REQUEST_IMAGE_GALLAREY) {
//            if (resultCode == RESULT_OK) {
//                Log.i("selector", "in result");
//                Log.i("onActivityResult","in gallery activity");
//                Uri selectedImage = data.getData();
//                try {
//
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                        if (selectedImage != null) {
//                            getActivity().getContentResolver().takePersistableUriPermission(selectedImage, Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//                        }
//                    }
//                    Bitmap myBitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
//                    tempUri = selectedImage;
//                    imag_view.setImageBitmap(myBitmap);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                //                ImageView myImage = (ImageView) findViewById(R.id.imageviewTest);
//            }
//        }
//
//    }
//
//
//
//    public MultipartBody.Part[] getpart(List<Uri> files) {
//        //List<Uri> files ; //These are the uris for the files to be uploaded
//        MediaType mediaType = MediaType.parse("multipart/form-data");//Based on the Postman logs,it's not specifying Content-Type, this is why I've made this empty content/mediaType
//        MultipartBody.Part[] fileParts = new MultipartBody.Part[files.size()];
//        for (int i = 0; i < files.size(); i++) {
//            new File(files.get(i).getPath());
//            //Setting the file name as an empty string here causes the same issue, which is sending the request successfully without saving the files in the backend, so don't neglect the file name parameter.
//            fileParts[i] = Utils.prepareFilePart(getActivity(), "profile_picutre", tempUri);//MultipartBody.Part.createFormData(String.format(Locale.ENGLISH, "proof_of_payment", i), file.getName(), fileBody);
//        }
//        return fileParts;
//    }
//
//
//}
//
