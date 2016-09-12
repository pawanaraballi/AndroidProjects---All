package mad.pawan.inclass.pizzastore;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    CharSequence[] toppings = {"Bacon","Cheese","Garlic","Green Pepper","Mushroom","Olives","Onions","Red pepper","Tomato"};
    ArrayList<String> selectedtoppings = new ArrayList<>();
    int realwhich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ids
        Button addtoppings = (Button) findViewById(R.id.button_addtoppings);
        Button checkout = (Button) findViewById(R.id.button_checkout);
        Button clearall = (Button) findViewById(R.id.button_clearpizza);

        //Alert Dialog

        final AlertDialog.Builder ad = new AlertDialog.Builder(this);

        ad.setTitle("Select the toppings")
                .setSingleChoiceItems(toppings, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("Demo", "Selected :" + toppings[which]);
                        selectedtoppings.add((String) toppings[which]);
                        realwhich=which;

                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TableRow tr = (TableRow) findViewById(R.id.tablerow1);
                        tr.setVisibility(View.VISIBLE);
                        ImageView imgv = new ImageView(MainActivity.this);
                        Log.d("Demo", "" + realwhich);
                        switch (realwhich) {
                            case 0:

                                imgv.setImageResource(R.drawable.bacon);
                                TableRow.LayoutParams lp = new TableRow.LayoutParams(150, 150);
                                imgv.setLayoutParams(lp);
                                tr.addView(imgv);
                                break;


                            case 1:
                                imgv.setImageResource(R.drawable.cheese);
                                imgv.setLayoutParams(new TableRow.LayoutParams(150, 150));
                                tr.addView(imgv);
                                break;

                            case 2:

                                imgv.setImageResource(R.drawable.garlic);
                                imgv.setLayoutParams(new TableRow.LayoutParams(150, 150));

                                tr.addView(imgv);
                                break;

                            case 3:

                                imgv.setImageResource(R.drawable.green_pepper);
                                imgv.setLayoutParams(new TableRow.LayoutParams(150, 150));

                                tr.addView(imgv);
                                break;
                            case 4:

                                imgv.setImageResource(R.drawable.mushroom);
                                imgv.setLayoutParams(new TableRow.LayoutParams(150, 150));

                                tr.addView(imgv);
                                break;
                            case 5:

                                imgv.setImageResource(R.drawable.olives);
                                imgv.setLayoutParams(new TableRow.LayoutParams(150, 150));

                                tr.addView(imgv);
                                break;
                            case 6:

                                imgv.setImageResource(R.drawable.onion);
                                imgv.setLayoutParams(new TableRow.LayoutParams(150, 150));

                                tr.addView(imgv);
                                break;
                            case 7:

                                imgv.setImageResource(R.drawable.red_pepper);
                                imgv.setLayoutParams(new TableRow.LayoutParams(150, 150));

                                tr.addView(imgv);
                                break;
                            default:
                                imgv.setImageResource(R.drawable.green_pepper);
                                imgv.setLayoutParams(new TableRow.LayoutParams(50, 50));

                                tr.addView(imgv);
                                break;

                        }
                    }
                })
                .setCancelable(true);

        addtoppings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad.show();
            }
        });

        clearall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedtoppings.clear();
                TableRow tr = (TableRow) findViewById(R.id.tablerow1);
                tr.removeAllViews();
            }
        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent int = new Intent(MainActivity.this, com.trpham.pizzastore.OrderActivity.this);
            }
        });
    }
}
