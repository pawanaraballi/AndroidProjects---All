package group5.com.geocodingdemo;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Geocoder.isPresent()){
            new GeoTask(this).execute("CLT");
        }else{
            Toast.makeText(this,"No Geocoding",Toast.LENGTH_LONG).show();
        }
    }

    class GeoTask extends AsyncTask<String,Void,List<Address>>{

        Context mContext;
        public GeoTask(Context context){
            mContext = context;
        }

        @Override
        protected List<Address> doInBackground(String... params) {
            List<Address> addressList = null;

            Geocoder geocoder = new Geocoder(mContext);
            try {
                addressList =  geocoder.getFromLocationName(params[0],10);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return addressList;
        }

        @Override
        protected void onPostExecute(List<Address> addresses) {
            if (addresses == null){
                Log.d("demo","No Results Found");
            }else{
                Log.d("demo",addresses.toString());
            }
        }
    }
}
