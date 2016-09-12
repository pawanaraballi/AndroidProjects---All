package mad.pawan.videos.activityforresultexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 100;
    public static final String VALUE_KEY = "value";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE){
            if(resultCode == RESULT_OK)
            {
                String result = data.getExtras().getString(VALUE_KEY);
                Log.d("demo",result);
            }else if(resultCode == RESULT_CANCELED){
                Log.d("demo","No result received");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_gotosecond).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(MainActivity.this,SecondActivity.class);
                startActivityForResult(inte,REQUEST_CODE);
            }
        });


    }
}
