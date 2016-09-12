package inclass11.group05.com.expenseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    Button signup, cancel;
    EditText fullName, email, password;
    Firebase ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        ref = Application.ref;

        signup = (Button) findViewById(R.id.button_signup);
        cancel = (Button) findViewById(R.id.button_cancel);

        fullName = (EditText) findViewById(R.id.editText_fullName);
        email = (EditText) findViewById(R.id.editText_email);
        password = (EditText) findViewById(R.id.editText_password);

        //if click cancel
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userName = fullName.getText().toString();
                final String userEmail = email.getText().toString();
                final String userPassword = password.getText().toString();


                ref.createUser(userEmail, userPassword, new Firebase.ValueResultHandler<Map<String, Object>>() {
                    @Override
                    public void onSuccess(Map<String, Object> result) {

                        //create object and push it into the database
                        User userObj = new User(userName, userEmail, userPassword);

                        ref = ref.child("username");
                        ref.push().setValue(userObj);

                        Toast.makeText(SignUpActivity.this, "Account was created.", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                        startActivity(intent);

                        System.out.println("Successfully created user account with uid: " + result.get("uid"));
                    }
                    @Override
                    public void onError(FirebaseError firebaseError) {
                        // there was an error
                        Log.d("test", firebaseError.getMessage());

                        switch (firebaseError.getCode()) {
                            case FirebaseError.EMAIL_TAKEN:
                                //
                                Toast.makeText(SignUpActivity.this, "Account was not created. Please use other email address.", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                // handle other errors
                                System.out.println("Can not create user");
                                break;
                        }
                    }
                });
            }
        });

    }
}
