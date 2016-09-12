package inclass11.group05.com.expenseapp;

import com.firebase.client.Firebase;

/**
 * Created by Truong Pham on 4/11/2016.
 */
public class Application extends android.app.Application {
    static Firebase ref;
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        ref = new Firebase("https://radiant-inferno-8138.firebaseio.com");
    }
}
