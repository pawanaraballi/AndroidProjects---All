package mad.pawan.class2.class2a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DistanceConversion extends AppCompatActivity {
    // Constants declared
    float valueinch = (float) 39.3701;
    float valuefeet = (float) 3.28;
    float valuemile = (float) 0.00006;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance_conversion);

        final EditText editTextinput = (EditText) findViewById(R.id.editText_enterdis);
        final TextView textviewfinalresult = (TextView) findViewById(R.id.textView_finalresult);
        final TextView textviewresultchange = (TextView) findViewById(R.id.textView_result);


        Button btninch = (Button) findViewById(R.id.button_inch);
        btninch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredinput = editTextinput.getText().toString();
                if (enteredinput.length()>0){
                    float convertedresult = Float.parseFloat(enteredinput) * valueinch;
                    textviewfinalresult.setText(convertedresult + "");
                    textviewresultchange.setText("Inches :");
                }
                else {
                    Toast.makeText(DistanceConversion.this.getApplicationContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
                }

            }
        });

        Button btnfeet = (Button) findViewById(R.id.button_feet);
        btnfeet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredinput = editTextinput.getText().toString();
                if (enteredinput.length()>0){
                    float convertedresult = Float.parseFloat(enteredinput) * valuefeet;
                    textviewfinalresult.setText(convertedresult + "");
                    textviewresultchange.setText("Feets :");
                }
                else {
                    Toast.makeText(DistanceConversion.this.getApplicationContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button btnmile = (Button) findViewById(R.id.button_mile);
        btnmile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredinput = editTextinput.getText().toString();
                if (enteredinput.length()>0){
                    float convertedresult = Float.parseFloat(enteredinput) * valuemile;
                    textviewfinalresult.setText(convertedresult + "");
                    textviewresultchange.setText("Miles :");
                }
                else {
                    Toast.makeText(DistanceConversion.this.getApplicationContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button btnclearall = (Button) findViewById(R.id.button_cancel);
        btnclearall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editTextinput.setText("");
                textviewresultchange.setText("Result :");
                textviewfinalresult.setText("");
            }
        });
    }
}
