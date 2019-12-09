package com.example.lawsyst;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Notes_Tab extends Fragment {


    public Notes_Tab() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Notes_Tab newInstance(String param1, String param2) {
        return new Notes_Tab();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {

        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes__tab, container, false);
    }

}
