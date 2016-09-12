package hw09.group5.com.stayintouchfragmentsapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContactFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ContactFragment extends Fragment {

    ArrayList<User> usersArrayList = new ArrayList<>();
    ListView listView_users;
    ContactAdapter adapter;
    Firebase ref;
    String userID;
    User otherUser;
    View view;
    User currUser;

    private OnFragmentInteractionListener mListener;

    public ContactFragment() {
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
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_conversation, container, false);
            userID = getArguments().getString("email");
            Log.d("loginUserEmail", userID);
            listView_users = (ListView) view.findViewById(R.id.listView);
            Firebase.setAndroidContext(getActivity());
            ref = new Firebase(MainActivity.FIREBASE_ROOT);
            ref.child("users/username").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        User iteratoruser = postSnapshot.getValue(User.class);
                        Log.d("otherUser from db", iteratoruser.getUserEmail());
                        if (iteratoruser.getUserEmail().equalsIgnoreCase(userID)) {
                            currUser = iteratoruser;
                        } else {
                            if (!(usersArrayList.contains(iteratoruser))) {
                                usersArrayList.add(iteratoruser);
                            }
                        }
                    }

                    adapter = new ContactAdapter(getActivity(), R.layout.contact_rowitemlayout, usersArrayList);
                    listView_users.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });



            listView_users.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    otherUser = usersArrayList.get(position);
                    mListener.goToViewMessage(currUser, otherUser);
                }
            });

            listView_users.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    otherUser = usersArrayList.get(position);
                    mListener.goToViewContact(otherUser);
                    return true;
                }
            });

        } else {
            container.removeView(view);
        }
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
        void goToViewMessage(User currentUser, User otherUser);
        void goToViewContact(User otherUser);
    }
}
