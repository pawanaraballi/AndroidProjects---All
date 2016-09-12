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
public class ConversationAdapter extends ArrayAdapter<User> {

    List<User> userList;
    Context mContext;
    int mResource;

    public ConversationAdapter(Context context, int resource, List<User> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.userList = objects;
        this.mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, parent, false);
        }

        final User user = userList.get(position);

        ImageView profilePicImageView = (ImageView) convertView.findViewById(R.id.imageView_dp);
        byte[] decodedString = Base64.decode(user.getProfilepic(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        profilePicImageView.setImageBitmap(decodedByte);


        TextView fullNameTextview = (TextView) convertView.findViewById(R.id.textView_convoflname);
        fullNameTextview.setText(user.getUserFullname());
        ImageView readIcon = (ImageView) convertView.findViewById(R.id.imageView_Read);
        Log.d("isRead", user.isRead() + "");
        if (user.isRead() == true) {
            readIcon.setVisibility(View.INVISIBLE);
        }
        else{
            readIcon.setVisibility(View.VISIBLE);
        }

        return convertView;
    }
}
