package inclass11.group05.com.expenseapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddExpenseActivity extends AppCompatActivity {
    int selectedSpinner = 0;
    private Calendar calendar;
    static final int DEPART_DIALOG_ID = 0;
    private int departYear, departMonth, departDate;
    EditText date;
    String user,formatDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        Firebase.setAndroidContext(this);
        setTitle("Add Expense");

        if (getIntent().getExtras() != null){
            user = getIntent().getExtras().getString("USER");
        }
        final EditText name = (EditText) findViewById(R.id.editText_expenseName);
        final EditText amount = (EditText) findViewById(R.id.editText_amount);
        Button button = (Button) findViewById(R.id.button_addexpense);

        calendar = Calendar.getInstance();
        departYear = calendar.get(Calendar.YEAR);
        departMonth = calendar.get(Calendar.MONTH);
        departDate = calendar.get(Calendar.DAY_OF_MONTH);

        date = (EditText) findViewById(R.id.editText_date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DEPART_DIALOG_ID);
            }
        });

        final Spinner spinner = (Spinner) findViewById(R.id.spinnerCategory);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSpinner = position - 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        assert button != null;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().isEmpty() && name.getText().toString().equals("")){
                    Toast.makeText(AddExpenseActivity.this,"Expense Name is required.",Toast.LENGTH_SHORT).show();
                }else if(amount.getText().toString().isEmpty() && amount.getText().toString().equals("")){
                    Toast.makeText(AddExpenseActivity.this,"Amount is required.",Toast.LENGTH_SHORT).show();
                }else if (date.getText().toString().isEmpty() && date.getText().toString().equals("")){
                    Toast.makeText(AddExpenseActivity.this,"Date is required.",Toast.LENGTH_SHORT).show();
                } else {
                    Firebase ref = new Firebase("https://radiant-inferno-8138.firebaseio.com/expenses");
                    expense expense = new expense();
                    expense.setAmount(amount.getText().toString());
                    expense.setCategory(spinner.getSelectedItem().toString().trim());
                    String dateParsed = String.valueOf(date.getText());
                    try {
                        formatDate = convertToDate(dateParsed);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    expense.setDate(formatDate);
                    expense.setName(String.valueOf(name.getText()));
                    expense.setUser(user);

                    ref.push().setValue(expense, new Firebase.CompletionListener() {
                        @Override
                        public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                            if (firebaseError != null) {
                                System.out.println("Data could not be saved. " + firebaseError.getMessage());
                            } else {
                                System.out.println("Data saved successfully.");
                                Intent intent = new Intent(AddExpenseActivity.this, ExpensesList.class);
                                startActivity(intent);
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    protected Dialog onCreateDialog ( int id){
        //return calendar picker
        if (id == DEPART_DIALOG_ID) {
            return new DatePickerDialog(this, departDatePicker, departYear, departMonth, departDate);
        } else {
            return null;
        }
    }

    private DatePickerDialog.OnDateSetListener departDatePicker
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            departYear = year;
            departMonth = monthOfYear + 1;
            departDate = dayOfMonth;
            date.setText(departMonth + "/" + departDate + "/" + departYear);
        }
    };

    String convertToDate(String receivedDate) throws ParseException {
        SimpleDateFormat inputformatter = new SimpleDateFormat("M/dd/yyyy");
        Date date = inputformatter.parse(receivedDate);
        SimpleDateFormat outputformatter = new SimpleDateFormat("MMM dd yyyy");
        String newDate = outputformatter.format(date);
        return newDate;
    }
}
