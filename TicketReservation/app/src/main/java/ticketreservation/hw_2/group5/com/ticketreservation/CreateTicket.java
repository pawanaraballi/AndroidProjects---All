package ticketreservation.hw_2.group5.com.ticketreservation;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class CreateTicket extends Activity {

    final static String TICKET_KEY = "KEY";

    CharSequence[] cities = {"Albany, NY", "Houston, TX", "Portland, OR", "Atlanta, GA", "Boston, MA",
            "Las Vegas, NV", "Los Angeles, CA", "Raleigh, NC", "San Jose, CA",
            "Charlotte, NC", "Chicago, IL", "Greenville, SC", "Miami, FL", "Myrtle Beach, SC",
            "New York, NY", "Washington, DC"};
    private Calendar calendar;
    private int departYear, departMonth, departDate, returnYear, returnMonth, returnDate;
    private int departHour, departMinute, returnHour, returnMinute;
    static final int DEPART_DIALOG_ID = 0;
    static final int RETURN_DIALOG_ID = 1;
    static final int DEPART_TIME_ID = 2;
    static final int RETURN_TIME_ID = 3;

    EditText editTextDepartureDate;
    EditText editTextDepartureTime;
    EditText editTextReturningDate;
    EditText editTextReturningTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ticket);


        //set date picker to current date
        calendar = Calendar.getInstance();
        departYear = calendar.get(Calendar.YEAR);
        departMonth = calendar.get(Calendar.MONTH);
        departDate = calendar.get(Calendar.DAY_OF_MONTH);
        returnYear = calendar.get(Calendar.YEAR);
        returnMonth = calendar.get(Calendar.MONTH);
        returnDate = calendar.get(Calendar.DAY_OF_MONTH);


        editTextDepartureDate = (EditText) findViewById(R.id.editText_createticketdeparturedate);
        editTextReturningDate = (EditText) findViewById(R.id.editText_createticketreturningdate);
        editTextDepartureTime = (EditText) findViewById(R.id.editText_createticketdeparturetime);
        editTextReturningTime = (EditText) findViewById(R.id.editText_createticketreturningtime);
        final EditText editTextName = (EditText) findViewById(R.id.editText_createticketname);
        Button buttonPrintSummary = (Button) findViewById(R.id.button_printsummary);
        final EditText editTextSource = (EditText) findViewById(R.id.editText_edittextsource);
        final EditText editTextDestination = (EditText) findViewById(R.id.editText_createticketdestination);


        final RadioButton radioButtonOneWay = (RadioButton) findViewById(R.id.radioButton_createticketoneway);
        RadioButton radioButtonRoundTrip = (RadioButton) findViewById(R.id.radioButton_createticketroundtrip);


        //date picker
        editTextDepartureDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DEPART_DIALOG_ID);
            }
        });


        editTextReturningDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(RETURN_DIALOG_ID);
            }
        });

        //time picker
        editTextDepartureTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DEPART_TIME_ID);
            }
        });
        editTextReturningTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(RETURN_TIME_ID);
            }
        });


        radioButtonRoundTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextReturningDate.setVisibility(View.VISIBLE);
                editTextReturningTime.setVisibility(View.VISIBLE);
            }
        });

        radioButtonOneWay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextReturningDate.setVisibility(View.INVISIBLE);
                editTextReturningTime.setVisibility(View.INVISIBLE);
            }
        });

        editTextSource.setKeyListener(null);
        editTextDestination.setKeyListener(null);
        editTextDepartureDate.setKeyListener(null);
        editTextDepartureTime.setKeyListener(null);
        editTextReturningDate.setKeyListener(null);
        editTextReturningTime.setKeyListener(null);


        final AlertDialog.Builder alertSource = new AlertDialog.Builder(this);

        alertSource.setTitle("Source")
                .setItems(cities, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editTextSource.setText(cities[which] + "");
                        if (editTextSource.getText().toString().equalsIgnoreCase((editTextDestination.getText()).toString())) {
                            editTextSource.setError("Source and Destination CAN NOT be same");
                            //Toast.makeText(CreateTicket.this.getApplicationContext(),"Source and Destination CAN NOT be same", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setCancelable(false);
        editTextSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertSource.show();
            }
        });


        final AlertDialog.Builder alertDestination = new AlertDialog.Builder(this);
        alertDestination.setTitle("Destination")
                .setItems(cities, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                editTextDestination.setText(cities[which] + "");
                                if (editTextSource.getText().toString().equalsIgnoreCase((editTextDestination.getText()).toString())) {
                                    editTextDestination.setError("Source and Destination CAN NOT be same");
                                    //Toast.makeText(CreateTicket.this.getApplicationContext(),"Source and Destination CAN NOT be same", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }

                )
                .setCancelable(false);


        editTextDestination.setOnClickListener(new View.OnClickListener() {
                                                   @Override
                                                   public void onClick (View v){
                                                       alertDestination.show();
                                                   }
                                               }

        );


        final Ticket ticketObject = new Ticket();

        //Button Click action

        buttonPrintSummary.setOnClickListener(new View.OnClickListener()

                                              {
                                                  @Override
                                                  public void onClick (View v){

                                                      if (editTextName.getText().toString().length() > 20) {
                                                          Toast.makeText(CreateTicket.this.getApplicationContext(),
                                                                  "Name should be less than 20 characters", Toast.LENGTH_SHORT).show();
                                                      }


                                                      ticketObject.name = editTextName.getText().toString();
                                                      ticketObject.source = editTextSource.getText().toString();
                                                      if (radioButtonOneWay.isChecked()) {
                                                          ticketObject.triptype = "OneWay";
                                                      } else {
                                                          ticketObject.triptype = "RoundTrip";
                                                      }
                                                      ticketObject.destination = editTextDestination.getText().toString();
                                                      ticketObject.destinationdate = editTextDepartureDate.getText().toString();
                                                      ticketObject.destinationtime = editTextDepartureTime.getText().toString();
                                                      ticketObject.returndate = editTextReturningDate.getText().toString();
                                                      ticketObject.returntime = editTextReturningTime.getText().toString();


                                                      LinkedListClass.ticketLinkedList.add(ticketObject);

                                                      Log.d("demo", LinkedListClass.ticketLinkedList.toString());
                                                      Log.d("demo", LinkedListClass.ticketLinkedList.getLast().name);

                                                      Intent inte = new Intent(CreateTicket.this, PrintTicket.class);
                                                      inte.putExtra(TICKET_KEY, ticketObject);
                                                      startActivity(inte);

                                                  }
                                              }

        );
    }
    @Override
    protected Dialog onCreateDialog ( int id){
        //return calendar picker
        if (id == DEPART_DIALOG_ID) {
            return new DatePickerDialog(this, departDatePicker, departYear, departMonth, departDate);
        }
        //return calendar picker
        else if (id == RETURN_DIALOG_ID) {
            return new DatePickerDialog(this, returnDatePicker, returnYear, returnMonth, returnDate);
        }
        //return time picker
        else if (id == DEPART_TIME_ID) {
            return new TimePickerDialog(this, departTimePicker, departHour, departMinute, false);
        }
        //return time picker
        else if (id == RETURN_TIME_ID) {
            return new TimePickerDialog(this, returnTimePicker, returnHour, returnMinute, false);
        } else {
            return null;
        }
    }

    private TimePickerDialog.OnTimeSetListener departTimePicker =
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    departHour = hourOfDay;
                    departMinute = minute;
                    String AM_PM;
                    if (hourOfDay < 12) {
                        AM_PM = "AM";
                    } else {
                        AM_PM = "PM";
                    }
                    editTextDepartureTime.setText(departHour + ":" + departMinute + AM_PM);
                }
            };
    private TimePickerDialog.OnTimeSetListener returnTimePicker =
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    returnHour = hourOfDay;
                    returnMinute = minute;
                    String AM_PM;
                    if (hourOfDay < 12) {
                        AM_PM = "AM";
                    } else {
                        AM_PM = "PM";
                    }
                    editTextReturningTime.setText(returnHour + ":" + returnMinute + AM_PM);
                    if (editTextDepartureDate.getText().toString().equalsIgnoreCase(editTextReturningDate.getText().toString()) &&
                            editTextDepartureTime.getText().toString().equalsIgnoreCase(editTextReturningTime.getText().toString())) {
                        editTextReturningTime.setError("Departure Date/Time and Return Date/Time cannot be equal");
                    }
                }
            };


    private DatePickerDialog.OnDateSetListener departDatePicker
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            departYear = year;
            departMonth = monthOfYear + 1;
            departDate = dayOfMonth;
            editTextDepartureDate.setText(departMonth + "/" + departDate + "/" + departYear);
        }
    };

    private DatePickerDialog.OnDateSetListener returnDatePicker
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            returnYear = year;
            returnMonth = monthOfYear + 1;
            returnDate = dayOfMonth;
            editTextReturningDate.setText(returnMonth + "/" + returnDate + "/" + returnYear);
        }
    };
}