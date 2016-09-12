package group5.com.fragmentdemo;

import android.app.Fragment;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AFragment.OnFragmentTextChange {

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("demo","Mainactivity: onStart");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("demo","Mainactivity: onCreate before inflating layout");
        setContentView(R.layout.activity_main);
        Log.d("demo","Mainactivity: onCreate after inflating layout");

        getFragmentManager().beginTransaction()
                .add(R.id.container,new AFragment(),"tag_afragment")
                .commit();

        getFragmentManager().beginTransaction()
                .add(R.id.container,new AFragment(),"tag_afragment_1")
                .commit();

        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                AFragment fragment = (AFragment)getFragmentManager().findFragmentByTag("tag_afragment");
                AFragment fragment1 = (AFragment)getFragmentManager().findFragmentByTag("tag_afragment_1");

                if (checkedId == R.id.radioButtonBlue){
                    fragment.changeColor(Color.BLUE);
                    fragment1.changeColor(Color.BLUE);
                }else if (checkedId == R.id.radioButtonGreen){
                    fragment.changeColor(Color.GREEN);
                    fragment1.changeColor(Color.GREEN);
                }else if (checkedId == R.id.radioButtonRed){
                    fragment.changeColor(Color.RED);
                    fragment1.changeColor(Color.RED);
                }
               /* AFragment fragment = (AFragment)getFragmentManager().findFragmentById(R.id.fragment);

                if (checkedId == R.id.radioButtonBlue){
                    fragment.changeColor(Color.BLUE);
                }else if (checkedId == R.id.radioButtonGreen){
                    fragment.changeColor(Color.GREEN);
                }else if (checkedId == R.id.radioButtonRed){
                    fragment.changeColor(Color.RED);
                }*/
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("demo","Mainactivity: onDestroy");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("demo","Mainactivity: onResume");
    }

    @Override
    public void onTextChanged(String text) {
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(text);

    }
}
