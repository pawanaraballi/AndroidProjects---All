package pawan.com.midterm;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Pawan on 3/21/2016.
 */
public class Venue implements Parcelable {

    private String VenueID;
    private String VenueName;
    private String CategoryName;
    private String CategoryIcon;
    private String checkinCount;
    int bookmark;

    public Venue() {
    }


    protected Venue(Parcel in) {
        VenueID = in.readString();
        VenueName = in.readString();
        CategoryName = in.readString();
        CategoryIcon = in.readString();
        checkinCount = in.readString();
        bookmark = in.readInt();
    }

    public static final Creator<Venue> CREATOR = new Creator<Venue>() {
        @Override
        public Venue createFromParcel(Parcel in) {
            return new Venue(in);
        }

        @Override
        public Venue[] newArray(int size) {
            return new Venue[size];
        }
    };

    public String getVenueID() {
        return VenueID;
    }

    public void setVenueID(String venueID) {
        VenueID = venueID;
    }

    public String getVenueName() {
        return VenueName;
    }

    public void setVenueName(String venueName) {
        VenueName = venueName;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getCategoryIcon() {
        return CategoryIcon;
    }

    public void setCategoryIcon(String categoryIcon) {
        CategoryIcon = categoryIcon;
    }

    public String getCheckinCount() {
        return checkinCount;
    }

    public void setCheckinCount(String checkinCount) {
        this.checkinCount = checkinCount;
    }



    public Venue(String venueID, String venueName, String categoryName, String categoryIcon, String checkinCount) {
        VenueID = venueID;
        VenueName = venueName;
        CategoryName = categoryName;
        CategoryIcon = categoryIcon;
        this.checkinCount = checkinCount;
        bookmark=R.drawable.unvisited;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(VenueID);
        dest.writeString(VenueName);
        dest.writeString(CategoryName);
        dest.writeString(CategoryIcon);
        dest.writeString(checkinCount);
        dest.writeInt(bookmark);
    }
}
