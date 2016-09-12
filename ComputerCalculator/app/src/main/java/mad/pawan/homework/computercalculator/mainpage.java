/*Group 5
* Author: Pawan Araballi(800935601) , Sujit Nanda(800937636) and Truong Pham(800829408)
 */

package mad.pawan.homework.computercalculator;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.text.DecimalFormat;

public class mainpage extends AppCompatActivity {

    int memorySize = 0;
    int storageSize = 0;
    int selectedAccessories = 0;
    int shippingOption = 1;

    CheckBox mouseCB,flashdriveCB,coolingpadCB,carryingCaseCB ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

// Retrieving id
        mouseCB = (CheckBox) findViewById(R.id.checkBox_mouse);
        flashdriveCB = (CheckBox) findViewById(R.id.checkBox_flashdrive);
        coolingpadCB = (CheckBox) findViewById(R.id.checkBox_coolingpad);
        carryingCaseCB = (CheckBox) findViewById(R.id.checkBox_carryingcase);

        final EditText editDollarAmount = (EditText) findViewById(R.id.editText_budgetinput);
        final RadioGroup memoryRg = (RadioGroup) findViewById(R.id.radioGroup_memory);

        memoryRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
              RadioButton memoryrb  = (RadioButton)findViewById(checkedId);
            }
        });
        final RadioGroup storageRg = (RadioGroup)findViewById(R.id.radioGroup_storage);
        storageRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton storagerb = (RadioButton) findViewById(checkedId);
            }
        });

        final SeekBar tipSeekBar = (SeekBar) findViewById(R.id.seekBar_tips);
        final TextView tipbartext = (TextView) findViewById(R.id.textView_progress);
        tipbartext.setText(tipSeekBar.getProgress() + "%");
        //tipSeekBar.incrementProgressBy(5);
        tipSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress = ((int) Math.round(progress / 5)) * 5;
                seekBar.setProgress(progress);
                tipbartext.setText(progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final Switch deliveryswitch = (Switch) findViewById(R.id.switch_delivery);
        deliveryswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    shippingOption = 0;
                }
            }
        });
        final Switch deliverySwitch = (Switch) findViewById(R.id.switch_delivery);
        final TextView priceText = (TextView) findViewById(R.id.textView_pricevalue);
        final TextView status = (TextView) findViewById(R.id.textView_finalstatus);
        Button calculateButton = (Button) findViewById(R.id.button_calculate);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editDollarAmount.length() == 0) {
                    editDollarAmount.setError("Enter a dollar amount.");
                } else {
                    getMemorySize(memoryRg);
                    getStorageSize(storageRg);
                    getAccessoriesCount();
                    Double tip = Double.parseDouble(String.valueOf(tipSeekBar.getProgress()));
                    double ss = tip / 100;
                    Double cost = ((((10 * memorySize) + (0.75 * storageSize) + (20 * selectedAccessories)) *
                            (1 + (ss))) + (5.95 * shippingOption));
                    DecimalFormat df = new DecimalFormat("#.##");
                    df.format(cost);
                    priceText.setText("$" + cost.toString());
                    Double budget = Double.parseDouble(editDollarAmount.getText().toString());
                    if (cost < budget) {
                        status.setText("Within Budget");
                        status.setBackgroundColor(Color.GREEN);
                    } else {
                        status.setText("Over Budget");
                        status.setBackgroundColor(Color.RED);
                    }
                }
            }

        });
        Button resetButton = (Button) findViewById(R.id.button_reset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editDollarAmount.setText("");
                memoryRg.clearCheck();
                memoryRg.check(R.id.radioButton_2gb);
                storageRg.clearCheck();
                storageRg.check(R.id.radioButton_250gb);
                mouseCB.setChecked(false);
                flashdriveCB.setChecked(false);
                coolingpadCB.setChecked(false);
                carryingCaseCB.setChecked(false);
                deliverySwitch.setChecked(true);
                tipSeekBar.setProgress(15);
                priceText.setText("$0.00");
                status.setText("");

            }


        });
    }

    private void getMemorySize(RadioGroup group) {
        if (group.getCheckedRadioButtonId() == R.id.radioButton_2gb) {
            memorySize = 2;
        } else if (group.getCheckedRadioButtonId() == R.id.radioButton_4gb) {
            memorySize = 4;
        } else if (group.getCheckedRadioButtonId() == R.id.radioButton_8gb) {
            memorySize = 8;
        } else if (group.getCheckedRadioButtonId() == R.id.radioButton_16gb) {
            memorySize = 16;
        } else {
            memorySize = 0;
        }
    }

    private void getStorageSize(RadioGroup group) {
        if (group.getCheckedRadioButtonId() == R.id.radioButton_250gb) {
            storageSize = 250;
        } else if (group.getCheckedRadioButtonId() == R.id.radioButton_500gb) {
            storageSize = 500;
        } else if (group.getCheckedRadioButtonId() == R.id.radioButton_750gb) {
            storageSize = 750;
        } else if (group.getCheckedRadioButtonId() == R.id.radioButton_1TB) {
            storageSize = 1000;
        } else {
            storageSize = 0;
        }
    }

    private void getAccessoriesCount() {


        if (mouseCB.isChecked()) {
            selectedAccessories++;
        }
        if (flashdriveCB.isChecked()) {
            selectedAccessories++;
        }
        if (coolingpadCB.isChecked()) {
            selectedAccessories++;
        }
        if (carryingCaseCB.isChecked()) {
            selectedAccessories++;
        }
    }

}