/**
 * Author : Pawan Araballi
 * Student id : 800935601
 * Author : Sujit Nanda
 * Student id : 800937636
 * Author : Truong Pham
 * Student id : 800829408
 */


package group5_inclass_04.com.inclass04;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.*;

import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//import java.util.logging.Handler;

public class MainActivity extends Activity {

    static boolean numbers = false;
    static boolean uppercase = false;
    static boolean lowercase = false;
    static boolean specialChar = false;
    String passwordfetched;
    ProgressDialog progressDialog;
    ExecutorService threadPool;
    Handler handler;
    int selectedSpinner = 0;
    int checkboxCount =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinnerPasswordlength);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.passwordlength_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("demo",position+"");
                selectedSpinner = position - 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        final Button buttonThreadGeneratePassword = (Button)findViewById(R.id.button_threadgeneratepassword);
        Button buttonAsyncGeneratePassword = (Button) findViewById(R.id.button_asyncgeneratepassword);

        final CheckBox checkBoxNumbers = (CheckBox) findViewById(R.id.checkBoxNumber);
        final CheckBox checkBoxUpperLetter = (CheckBox) findViewById(R.id.checkBoxUpperletter);
        final CheckBox checkBoxLowerCase = (CheckBox) findViewById(R.id.checkBoxLowercase);
        final CheckBox checkBoxSpecialCharacter = (CheckBox) findViewById(R.id.checkBoxSpecialcharacter);

//handler to exchange message
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                TextView textViewFinalPassword = (TextView) findViewById(R.id.textViewFinalPassword);
                textViewFinalPassword.setText(msg.getData().getString("KEY").toString());
                textViewFinalPassword.setTextColor(Color.parseColor("red"));

                return false;
            }
        });



        buttonAsyncGeneratePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(checkBoxNumbers.isChecked())
                {
                    numbers = true;
                    checkboxCount++;
                }
                if(checkBoxLowerCase.isChecked()){
                    lowercase = true;
                    checkboxCount++;
                }
                if(checkBoxUpperLetter.isChecked())
                {
                    uppercase = true;
                    checkboxCount++;
                }
                if(checkBoxSpecialCharacter.isChecked()){
                    specialChar = true;
                    checkboxCount++;
                }

                if (checkboxCount == 0)
                {
                    Toast.makeText(MainActivity.this,"No CheckBox Selected",Toast.LENGTH_LONG).show();
                }
                else
                {
                    new DoWork().execute();
                    progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setCancelable(false);
                    progressDialog.setMessage("Generating Password");
                    progressDialog.show();
                }

            }
        });


        //thread
        threadPool = Executors.newFixedThreadPool(2);
        buttonThreadGeneratePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkBoxNumbers.isChecked())
                {
                    numbers = true;
                    checkboxCount++;
                }
                if(checkBoxLowerCase.isChecked()){
                    lowercase = true;
                    checkboxCount++;
                }
                if(checkBoxUpperLetter.isChecked())
                {
                    uppercase = true;
                    checkboxCount++;
                }
                if(checkBoxSpecialCharacter.isChecked()){
                    specialChar = true;
                    checkboxCount++;
                }

                if (checkboxCount == 0)
                {
                    Toast.makeText(MainActivity.this,"No CheckBox Selected",Toast.LENGTH_LONG).show();
                }
                else
                {
                    //create child thread
                    Thread thread = new Thread(new DoWorkThread());
                    thread.start(); //child thread, main class is parent thread
                    progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setCancelable(false);
                    progressDialog.setMessage("Generating Password");
                    progressDialog.show();
                }
            }
        });

    }


    class DoWork extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {




            passwordfetched = Util.getPassword(selectedSpinner,numbers,uppercase,lowercase,specialChar);
            return passwordfetched;
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            TextView textViewFinalPassword = (TextView) findViewById(R.id.textViewFinalPassword);
            textViewFinalPassword.setText(passwordfetched);
            textViewFinalPassword.setTextColor(Color.parseColor("red"));
            progressDialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }



    class DoWorkThread implements Runnable {

        @Override
        public void run() {
            Message msg = new Message();
            Bundle data = new Bundle();
            //call the getPassword

            String password =Util.getPassword(selectedSpinner, numbers, uppercase, lowercase, specialChar);

            data.putString("KEY", password);
            msg.setData(data);
            handler.sendMessage(msg);
            progressDialog.dismiss();
        }
    }
}
