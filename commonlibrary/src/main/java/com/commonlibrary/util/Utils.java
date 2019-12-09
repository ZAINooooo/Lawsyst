package com.commonlibrary.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.commonlibrary.BuildConfig;
import com.commonlibrary.R;
import com.commonlibrary.common.DATETIME_FORMAT;
import com.commonlibrary.common.DEVICE_DENSITY;
import com.commonlibrary.common.NETWORK_TYPE;
import com.commonlibrary.listeners.IDialogListener;
import com.commonlibrary.logger.Log;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Formatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;

/**
 * The purpose of this class is to provide static common functionality to other classes.
 */
public class Utils {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
    public static final String FILE_NAME = "image.jpg";
    public static final String IMAGE_NAME = "/appimage";
    public static final String HTTP = "http://";
    public static final int YOUVERSION_FEED_IMAGE_INDEX = 2;
    private static String mdy;
    private static String TAG = Utils.class.getSimpleName();
    public static boolean isGivingActivityCalled = false;
    private static boolean mailSent;
    private static boolean supportMailSent;

    public static String getFolderSizeOnSDcard(final File file) {
        if (file == null || !file.exists())
            return "0";

        final List<File> dirs = new LinkedList<File>();
        dirs.add(file);
        long result = 0;
        while (!dirs.isEmpty()) {
            final File dir = dirs.remove(0);
            if (!dir.exists())
                continue;
            final File[] listFiles = dir.listFiles();
            if (listFiles == null || listFiles.length == 0)
                continue;
            for (final File child : listFiles) {
                result += child.length();
                if (child.isDirectory())
                    dirs.add(child);
            }
        }

        if (result > 0) {
            final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
            int digitGroups = (int) (Math.log10(result) / Math.log10(1024));
            return new DecimalFormat("#,##0.#").format(result / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
        }

        return "";
    }

    private static Utils utils;

    public static Utils getInstance() {
        if (utils == null)
            return new Utils();
        return utils;
    }

    public void generateHashKey(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }

    public boolean isOnWifiNetwork(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                // Toast.makeText(context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
                return true;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's com.guilder.guilder.guilder.data plan
                // Toast.makeText(context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
                return false;
            }

        } else {
            // not connected to the internet
            return false;
        }

        return false;

    }

    public boolean isOnCellularNetworkAvailable(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                // Toast.makeText(context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
                return false;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's com.guilder.guilder.guilder.data plan
                // Toast.makeText(context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
                return true;
            }

        } else {
            // not connected to the internet
            return false;
        }

        return false;

    }

    public boolean validateEmailAddress(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isNetworkAvailable(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                // Toast.makeText(context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
                return true;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's com.guilder.guilder.guilder.data plan
                // Toast.makeText(context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
                return true;
            }

        } else {
            // not connected to the internet
            return false;
        }

        return false;
    }

    public int getListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            if (listItem instanceof ViewGroup) {
                listItem.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            }
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        return (totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1)));
    }

    /***
     * @param activityName
     *            Activity Name to call
     * @param context
     *            Activity context
     * @param milliseconds
     *            Time to wait in milliseconds e.g (1 sec equals 1000 millis)
     */
    public void callIntentWithTime(final Class<?> activityName, final Activity context, final int milliseconds) {

        Thread splashThread = new Thread() {

            @Override
            public void run() {
                try {
                    int splashTime = 0;
                    while (splashTime < milliseconds) {
                        sleep(100);
                        splashTime += 100;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    context.finish();
                    callIntent(activityName, context);
                }

            }

        };
        splashThread.start();

    }

    /**
     * This method call the intent and delete rest of activities from the
     * activity stack to make the current activity on top.
     */
    public void callAndMakeTopIntent(final Class<?> activityName, Context context) {
        String pacakageName = context.getPackageName();

        Intent i = new Intent();
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        i.setClassName(pacakageName, activityName.getName());

        android.util.Log.e("Activity", activityName.getName());
        context.startActivity(i);
    }

    public void callAndMakeTopIntent(final Class<?> activityName, Activity context) {
        String pacakageName = context.getPackageName();

        Intent i = new Intent();
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        i.setClassName(pacakageName, activityName.getName());

        android.util.Log.e("Activity", activityName.getName());
        context.startActivity(i);
    }

    /**
     * This method call the intent and delete rest of activities from the
     * activity stack to make the current activity on top.
     */
    public void callAndMakeTopIntent(Class<?> activityName, Activity context, Bundle bundle) {
        String pacakageName = context.getPackageName();
        Intent i = new Intent();
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.putExtras(bundle);
        i.setClassName(pacakageName, activityName.getName());

        android.util.Log.e("Activity", activityName.getName());
        context.startActivity(i);
    }

    public Intent callIntent(Class<?> activityName, Activity context) {
        Intent i = new Intent();
        String pacakageName = context.getPackageName();
        i.setClassName(pacakageName, activityName.getName());

        android.util.Log.e("Activity", activityName.getName());
        context.startActivity(i);
        // context.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        return i;
    }

    public Intent callIntentBack(String activityName, Activity context) {
        Intent i = new Intent();
        String pacakageName = context.getPackageName();
        i.setClassName(pacakageName, activityName);

        android.util.Log.e("Activity", activityName);
        context.startActivity(i);
        // context.overridePendingTransition(R.anim.slide_out_left,R.anim.slide_in_right);
        return i;
    }

    public void callIntent(Class<?> activityName, Context context, Bundle bundle) {
        String pacakageName = context.getPackageName();
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClassName(pacakageName, activityName.getName());

        android.util.Log.e("Activity", activityName.getName());
        context.startActivity(intent);
        // context.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void callIntent(Class<?> activityName, Activity context, Bundle bundle) {
        String pacakageName = context.getPackageName();
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClassName(pacakageName, activityName.getName());

        android.util.Log.e("Activity", activityName.getName());
        context.startActivity(intent);
        // context.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void callIntentWithResult(Class<?> activityName, Activity context, Bundle bundle, int requestCode) {
        String pacakageName = context.getPackageName();
        Intent intent = new Intent();

        if (bundle != null)
            intent.putExtras(bundle);

        intent.setClassName(pacakageName, activityName.getName());

        android.util.Log.e("Activity", activityName.getName());
        context.startActivityForResult(intent, requestCode);
    }

    public String getMonthName(int monthID) {

        switch (monthID) {
            case 1:
                return "January";
            case 2:
                return "Feburary";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                return null;
        }

    }

    public File storeImage(Bitmap imageData) {
        String filePath = null;
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + Utils.IMAGE_NAME);
        myDir.delete();
        myDir.mkdirs();
        String fileName = Utils.FILE_NAME;
        File fileImageShare = new File(myDir, fileName);
        try {
            filePath = fileImageShare.getAbsolutePath();
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);
            imageData.compress(Bitmap.CompressFormat.PNG, 100, bos);
            bos.flush();
            bos.close();
        } catch (FileNotFoundException e) {
            Log.w("TAG", "Error saving image file: " + e.getMessage());
        } catch (IOException e) {
            Log.w("TAG", "Error saving image file: " + e.getMessage());
        }
        return fileImageShare;
    }

    public String getUrlWithHttp(String url) {
        if (!url.startsWith(HTTP)) {
            url = HTTP + url;
        }
        return url;

    }

    public void loadUrlInWebView(WebView webView, String url) {
        Log.d(TAG, "loading url: " + url);
        setWebViewProperties(webView);
        webView.loadUrl(url);
    }

    private void setWebViewProperties(WebView webView) {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
    }

    public String getUrlFromDoc(Document document) {
        String url = null;
        NodeList nodeList = document.getElementsByTagName("video");
        if (nodeList != null) {
            Element element = (Element) nodeList.item(0);
            url = element.getAttribute("src");
        }
        Log.d(TAG, "url: " + url);
        // return "http://lifechurch1-vh.akamaihd.net/i/LIFECHURCH/236/1019/20_Year_Sermon_MASTER__462955.mp4";
        return url;
    }

    public boolean isPackageExists(Context context, String targetPackage) {
        List<ApplicationInfo> packages;
        PackageManager pm;

        pm = context.getPackageManager();
        packages = pm.getInstalledApplications(0);
        for (ApplicationInfo packageInfo : packages) {
            Log.d(TAG, "package: " + packageInfo.packageName);
            if (packageInfo.packageName.equals(targetPackage))
                return true;
        }
        return false;
    }

    public void showDialog(Context context, String message, final IDialogListener listener) {
        final MaterialDialog.Builder materialDialog = new MaterialDialog.Builder(context);
        materialDialog.content(message).positiveColorRes(R.color.blue_button_background).negativeColorRes(R.color.blue_button_background).onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                if (listener != null)
                    listener.onClickOk(true);
            }
        }).positiveText(context.getString(R.string.ok));
        materialDialog.show();
    }

    public void showDialog(Context context, String title, String message, final IDialogListener listener) {
        try {
            final MaterialDialog.Builder materialDialog = new MaterialDialog.Builder(context);
            materialDialog.title(title).cancelable(false).content(message).positiveColorRes(R.color.blue_button_background).negativeColorRes(R.color.blue_button_background).onPositive(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    if (listener != null)
                        listener.onClickOk(true);
                }
            }).positiveText(context.getString(R.string.ok));
            materialDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showDialog(Context context, String title, String message, String ok, String cancel, final IDialogListener listener) {
        try {
            final MaterialDialog.Builder materialDialog = new MaterialDialog.Builder(context);
            materialDialog.title(title).cancelable(false).content(message).positiveColorRes(R.color.blue_button_background).negativeColorRes(R.color.blue_button_background).onPositive(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    dialog.dismiss();
                    if (listener != null)
                        listener.onClickOk(true);
                }
            }).onNegative(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    dialog.dismiss();
                    if (listener != null)
                        listener.onClickOk(false);
                }
            }).positiveText(ok).negativeText(cancel);
            // materialDialog.titleGravity(GravityEnum.CENTER);
            // materialDialog.contentGravity(GravityEnum.CENTER);

            materialDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void messageBox(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public void openWebURL(String inURL, Activity context) {
        Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse(inURL));
        context.startActivity(browse);
    }

    public void showDialog7(Context context, String title, String message, String ok, final IDialogListener listener) {
        try {
            final MaterialDialog.Builder materialDialog = new MaterialDialog.Builder(context);
            if (!TextUtils.isEmpty(title)) {
                materialDialog.title(title).content(message).positiveColorRes(R.color.blue_button_background).negativeColorRes(R.color.blue_button_background).positiveText(ok);

            } else {
                materialDialog.content(message).positiveColorRes(R.color.blue_button_background).negativeColorRes(R.color.blue_button_background).positiveText(ok);
            }
            materialDialog.onPositive(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    dialog.dismiss();
                    if (listener != null)
                        listener.onClickOk(true);
                }
            });
            // materialDialog.titleGravity(GravityEnum.CENTER);
            // materialDialog.contentGravity(GravityEnum.CENTER);

            materialDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showDialog(Context context, String message, String ok, String cancel, final IDialogListener listener) {
        try {
            final MaterialDialog.Builder materialDialog = new MaterialDialog.Builder(context);
            materialDialog.content(message).positiveColorRes(R.color.blue_button_background).negativeColorRes(R.color.blue_button_background).positiveText(ok).negativeText(cancel);

            materialDialog.onPositive(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    dialog.dismiss();
                    if (listener != null)
                        listener.onClickOk(true);
                }
            });

            materialDialog.onNegative(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    dialog.dismiss();
                    if (listener != null)
                        listener.onClickOk(false);
                }
            });

            materialDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[] getAmPm(String amPm) {

        String d[] = new String[2];
        if (amPm != null && amPm.length() > 0) {
            StringTokenizer stok = new StringTokenizer(amPm);
            int str = Integer.parseInt(stok.nextToken(":"));
            int str1 = Integer.parseInt(stok.nextToken(":"));
            String t1 = "";
            if (str1 == 0) {
                t1 = str1 + "0";
            } else {
                t1 = str1 + "";
            }
            if (str >= 12) {
                String s = "";
                int t = str - 12;
                if (t > 9) {
                    s = String.valueOf(t) + ":" + t1;
                } else {
                    if (t == 0) {
                        s = "00" + ":" + t1;
                    } else {
                        s = "0" + t + ":" + t1;
                    }
                }
                d[0] = s;
                if (t == 12) {
                    d[1] = "AM";
                } else {
                    d[1] = "PM";
                }
            } else {
                d[0] = amPm;
                d[1] = "AM";
            }

        }
        return d;
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                float px = 500 * (listView.getResources().getDisplayMetrics().density);
                item.measure(View.MeasureSpec.makeMeasureSpec((int) px, View.MeasureSpec.AT_MOST), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() * (numberOfItems - 1);
            // Get padding
            int totalPadding = listView.getPaddingTop() + listView.getPaddingBottom();

            // Set list height.
            LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight + totalPadding;
            listView.setLayoutParams(params);
            listView.requestLayout();
            // return true;

        } else {
            // return false;
        }

    }

    public void setListViewHeightBasedOnChildren(GridView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0) {
                view.setLayoutParams(new LayoutParams(desiredWidth, LayoutParams.WRAP_CONTENT));
            }
            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    public String getDeviceDetails() {
        StringBuilder sbDeviceDetails = new StringBuilder();
        sbDeviceDetails.append(Build.MODEL);
        sbDeviceDetails.append(" - ");
        sbDeviceDetails.append("Android OS ");
        sbDeviceDetails.append(Build.VERSION.RELEASE);
        sbDeviceDetails.append(" - App Version ");
        sbDeviceDetails.append(BuildConfig.VERSION_NAME);
        return sbDeviceDetails.toString();
    }

    public int extractNumbersFromString(String str) {
        StringBuilder sbNumber = new StringBuilder();
        String regex = "(\\d+)";
        Matcher matcher = Pattern.compile(regex).matcher(str);
        while (matcher.find()) {
            String num = matcher.group();
            sbNumber.append(num);
        }

        if (sbNumber.length() > 0)
            return Integer.parseInt(sbNumber.toString());
        else
            return -1;
    }

    public SimpleDateFormat getProfileDateFormat() {
        return new SimpleDateFormat("MMM dd");
    }

    public String stringForTime(Long remainingTime) {
        StringBuilder mFormatBuilder = new StringBuilder();
        Formatter mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());
        long totalSeconds = remainingTime / 1000;

        long seconds = totalSeconds % 60;
        long minutes = (totalSeconds / 60) % 60;
        long hours = totalSeconds / 3600;

        mFormatBuilder.setLength(0);
        if (hours > 0) {
            return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString();
        } else {
            return mFormatter.format("%02d:%02d", minutes, seconds).toString();
        }
    }

    /***
     * Becarefull on calling this method. Just double check the path of file or folder. Make sure it should not be a root path of
     * mobile sdcard. Otherwise it may can damange your device by delete any system files.
     *
     * @param fileOrDirectory
     */
    public void deleteFolderRecursively(File fileOrDirectory) {
        if (fileOrDirectory != null && fileOrDirectory.isDirectory())
            for (File child : fileOrDirectory.listFiles())
                deleteFolderRecursively(child);
        {
            fileOrDirectory.delete();
        }
    }

    @NETWORK_TYPE
    public int getTypeOfNetwork(Context context) {
        if (isNetworkAvailable(context)) {
            if (isOnWifiNetwork(context))
                return NETWORK_TYPE.WiFi;
            else
                return NETWORK_TYPE.DATA;
        } else {
            return NETWORK_TYPE.NO_CONNECTION;
        }
    }

    public void hideKeyboard(Activity activity) {
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public void showNetworkMessage(Context context) {
        Utils.getInstance().showDialog(context, context.getString(R.string.app_name), context.getString(R.string.msgNetworkError), null);
    }

    @DEVICE_DENSITY
    public int getDeviceResolution(Activity context) {
        DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        switch (metrics.densityDpi) {

            case DisplayMetrics.DENSITY_LOW:
                return DEVICE_DENSITY.ldpi;

            case DisplayMetrics.DENSITY_MEDIUM:
                boolean tabletSize = context.getResources().getBoolean(R.bool.md_is_tablet);
                if (tabletSize) {
                    // do something
                    return DEVICE_DENSITY.xhdpi;
                } else {
                    // do something else
                    return DEVICE_DENSITY.mdpi;
                }

            case DisplayMetrics.DENSITY_HIGH:
                return DEVICE_DENSITY.hdpi;

            case DisplayMetrics.DENSITY_TV:
            case DisplayMetrics.DENSITY_XHIGH:
                return DEVICE_DENSITY.xhdpi;

            case DisplayMetrics.DENSITY_XXHIGH:
                return DEVICE_DENSITY.xxhdpi;

            case DisplayMetrics.DENSITY_560:// Note 5 and later.
            case DisplayMetrics.DENSITY_XXXHIGH:
                return DEVICE_DENSITY.xxxhdpi;

            default:
                return DEVICE_DENSITY.UNKNOWN;
        }
    }

    public boolean writeFileToDisk(ResponseBody body, File file) {
        try {

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(file);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    Log.d(TAG, "file download: " + fileSizeDownloaded + " of " + fileSize);
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }

    public boolean isTwitterAppInstalled(Context context) {

        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo("com.twitter.android", 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public boolean appInstalledOrNot(String uri, Activity context) {
        PackageManager pm = context.getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }

    public String getPackageNameInstagram() {
        return "com.instagram.android";
    }

    public int generateRandomNumber(int min, int max) {
        return (int) (Math.floor(Math.random() * (max - min + 1)) + min);
    }

    public int getAngleOfImage(String path) {
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(path);
            int exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (exifOrientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    return 90;

                case ExifInterface.ORIENTATION_ROTATE_180:
                    return 180;

                case ExifInterface.ORIENTATION_ROTATE_270:
                    return 270;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;

    }

    public static boolean isLocationEnabled(Context context) {
        int locationMode = 0;
        String locationProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);

            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
            }

            return locationMode != Settings.Secure.LOCATION_MODE_OFF;

        } else {
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }
    }

    public void callIntentBackToPreviousWithTop(Class<?> activityName, Activity context) {
        String pacakageName = context.getPackageName();
        Intent i = new Intent();
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        i.setClassName(pacakageName, activityName.getName());

        android.util.Log.e("Activity", activityName.getName());
        context.startActivity(i);
    }

    public void callIntentBackToPreviousWithTop(Class<?> activityName, Activity context, Bundle bundle) {
        String pacakageName = context.getPackageName();
        Intent i = new Intent();
        i.putExtras(bundle);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        i.setClassName(pacakageName, activityName.getName());

        android.util.Log.e("Activity", activityName.getName());
        context.startActivity(i);
    }

    public boolean checkPlayServices(Activity activity) {
        /*
         * GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
         * int result = googleAPI.isGooglePlayServicesAvailable(activity);
         * if(result != ConnectionResult.SUCCESS) {
         * if(googleAPI.isUserResolvableError(result)) {
         * googleAPI.getErrorDialog(activity, result,
         * 9000).show();
         * }
         * return false;
         * }
         */

        return true;
    }

    public String formatDateString(org.joda.time.DateTime dateTime, @DATETIME_FORMAT String format) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(format).withLocale(Locale.US);
        return dateTime.toString(formatter);
    }

    public int convertToMilliseconds(String time) {
        int quoteInd = time.indexOf(":");
        int pointInd = time.lastIndexOf(":");

        int hour = Integer.valueOf(time.substring(0, quoteInd));
        int min = Integer.valueOf(time.substring(++quoteInd, pointInd));
        int sec = Integer.valueOf(time.substring(++pointInd, time.length()));

        return (((hour * 60 * 60) + (min * 60) + sec) * 1000);
    }

    public String convertInputStreamToString(InputStream is) throws IOException {

        StringBuffer sb = new StringBuffer();
        if (is != null) {
            String line = null;
            while (true) {
                final int ch = is.read();
                if (ch < 0) {
                    break;
                } else {
                    sb.append((char) ch);
                }
            }

            is.close();
        }
        return sb.toString();

    }

}
