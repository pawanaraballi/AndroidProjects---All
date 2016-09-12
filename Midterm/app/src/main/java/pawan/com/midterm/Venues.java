package pawan.com.midterm;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Venues extends AppCompatActivity implements GetData.IData {

    private String url = "https://api.foursquare.com/v2/venues/search?client_id=";
    private String client_id="4EMUIXIF2QNVSNOGJ3K24YYCDVIQBCJ2V3AZJSB0SS4NV1BX";
    private String client_secret = "PTAWECKHW0TRUUYIOCOHOCOIFTJ2TACPAKTN533122M2RTX0";
    private String city_sent,final_url;
    ListView listView;
    VenueAdapter adapter;
    ArrayList<Venue> venueArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venues);

        Calendar c = Calendar.getInstance();
        Log.d("formattedDate", c.getTime().toString());
        String date = c.getTime().toString();
        Log.d("formattedDate",date.substring(8,10));
        SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
        String formattedDate = df.format(c.getTime());
        Log.d("formattedDate",formattedDate);

        if (getIntent().getExtras() != null){
            city_sent = getIntent().getExtras().getString(MainActivity.CITY_KEY);
        }
        final_url = url+client_id+"&client_secret="+client_secret+"&v="+formattedDate+date.substring(8,10)+"&near="+city_sent;
        Log.d("finalurl",final_url);
        if(isConnected()) {
            new GetData(this).execute(final_url);
        }
        else
        {
            Toast.makeText(this, "Enable Internet", Toast.LENGTH_SHORT).show();
        }
        listView = (ListView) findViewById(R.id.listViewVenue);

        /*listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Venue venue = (Venue) listView.getItemAtPosition(position);

                if (venue.bookmark == R.drawable.unvisited) {
                    venue.bookmark = R.drawable.visited;
                    //long count = MainActivity.dao.store(story,category);
                    Toast.makeText(Venues.this, "Successful Addition", Toast.LENGTH_SHORT).show();
                } else {
                    venue.bookmark = R.drawable.unvisited;
                    //long count = MainActivity.dao.delete(story.getTitle().trim(),category);
                    Toast.makeText(Venues.this, "Successful deletion", Toast.LENGTH_SHORT).show();
                }

                setupData(venueArrayList);
                return true;
            }
        });*/

    }

    public boolean isConnected()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            return true;
        }
        return false;
    }

    @Override
    public void setupData(final ArrayList<Venue> venueArrayList) {

        adapter = new VenueAdapter(this,R.layout.rowitemlayout,venueArrayList);
        adapter.setNotifyOnChange(true);
        listView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_addcity:
                Intent inte = new Intent(this,Venues.class);
                startActivity(inte);
                break;
            // action with ID action_settings was selected
            case R.id.action_clearsavedcity:
                //dm.deleteAllCity();
                Toast.makeText(this,"Cleared Bookmark",Toast.LENGTH_LONG).show();
                adapter.setNotifyOnChange(true);
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    public Context getContext() {
        return this;
    }
}
