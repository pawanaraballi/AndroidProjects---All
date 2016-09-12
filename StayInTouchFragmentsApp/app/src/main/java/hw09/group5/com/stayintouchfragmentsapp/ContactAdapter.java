package hw09.group5.com.stayintouchfragmentsapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sujit on 4/23/2016.
 */
public class ContactAdapter extends ArrayAdapter<User> {

    List<User> mData;
    Context mContext;
    int mResource;
    Bitmap decodedByte;

    public ContactAdapter(Context context, int resource, List<User> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mData = objects;
        this.mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, parent, false);
        }

        final User user = mData.get(position);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView_dp);
        byte[] decodedString = Base64.decode(user.getProfilepic(), Base64.DEFAULT);
        decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imageView.setImageBitmap(decodedByte);

        TextView textViewExpenseName = (TextView) convertView.findViewById(R.id.textView_convoflname);
        textViewExpenseName.setText(user.getUserFullname());
        ImageView readIcon = (ImageView) convertView.findViewById(R.id.imageView_Read);
        Log.d("isRead", user.isRead() + "");
/*
        if (user.isRead()) {
            readIcon.setVisibility(View.INVISIBLE);
        }
        else{
            readIcon.setVisibility(View.VISIBLE);
        }
*/
        ImageView callImageView = (ImageView) convertView.findViewById(R.id.imageView_Call);
        callImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + user.getPhoneNo()));
                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                mContext.startActivity(callIntent);
            }
        });


        return convertView;
    }
}
