package com.commonlibrary.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.media.ExifInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.commonlibrary.R;
import com.commonlibrary.adapters.GalleryAdapter;
import com.commonlibrary.data.GalleryImage;
import com.commonlibrary.logger.Log;
import com.commonlibrary.viewholders.CustomGalleryViewHolder;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CustomGalleryActivity extends BaseActivity {

    public static final String ACTIVITY_NAME = "CustomGalleryActivity";

    List<GalleryImage> _galleryImageList = null;
    List<GalleryImage> _allImageList = null;
    private BaseActivity _context;
    GalleryAdapter _galleryAdapter = null;
    private ProgressDialog _progressDialog;
    private List<GalleryImage> _tempGalleryImageList;
    private List<String> _filters;

    //private int _startIndex;
    //private int _interval = 20;
    //private int _endIndex;
    private CustomGalleryViewHolder mHolder;


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_gallery);
        _context = CustomGalleryActivity.this;

        setUI();
        setListeners();
        LoadGallery();
    }


    public void setListeners() {

        mHolder.getGridViewGallery().setRecyclerListener(new AbsListView.RecyclerListener() {
            @Override
            public void onMovedToScrapHeap(View view) {
                final ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
                imageView.setImageBitmap(null);
            }
        });
    }

    private void setUI() {
        mHolder = new CustomGalleryViewHolder(this);
    }

    private void LoadGallery() {
        try
        {
            mHolder.getGridViewGallery().setAdapter(null);

            Thread loadGalleryThread = new Thread() {
                @Override
                public void run() {

                    try
                    {
                        setAllGallery();
                    }
                    catch (Exception e)
                    {
                        Log.e("CustomerGallery",e.getMessage());
                    }
                }
            };

            loadGalleryThread.start();

            _progressDialog = ProgressDialog.show(_context, "Loading photos", "Please wait. The more photos you have, the longer it will take.");

        }
        catch (Exception e) {

            Log.e("CustomerGallery",e.getMessage());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();


        if(_progressDialog != null)
        {
            _progressDialog.dismiss();
        }
        //UploadActivity._activityState = Constants.ActivityState.Paused;
        finish();
    }

    @Override
    protected void onResume() {

        super.onResume();


    }



    int _midEndInterval;

    private Handler handlerResponse = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            /*_startIndex = 0;
            _endIndex = _interval;
*/
            if(mHolder != null)
            {
                Collections.sort(_galleryImageList);

                final List<GalleryImage> pageList = new LinkedList<GalleryImage>();
                pageList.addAll(_galleryImageList);

                DisplayMetrics dm = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(dm);
                int size = dm.widthPixels / 3;


                _galleryAdapter = new GalleryAdapter(_context,pageList,size);

                List<GalleryImage> selectedImages = _galleryAdapter.getAllImages();
                if(selectedImages != null && selectedImages.size() > 0)
                {
                    _galleryAdapter._countPhotos = selectedImages.size();
                }
                else
                {
                    _galleryAdapter._countPhotos = 0;
                }


                mHolder.getGridViewGallery().setAdapter(_galleryAdapter);
                mHolder.getGridViewGallery().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        GalleryImage galleryImage = pageList.get(position);
                        Bundle bundle = new Bundle();
                        bundle.putParcelable(GalleryImage.class.getSimpleName(),galleryImage);
                        Intent intent = new Intent();
                        intent.putExtras(bundle);
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                });
                _tempGalleryImageList = _galleryImageList;

            }

            if(_progressDialog != null)
            {
                _progressDialog.dismiss();
            }

        }

    };

    private final String[] projection = new String[]{ MediaStore.Images.Media._ID, MediaStore.Images.Media.DISPLAY_NAME, MediaStore.Images.Media.DATA };

    private void setAllGallery() throws IOException {


        try {
            //_galleryImageList = new LinkedList<GalleryImage>();

            final String[] columns = { MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID };
            final String orderBy = MediaStore.Images.Media._ID;
            //Cursor _imageCursor = managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,null, orderBy);
            Cursor _imageCursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection,
                    null, null, MediaStore.Images.Media.DATE_ADDED);
            if (_imageCursor != null && !_imageCursor.isClosed())
            {
                List<GalleryImage> allImages = new LinkedList<GalleryImage>();
                _galleryImageList = new LinkedList<GalleryImage>();

                int image_column_index = _imageCursor.getColumnIndex(MediaStore.Images.Media._ID);

                int maxCount = _imageCursor.getCount();
                for (int i = 0; i < maxCount; i++) {

                    if(_imageCursor == null || _imageCursor.isClosed())
                    {
                        _imageCursor = managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,null, orderBy);
                    }

                    GalleryImage galleryImage = new GalleryImage();
                    _imageCursor.moveToPosition(i);

                    int dataColumnIndex = _imageCursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    galleryImage.setImageID(_imageCursor.getInt(image_column_index));
                    galleryImage.setPath(_imageCursor.getString(dataColumnIndex));

                    int index = galleryImage.getPath().lastIndexOf("/");
                    String filename = galleryImage.getPath().substring(index + 1, galleryImage.getPath().length());

                    /*Bitmap image = MediaStore.Images.Thumbnails.getThumbnail(
                            _context.getContentResolver(), galleryImage.getImageID(),
                            MediaStore.Images.Thumbnails.MICRO_KIND, null);*/

                    //galleryImage.setImage(image);
                    galleryImage.setName(filename);

                    File imageFile = new File(galleryImage.getPath());
                    if(imageFile.exists())
                    {
                        //_galleryImageList.add(galleryImage);
                        galleryImage.setPath(imageFile.getPath());
                        allImages.add(galleryImage);
                    }

                }

                _imageCursor.close();

                _allImageList = new ArrayList<GalleryImage>();

                for(int index = (allImages.size() - 1); index >= 0; index--)
                {
                    _galleryImageList.add(allImages.get(index));
                    _allImageList.add(allImages.get(index));
                }

                Message myMessage=new Message();
                handlerResponse.sendMessage(myMessage);

            }
        }
        catch (Exception e)
        {
            Log.e("CustomerGallery",e.getMessage());
        }

    }

    private void AddToGalleryList(GalleryImage galleryImage, List<GalleryImage> galleryImageList) throws IOException {

        ExifInterface exif = new ExifInterface(galleryImage.getPath());
        if(exif != null)
        {
            String strImageDataTime = exif.getAttribute(ExifInterface.TAG_DATETIME);
            int exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            switch (exifOrientation)
            {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    galleryImage.setAngle(90);
                    break;

                case ExifInterface.ORIENTATION_ROTATE_180:
                    galleryImage.setAngle(180);
                    break;

                case ExifInterface.ORIENTATION_ROTATE_270:
                    galleryImage.setAngle(270);
                    break;
            }

            if(strImageDataTime != null && strImageDataTime.length() > 0 && !strImageDataTime.contains("0000"))
            {
                //Shared.WriteLogToSDCard("ExifInterface.TAG_DATETIME for image " + galleryImage.getName() + " " + strImageDataTime + "\n");

                String strMDateTime = strImageDataTime.replaceFirst(":","-");
                strMDateTime = strMDateTime.replaceFirst(":","-");
                DateTime dateTime = null;
                DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

                try
                {
                    dateTime = formatter.parseDateTime(strMDateTime);
                }
                catch (Exception e)
                {
                    /*DateTimeFormatter formatterS4 = DateTimeFormat.forPattern("MMM dd, yyyy HH:mm:ss");
                    dateTime = formatterS4.parseDateTime(strImageDataTime);*/
                    dateTime = null;

                }

                if(dateTime != null)
                {
                    galleryImage.setCapturedDate(dateTime);
                }
            }
        }
    }

    private boolean isSelected(GalleryImage galleryImage)
    {
        if(_tempGalleryImageList != null)
        {
            for(GalleryImage image:_tempGalleryImageList)
            {
                if(image.getPath().compareTo(galleryImage.getPath()) == 0)
                {
                    return image.getSelected();
                }
            }
        }

        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.gc();


        mHolder = null;

        _allImageList = null;
        _tempGalleryImageList = null;
        _galleryImageList = null;
        _galleryAdapter = null;

    }


}
