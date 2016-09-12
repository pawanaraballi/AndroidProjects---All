package com.example.quiz_homework3;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;

public class WelcomeActivity extends AppCompatActivity implements MyAsync.IData{

    Button exitBtn,startBtn;
    ImageView img;
    static ProgressDialog pd;
     ArrayList<Question> QL=new ArrayList<Question>();
    public static String QUESTIONS="questions";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Welcome");


        img=(ImageView)findViewById(R.id.imageView2);
        exitBtn =(Button)findViewById(R.id.exitButton);
        startBtn = (Button)findViewById(R.id.startButton);

        if(isConnected()) {
            pd = new ProgressDialog(this);
            pd.setMessage("Loading Questions");
            pd.setCancelable(false);
            pd.show();
            new MyAsync(this).execute("http://dev.theappsdr.com/apis/spring_2016/hw3/index.php?qid=");

            startBtn.setEnabled(false);
        }
        else
        {
            Toast.makeText(this,"No Internet Connection",Toast.LENGTH_SHORT).show();
        }

    }
    public void exitPressed(View v)
    {
        finish();
        System.exit(0);
    }

    public void getQuestions(ArrayList<Question> QList) {
        QL = QList;
        startBtn.setEnabled(true);
        Button b= (Button) findViewById(R.id.startButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(WelcomeActivity.this, QuizActivity.class);
                Bundle b = new Bundle();
                b.putParcelableArrayList(QUESTIONS, QL);
                i.putExtras(b);
                finish();
                startActivity(i);
            }
        });
    }

    public boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }
}
