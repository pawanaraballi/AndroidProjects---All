package com.example.quiz_homework3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    ImageView image;
    TextView resultText,categoryText;
    ArrayList<Question> QL = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        int result = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Results");
        image = (ImageView) findViewById(R.id.imageView4);
        categoryText = (TextView) findViewById(R.id.textView5);
        resultText=(TextView)findViewById(R.id.textView6);
        if(getIntent().getExtras()!=null)
        {
            Bundle b=getIntent().getExtras();
            QL = b.getParcelableArrayList("questions");
            result = getIntent().getIntExtra("result_key",0);

        }

        if(result >=0 && result <=10)
        {
            categoryText.setText("NON GEEK");
            image.setImageResource(R.drawable.non_geek);
            resultText.setText(R.string.non_geek);
        }
        else if (result >10 && result <=50)
        {
            categoryText.setText("SEMI GEEK");
            image.setImageResource(R.drawable.semi_geek);
            resultText.setText(R.string.semi_geek);
        }
        else
        {
            categoryText.setText("UBER GEEK");
            image.setImageResource(R.drawable.uber_geek);
            resultText.setText(R.string.uber_geek);
        }

    }
    public void tryAgainClicked(View v)
    {
        Intent i=new Intent(this,QuizActivity.class);
        Bundle b=new Bundle();
        b.putParcelableArrayList("questions",QL);
        i.putExtras(b);
        finish();
        startActivity(i);
    }

    public void quitClicked(View v)
    {
        Intent  i = new Intent(this,WelcomeActivity.class);
        finish();
        startActivity(i);
    }
}
