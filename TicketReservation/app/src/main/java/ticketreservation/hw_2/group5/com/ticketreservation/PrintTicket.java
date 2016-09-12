package ticketreservation.hw_2.group5.com.ticketreservation;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PrintTicket extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_ticket);

        TextView textViewName = (TextView) findViewById(R.id.textView_itenaryname);
        TextView textViewSource = (TextView) findViewById(R.id.textView_itenarysource);
        TextView textViewDestination = (TextView) findViewById(R.id.textView_itenarydestination);
        TextView textViewDeparture = (TextView) findViewById(R.id.textView_itenarydeparture);
        TextView textViewReturn = (TextView) findViewById(R.id.textView_itenaryreturn);

        Button buttonFinish = (Button) findViewById(R.id.button_itenaryfinish);

        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(PrintTicket.this , MainActivity.class);
                startActivity(inte);

            }
        });



        if (getIntent().getExtras() != null){
            Ticket tic = getIntent().getExtras().getParcelable(CreateTicket.TICKET_KEY);
            textViewName.setText(tic.name);
            textViewSource.setText(tic.source);
            textViewDestination.setText(tic.destination);
            String textViewDep = tic.destinationdate + "," + tic.destinationtime;
            Log.d("demo",textViewDep);
            //Write a if condition for return date
            if(tic.triptype.equalsIgnoreCase("OneWay")){
                textViewDeparture.setText(textViewDep);
            }
            else {
                textViewDeparture.setText(textViewDep);
                textViewReturn.setText(tic.returndate + "," + tic.returntime);
            }
        }
    }
}
