package hw09.group5.com.stayintouchfragmentsapp;

import android.content.Context;
import android.net.Uri;
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
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ArchiveFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ArchiveFragment extends Fragment {

    ArrayList<User> usersArrayList = new ArrayList<>();
    ListView listView_users;
    ArchiveAdapter adapter;
    Firebase ref;
    String userID;
    User otherUser;
    View view;
    User currUser;
    ArrayList<String> otherUsers = new ArrayList<>();

    private OnFragmentInteractionListener mListener;

    public ArchiveFragment() {
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
            listView_users = (ListView) view.findViewById(R.id.listView_archive);
            Firebase.setAndroidContext(getActivity());
            ref = new Firebase("https://hw09-stayintouch-group05.firebaseio.com/");

            final Firebase conRef = new Firebase("https://hw09-stayintouch-group05.firebaseio.com/");
            conRef.child("conversations").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Conversation conversationObj = postSnapshot.getValue(Conversation.class);
                        if (userID.equals(conversationObj.getParticipant1())
                                && conversationObj.isArchieved_by_participant1() == true) {
                            otherUsers.add(conversationObj.getParticipant2());
                        } else if (userID.equals(conversationObj.getParticipant2())
                                && conversationObj.isArchieved_by_participant2() == true) {
                            otherUsers.add(conversationObj.getParticipant1());
                        }
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            ref.child("users/username").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        User iteratoruser = postSnapshot.getValue(User.class);
                        Log.d("otherUser from db", iteratoruser.getUserEmail());
                        if (otherUsers.contains(iteratoruser.getUserEmail())) {
                            currUser = iteratoruser;
                            usersArrayList.add(iteratoruser);
                        }
                    }

                    adapter = new ArchiveAdapter(getActivity(), R.layout.archive_rowitemlayout, usersArrayList);
                    Log.d("Adapter",adapter.toString());
                    listView_users.setAdapter(adapter);
                    adapter.setNotifyOnChange(true);

                    listView_users.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            otherUser = usersArrayList.get(position);
                            mListener.goToViewMessage(currUser, otherUser);
                        }
                    });

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

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
    }
}
