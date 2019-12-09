package com.commonlibrary.viewholders;

import android.app.Activity;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.commonlibrary.R;


/**
 * Created by    on 12/2/2016.
 */

public class CustomGalleryViewHolder {

    private Button btnUploadSelectPhotos = null;
    private GridView gridViewGallery = null;
    private RelativeLayout layoutMain;

    public CustomGalleryViewHolder(Activity view) {

        gridViewGallery = (GridView) view.findViewById(R.id.gridViewGallery);
        layoutMain = (RelativeLayout) view.findViewById(R.id.layoutMain);

    }

    public GridView getGridViewGallery() {
        return gridViewGallery;
    }

    public RelativeLayout getLayoutMain() {
        return layoutMain;
    }
}
