package mad.pawan.videos.activityforresultexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final EditText editText = (EditText) findViewById(R.id.editText);

        findViewById(R.id.button_sendresult).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = editText.getText().toString();
                if(str == null || str.length() == 0)
                {
                    setResult(RESULT_CANCELED);
                }
                else
                {
                    Intent inte = new Intent();
                    inte.putExtra(MainActivity.VALUE_KEY, str);
                    setResult(RESULT_OK,inte);
                }
                finish();
            }
        });
    }
}
