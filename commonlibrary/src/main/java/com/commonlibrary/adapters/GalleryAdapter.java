package com.commonlibrary.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.commonlibrary.R;
import com.commonlibrary.data.GalleryImage;
import com.commonlibrary.logger.Log;

import java.util.List;

/**
 * Created by asif.javaid on 7/2/13.
 */
public class GalleryAdapter extends ArrayAdapter<GalleryImage> {

    private static int RESOURCE_ID = R.layout.rowlayout_gallery;
    private List<GalleryImage> _ListOfImages = null;
    private int mSize;
    private Activity _context;
    public static int _countPhotos = 0;
    private int _totalPhotos = 0;


    public GalleryAdapter(Activity context, List<GalleryImage> objects, int size) {
        super(context, RESOURCE_ID, objects);
        this._context = context;
        this._ListOfImages = objects;
        mSize = size;
    }

    class ViewHolder {
        ImageView imageview;
        //CheckBox checkbox;
        TextView textViewName;
        int id;
        public ProgressBar progressBar;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        try {
            final ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                LayoutInflater mInflater = _context.getLayoutInflater();
                convertView = mInflater.inflate(RESOURCE_ID, null);

                holder.imageview = (ImageView) convertView.findViewById(R.id.imageView);
                //holder.checkbox = (CheckBox) convertView.findViewById(R.id.checkBox);
                //holder.checkbox.setOnClickListener(imageViewClickListener);
                //holder.imageview.setOnClickListener(imageViewClickListener);
                holder.progressBar = (ProgressBar) convertView.findViewById(R.id.progressBar);
                //holder.textViewName = (TextView) convertView.findViewById(R.id.textViewName);
                convertView.setLayoutParams(new GridView.LayoutParams(GridView.AUTO_FIT, mSize));
                convertView.setTag(holder);
            }
            else
            {
                holder = (ViewHolder) convertView.getTag();
            }

            if (this._ListOfImages != null)
            {
                final GalleryImage galleryImage = this._ListOfImages.get(position);
                if(galleryImage != null)
                {
                    //galleryImage.setCheckBox(holder.checkbox);
                    //holder.checkbox.setTag(galleryImage);
                    holder.imageview.setTag(galleryImage);

                    holder.id = position;
                    if(galleryImage.getPath() != null)
                    {
                        ViewGroup.LayoutParams params = holder.imageview.getLayoutParams();
                        int width = params.width;
                        int height = params.height;

                        galleryImage.setWidth(width);
                        galleryImage.setHeight(height);

                        //TODO
//                        Glide.with(_context).load("file://"+galleryImage.getPath()).into(holder.imageview);

                    }

                    //holder.checkbox.setChecked(galleryImage.getSelected());
                }
            }


            return convertView;
        }
        catch (Exception e)
        {
            Log.e("GalleryAdapter",e.getMessage());
            return null;
        }
    }

    private View.OnClickListener imageViewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            GalleryImage galleryImage = (GalleryImage) v.getTag();
            if(galleryImage != null)
            {
                for(int index = 0; index < _ListOfImages.size(); index++)
                {
                    _ListOfImages.get(index).setSelected(false);
                }
                galleryImage.setSelected(true);
                notifyDataSetChanged();
            }

        }
    };

    public List<GalleryImage> getAllImages()
    {
        return _ListOfImages;
    }


    public GalleryImage getSelectedImage()
    {
        if (_ListOfImages != null)
        {
            for(GalleryImage galleryImage : _ListOfImages)
            {
                if(galleryImage.getSelected())
                    return galleryImage;
            }
        }
        return null;
    }

}
