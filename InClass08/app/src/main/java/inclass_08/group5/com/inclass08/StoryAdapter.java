package inclass_08.group5.com.inclass08;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Pawan on 2/29/2016.
 */
public class StoryAdapter extends ArrayAdapter<Story> {
    List<Story> mData;
    Context mContext;
    int mResource;
    String formatDate;
    public StoryAdapter(Context context, int resource, List<Story> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mData = objects;
        this.mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource,parent,false);
        }



        Story story = mData.get(position);
        Log.d("test", story.getStoryThumb_image_url());
        Picasso.with(mContext).load(story.getStoryThumb_image_url()).into((ImageView) convertView.findViewById(R.id.imageView));
        convertView.setBackgroundColor(Color.GRAY);

        TextView colorNameTextView = (TextView) convertView.findViewById(R.id.textView_Headline);
        colorNameTextView.setText(story.getStoryTitle());

        ImageView imageView = (ImageView)convertView.findViewById(R.id.imageView2);


        TextView colorHexTextView = (TextView) convertView.findViewById(R.id.textView_Date);
        String dateParsed = story.getStoryCreated_date();
        try {
            formatDate = convertToDate(dateParsed);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        colorHexTextView.setText(formatDate);
        //colorHexTextView.setTextColor(android.graphics.Color.parseColor(color.colorHex));

        return convertView;

    }
    String convertToDate(String receivedDate) throws ParseException {
        SimpleDateFormat inputformatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss-hh:mm");
        Date date = inputformatter.parse(receivedDate);
        SimpleDateFormat outputformatter = new SimpleDateFormat("MM/dd");
        String newDate = outputformatter.format(date);
        return newDate;
    }
}
