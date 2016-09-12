package pawan.com.midterm;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Pawan on 3/21/2016.
 */
public class VenueUtil {

    static public class JSONParseVenue {
        ArrayList<Venue> venueArrayList;


        static ArrayList<Venue> parseVenue(String in) throws JSONException {
            ArrayList<Venue> venueArrayList = new ArrayList<>();
            String VenueID;
            String VenueName;
            String CategoryName;
            String CategoryIcon;
            String checkinCount;

            JSONObject root = new JSONObject(in);
            JSONObject venueJSONOBJ1 = root.getJSONObject("response");
            JSONArray venueJSONarray = venueJSONOBJ1.getJSONArray("venues");
            Log.d("venueJSONarray",venueJSONarray.toString());
            try {
                for (int i = 0; i < venueJSONarray.length(); i++) {
                    JSONObject venueJSONObj = venueJSONarray.getJSONObject(i);
                    VenueID = venueJSONObj.getString("id");
                    VenueName = venueJSONObj.getString("name");
                    JSONArray cateArray = venueJSONObj.getJSONArray("categories");
                    CategoryName = cateArray.getJSONObject(0).getString("name");
                    CategoryIcon = cateArray.getJSONObject(0).getJSONObject("icon").getString("prefix")+"bg_64"+
                            cateArray.getJSONObject(0).getJSONObject("icon").getString("suffix");
                    Log.d("CategoryIcon",CategoryIcon);
                    if (venueJSONObj.getJSONObject("stats").getString("checkinsCount") == null){
                        checkinCount = "0";
                    }else {
                        checkinCount = venueJSONObj.getJSONObject("stats").getString("checkinsCount");
                    }

                    Venue venue = new Venue(VenueID,VenueName,CategoryName,CategoryIcon,checkinCount);
                    Log.d("venueObj",venue.toString());
                    venueArrayList.add(venue);

                }
            }catch(Exception e){
                e.printStackTrace();
            }
            return venueArrayList;
        }
    }
}
