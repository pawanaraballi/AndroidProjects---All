package inclass11.group05.com.expenseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class ExpenseDetailActivity extends AppCompatActivity {

    Firebase ref;
    expense expenseObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_detail);


        final TextView nameData = (TextView) findViewById(R.id.textView_nameData);
        final TextView categoryData = (TextView) findViewById(R.id.textView_categoryData);
        final TextView amountData = (TextView) findViewById(R.id.textView_amountData);
        final TextView dateData = (TextView) findViewById(R.id.textView_dateData);



        Firebase.setAndroidContext(this);
        ref = new Firebase("https://radiant-inferno-8138.firebaseio.com/expenses");


        if (getIntent().getExtras() != null) {
            expenseObj = (expense) getIntent().getExtras().getSerializable(ExpensesList.CLICK_OBJ);

            Log.d("here", expenseObj.toString());

        }

        nameData.setText(expenseObj.getName());
        categoryData.setText(expenseObj.getCategory());
        amountData.setText(expenseObj.getAmount() + "");
        dateData.setText(expenseObj.getDate());

       /* ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("There are " + snapshot.getChildrenCount() + " blog posts");
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    expense expensedetail = postSnapshot.getValue(expense.class);

                    if (expensedetail.getUser().equals(expenseObj.getUser())) {
                        nameData.setText(expenseObj.getName());
                        categoryData.setText(expenseObj.getCategory());
                        amountData.setText(expenseObj.getAmount()+"");
                        dateData.setText(expenseObj.getDate());

                    }
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_delete was selected
            case R.id.action_delete:
                //delete the expense from the database
                Firebase fredRef = new Firebase("https://radiant-inferno-8138.firebaseio.com/expenses/"+expenseObj.getPushid()+"/amount");
                fredRef.removeValue();
                Firebase fredRef1 = new Firebase("https://radiant-inferno-8138.firebaseio.com/expenses/"+expenseObj.getPushid()+"/user");
                fredRef1.removeValue();
                Firebase fredRef2 = new Firebase("https://radiant-inferno-8138.firebaseio.com/expenses/"+expenseObj.getPushid()+"/name");
                fredRef2.removeValue();
                Firebase fredRef3 = new Firebase("https://radiant-inferno-8138.firebaseio.com/expenses/"+expenseObj.getPushid()+"/category");
                fredRef3.removeValue();
                Firebase fredRef4 = new Firebase("https://radiant-inferno-8138.firebaseio.com/expenses/"+expenseObj.getPushid()+"/date");
                fredRef4.removeValue();



/*
                Firebase refTest = new Firebase("https://radiant-inferno-8138.firebaseio.com/expenses/");
                ref.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {
                        String title = (String) dataSnapshot.child("title").getValue();
                        System.out.println("The blog post titled " + title + " has been deleted");
                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });*/

                Intent intent = new Intent(ExpenseDetailActivity.this, ExpensesList.class);
                startActivity(intent);
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ExpenseDetailActivity.this, ExpensesList.class);
        startActivity(intent);

    }
}
