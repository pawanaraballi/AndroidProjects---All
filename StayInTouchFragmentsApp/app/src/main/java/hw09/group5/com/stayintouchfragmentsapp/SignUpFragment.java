package hw09.group5.com.stayintouchfragmentsapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.io.ByteArrayOutputStream;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SignUpFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class SignUpFragment extends Fragment {

    Button buttonsignup,buttoncancel;
    EditText editTextName,editTextEmail,editTextPassword,editTextConfirmPassword,editTextPhoneno;
    Firebase ref;
    private OnFragmentInteractionListener mListener;

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Firebase.setAndroidContext(getActivity());
        ref = new Firebase(MainActivity.FIREBASE_ROOT);

        editTextName = (EditText) getActivity().findViewById(R.id.editText_firstlastname);
        editTextEmail = (EditText) getActivity().findViewById(R.id.editText_email);
        editTextPhoneno = (EditText) getActivity().findViewById(R.id.editText_phone);
        editTextPassword = (EditText) getActivity().findViewById(R.id.editText_password);
        editTextConfirmPassword = (EditText) getActivity().findViewById(R.id.editText_confirmpassword);
        buttonsignup = (Button) getActivity().findViewById(R.id.button_signup);
        buttoncancel = (Button) getActivity().findViewById(R.id.button_cancel);

        buttonsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userName = editTextName.getText().toString();
                final String userEmail = editTextEmail.getText().toString();
                final String userPassword = editTextPassword.getText().toString();
                final String userPhoneNo = editTextPhoneno.getText().toString();
                final String userConfirmPassword = editTextConfirmPassword.getText().toString();

                if (userConfirmPassword.equals(userPassword)){
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_user_default);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
                    final byte[] image = stream.toByteArray();
                    System.out.println("byte array:" + image);
                    final String img_str = Base64.encodeToString(image, 0);

                    ref.createUser(userEmail, userPassword, new Firebase.ValueResultHandler<Map<String, Object>>() {
                        @Override
                        public void onSuccess(Map<String, Object> result) {
                            //create object and push it into the database
                            ref = ref.child("users/username");
                            Firebase userpush = ref.push();
                            User userObj = new User(userName, userEmail, userPassword,userPhoneNo,img_str,userpush.getKey(),true);
                            //Firebase uuu = new Firebase("https://hw09-stayintouch-group05.firebaseio.com").child("users/username").child(""+result.get("uid"));
                            userpush.setValue(userObj);
                            Toast.makeText(getActivity(), "Account was created.", Toast.LENGTH_SHORT).show();
                            getFragmentManager().popBackStack();
                        }

                        @Override
                        public void onError(FirebaseError firebaseError) {
                            // there was an error
                            switch (firebaseError.getCode()) {
                                case FirebaseError.EMAIL_TAKEN:
                                    Toast.makeText(getActivity(), "Account was not created. Please use other email address.", Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    // handle other errors
                                    break;
                            }
                        }
                    });
                }else{
                    editTextConfirmPassword.setError("Password Does not match");
                }
            }
        });

        buttoncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
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

    }
}
