package ticketreservation.hw_2.group5.com.ticketreservation;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class EditTicket extends Activity {

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

    CharSequence[] cities = {"Albany, NY", "Houston, TX", "Portland, OR", "Atlanta, GA", "Boston, MA",
            "Las Vegas, NV", "Los Angeles, CA", "Raleigh, NC", "San Jose, CA",
            "Charlotte, NC", "Chicago, IL", "Greenville, SC", "Miami, FL", "Myrtle Beach, SC",
            "New York, NY", "Washington, DC"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ticket);

        //set date picker to current date
        calendar = Calendar.getInstance();
        departYear = calendar.get(Calendar.YEAR);
        departMonth = calendar.get(Calendar.MONTH);
        departDate = calendar.get(Calendar.DAY_OF_MONTH);
        returnYear = calendar.get(Calendar.YEAR);
        returnMonth = calendar.get(Calendar.MONTH);
        returnDate = calendar.get(Calendar.DAY_OF_MONTH);


        final Ticket ticketObject = new Ticket();


        final String TICKET_KEY = "KEY";

        final int[] SelectedIndex = new int[1];

        final EditText editTextName = (EditText) findViewById(R.id.editText_editticketname);
        final EditText editTextSource = (EditText) findViewById(R.id.editText_edittextsource);
        final EditText editTextDestination = (EditText) findViewById(R.id.editText_editticketdestination);
        editTextDepartureDate = (EditText) findViewById(R.id.editText_editticketdeparturedate);
        editTextReturningDate = (EditText) findViewById(R.id.editText_editticketreturningdate);
        editTextDepartureTime = (EditText) findViewById(R.id.editText_editticketdeparturetime);
        editTextReturningTime = (EditText) findViewById(R.id.editText_editticketreturningtime);

        final RadioButton radioButtonOneWay = (RadioButton) findViewById(R.id.radioButton_editticketoneway);
        final RadioButton radioButtonRoundTrip = (RadioButton) findViewById(R.id.radioButton_editticketroundtrip);

        Button buttonSelectTicket = (Button) findViewById(R.id.button_editticketselectticket);
        Button buttonSave = (Button) findViewById(R.id.button_editticketsave);

        final AlertDialog.Builder builderSelectTicket = new AlertDialog.Builder(this);


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

        List<String> listItems = new ArrayList<String>();


        ListIterator<Ticket> iteratorticket = LinkedListClass.ticketLinkedList.listIterator();
        Log.d("demo", LinkedListClass.ticketLinkedList.size() + "");

        if (LinkedListClass.ticketLinkedList.isEmpty()) {
            Toast.makeText(EditTicket.this, "There is no tickets which are already saved", Toast.LENGTH_LONG).show();
        } else
        {
            for (int i = 0; i <LinkedListClass.ticketLinkedList.size() ; i++) {
                listItems.add(iteratorticket.next().name);
            }
            final CharSequence[] charSequenceItems = listItems.toArray(new CharSequence[listItems.size()]);

            // Toast.makeText(EditTicket.this,"There is no tickets which are already saved",Toast.LENGTH_LONG);


            builderSelectTicket.setTitle("Select a Ticket")
                    .setItems(charSequenceItems, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.d("Demo", "Selected :" + charSequenceItems[which]);
                            Log.d("Demo", "Selected :" + LinkedListClass.ticketLinkedList.get(which));
                            Ticket ticObj = new Ticket();
                            ticObj = LinkedListClass.ticketLinkedList.get(which);
                            SelectedIndex[0] = which;
                            editTextName.setText(ticObj.name);
                            editTextSource.setText(ticObj.source);
                            if (ticObj.triptype.equalsIgnoreCase("OneWay")){
                                radioButtonOneWay.setChecked(true);
                                editTextReturningDate.setVisibility(View.INVISIBLE);
                                editTextReturningTime.setVisibility(View.INVISIBLE);
                            }else
                            {
                                radioButtonRoundTrip.setChecked(true);
                                editTextReturningDate.setVisibility(View.VISIBLE);
                                editTextReturningTime.setVisibility(View.VISIBLE);
                            }
                            editTextDestination.setText(ticObj.destination);
                            editTextDepartureDate.setText(ticObj.destinationdate);
                            editTextDepartureTime.setText(ticObj.destinationtime);
                            editTextReturningDate.setText(ticObj.returndate);
                            editTextReturningTime.setText(ticObj.returntime);

                        }
                    })
                    .setCancelable(false);
        }

        buttonSelectTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builderSelectTicket.show();


            }
        });

        //Fetch Source and Destination

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






        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LinkedListClass.ticketLinkedList.remove(SelectedIndex[0]);

                ticketObject.name = editTextName.getText().toString();
                ticketObject.source = editTextSource.getText().toString();
                if (radioButtonOneWay.isChecked()){
                    ticketObject.triptype = "OneWay";
                }else
                {
                    ticketObject.triptype = "RoundTrip";
                }
                ticketObject.destination = editTextDestination.getText().toString();
                ticketObject.destinationdate = editTextDepartureDate.getText().toString();
                ticketObject.destinationtime = editTextDepartureTime.getText().toString();
                if (editTextReturningDate.getText().toString()==null){
                    ticketObject.returndate = "novalue";
                }
                else {
                    ticketObject.returndate = editTextReturningDate.getText().toString();
                }

                if(editTextReturningTime.getText().toString() == null){
                    ticketObject.returndate = "novalue";
                }else
                {
                    ticketObject.returntime = editTextReturningTime.getText().toString();
                }

                LinkedListClass.ticketLinkedList.add(ticketObject);

                Log.d("demo", LinkedListClass.ticketLinkedList.toString());
                Log.d("demo", LinkedListClass.ticketLinkedList.getLast().name);

                Intent inte = new Intent(EditTicket.this, PrintTicket.class);
                inte.putExtra(TICKET_KEY, ticketObject);
                startActivity(inte);
            }
        });
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
                    editTextDepartureTime.setText(departHour + ":" + departMinute);
                }
            };
    private TimePickerDialog.OnTimeSetListener returnTimePicker =
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    returnHour = hourOfDay;
                    returnMinute = minute;
                    editTextReturningTime.setText(returnHour + ":" + returnMinute);
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
