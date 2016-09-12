package pawan.com.midterm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    final static String CITY_KEY = "CITY";
    String cityName = "";
    Spinner citySpinner;
    Button button_submit;
    final static String MyPREFERENCES = "MyPREFERENCES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        citySpinner = (Spinner) findViewById(R.id.spinner);
        button_submit = (Button) findViewById(R.id.button_submit);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.citynames, android.R.layout.simple_spinner_item);
        citySpinner.setAdapter(adapter);

        adapter.setNotifyOnChange(true);

        citySpinner.setOnItemSelectedListener(this);

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(MainActivity.this, Venues.class);
                inte.putExtra(CITY_KEY, cityName);
                startActivity(inte);

                Log.d("demo", cityName);
            }
        });
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        TextView selectedText = (TextView) view;
        cityName = (String) selectedText.getText();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
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
                Intent inte = new Intent(MainActivity.this,Venues.class);
                startActivity(inte);
                break;
            // action with ID action_settings was selected
            case R.id.action_clearsavedcity:
                Toast.makeText(this,"Cleared Bookmark",Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }

        return true;
    }
}
