package group5.com.finalsmad;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;

    final Context context = this;
    Firebase ref;
    EditText userInput;
    ArrayList<Addresss> favList = new ArrayList<>();
    ArrayList<Address> loc = new ArrayList<>();
    Address currloc;
    Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ref = Application.ref;
        Firebase.setAndroidContext(this);

        alertDialog();
        ShowAllFav();

        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void alertDialog(){
        // get prompts.xml view
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.customlayout, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                if (userInput.getText() == null || userInput.getText().equals("")){
                                    Toast.makeText(getApplicationContext(),"Please Enter a Valid Address",Toast.LENGTH_LONG).show();
                                }else{
                                    if (Geocoder.isPresent()){
                                        new GeoTask(getApplicationContext()).execute(String.valueOf(userInput.getText()));
                                    }else{
                                        Toast.makeText(getApplicationContext(),"No Geocoding",Toast.LENGTH_LONG).show();
                                    }

                                }
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Log.d("demo","Hi");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (favList.contains(currloc)){
                    Toast.makeText(getApplicationContext(),"Data already saved",Toast.LENGTH_LONG).show();
                    return false;
                }else {
                    Addresss ss = new Addresss();
                    ss.setCountryCode(currloc.getCountryCode());
                    ss.setCountryName(currloc.getCountryName());
                    ss.setFeatureName(currloc.getFeatureName());
                    ss.setLatitude(currloc.getLatitude());
                    ss.setLongtitude(currloc.getLongitude());
                    ss.setLocale(String.valueOf(currloc.getLocale()));
                    ss.setMaxAddressLineIndex(String.valueOf(currloc.getMaxAddressLineIndex()));
                    ref.child("Favorites").push().setValue(ss);
                    Toast.makeText(getApplicationContext(), "Data saved",Toast.LENGTH_SHORT).show();
                    marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                    //ShowAllFav();
                    return true;
                }
            }
        });
    }

    class GeoTask extends AsyncTask<String,Void,List<Address>> {

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
            if (addresses == null || addresses.size() == 0){
                Toast.makeText(getApplicationContext(),"Invalid address entered",Toast.LENGTH_LONG).show();
            }else{
                mMap.clear();
                Address address = addresses.get(0);
                currloc = address;
                LatLng latLng = new LatLng(address.getLatitude(),address.getLongitude());
                marker = mMap.addMarker(new MarkerOptions().position(latLng).title(address.getAddressLine(0)));
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                marker.showInfoWindow();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.viewmessageoptions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.enteraddress:
                alertDialog();
                break;

            case R.id.clearfav:
                ref.removeValue();
                Toast.makeText(this,"Cleared all the data from database",Toast.LENGTH_LONG).show();
                mMap.clear();
                break;
            // action with ID action_settings was selected
            case R.id.zoomtofit:
                ShowAllFav();
                break;
            default:
                break;
        }

        return true;
    }

    public void ShowAllFav(){
        ref.child("Favorites").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Addresss addd = postSnapshot.getValue(Addresss.class);
                    LatLng latLng = new LatLng(addd.getLatitude(),addd.getLongtitude());
                    Marker marker = mMap.addMarker(new MarkerOptions().position(latLng).title(addd.getFeatureName())
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                    marker.showInfoWindow();
                    favList.add(addd);
                }
                Log.d("demo",favList.size()+"");
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Toast.makeText(getApplicationContext(),"Error getting data",Toast.LENGTH_LONG).show();
            }
        });
    }
}
