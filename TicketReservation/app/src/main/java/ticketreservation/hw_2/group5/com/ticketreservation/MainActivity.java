package ticketreservation.hw_2.group5.com.ticketreservation;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.LinkedList;





public class MainActivity extends Activity {

    final static LinkedList<Ticket> ticketList = new LinkedList<Ticket>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonCreatteTicket = (Button) findViewById(R.id.bt_createTicket);
        Button buttonEditTicket = (Button) findViewById(R.id.bt_editTicket);
        Button buttonDeleteTicket = (Button) findViewById(R.id.bt_deleteTicket);
        Button buttonViewTicket = (Button) findViewById(R.id.bt_viewTicket);
        Button buttonFinish = (Button) findViewById(R.id.bt_finish);

        TextView textViewCustomerCare = (TextView) findViewById(R.id.textView_customercare);


        Intent intent = new Intent();


        textViewCustomerCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:9999999999"));
                startActivity(callIntent);
            }
        });

        buttonCreatteTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateTicket.class);
                startActivity(intent);
            }
        });

        buttonEditTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditTicket.class);
                startActivity(intent);
            }
        });

        buttonDeleteTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DeleteTicket.class);
                startActivity(intent);
            }
        });

        buttonViewTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewTicket.class);
                startActivity(intent);
            }
        });

        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}