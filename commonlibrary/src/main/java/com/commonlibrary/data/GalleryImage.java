package com.commonlibrary.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;

import org.joda.time.DateTime;

/**
 * Created by asif.javaid on 7/2/13.
 */
public class GalleryImage implements Parcelable, Comparable<GalleryImage> {

    private DateTime capturedDate;
    private long ImageID;
    //private Bitmap Image;
    private String Name;
    private Boolean Selected = false;
    private String path;
    private Boolean IsUploaded = false;
    private Boolean isLoaded = false;
    private ImageView pictureView = null;
    private ProgressBar progressBar = null;
    private CheckBox checkBox = null;
    private String ImageDateTime;
    private int angle;
    private int width;
    private int height;

    public GalleryImage(){}

    protected GalleryImage(Parcel in) {
        ImageID = in.readLong();
        Name = in.readString();
        path = in.readString();
        ImageDateTime = in.readString();
        angle = in.readInt();
        width = in.readInt();
        height = in.readInt();
    }

    public static final Creator<GalleryImage> CREATOR = new Creator<GalleryImage>() {
        @Override
        public GalleryImage createFromParcel(Parcel in) {
            return new GalleryImage(in);
        }

        @Override
        public GalleryImage[] newArray(int size) {
            return new GalleryImage[size];
        }
    };

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public long getImageID() {
        return ImageID;
    }

    public void setImageID(long imageID) {
        ImageID = imageID;
    }

    /*public Bitmap getImage() {
        return Image;
    }

    public void setImage(Bitmap image) {
        Image = image;
    }*/

    public Boolean getSelected() {
        return Selected;
    }

    public void setSelected(Boolean selected) {
        Selected = selected;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public Boolean isUploaded() {
        return IsUploaded;
    }

    public void setUploaded(Boolean isUploaded) {
        IsUploaded = isUploaded;
    }


    public DateTime getCapturedDate() {
        return capturedDate;
    }

    public void setCapturedDate(DateTime capturedDate) {
        this.capturedDate = capturedDate;
    }

    /**
     * Return descending order.
     * @param another
     * @return
     */
    @Override
    public int compareTo(GalleryImage another)
    {
        if(this.getCapturedDate() != null && another.getCapturedDate() != null)
        {
            if(this.getCapturedDate().compareTo(another.getCapturedDate()) == 0)
            {
                return 0;
            }
            else if(this.getCapturedDate().compareTo(another.getCapturedDate()) > 0)
            {
                return -1;
            }
            else if(this.getCapturedDate().compareTo(another.getCapturedDate()) < 0)
            {
                return 1;
            }
        }

        return 0;
    }

    public Boolean getIsLoaded() {
        return isLoaded;
    }

    public void setIsLoaded(Boolean isLoaded) {
        this.isLoaded = isLoaded;
    }

    public ImageView getPictureView() {
        return pictureView;
    }

    public void setPictureView(ImageView pictureView) {
        this.pictureView = pictureView;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public String getImageDateTime() {
        return ImageDateTime;
    }

    public void setImageDateTime(String imageDateTime) {
        ImageDateTime = imageDateTime;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(ImageID);
        parcel.writeString(Name);
        parcel.writeString(path);
        parcel.writeString(ImageDateTime);
        parcel.writeInt(angle);
        parcel.writeInt(width);
        parcel.writeInt(height);
    }
}
