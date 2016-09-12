package hw09.group5.com.stayintouchfragmentsapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class LoginFragment extends Fragment {

    Button createuser,login;
    EditText editTextemail,editTextpassword;
    Firebase ref;
    private OnFragmentInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // disable the action bar
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);

        Firebase.setAndroidContext(getActivity());
        ref = new Firebase(MainActivity.FIREBASE_ROOT);

        login = (Button) getActivity().findViewById(R.id.button_login);
        createuser = (Button) getActivity().findViewById(R.id.button_createnewuser);
        editTextemail = (EditText) getActivity().findViewById(R.id.editText_loginemail);
        editTextpassword = (EditText) getActivity().findViewById(R.id.editText_loginpassword);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pw = editTextpassword.getText().toString();
                final String email = editTextemail.getText().toString();
                ref.authWithPassword(email, pw, new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        mListener.returnEmail(email);
                        Toast.makeText(getActivity(), "Successfully Logged In", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        switch (firebaseError.getCode()) {
                            case FirebaseError.USER_DOES_NOT_EXIST:
                                // handle a non existing otherUser
                                Toast.makeText(getActivity(), "USER DOES NOT EXIST", Toast.LENGTH_SHORT).show();
                                System.out.println("USER_DOES_NOT_EXIST");
                                break;
                            case FirebaseError.INVALID_PASSWORD:
                                // handle an invalid password
                                Toast.makeText(getActivity(), "INVALID PASSWORD", Toast.LENGTH_SHORT).show();
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
        });

        createuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoSignUp();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void gotoSignUp();
        void returnEmail(String string);
    }
}
