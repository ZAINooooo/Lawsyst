package com.example.lawsyst;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.example.lawsyst.EditMatter.days_leave467;
import static com.example.lawsyst.Filter_Matter_Screen.days_leave4543;
import static com.example.lawsyst.Filter_Matter_Screen.days_leave4544;
import static com.example.lawsyst.MattersDetailsScreen.days_leave4;
import static com.example.lawsyst.MattersDetailsScreen.days_leave5;


public class selected_date_fragment45 extends DialogFragment {

    DatePicker mDatePicker;
    public static String date_conversion2;

    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.N)

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {

        final Calendar c = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        final Calendar calendar= Calendar.getInstance();
        calendar.set(year, month,day);

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                calendar.set(year,month,dayOfMonth);

                date_conversion2 = dateFormat1.format(calendar.getTime());
                days_leave4544.setText(date_conversion2);
                Log.d("DateIs" , date_conversion2);

            }
        };

        DatePickerDialog dialog=new DatePickerDialog(getActivity(),dateSetListener,year,month,day);

        return dialog;
    }

}
