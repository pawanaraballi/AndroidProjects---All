package group5.com.finalsmad;

import com.firebase.client.Firebase;

/**
 * Created by Pawan on 4/13/2016.
 */
public class Application extends android.app.Application {
    static Firebase ref;
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        ref = new Firebase("https://finalsmadexams.firebaseio.com");
    }
}
