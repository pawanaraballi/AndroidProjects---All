package hw09.group5.com.stayintouchfragmentsapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
 * {@link ConversationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ConversationFragment extends Fragment {

    ArrayList<User> usersArrayList = new ArrayList<>();
    ArrayList<Message> messageArrayList = new ArrayList<>();
    ListView listView_users;
    ConversationAdapter adapter;
    Firebase userRef, messageRef;
    String loginUserEmail;
    User user;
    View view;
    Message message;
    static User currUser, conUser;

    private OnFragmentInteractionListener mListener;

    public ConversationFragment() {
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
            loginUserEmail = getArguments().getString("email");
            Log.d("loginUserEmail", loginUserEmail);
            listView_users = (ListView) view.findViewById(R.id.listView);
            userRef = new Firebase("https://hw09-stayintouch-group05.firebaseio.com/");
            messageRef = new Firebase("https://hw09-stayintouch-group05.firebaseio.com/");

            userRef.child("users/username").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        User iteratoruser = postSnapshot.getValue(User.class);
                        Log.d("otherUser from db", iteratoruser.getUserEmail());
                        if ( !(iteratoruser.getUserEmail().equalsIgnoreCase(loginUserEmail)) ) {
                            //only add users the not login
                            usersArrayList.add(iteratoruser);
                        } else {
                            currUser = iteratoruser;
                        }
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });


            adapter = new ConversationAdapter(getActivity(), R.layout.conversation_rowitemlayout, usersArrayList);
            listView_users.setAdapter(adapter);
            adapter.notifyDataSetChanged();


            listView_users.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    conUser = usersArrayList.get(position);
                    mListener.conversationToViewFrag(currUser, conUser);
                    //set message read to true
                }
            });


            listView_users.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                    //get message to delete
                    //message = messageArrayList.get(position);
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    //Set a title
                    builder.setTitle("Confirm");
                    //Set message
                    builder.setMessage("Please choose action");
                    builder.setPositiveButton("Archived", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            final Firebase conRef = new Firebase("https://hw09-stayintouch-group05.firebaseio.com/");
                            conRef.child("conversations").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                        Conversation conversationObj = postSnapshot.getValue(Conversation.class);
                                        if (loginUserEmail.equals(conversationObj.getParticipant1()) &&
                                                usersArrayList.get(position).getUserEmail().equals(conversationObj.getParticipant2())) {
                                            final Firebase userRef = conRef.child(conversationObj.getConversationID());
                                            Map<String, Object> taskMap = new HashMap<String, Object>();
                                            taskMap.put("isArchieved_by_participant1", "true");
                                            userRef.updateChildren(taskMap);
                                        } else if (loginUserEmail.equals(conversationObj.getParticipant2()) &&
                                                usersArrayList.get(position).getUserEmail().equals(conversationObj.getParticipant1())) {
                                            final Firebase userRef = conRef.child(conversationObj.getConversationID());
                                            Map<String, Object> taskMap = new HashMap<String, Object>();
                                            taskMap.put("isArchieved_by_participant2", "true");
                                            userRef.updateChildren(taskMap);
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(FirebaseError firebaseError) {

                                }
                            });
                        }
                    });
                    builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Firebase deletemessage = messageRef.child(message.getPushId());
                            //TODO : need to add the delete participant
                            deletemessage.removeValue();
                            dialog.dismiss();
                            adapter.notifyDataSetChanged();

                            //back to ocnversation
                        }
                    });

                    //Create the dialog
                    AlertDialog alertdialog = builder.create();
                    alertdialog.show();
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
        void conversationToViewFrag(User loginUser, User conUser);

    }
}
