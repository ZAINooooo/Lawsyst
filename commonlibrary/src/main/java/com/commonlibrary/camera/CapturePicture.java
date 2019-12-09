package com.commonlibrary.camera;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.widget.Toast;

import com.commonlibrary.common.AppConstant;
import com.commonlibrary.logger.Log;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Asif on 7/23/2016.
 */
public class CapturePicture
{
    private static int SELECT_IMAGE = 100;
    private static final int CROP_PIC = 400;
    private static final int RESULT_CANCELED = 300;
    private static final int MY_PERMISSION_REQUEST_CODE_PROFILE_EDIT = 160;
    private String mImagePath;
    private Uri mOutputFileUri;
    private Activity context;

    private OnPictureSelectListener onPictureSelectListener;
    private String mFileName;

    public CapturePicture(Activity context) {
        this.context = context;
    }

    public CapturePicture(Activity context, int requestCode) {
        this.context = context;
        SELECT_IMAGE = requestCode;
    }

    public OnPictureSelectListener getOnPictureSelectListener() {
        return onPictureSelectListener;
    }

    public void setOnPictureSelectListener(OnPictureSelectListener onPictureSelectListener) {
        this.onPictureSelectListener = onPictureSelectListener;
    }


    public void showPhoneGallery(String fileName, String applicationId) {
        this.mFileName = fileName;

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/" + AppConstant.FOLDER_NAME);
        myDir.mkdir();

        File file = new File(myDir, mFileName);

        //mOutputFileUri = Uri.fromFile(file);
        mOutputFileUri = FileProvider.getUriForFile(context,
                applicationId + ".provider",
                file);

        // Camera.

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //File photo = new File(Environment.getExternalStorageDirectory(),  "Pic.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                mOutputFileUri);
        context.startActivityForResult(intent, SELECT_IMAGE);

    }

    private void performCrop(Uri videoUri) {
        // take care of exceptions
        try {
            // start picker to get image for cropping and then use the image in cropping activity
            CropImage.activity(videoUri).setFixAspectRatio(true)
                    //.setAspectRatio(4,3)
                    //.setRotationDegrees(0)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .start(context);

        }
        // respond to users whose devices do not support the crop action
        catch (ActivityNotFoundException anfe) {
            Toast toast = Toast
                    .makeText(context, "This device doesn't support the crop action!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (requestCode == SELECT_IMAGE && resultCode == RESULT_OK) {

            // Get (file) URI of the vid from the return Intent's data
            if (intent != null) {
                Uri videoUri = intent.getData();
                performCrop(videoUri);
            } else {
                performCrop(mOutputFileUri);
            }
        } else //if (requestCode == CROP_PIC && resultCode == activity.RESULT_OK)
        {
            if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = CropImage.getActivityResult(intent);
                if (resultCode == RESULT_OK) {
                    Uri resultUri = result.getUri();
                    File file = new File(resultUri.getPath());
                    //if (file.exists())
                        // file.delete();
                    try {
                        Bitmap selectedBitmap = BitmapFactory.decodeFile(file.getPath());

                        String root = Environment.getExternalStorageDirectory().toString();
                        File myDir = new File(root + "/" + AppConstant.FOLDER_NAME);
                        myDir.mkdir();

                        //File filesDir = context.getFilesDir();
                        File fileTo = new File(myDir, mFileName);

                        OutputStream os;
                        try {
                            os = new FileOutputStream(fileTo);
                            selectedBitmap.compress(Bitmap.CompressFormat.JPEG, 60, os);
                            os.flush();
                            os.close();
                            selectedBitmap.recycle();
                        } catch (Exception e) {
                            Log.e("Image", "Error writing bitmap" + e);
                        }

                        if (fileTo.exists()) {
                            mImagePath = fileTo.getAbsolutePath();
                            file.delete();
                            onPictureSelectListener.onPictureSelect(fileTo);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Exception error = result.getError();
                }
            }
        }
    }

}
