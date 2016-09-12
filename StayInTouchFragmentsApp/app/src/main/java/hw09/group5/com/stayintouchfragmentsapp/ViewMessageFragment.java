package hw09.group5.com.stayintouchfragmentsapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewMessageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ViewMessageFragment extends Fragment {

    Firebase ref;
    ListView listView_messages;
    EditText editText;
    ImageButton sendMessageButton;
    static User convouser, currentUser;
    ArrayList<Message> messageArrayList = new ArrayList<>();
    ArrayList<Conversation> conversationArrayList = new ArrayList<>();
    ViewMessageAdapter adapter;
    View view;
    int count = 0;
    Message message;

    private OnFragmentInteractionListener mListener;

    public ViewMessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        currentUser = (User) getArguments().getSerializable("participant1");
        convouser = (User) getArguments().getSerializable("participant2");

        // show the action bar for navigator
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        // Inflate the layout for this fragment
        if(view == null){
            view = inflater.inflate(R.layout.fragment_view_message, container, false);
            sendMessageButton = (ImageButton) view.findViewById(R.id.imageButton_SendMessage);
            editText = (EditText) view.findViewById(R.id.editText_SendMessage);
            listView_messages = (ListView) view.findViewById(R.id.listView_ViewMessage);
            Firebase.setAndroidContext(getActivity());
            ref = new Firebase(MainActivity.FIREBASE_ROOT);

            final Firebase userRef = ref.child("users/username/" + convouser.getPushId());
            Map<String, Object> taskMap = new HashMap<String, Object>();
            taskMap.put("read", "true");
            userRef.updateChildren(taskMap);

            final Firebase messageRef = ref.child("messages");
            messageRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Message message = postSnapshot.getValue(Message.class);


                        //below code is for adding the message to the view message list
                        if (message.getSenderName().equalsIgnoreCase(currentUser.getUserFullname())
                                && message.getReceiverName().equalsIgnoreCase(convouser.getUserFullname())
                                || message.getReceiverName().equalsIgnoreCase(currentUser.getUserFullname())
                                && message.getSenderName().equalsIgnoreCase(convouser.getUserFullname())) {
                            //make sure not duplicate, need to override equal method for message.
                            if (!(messageArrayList.contains(message))) {

                                messageArrayList.add(message);
                            }
                        }
                    }

                    //check if the first time talk by two participant
                    // if first time create conversation object and push to firebase
                    if (messageArrayList.size() == 1) {
                        //create conversation object because count = 1 means first time talk between the two
                        Firebase conversationRef = new Firebase("https://hw09-stayintouch-group05.firebaseio.com/");
                        conversationRef = conversationRef.child("conversations");

                        Firebase conversationRefPush = conversationRef.push();
                        Conversation conversationObject = new Conversation();

                        conversationObject.setConversationID(conversationRefPush.getKey());
                        conversationObject.setParticipant1(currentUser.getUserEmail());
                        conversationObject.setParticipant2(convouser.getUserEmail());
                        conversationObject.setIsArchieved_by_participant1(false);
                        conversationObject.setIsArchieved_by_participant2(false);
                        conversationObject.setDeletedBy("");

                        if (!conversationArrayList.contains(conversationObject)) {
                            conversationArrayList.add(conversationObject);
                            conversationRefPush.setValue(conversationObject);
                        }

                    }
                    adapter = new ViewMessageAdapter(getActivity(), R.layout.messageitemrowlayout, messageArrayList);
                    listView_messages.setAdapter(adapter);
                    adapter.setNotifyOnChange(true);
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

//
//            adapter = new ViewMessageAdapter(getActivity(), R.layout.messageitemrowlayout, messageArrayList);
//            listView_messages.setAdapter(adapter);
//            adapter.setNotifyOnChange(true);


            Query queryRef = messageRef.orderByChild("timeStamp");

            queryRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Message message = dataSnapshot.getValue(Message.class);
                    Log.d("messageadded", message.getMessageText());

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    Message message = dataSnapshot.getValue(Message.class);
                    Log.d("messageremoved", message.getMessageText());

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            sendMessageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Message enteredMessage = new Message();
                    //make sure the text is not exceed 140 characters
                    if (editText.getText().toString().length() > 140) {
                        editText.setError("MAXIMUM 140 characters allow!");
                    } else if (editText.getText().toString().length() < 1) {
                        Toast.makeText(getActivity(), "Please enter message to send", Toast.LENGTH_LONG).show();
                    } else {
                        Firebase newmessage = messageRef.push();
                        enteredMessage.setMessageText(editText.getText().toString());
                        enteredMessage.setReceiverName(convouser.getUserFullname());
                        enteredMessage.setSenderName(currentUser.getUserFullname());

                        Date date = new Date();
                        enteredMessage.setTimeStamp(String.valueOf(date.getTime()));
                        enteredMessage.setMessageRead(false);
                        enteredMessage.setPushId(newmessage.getKey());


                        newmessage.setValue(enteredMessage, new Firebase.CompletionListener() {
                            @Override
                            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                                Toast.makeText(getActivity(), "Message Sent", Toast.LENGTH_LONG).show();
                            }
                        });


                        editText.setText("");

                        adapter.notifyDataSetChanged();

                        //update firebase
//                        final Firebase userRef1 = ref.child("users/username/" + currentUser.getPushId());
//                        Map<String, Object> taskMap1 = new HashMap<String, Object>();
//                        taskMap1.put("read", "false");
//                        userRef1.updateChildren(taskMap1);


                        final Firebase userRef = ref.child("users/username/" + currentUser.getPushId());
                        Map<String, Object> taskMap = new HashMap<String, Object>();
                        taskMap.put("read", "false");
                        userRef.updateChildren(taskMap);

                    }//end else
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
        //TODO: for now it is coming back to view message fragment, needs to be redirected to conversation fragment
        void gobackToViewMessage(User currentUser, User otherUser);
    }
}
