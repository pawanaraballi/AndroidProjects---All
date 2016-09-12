package com.example.quiz_homework3;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;


public class MainActivity extends AppCompatActivity {

    private Thread thread;
    Boolean flag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Splash");

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    if(!flag) {
                        myMethod();
                    }
                }
            }, 8000);
    }




    public void startPressed(View v)
    {
     myMethod();
    }

    public void myMethod()
    {
        Intent i=new Intent(this,WelcomeActivity.class);
        flag=true;
        finish();
        startActivity(i);
    }
}
