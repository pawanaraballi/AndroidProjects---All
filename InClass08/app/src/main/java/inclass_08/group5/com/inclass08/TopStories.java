package inclass_08.group5.com.inclass08;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class TopStories extends AppCompatActivity {

    String reqdata,url,apikey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_stories);

        if (getIntent().getExtras() != null){
            reqdata = getIntent().getExtras().getString(MainActivity.NAME_KEY);
        }
        apikey = "b3ff45e79cdd161dd0d35c592b4757bf:18:74582599";

        url = "http://api.nytimes.com/svc/topstories/v1/"+reqdata+".json?api-key="+apikey;

        Log.d("url", url);

        new GetStoryAsyncTask(this).execute(url);
    }
}
