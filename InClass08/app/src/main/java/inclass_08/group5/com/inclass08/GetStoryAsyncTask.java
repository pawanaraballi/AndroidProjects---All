package inclass_08.group5.com.inclass08;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pawan on 2/26/2016.
 */

public class GetStoryAsyncTask extends AsyncTask<String, Void, ArrayList<Story>> {

    ProgressDialog progressDialog;
    TopStories searchMovie;
    DatabaseDataManager dm;
    final static String CLICKED_Title = "CLICK";
    final static String CLICKED_BYNAME = "CLICK_BYNAME";
    final static String CLICKED_IMAGE = "IMAGE";
    final static String CLICKED_ABSTRACT = "ABSTRACT";

    public GetStoryAsyncTask(TopStories searchMovie){this.searchMovie = searchMovie;}
    @Override
    protected ArrayList<Story> doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int statusCode = connection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line = reader.readLine();
                while (line != null){
                    stringBuilder.append(line);
                    line = reader.readLine();
                }
                Log.d("inside status", stringBuilder.toString());
                return StoryUtil.JSONParsestory.parsestory(stringBuilder.toString());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(final ArrayList<Story> movies) {
        super.onPostExecute(movies);
        if(movies != null) {
            Log.d("demo",movies.toString());
        }

        ListView listView = (ListView) searchMovie.findViewById(R.id.listView);
        //Log.d("thumbnail",movies.get(0).getStoryThumb_image_url());
        Log.d("pawan", movies.size() + "");
        StoryAdapter adapter = new StoryAdapter(searchMovie.getApplicationContext(),R.layout.rowitemlayout,movies);

        listView.setAdapter(adapter);

        adapter.setNotifyOnChange(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("demo", "Position : " + position + "Value : " + movies.get(position).toString());
                /*Intent inte = new Intent(searchMovie, DetailsActivity.class);
                inte.putExtra(CLICKED_ABSTRACT, movies.get(position).getStoryAbstract());
                inte.putExtra(CLICKED_BYNAME, movies.get(position).getStoryByline());
                inte.putExtra(CLICKED_IMAGE, movies.get(position).getStoryThumb_image_url());
                inte.putExtra(CLICKED_Title, movies.get(position).getStoryTitle());
                searchMovie.startActivity(inte);*/
            }
        });

        dm = new DatabaseDataManager(searchMovie);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                List<Story> stories = dm.getAllStory();
                try {
                    for (int i = 0; i < stories.size(); i++) {
                        if (movies.get(position).getStoryTitle() == stories.get(i).getStoryTitle()) {
                            dm.deleteStory(movies.get(position));
                        }
                    }
                }finally {
                    dm.saveStory(new Story(movies.get(position).getStoryTitle(),movies.get(position).getStoryByline(),
                            movies.get(position).getStoryAbstract(),movies.get(position).getStoryCreated_date(),
                            movies.get(position).getStoryThumb_image_url(),movies.get(position).getStoryNormal_image_url()));
                }


                return true;
            }
        });
        //scrollView.addView(linearLayout);
        progressDialog.dismiss();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(searchMovie);

        progressDialog.setMessage("Loading...");
        progressDialog.show();
        progressDialog.setCancelable(false);
    }
}


