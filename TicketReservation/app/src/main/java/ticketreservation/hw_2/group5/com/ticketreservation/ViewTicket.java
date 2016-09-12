package ticketreservation.hw_2.group5.com.ticketreservation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import ticketreservation.hw_2.group5.com.ticketreservation.R;
import ticketreservation.hw_2.group5.com.ticketreservation.Ticket;

public class ViewTicket extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ticket);

        final EditText editTextName = (EditText) findViewById(R.id.editText_editticketname);
        final EditText editTextSource = (EditText) findViewById(R.id.editText_edittextsource);
        final EditText editTextDestination = (EditText) findViewById(R.id.editText_editticketdestination);
        final EditText editTextDepartureDate = (EditText) findViewById(R.id.editText_editticketdeparturedate);
        final EditText editTextReturningDate = (EditText) findViewById(R.id.editText_editticketreturningdate);
        final EditText editTextDepartureTime = (EditText) findViewById(R.id.editText_editticketdeparturetime);
        final EditText editTextReturningTime = (EditText) findViewById(R.id.editText_editticketreturningtime);
        final RadioButton radioButtonOneWay = (RadioButton) findViewById(R.id.radioButton_editticketoneway);
        final RadioButton radioButtonRoundTrip = (RadioButton) findViewById(R.id.radioButton_editticketroundtrip);

        ImageButton rewindButton = (ImageButton) findViewById(R.id.leftimageButton);
        ImageButton forwardButton = (ImageButton) findViewById(R.id.rightimageButton);
        ImageButton fastRewindButton = (ImageButton) findViewById(R.id.fastforwardimageButton);
        ImageButton fastForwardButton = (ImageButton) findViewById(R.id.fastrewindimageButton);
        Button finishButton = (Button) findViewById(R.id.finishbutton);

        editTextSource.setKeyListener(null);
        editTextDestination.setKeyListener(null);
        editTextDepartureDate.setKeyListener(null);
        editTextDepartureTime.setKeyListener(null);
        editTextReturningDate.setKeyListener(null);
        editTextReturningTime.setKeyListener(null);

        fastRewindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // set values to below fields in order to display the first record of the linkedList.
                Ticket ticObj = new Ticket();
                ticObj = LinkedListClass.ticketLinkedList.getFirst();
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
        });

        fastForwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ticket ticObj = new Ticket();
                ticObj = LinkedListClass.ticketLinkedList.getLast();
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
        });

        final ListIterator listIterator = LinkedListClass.ticketLinkedList.listIterator();

        rewindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ticket ticObj = new Ticket();
                //iterate and get the previous value of current record.
                int prevIndex = listIterator.previousIndex();
                ticObj = LinkedListClass.ticketLinkedList.get(prevIndex);
                // set values to below fields in order to display the previous record of the linkedList.
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
        });

        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ticket ticObj = new Ticket();
                //iterate and get the previous value of current record.
                int nextIndex = listIterator.nextIndex();
                ticObj = LinkedListClass.ticketLinkedList.get(nextIndex);
                // set values to below fields in order to display the previous record of the linkedList.
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
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
