package inclass11.group05.com.expenseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExpensesList extends AppCompatActivity {

    ListView listView;
    static String userEmail = "";
    final ArrayList<expense> expensesArrayList = new ArrayList<>();
    Firebase ref = new Firebase("https://radiant-inferno-8138.firebaseio.com/expenses");
    final static String CLICK_OBJ = "CLICK_OBJ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        Firebase.setAndroidContext(this);

        if (getIntent().getExtras() != null) {
            userEmail = getIntent().getExtras().getString("USER");
        }

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("There are " + dataSnapshot.getChildrenCount() + " expensee");
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    expense expens = postSnapshot.getValue(expense.class);
                    Log.d("useremail", userEmail.toString());
                    System.out.println(expens.getName() + " - " + expens.getAmount());
                    Log.d("useremail", expens.getUser());
                    if (expens.getUser().equalsIgnoreCase(userEmail)){
                        expensesArrayList.add(expens);
                    }
                    Log.d("size",expensesArrayList.size()+"");
                    if (expens != null){
                        Log.d("demo",expens.toString());
                    }
                    //System.out.println(expens.getName() + " - " + expens.getAmount());
                }



                listView = (ListView) findViewById(R.id.listView);
                Log.d("pawan",expensesArrayList.size()+"");

                ListViewAdapter adapter = new ListViewAdapter(ExpensesList.this,R.layout.rowitemlayout,expensesArrayList);

                listView.setAdapter(adapter);

                adapter.setNotifyOnChange(true);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent inte = new Intent(ExpensesList.this, ExpenseDetailActivity.class);

                        Bundle bundle = new Bundle();
                        bundle.putSerializable(CLICK_OBJ, expensesArrayList.get(position));
                        inte.putExtras(bundle);
                        startActivity(inte);
                    }
                });
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed " + firebaseError.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_settings:
                Intent inte = new Intent(ExpensesList.this, AddExpenseActivity.class);
                String userEmail = "";
                if (getIntent().getExtras() != null) {
                    userEmail = getIntent().getExtras().getString("USER");
                }
                inte.putExtra("USER", userEmail);
                startActivity(inte);
                break;
            // action with ID action_settings was selected
            case R.id.action_add:
                ref.unauth();
                Intent intent = new Intent(ExpensesList.this,LoginActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

        return true;
    }
}