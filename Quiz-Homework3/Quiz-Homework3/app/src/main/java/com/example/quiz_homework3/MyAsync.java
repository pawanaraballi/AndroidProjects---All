package com.example.quiz_homework3;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MyAsync extends AsyncTask<String, Void, ArrayList<Question>>
{
    IData activity;
    public MyAsync(IData acticity)
    {
        this.activity=acticity;
    }

    BufferedReader reader;
    InputStream in = null;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    protected ArrayList<Question> doInBackground(String... params)
    {

        ArrayList<Question> QL =new ArrayList<>();
        URL url = null;
        try {

            for(int i=0;i<7;i++) {
                url = new URL(params[0] + i);

                HttpURLConnection con= (HttpURLConnection) url.openConnection();

                con.setRequestMethod("GET");
                 in=con.getInputStream();
                reader = new BufferedReader(new InputStreamReader(in));

                StringBuilder sb =new StringBuilder();
                String line="";

                while((line=reader.readLine())!=null)
                {

                   sb.append(line);
                }
                StringTokenizer st=new StringTokenizer(sb.toString(),";");
                int cnt=0,pos=0;
                Question q=new Question();
                String link=null;
                while(st.hasMoreElements())
                {
                    String token = st.nextToken();
                    int len=token.length();
                    if(len>5)
                    {
                        link = token.substring(len-4,len);
                    }
                    if(cnt == 0)
                    {
                        q.setId(Integer.parseInt(token));
                        cnt++;
                    }
                    else if(cnt ==1)
                    {
                        q.setQuestion(token);
                        cnt++;
                    }
                    else if(pos%2==0)
                    {

                        if(link.equals(".jpg"))
                        {
                         q.setLink(token);

                        }
                        else
                        {
                            q.setOptions(token);
                            pos++;
                        }
                    }
                    else if(pos%2!=0)
                    {
                        q.setPoints(token);
                        pos++;
                    }
                }
                QL.add(q);

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return(QL);
    }



    protected void onPostExecute(ArrayList<Question> QList)
    {
        WelcomeActivity.pd.dismiss();
       activity.getQuestions(QList);
        super.onPostExecute(QList);
    }

    static public interface IData
    {
        public void getQuestions(ArrayList<Question> QList);
    }


}


