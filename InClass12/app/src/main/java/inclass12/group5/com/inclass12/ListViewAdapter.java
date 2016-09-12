package inclass12.group5.com.inclass12;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Pawan on 4/11/2016.
 */
public class ListViewAdapter extends ArrayAdapter<Expense> {

    List<Expense> mData;
    Context mContext;
    int mResource;

    public ListViewAdapter(Context context, int resource, List<Expense> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mData = objects;
        this.mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, parent, false);
        }

        Expense expens = mData.get(position);


        TextView textViewExpenseName = (TextView) convertView.findViewById(R.id.textView_Expense);
        textViewExpenseName.setText(expens.getName());
        TextView textViewAmount = (TextView) convertView.findViewById(R.id.textView_Cost);
        textViewAmount.setText(expens.getAmount()+"");



        return convertView;
    }
}