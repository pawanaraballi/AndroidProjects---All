package inclass12.group5.com.inclass12;

import android.util.Log;

import com.firebase.client.Firebase;

/**
 * Created by Truong Pham on 4/11/2016.
 */
public class Application extends android.app.Application {
    static Firebase ref,refexpense;
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        ref = new Firebase("https://radiant-inferno-8138.firebaseio.com");
        refexpense = new Firebase("https://radiant-inferno-8138.firebaseio.com/expenses");
        Log.d("demo","Application Called");
    }
}
