package hw09.group5.com.stayintouchfragmentsapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.ByteArrayOutputStream;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class EditProfileFragment extends Fragment {

    ImageView imageViewdp;
    EditText editTextname,editTextemail,editTextphoneno,editTextpassword;
    Button buttonUpdate,buttonCancel;
    String userID;
    Firebase ref;
    static User curruser;
    static String strBase64;
    private static final int SELECT_PICTURE = 1;
    private String selectedImagePath;
    View view;

    private OnFragmentInteractionListener mListener;

    public EditProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // show the action bar for navigator
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        userID = getArguments().getString("email");

        editTextname = (EditText) view.findViewById(R.id.editText_DetailflName);
        editTextemail = (EditText) view.findViewById(R.id.editText_DetailEmail);
        editTextphoneno = (EditText) view.findViewById(R.id.editText_DetailPhoneNo);
        editTextpassword = (EditText) view.findViewById(R.id.editText_DetailPassword);
        imageViewdp = (ImageView) view.findViewById(R.id.imageView_Detaildp);

        buttonUpdate = (Button) view.findViewById(R.id.button_DetailUpdate);
        buttonCancel = (Button) view.findViewById(R.id.button_DetailCancel);

        Firebase.setAndroidContext(getActivity());
        ref = new Firebase(MainActivity.FIREBASE_ROOT);
        ref = ref.child("users/username");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("There are " + dataSnapshot.getChildrenCount() + " details");
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    User iteratoruser = postSnapshot.getValue(User.class);
                    Log.d("useremail", iteratoruser.getUserFullname());
                    if (iteratoruser.getUserEmail().equalsIgnoreCase(userID)) {
                        Log.d("User", iteratoruser.getUserFullname());
                        curruser = iteratoruser;
                        editTextname.setText(iteratoruser.getUserFullname());
                        editTextemail.setText(iteratoruser.getUserEmail());
                        editTextphoneno.setText(iteratoruser.getPhoneNo());
                        editTextpassword.setText(iteratoruser.getUserPassword());
                        strBase64 = iteratoruser.getProfilepic();
                    }
                }

                byte[] decodedString = Base64.decode(strBase64, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                imageViewdp.setImageBitmap(decodedByte);

                imageViewdp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
                    }
                });
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed " + firebaseError.getMessage());
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setPhoneNo(editTextphoneno.getText().toString());
                user.setUserEmail(editTextemail.getText().toString());
                user.setUserFullname(editTextname.getText().toString());
                user.setUserPassword(editTextpassword.getText().toString());
                user.setPushId(curruser.getPushId());

                ImageView iv1 = (ImageView) view.findViewById(R.id.imageView_Detaildp);
                BitmapDrawable drawable = (BitmapDrawable) iv1.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] image = stream.toByteArray();
                String image_str = Base64.encodeToString(image, 0);

                Log.d("truong", image_str);
                user.setProfilepic(image_str);
                Log.d("wow", curruser.getPushId());
                //update the database
                ref.child(curruser.getPushId()).setValue(user);
                Toast.makeText(getActivity(), "Saved Successfully!!!", Toast.LENGTH_LONG).show();
                mListener.goToContactFragment(userID);
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.goToContactFragment(userID);
            }
        });
        return view;
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


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == getActivity().RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
                System.out.println("Image Path : " + selectedImagePath);
                imageViewdp.setImageURI(selectedImageUri);
            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getActivity().managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
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
        //TODO: currently returned to contact fragment, need to be returned to conversation Fragment.
        void goToContactFragment(String string);
    }
}
