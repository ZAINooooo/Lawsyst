package com.example.lawsyst;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ExpandableListAdapters extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;
    private List<Integer> _icon;           //icon List


    static
    {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public ExpandableListAdapters(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData,
                                 List<Integer> icons) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        this._icon = icons;

    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
    {


//        if (isLastChild)
//        {
//            convertView.setPadding(0, 0, 0, 30);
//        }


        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null)
        {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);


        }

        TextView txtListChild = convertView.findViewById(R.id.lblListItem);

        txtListChild.setText(childText);

        return convertView;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int getChildrenCount(int groupPosition) {

        int size =0;
        if(!Objects.equals(this._listDataChild.get(this._listDataHeader.get(groupPosition)),null)){
            size = this._listDataChild.get(this._listDataHeader.get(groupPosition)).size();
        }
        return size;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("ResourceAsColor")
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);




        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = Objects.requireNonNull(infalInflater).inflate(R.layout.list_group, null);
        }


        ImageView iconView = convertView.findViewById(R.id.icon);
        iconView.setImageResource(this._icon.get(groupPosition));

        TextView lblListHeader = convertView.findViewById(R.id.lblListHeader);

//        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        LinearLayout linearLayout = convertView.findViewById(R.id.main_group);

        if(isExpanded)
        {
            linearLayout.setBackgroundColor(ContextCompat.getColor(_context, R.color.darkGreen));
        }

        else
        {
            linearLayout.setBackgroundColor(ContextCompat.getColor(_context, R.color.darkGreen));

        }

        switch (groupPosition){
            case 0:

            case 4:
//
////
//            case 5:

            case 5:
                lblListHeader.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                break;


            default:
                lblListHeader.setCompoundDrawablesWithIntrinsicBounds(0, 0, isExpanded ? 0 : R.drawable.ic_add2, 0);
        }

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition)
    {

        return true;

        //txtListChild.setText(childText);

    }
}