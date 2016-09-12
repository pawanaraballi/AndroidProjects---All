package inclass12.group5.com.inclass12;

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

public class MainActivity extends AppCompatActivity implements ExpenseListFragment.OnFragmentInteractionListener {

    ListView listView;
    static String userEmail = "";
     static final ArrayList<Expense> expensesArrayList = new ArrayList<>();
    //Firebase ref = new Firebase("https://radiant-inferno-8138.firebaseio.com/expenses");
    final static String CLICK_OBJ = "CLICK_OBJ";
    Firebase ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ref = Application.refexpense;
        Log.d("demo","OnCreate");

        setContentView(R.layout.activity_main);
        populateExpense();

    }

    @Override
    public void addExpense() {
    }

    @Override
    public void populateExpense() {
        Log.d("demo","I am here");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("There are " + dataSnapshot.getChildrenCount() + " expensee");
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Expense expens = postSnapshot.getValue(Expense.class);
                    if (expens.getUser().equalsIgnoreCase("test3@uncc.edu")){
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

                ListViewAdapter adapter = new ListViewAdapter(MainActivity.this,R.layout.rowitemlayout,expensesArrayList);

                listView.setAdapter(adapter);

                adapter.setNotifyOnChange(true);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        getFragmentManager().beginTransaction()
                                .add(R.id.container,new ExpenseListFragment(),"first")
                                .commit();
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

                break;
            // action with ID action_settings was selected
            case R.id.action_add:
                ref.unauth();
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount()>0){
            getFragmentManager().popBackStack();
        }else{
            super.onBackPressed();
        }
    }
}
