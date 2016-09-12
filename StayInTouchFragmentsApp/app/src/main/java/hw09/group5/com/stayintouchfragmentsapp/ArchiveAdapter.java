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
public class ArchiveAdapter extends ArrayAdapter<User> {

    List<User> mData;
    Context mContext;
    int mResource;
    Bitmap decodedByte;

    public ArchiveAdapter(Context context, int resource, List<User> objects) {
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

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView_dp_archive);
        byte[] decodedString = Base64.decode(user.getProfilepic(), Base64.DEFAULT);
        decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imageView.setImageBitmap(decodedByte);

        TextView textViewExpenseName = (TextView) convertView.findViewById(R.id.textView_convoflname_archive);
        textViewExpenseName.setText(user.getUserFullname());


        return convertView;
    }
}
