package mad.pawan.class2.class2b;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class DistanceConversionRadio extends AppCompatActivity {

    // Constants declared
    float valueinch = (float) 39.3701;
    float valuefeet = (float) 3.28;
    float valuemile = (float) 0.00006;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance_conversion_radio);

        final EditText editTextinput = (EditText) findViewById(R.id.editText_enterdis);
        final TextView textviewfinalresult = (TextView) findViewById(R.id.textView_finalresult);
        final TextView textviewresultchange = (TextView) findViewById(R.id.textView_result);

        final RadioButton rbtninch = (RadioButton) findViewById(R.id.radioButton_inch);
        final RadioButton rbtnfeet = (RadioButton) findViewById(R.id.radioButton_feet);
        final RadioButton rbtnmile = (RadioButton) findViewById(R.id.radioButton_mile);
        final RadioButton rbtnclear = (RadioButton) findViewById(R.id.radioButton_clear);

        Button btn = (Button) findViewById(R.id.button_convert);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rbtninch.isChecked()){
                    String enteredinput = editTextinput.getText().toString();
                    if (enteredinput.length()>0){
                        float convertedresult = Float.parseFloat(enteredinput) * valueinch;
                        textviewfinalresult.setText(convertedresult + "");
                        textviewresultchange.setText("Inches :");
                    }
                    else {
                        Toast.makeText(DistanceConversionRadio.this.getApplicationContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
                    }
                }
                else if(rbtnfeet.isChecked()){
                    String enteredinput = editTextinput.getText().toString();
                    if (enteredinput.length()>0){
                        float convertedresult = Float.parseFloat(enteredinput) * valuefeet;
                        textviewfinalresult.setText(convertedresult + "");
                        textviewresultchange.setText("Feets :");
                    }
                    else {
                        Toast.makeText(DistanceConversionRadio.this.getApplicationContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
                    }
                }
                else if (rbtnmile.isChecked()){
                    String enteredinput = editTextinput.getText().toString();
                    if (enteredinput.length()>0){
                        float convertedresult = Float.parseFloat(enteredinput) * valuemile;
                        textviewfinalresult.setText(convertedresult + "");
                        textviewresultchange.setText("Miles :");
                    }
                    else {
                        Toast.makeText(DistanceConversionRadio.this.getApplicationContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
                    }
                }
                else if(rbtnclear.isChecked()){
                    editTextinput.setText("");
                    textviewresultchange.setText("Result :");
                    textviewfinalresult.setText("");
                }
            }
        });

    }
}
