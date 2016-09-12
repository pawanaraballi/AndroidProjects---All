package inclass11.group05.com.expenseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    Firebase ref;
    EditText email;
    EditText password;
    Button loginButton;
    Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.editText_email);
        password = (EditText) findViewById(R.id.editText_password);
        loginButton = (Button) findViewById(R.id.button_login);
        createAccountButton = (Button) findViewById(R.id.button_createNewAccount);
        Firebase.setAndroidContext(this);


        ref = Application.ref;

        ref.addAuthStateListener(new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                if (authData != null) {
                    // user is logged in
                    Intent intent = new Intent(LoginActivity.this, ExpensesList.class);
                    startActivity(intent);
                    finish();
                } else {
                    // user is not logged in

                }
            }
        });






        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });



        //START SIGN UP ACTIVITY
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);

                startActivity(intent);
                finish();
            }
        });
    }


    public void checkLogin() {

        final String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();



        ref.authWithPassword(userEmail, userPassword,
                new Firebase.AuthResultHandler() {
                    @Override
        public void onAuthenticated(AuthData authData) {
                        // Authentication just completed successfully :)
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("provider", authData.getProvider());
                        if (authData.getProviderData().containsKey("displayName")) {
                            map.put("displayName", authData.getProviderData().get("displayName").toString());
                        }
            ref.child("users").child(authData.getUid()).setValue(map);
                        System.out.println("Success");


            Toast.makeText(LoginActivity.this, "Login Successly.", Toast.LENGTH_SHORT).show();


                        Intent intent = new Intent(LoginActivity.this, ExpensesList.class);
                        intent.putExtra("USER", userEmail);
                        startActivity(intent);


                        //Success login
                    }

                    @Override
                    public void onAuthenticationError(FirebaseError error) {
                        System.out.println("Error");
                        switch (error.getCode()) {
                            case FirebaseError.USER_DOES_NOT_EXIST:
                                // handle a non existing user
                                Toast.makeText(LoginActivity.this, "UUSER DOES NOT EXIST", Toast.LENGTH_SHORT).show();
                                System.out.println("USER_DOES_NOT_EXIST");
                                break;
                            case FirebaseError.INVALID_PASSWORD:
                                // handle an invalid password
                                Toast.makeText(LoginActivity.this, "INVALID PASSWORD", Toast.LENGTH_SHORT).show();
                                System.out.println("INVALID_PASSWORD");
                                break;
                            default:
                                // handle other errors
                                System.out.println("Unknown error");
                                break;
                        }
                    }
                });
    }
}
