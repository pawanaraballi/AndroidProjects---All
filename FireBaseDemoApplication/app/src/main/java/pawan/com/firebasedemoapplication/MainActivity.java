package pawan.com.firebasedemoapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.MutableData;
import com.firebase.client.Query;
import com.firebase.client.Transaction;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> primary, secondary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);

      /*  Firebase ref = new Firebase("https://pawanaraballi.firebaseio.com");
        ref.addAuthStateListener(new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                if (authData != null) {
                    // user is logged in
                } else {
                    // user is not logged in
                }
            }
        });*/


        final Firebase ref = new Firebase("https://pawanaraballi.firebaseio.com");
        ref.authWithPassword("bobtoy@firebase.com", "correcthorsebatterystaple",
                new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        // Authentication just completed successfully :)
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("provider", authData.getProvider());
                        if(authData.getProviderData().containsKey("displayName")) {
                            map.put("displayName", authData.getProviderData().get("displayName").toString());
                        }
                        ref.child("users").child(authData.getUid()).setValue(map);
                        System.out.println("Success");
                    }
                    @Override
                    public void onAuthenticationError(FirebaseError error) {
                        System.out.println("Error");
                        switch (error.getCode()) {
                            case FirebaseError.USER_DOES_NOT_EXIST:
                                // handle a non existing user
                                System.out.println("USER_DOES_NOT_EXIST");
                                break;
                            case FirebaseError.INVALID_PASSWORD:
                                // handle an invalid password
                                System.out.println("INVALID_PASSWORD");
                                break;
                            default:
                                // handle other errors
                                System.out.println("Unknown error");
                                break;
                        }
                    }
                });


        //This is retrieving data part of the tutorial
        /*/
/*************************************************************************************************************

        Firebase ref = new Firebase("https://pawanaraballi.firebaseio.com/android/saving-data/fireblog/posts");

        // Attach an listener to read the data at our posts reference

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("There are " + dataSnapshot.getChildrenCount() + " blog posts");
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    BlogPost post = postSnapshot.getValue(BlogPost.class);
                    System.out.println(post.getAuthor() + " - " + post.getTitle());
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed " + firebaseError.getMessage());
            }
        });

        Firebase dinasaursRef = ref.child("dinasaurs");

        Map<String, String> alanisawesomeMap = new HashMap<String, String>();
        alanisawesomeMap.put("height", "2.1");
        alanisawesomeMap.put("length", "12.5.");

        Map<String, Map<String, String>> users = new HashMap<String, Map<String, String>>();
        users.put("lambeosaurus", alanisawesomeMap);

        Map<String, String> GracehopMap = new HashMap<String, String>();
        GracehopMap.put("height", "2");
        GracehopMap.put("length", "9");

        users.put("stegosaurus", GracehopMap);

        dinasaursRef.setValue(users, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                if (firebaseError != null) {
                    System.out.println("Data could not be saved. " + firebaseError.getMessage());
                } else {
                    System.out.println("Data saved successfully.");
                }
            }
        });


        Query queryRef = dinasaursRef.orderByChild("height");

        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                DinasaurFacts facts = snapshot.getValue(DinasaurFacts.class);
                System.out.println(snapshot.getKey() + " was " + facts.getHeight() + " meters tall");
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        //(Pawan) removing the listner

        //ref.removeEventListener(originalListener);


        // (Pawan) Always child runs first
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                BlogPost newPost = dataSnapshot.getValue(BlogPost.class);
                System.out.println("Author " + newPost.getAuthor());
                System.out.println("Title: " + newPost.getTitle());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                String title = (String) dataSnapshot.child("title").getValue();
                System.out.println("The updated post title is " + title);
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
        });
//******************************************************************************************************************
*/


// ********************************************************************************************************************
// This is saving data part and even the creating user section.

        /*        //Firebase myFirebaseRef  = new Firebase("https://pawanaraballi.firebaseio.com/web/data");
        Firebase ref = new Firebase("https://pawanaraballi.firebaseio.com/android/saving-data/fireblog");

*//*        User alan = new User("Alan Turing", 1912);

        alanRef.setValue(alan);

        //Referencing the child node using a .child() on it's parent node
        alanRef.child("fullName").setValue("Alan Turing");
        alanRef.child("birthYear").setValue(1912);*//*

        // next step

        Firebase usersRef = ref.child("users");

        Map<String, String> alanisawesomeMap = new HashMap<String, String>();
        alanisawesomeMap.put("birthYear", "1912");
        alanisawesomeMap.put("fullName", "Alan Turing.");

        Map<String, Map<String, String>> users = new HashMap<String, Map<String, String>>();
        users.put("alanisawesome", alanisawesomeMap);

        Map<String, String> GracehopMap = new HashMap<String, String>();
        GracehopMap.put("birthYear", "1912");
        GracehopMap.put("fullName", "Grace");

        users.put("gracehop", GracehopMap);

        usersRef.setValue(users, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                if (firebaseError != null) {
                    System.out.println("Data could not be saved. " + firebaseError.getMessage());
                } else {
                    System.out.println("Data saved successfully.");
                }
            }
        });

        Firebase postRef = ref.child("posts");

        // Generate a reference to a new location and add some data using push()
        Firebase newPostRef = postRef.push();


        Map<String, String> post1 = new HashMap<String, String>();
        post1.put("author", "gracehop");
        post1.put("title", "Announcing COBOL, a New Programming Language");
        postRef.push().setValue(post1);

        // Get the unique ID generated by push()
        String postId = newPostRef.getKey();


        Map<String, String> post2 = new HashMap<String, String>();
        post2.put("author", "alanisawesome");
        post2.put("title", "The Turing Machine");
        postRef.push().setValue(post2);



        //usersRef.setValue(users);

        //Firebase alanRef = usersRef.child("alanisawesome");
*//*        Map<String, Object> nickname = new HashMap<String, Object>();
        nickname.put("nickname", "Alan The Machine");
        alanRef.updateChildren(nickname);*//*

        Map<String, Object> nicknames = new HashMap<String, Object>();
        nicknames.put("alanisawesome/nickname", "Alan The Machine");
        nicknames.put("gracehop/nickname", "Amazing Grace");
        usersRef.updateChildren(nicknames);


        // want to increment the number of upvotes

        Firebase upvotesRef = new Firebase("https://pawanaraballi.firebaseio.com/android/saving-data/fireblog/posts/"+postId+"/upvotes");

        upvotesRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData currentData) {
                if(currentData.getValue() == null) {
                    currentData.setValue(1);
                } else {
                    currentData.setValue((Long) currentData.getValue() + 1);
                }

                return Transaction.success(currentData); //we can also abort by calling Transaction.abort()
            }

            @Override
            public void onComplete(FirebaseError firebaseError, boolean committed, DataSnapshot currentData) {
                //This method will be called once with the results of the transaction.
            }
        });




*//*        Firebase rootRef = new Firebase("https://pawanaraballi.firebaseio.com/web/data");
        rootRef.child("users/mchen/name");
        myFirebaseRef.child("messages").setValue("Do you have data? You'll love Firebase.");

        myFirebaseRef.child("message").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println(snapshot.getValue());  //prints "Do you have data? You'll love Firebase."
            }
            @Override public void onCancelled(FirebaseError error) { }
        });

        myFirebaseRef.createUser("bobtony@firebase.com", "correcthorsebatterystaple", new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                System.out.println("Successfully created user account with uid: " + result.get("uid"));
            }
            @Override
            public void onError(FirebaseError firebaseError) {
                // there was an error
            }
        });*//*
        *///**************************************************************************************************************
    }
}
