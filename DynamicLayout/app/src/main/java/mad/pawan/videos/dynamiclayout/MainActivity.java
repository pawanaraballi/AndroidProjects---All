package mad.pawan.videos.dynamiclayout;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        RelativeLayout rl = new RelativeLayout(this);
        rl.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        setContentView(rl);

        TextView tv = new TextView(this);
        tv.setText(R.string.helloworld);
        tv.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        tv.setId(01750);
        rl.addView(tv);


        Button btn = new Button(this);
        btn.setText(R.string.clickme);
        RelativeLayout.LayoutParams buttonLayoutparams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        buttonLayoutparams.addRule(RelativeLayout.BELOW,tv.getId());
        btn.setLayoutParams(buttonLayoutparams);
        rl.addView(btn);

    }
}
