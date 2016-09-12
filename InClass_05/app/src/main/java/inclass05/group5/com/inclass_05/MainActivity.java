package inclass05.group5.com.inclass_05;

import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> UNCC = new ArrayList<>();
    static ArrayList<String> ANDROID = new ArrayList<>();
    static ArrayList<String> WINTER =new ArrayList<>();
    static ArrayList<String> AURORA =new ArrayList<>();
    static ArrayList<String> WONDERS = new ArrayList<>();
    Button buttongo;
    ImageButton imageButtonright,imageButtonleft;
    EditText editTextinput;
    static ImageView imageViewcontent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new GetPhotoIDs().execute("http://dev.theappsdr.com/apis/spring_2016/inclass5/URLs.txt");
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this.getApplicationContext());

        final ListIterator UncclistIterator = UNCC.listIterator();
        final ListIterator AndroidlistIterator = ANDROID.listIterator();
        final ListIterator winterlistIterator = WINTER.listIterator();
        final ListIterator AuroralistIterator = AURORA.listIterator();
        final ListIterator WonderslistIterator = WONDERS.listIterator();

        buttongo = (Button) findViewById(R.id.button_go);
        editTextinput = (EditText) findViewById(R.id.editText_searchkeyword);
        imageButtonleft = (ImageButton) findViewById(R.id.imageButton_left);
        imageButtonright = (ImageButton) findViewById(R.id.imageButton_right);
        imageViewcontent = (ImageView) findViewById(R.id.imageView_displayimage);
        imageButtonleft.setClickable(false);
        imageButtonright.setClickable(false);

        buttongo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("demo", editTextinput.getText() + "");
                if (editTextinput.getText().toString().length() > 0) {
                    if (editTextinput.getText().toString().equals("UNCC")) {
                        Log.d("demo", "get first element" + UNCC.get(0));
                        new GetImage().execute(UNCC.get(0));
                        imageButtonleft.setClickable(true);
                        imageButtonright.setClickable(true);
                    }
                    if (editTextinput.getText().toString().equals("Android")) {
                        new GetImage().execute(ANDROID.get(0));
                        imageButtonleft.setClickable(true);
                        imageButtonright.setClickable(true);
                    }
                    if (editTextinput.getText().toString().equals("winter")) {
                        new GetImage().execute(WINTER.get(0));
                        imageButtonleft.setClickable(true);
                        imageButtonright.setClickable(true);
                    }
                    if (editTextinput.getText().toString().equals("aurora")) {
                        new GetImage().execute(AURORA.get(0));
                        imageButtonleft.setClickable(true);
                        imageButtonright.setClickable(true);
                    }
                    if (editTextinput.getText().toString().equals("wonders")) {
                        new GetImage().execute(WONDERS.get(0));
                        imageButtonleft.setClickable(true);
                        imageButtonright.setClickable(true);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Wrong Input", Toast.LENGTH_LONG).show();
                }

            }
        });

        imageButtonleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextinput.getText().toString().equals("UNCC")) {
                    int prevIndex = UncclistIterator.previousIndex();
                    String str =UNCC.get(prevIndex);
                    new GetImage().execute(str);
                    imageButtonleft.setClickable(true);
                    imageButtonright.setClickable(true);
                }
                if (editTextinput.getText().toString().equals("Android")) {
                    int prevIndex = AndroidlistIterator.previousIndex();
                    String str =ANDROID.get(prevIndex);
                    new GetImage().execute(str);
                    imageButtonleft.setClickable(true);
                    imageButtonright.setClickable(true);
                }
                if (editTextinput.getText().toString().equals("winter")) {
                    int prevIndex = winterlistIterator.previousIndex();
                    String str =WINTER.get(prevIndex);
                    new GetImage().execute(str);
                    imageButtonleft.setClickable(true);
                    imageButtonright.setClickable(true);
                }
                if (editTextinput.getText().toString().equals("aurora")) {
                    int prevIndex = AuroralistIterator.previousIndex();
                    String str =AURORA.get(prevIndex);
                    new GetImage().execute(str);
                    imageButtonleft.setClickable(true);
                    imageButtonright.setClickable(true);
                }
                if (editTextinput.getText().toString().equals("wonders")) {
                    int prevIndex = WonderslistIterator.previousIndex();
                    String str =WONDERS.get(prevIndex);
                    new GetImage().execute(str);
                    imageButtonleft.setClickable(true);
                    imageButtonright.setClickable(true);
                }

            }
        });

        imageButtonright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str ="";
                Log.d("demo",UncclistIterator.next().toString());
                if (editTextinput.getText().toString().equals("UNCC")) {
                    if (UncclistIterator.hasNext())
                    {
                        str = (String) UncclistIterator.next();
                    }

                    new GetImage().execute(str);
                    imageButtonleft.setClickable(true);
                    imageButtonright.setClickable(true);
                }

            }
        });

    }

    private boolean isConnectedOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        //networkInfo.getType()
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }
}
