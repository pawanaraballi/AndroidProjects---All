package mad.pawan.class2.class2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);


    }
}
/*
//Note : Before Radio Button
        Button btn = (Button) findViewById(R.id.buttonOk);
        //Event Handlers
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("demo", " Ok Button Clicked");
            }
        });


        findViewById(R.id.buttonCancel).setOnClickListener(this);
        findViewById(R.id.buttonOtherCancel).setOnClickListener(this);
    }

        public void WrapButtonClick(View v){
        Log.d("demo", "Wrap Button Clicked");
    }

        //IOverview of UI Video
        //Log.d("Demo","The Button String Clicked :" + btn.getText().toString());


        //First Few classes
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Log.d("Deebug", "onCreate: ");
        Log.w("Deebug", "onCreate: ");

        String s = getResources().getString(R.string.label_name_header);
        Log.d("demo", s);

        String[] colors_array = getResources().getStringArray(R.array.colors);
        for (String str: colors_array) {
            Log.d("demo",str);
        }
        // First Few Classes End


    @Override
    public void onClick(View v) {
        //int id1 = v.getId();

        if(v.getId() == R.id.buttonCancel){
            Log.d("demo", " Cancel Button Clicked");
        }else if (v.getId() == R.id.buttonOtherCancel){
            Log.d("demo", "Other Cancel Button Clicked");
        }
        //Log.d("demo", " Cancel Button Clicked");
    }
}*/