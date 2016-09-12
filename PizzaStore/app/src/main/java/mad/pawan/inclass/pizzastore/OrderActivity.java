package com.trpham.pizzastore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mad.pawan.inclass.pizzastore.R;

public class OrderActivity extends AppCompatActivity
{
    //
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        //need delivery charge as a key, if the user check delivery
        //set
        //final static int DELIVERY_KEY = 1;

        ArrayList<String> test = new ArrayList<String>();
        test.add("Tomato");
        test.add("Bacon");
        test.add("Olives");
        test.add("Onions");
        test.add("Green Pepper");
        test.add("Tomato");
        test.add("Tomato");
        test.add("Cheese");

        int delivery = 1; //test

        TextView toppingDisplay = (TextView) findViewById(R.id.textView_toppingsDisplay);
        TextView toppingPrice = (TextView) findViewById(R.id.textView_toppingsPrice);
        TextView deliveryPrice = (TextView) findViewById(R.id.textView_deliveryPrice);
        TextView total = (TextView) findViewById(R.id.textView_totalCost);
        final Button finish = (Button) findViewById(R.id.button_finish);
        if (getIntent().getExtras() != null)
        {


            for (int i = 0; i < test.size(); i++)
            {
                toppingDisplay.setText(test.get(i) + ", ");
            }

            int numToppings = test.size();
            double toppingsCost = numToppings * 1.50;
            double totalCost = 0.0;

            if (delivery == 0)
            {
                deliveryPrice.setText("0.0$");
                totalCost = 6.5 + toppingsCost;
            }
            else
            {
                deliveryPrice.setText("4.0$");
                totalCost = 6.5 + toppingsCost + 4.0;
            }

            toppingPrice.setText(toppingsCost + "$");
            total.setText(totalCost + "$");
        }
        else
        {

        }

        finish.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
    }
}
