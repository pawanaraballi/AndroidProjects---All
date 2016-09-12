package hw09.group5.com.stayintouchfragmentsapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.Firebase;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener,
        SignUpFragment.OnFragmentInteractionListener, ContactFragment.OnFragmentInteractionListener,
        ViewContactFragment.OnFragmentInteractionListener, ViewMessageFragment.OnFragmentInteractionListener,
        EditProfileFragment.OnFragmentInteractionListener, ConversationFragment.OnFragmentInteractionListener{

    final static String FIREBASE_ROOT = "https://hw09-stayintouch-group05.firebaseio.com/";
    Firebase ref;
    static TextView contactName;
    static ImageView image;
    String returnedEmail;
    User participant1,participant2;
    private DrawerLayout mDrawer;
    ActionBar actionBar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView nvDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        ref = new Firebase(FIREBASE_ROOT);

        if (!(ref.getAuth() == null)){
            String email = (String) ref.getAuth().getProviderData().get("email");
            Bundle bundle = new Bundle();
            bundle.putString("email", email);
            //TODO: currently going to contact fragment but needs to be redirected to Conversation fragment.
            ContactFragment fragment = new ContactFragment();
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment , "contactList")
                    .addToBackStack(null)
                    .commit();
        }

        else {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new LoginFragment(), "login").commit();
        }

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,mDrawer,R.string.openDrawer,R.string.closeDrawer);
        mDrawer.setDrawerListener(actionBarDrawerToggle);
        actionBar = getSupportActionBar();
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);
        // TODO: Display current logged in otherUser and profile pic in the navigator

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void gotoSignUp() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new SignUpFragment(), "signup")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void returnEmail(String string) {
        returnedEmail = string;
        Bundle bundle = new Bundle();
        bundle.putString("email", returnedEmail);
        ConversationFragment fragment = new ConversationFragment();
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment , "contactList")
                .addToBackStack(null)
                .commit();

    }

    // Code for Navigator
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            if (mDrawer.isDrawerOpen(nvDrawer)){
                mDrawer.closeDrawer(nvDrawer);
            }else {
                mDrawer.openDrawer(nvDrawer);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {





        // Navigate to respective fragments based on nav item clicked
        switch(menuItem.getItemId()) {
            case R.id.nav_contact_fragment:
                String email = (String) ref.getAuth().getProviderData().get("email");
                Bundle bundle = new Bundle();
                bundle.putString("email", email);
                ContactFragment fragment = new ContactFragment();
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment , "conversationList")
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.nav_conversation_fragment:
                String emailConversation = (String) ref.getAuth().getProviderData().get("email");
                Bundle bundleConversation = new Bundle();
                bundleConversation.putString("email", emailConversation);
                ConversationFragment fragmentCon = new ConversationFragment();
                fragmentCon.setArguments(bundleConversation);
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentCon , "conversationList")
                        .addToBackStack(null)
                        .commit();
                 //TODO:
                break;
            case R.id.nav_archive_fragment:
                 // TODO:
                break;
            case R.id.nav_setting_fragment:
                String emailID = (String) ref.getAuth().getProviderData().get("email");
                Bundle newbundle = new Bundle();
                newbundle.putString("email", emailID);
                EditProfileFragment editContactFragment = new EditProfileFragment();
                editContactFragment.setArguments(newbundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.container, editContactFragment , "editUser")
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.nav_logout_fragment:
                Firebase firebase = new Firebase(MainActivity.FIREBASE_ROOT);
                firebase.unauth();
                mDrawer.closeDrawers();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new LoginFragment(), "login").commit();
                break;
            case R.id.nav_exit_fragment:
                finish();
                break;
            default:
                break;
        }
        menuItem.setChecked(true);
        mDrawer.closeDrawers();
    }


    @Override
    public void goToViewMessage(User currentUser, User otherUser) {
        participant1 = currentUser;
        participant2 = otherUser;
        Bundle bundle = new Bundle();
        bundle.putSerializable("participant1", participant1);
        bundle.putSerializable("participant2", participant2);
        ViewMessageFragment fragment = new ViewMessageFragment();
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, "messageList")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToViewContact(User otherUser) {
        participant2 = otherUser;
        Bundle bundle = new Bundle();
        bundle.putSerializable("participant2", participant2);
        ViewContactFragment fragment = new ViewContactFragment();
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, "contact")
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() > 0){
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void gobackToViewMessage(User currentUser, User otherUser) {
        participant1 = currentUser;
        participant2 = otherUser;
        Bundle bundle = new Bundle();
        bundle.putSerializable("participant1", participant1);
        bundle.putSerializable("participant2", participant2);
        ViewMessageFragment fragment = new ViewMessageFragment();
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, "messageList")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToContactFragment(String string) {
        returnedEmail = string;
        Bundle bundle = new Bundle();
        bundle.putString("email", returnedEmail);
        ContactFragment fragment = new ContactFragment();
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment , "contactList")
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void conversationToViewFrag(User loginUser, User conUser) {
        participant1 = loginUser;
        participant2 = conUser;
        Bundle bundle = new Bundle();
        bundle.putSerializable("participant1", participant1);
        bundle.putSerializable("participant2", participant2);
        ViewMessageFragment fragment = new ViewMessageFragment();
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, "messageList")
                .addToBackStack(null)
                .commit();
    }
}
