package inclass_08.group5.com.inclass08;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Pawan on 2/29/2016.
 */
public class StoryUtil {

    static public class JSONParsestory{
        ArrayList<Story> storyList;
        Story story;

        static ArrayList<Story> parsestory(String in) throws JSONException {
            ArrayList<Story> storyList = new ArrayList<>();

            JSONObject root = new JSONObject(in);
            JSONArray storyJSONarray = root.getJSONArray("results");

            String title,abstractstring,byline,created_date,url,thumbnail;
            url = "http://www.them.pro/files/images/url.preview.jpg";
            thumbnail = "http://www.them.pro/files/images/url.preview.jpg";

            Log.d("demo", storyJSONarray.toString());
            Log.d("demo length",storyJSONarray.length()+"");
try{
    for (int i = 0; i < storyJSONarray.length(); i++) {
        JSONObject storyJSONObj = storyJSONarray.getJSONObject(i);
        Log.d("inner demo",storyJSONObj.getString("section"));
        Log.d("title",storyJSONObj.getString("title"));
        // if (storyJSONObj.getString("title") != null && storyJSONObj.getString("abstract")!=null && storyJSONObj.getString("byline")!=null &&
        //       storyJSONObj.getString("created_date")!=null && storyJSONObj.getString("url")!= null ){
        title = storyJSONObj.getString("title");
        abstractstring = storyJSONObj.getString("abstract");
        byline  = storyJSONObj.getString("byline");
        created_date = storyJSONObj.getString("created_date");
        //url = storyJSONObj.getString("url");
        if(storyJSONObj.getJSONArray("multimedia") != null){
            Log.d("demo multimedia",storyJSONObj.getJSONArray("multimedia").toString());
            JSONArray thumbnailArray = storyJSONObj.getJSONArray("multimedia");
            if (thumbnailArray.length()>3){
                JSONObject thumbObj = thumbnailArray.getJSONObject(0);
                JSONObject thumbObj1 = thumbnailArray.getJSONObject(2);
                Log.d("pawan",thumbObj.getString("url"));
                url = thumbObj1.getString("url");
                thumbnail = thumbObj.getString("url");
            }else{
                JSONObject thumbObj = thumbnailArray.getJSONObject(0);
                thumbnail = thumbObj.getString("url");
                url = "http://www.them.pro/files/images/url.preview.jpg";
            }
             /*       }else {
                        url = "http://www.them.pro/files/images/url.preview.jpg";
                        thumbnail = "http://www.them.pro/files/images/url.preview.jpg";
                    }
*/

        }
        Story st = new Story(title,abstractstring,byline,created_date,url,thumbnail);
        Log.d("demo st",st.toString());
        storyList.add(st);

    }
}catch (Exception e){
    e.printStackTrace();
}

            //Log.d("storylist",storyList.get(0).getStoryThumb_image_url());
            Log.d("storylistsize",storyList.size()+"");
            return storyList;
        }

    }
}


