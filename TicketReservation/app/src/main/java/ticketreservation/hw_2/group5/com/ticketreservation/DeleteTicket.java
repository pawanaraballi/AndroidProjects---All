package ticketreservation.hw_2.group5.com.ticketreservation;

        import android.app.AlertDialog;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.RadioButton;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.LinkedList;
        import java.util.List;
        import java.util.ListIterator;

public class DeleteTicket extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_ticket);

        final Ticket ticketObject = new Ticket();

        final int[] SelectedIndex = new int[1];


        final EditText editTextName = (EditText) findViewById(R.id.editText_editticketname);
        final EditText editTextSource = (EditText) findViewById(R.id.editText_edittextsource);
        final EditText editTextDestination = (EditText) findViewById(R.id.editText_editticketdestination);
        final EditText editTextDepartureDate = (EditText) findViewById(R.id.editText_editticketdeparturedate);
        final EditText editTextReturningDate = (EditText) findViewById(R.id.editText_editticketreturningdate);
        final EditText editTextDepartureTime = (EditText) findViewById(R.id.editText_editticketdeparturetime);
        final EditText editTextReturningTime = (EditText) findViewById(R.id.editText_editticketreturningtime);

        final RadioButton radioButtonOneWay = (RadioButton) findViewById(R.id.radioButton_editticketoneway);
        final RadioButton radioButtonRoundTrip = (RadioButton) findViewById(R.id.radioButton_editticketroundtrip);

        radioButtonRoundTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextReturningDate.setVisibility(View.VISIBLE);
                editTextReturningTime.setVisibility(View.VISIBLE);
            }
        });


        List<String> listItems = new ArrayList<String>();


        ListIterator<Ticket> iteratorticket = LinkedListClass.ticketLinkedList.listIterator();

        for (int i = 0; i <LinkedListClass.ticketLinkedList.size() ; i++) {
            listItems.add(iteratorticket.next().name);
        }
        final CharSequence[] charSequenceItems = listItems.toArray(new CharSequence[listItems.size()]);
        final AlertDialog.Builder adb = new AlertDialog.Builder(this);

        if (LinkedListClass.ticketLinkedList.isEmpty()) {
            Toast.makeText(DeleteTicket.this, "There is no tickets which are already saved", Toast.LENGTH_LONG).show();
        } else {

            adb.setTitle("Select a Ticket")
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
                            if (ticObj.triptype.equalsIgnoreCase("OneWay")) {
                                radioButtonOneWay.setChecked(true);
                                editTextReturningDate.setVisibility(View.INVISIBLE);
                                editTextReturningTime.setVisibility(View.INVISIBLE);
                            } else {
                                radioButtonRoundTrip.setChecked(true);
                                editTextReturningDate.setVisibility(View.VISIBLE);
                                editTextReturningTime.setVisibility(View.VISIBLE);
                            }
                            editTextDestination.setText(ticObj.destination);
                            editTextDepartureDate.setText(ticObj.destinationdate);
                            editTextDepartureTime.setText(ticObj.destinationtime);
                            editTextReturningDate.setText(ticObj.returndate);
                            editTextReturningTime.setText(ticObj.returntime);
                            findViewById(R.id.selectbutton).setVisibility(View.INVISIBLE);
                        }
                    })
                    .setCancelable(false);
        }

        AlertDialog alert = adb.create();
        Button selectButton = (Button) findViewById(R.id.selectbutton);
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adb.show();
            }
        });
        Button deleteButton = (Button) findViewById(R.id.deletebutton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinkedListClass.ticketLinkedList.remove(SelectedIndex[0]);
                Intent inte = new Intent(DeleteTicket.this, MainActivity.class);

                startActivity(inte);

            }
        });
        Button cancelButton = (Button) findViewById(R.id.cancelbutton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextName.setText("");
                editTextSource.setText("");
                radioButtonOneWay.setChecked(true);
                editTextDestination.setText("");
                editTextDepartureDate.setText("");
                editTextDepartureTime.setText("");
                editTextReturningDate.setText("");
                editTextReturningTime.setText("");
                findViewById(R.id.selectbutton).setVisibility(View.VISIBLE);
            }
        });
    }
}
