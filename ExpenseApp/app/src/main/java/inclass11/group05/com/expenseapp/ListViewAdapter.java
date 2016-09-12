package inclass11.group05.com.expenseapp;

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
public class ListViewAdapter extends ArrayAdapter<expense> {

    List<expense> mData;
    Context mContext;
    int mResource;

    public ListViewAdapter(Context context, int resource, List<expense> objects) {
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

        expense expens = mData.get(position);

        String email = ExpensesList.userEmail;


        TextView textViewExpenseName = (TextView) convertView.findViewById(R.id.textView_Expense);
        textViewExpenseName.setText(expens.getName());
        TextView textViewAmount = (TextView) convertView.findViewById(R.id.textView_Cost);
        textViewAmount.setText(expens.getAmount()+"");



        return convertView;
    }
}