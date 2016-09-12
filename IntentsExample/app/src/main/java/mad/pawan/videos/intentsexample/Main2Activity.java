package mad.pawan.videos.intentsexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        if(getIntent().getExtras() != null){
            String name = getIntent().getExtras().getString(MainActivity.NAME_KEY);
            double age = getIntent().getExtras().getDouble(MainActivity.AGE, 22);
            User usr = (User) getIntent().getExtras().getSerializable(MainActivity.USER_KEY);
            Person per = getIntent().getExtras().getParcelable(MainActivity.PERSON_KEY);
            //Toast.makeText(this,name+" "+age,Toast.LENGTH_LONG).show();
            Toast.makeText(this,per.toString(),Toast.LENGTH_LONG).show();
        }
        else {

        }

        findViewById(R.id.button_goback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent inte = new Intent(Main2Activity.this, MainActivity.class);
                finish();
            }
        });
    }
}
