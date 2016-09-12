package group5.com.fragmentdemo;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AFragment extends Fragment {


    public AFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("demo","AFragment: onCreateView");
        // Inflate the layout for this fragment
        /*View view = inflater.inflate(R.layout.fragment_a, container, false);

        view.findViewById(R.id.buttonClickMe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Button Pressed",Toast.LENGTH_LONG).show();
            }
        });

        return view;*/


        return inflater.inflate(R.layout.fragment_a, container, false);
    }

    public void changeColor(int color){

        getView().setBackgroundColor(color);
        /*getActivity().findViewById(R.id.fragment_root).setBackgroundColor(color);*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("demo","AFragment: onDestroy");
    }

    OnFragmentTextChange mListener;

    @Override
    public void onStart() {
        super.onStart();
        Log.d("demo","AFragment: onStart");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("demo","AFragment: onActivityCreated");

        getActivity().findViewById(R.id.editTextInFragment);

        getView().findViewById(R.id.buttonInFragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) getView().findViewById(R.id.editTextInFragment);
                mListener.onTextChanged(editText.getText().toString());
            }
        });

        /*getActivity().findViewById(R.id.buttonClickMe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Button Pressed",Toast.LENGTH_LONG).show();
            }
        });*/
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("demo","AFragment: onAttach");

        try {
            mListener = (OnFragmentTextChange) getActivity();
        }catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString() + "should implement OnFragmentTextChange");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("demo","AFragment: onCreate");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("demo","AFragment: onResume");
    }

    public interface OnFragmentTextChange{
        void onTextChanged(String text);
    }
}
