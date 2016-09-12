package com.example.quiz_homework3;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class QuizActivity extends AppCompatActivity
{

    int i=0;
    TextView Qnum,test;
     ImageView img;
    ProgressDialog pd;
    LinearLayout ll;
    ArrayList<Question> QList= new ArrayList<>();
    ArrayList<Integer> shuffleOptions;
    RadioGroup rg;
    LinearLayout.LayoutParams lb;
    Question q;
    int score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Quiz");

        Qnum = (TextView)findViewById(R.id.textView2);
        test= (TextView) findViewById(R.id.textView3);
        img= (ImageView) findViewById(R.id.imageView3);
        rg = (RadioGroup) findViewById(R.id.radioGrp);
        ll=(LinearLayout) findViewById(R.id.linear);
        pd= new ProgressDialog(this);
        pd.setCancelable(false);
         lb = new LinearLayout.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, AbsListView.LayoutParams.WRAP_CONTENT);


        Intent intent=getIntent();
        if(intent.getExtras()!=null)
        {
            Bundle b = intent.getExtras();
            QList = b.getParcelableArrayList(WelcomeActivity.QUESTIONS);

           q=QList.get(i);
            if(q.getLink()!=null)
            {
                new ImageSync().execute(q.getLink());
            }
            else
            {
                img.setImageBitmap(null);
            }
            test.setText(q.getQuestion());
            addOptions(q);

        }

    }

    private void addOptions(Question q)
    {

        List<String> op=q.getOptions();
            int cnt = op.size();
            shuffleOptions = new ArrayList<>();
for(int i=0;i<cnt;i++)
{
    shuffleOptions.add(i);
}
        Collections.shuffle(shuffleOptions);
          for(int i=0;i<cnt;i++)
            {
                RadioButton rb =new RadioButton(this);
                rb.setId(i);
                rb.setText(q.options.get(shuffleOptions.get(i)));
                rb.setLayoutParams(lb);
                rg.addView(rb);


            }
    }


    public void quitPressed(View v)
    {
        Intent i =new Intent(this,WelcomeActivity.class);
        finish();
        startActivity(i);
    }


    public void nextClicked(View v)
    {
        int id =rg.getCheckedRadioButtonId();
        if(id==-1)
        {

            Toast.makeText(this,"Please Select option",Toast.LENGTH_SHORT).show();

        }
        else {

            score = score + q.getPoints(shuffleOptions.get(id));
            rg.clearCheck();
            rg.removeAllViews();
            i++;
            if (i > QList.size() - 1) {

                Intent i = new Intent(this,ResultActivity.class);

                Bundle b =new Bundle();
                b.putParcelableArrayList("questions",QList);
                i.putExtra("result_key",score);
                i.putExtras(b);
                finish();
                startActivity(i);
            }
            else {
                Qnum.setText("Q" + (i + 1));

                 q = QList.get(i);

                test.setText(q.getQuestion());
                addOptions(q);
                if (q.getLink() != null) {

                    new ImageSync().execute(q.getLink());


                } else {
                    img.setImageBitmap(null);
                }
            }
        }

    }



    private class ImageSync extends AsyncTask<String,Void,Bitmap>
    {

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            pd.show();

        }

        @Override
        protected Bitmap doInBackground(String... params)
        {
            InputStream in;
            Bitmap image =null;
            try {
                URL url =new URL(params[0]);
                HttpURLConnection con= (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                in=con.getInputStream();
                 image= BitmapFactory.decodeStream(in);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return image;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap)
        {
            super.onPostExecute(bitmap);
            if(bitmap!=null) {
                try {
                    img.setImageBitmap(bitmap);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            pd.dismiss();

            }
        }
    }

}
