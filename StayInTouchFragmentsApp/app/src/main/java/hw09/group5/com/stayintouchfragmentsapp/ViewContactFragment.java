package hw09.group5.com.stayintouchfragmentsapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewContactFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ViewContactFragment extends Fragment {

    TextView textViewFullname,textViewname,textViewphoneno,textViewemail;
    ImageView imageView;
    static User otherUser;
    View view;

    private OnFragmentInteractionListener mListener;

    public ViewContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        otherUser = (User) getArguments().getSerializable("participant2");
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_view_contact, container, false);
        textViewFullname = (TextView) view.findViewById(R.id.textView_ViewContactName);
        textViewemail = (TextView) view.findViewById(R.id.textView_ViewContactemail);
        textViewname = (TextView) view.findViewById(R.id.textView_ViewContactflname);
        textViewphoneno = (TextView) view.findViewById(R.id.textView_ViewContactPhoneno);

        imageView = (ImageView) view.findViewById(R.id.imageView_ViewContactdp);

        textViewFullname.setText(otherUser.getUserFullname());
        textViewphoneno.setText(otherUser.getPhoneNo());
        textViewname.setText(otherUser.getUserFullname());
        textViewemail.setText(otherUser.getUserEmail());

        byte[] decodedString = Base64.decode(otherUser.getProfilepic(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imageView.setImageBitmap(decodedByte);

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
    }
}
