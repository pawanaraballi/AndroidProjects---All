package pawan.com.midterm;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

/**
 * Created by Pawan on 3/21/2016.
 */
public class VenueAdapter extends ArrayAdapter<Venue> {


        List<Venue> mData;
        Context mContext;
        int mResource;
        public VenueAdapter(Context context, int resource, List<Venue> objects) {
            super(context, resource, objects);
            this.mContext = context;
            this.mData = objects;
            this.mResource = resource;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            if (convertView == null){
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(mResource,parent,false);
            }

            final Venue fo = mData.get(position);

            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.networkInterceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder().header("Cache-Control", "max-age=" + (60 * 60 * 24 * 365)).build();
                }
            });

            okHttpClient.setCache(new Cache(mContext.getCacheDir(), Integer.MAX_VALUE));
            OkHttpDownloader okHttpDownloader = new OkHttpDownloader(okHttpClient);
            final Picasso picasso = new Picasso.Builder(mContext).downloader(okHttpDownloader).build();
            final View finalConvertView = convertView;

            picasso.load(fo.getCategoryIcon())
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .into((ImageView) convertView.findViewById(R.id.imageView), new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {
                            Log.d("picaso", "I am here");
                            picasso.load(fo.getCategoryIcon()).into((ImageView) finalConvertView.findViewById(R.id.imageView));
                        }
                    });

            TextView timeTextView = (TextView) convertView.findViewById(R.id.textView_time);

            //Log.d("getcivic",fo.getMaximumTemp());

            timeTextView.setText(fo.getVenueName());

            TextView textViewType = (TextView) convertView.findViewById(R.id.textView_type);
            textViewType.setText(fo.getCategoryName());

            int checkincount = Integer.parseInt(fo.getCheckinCount());

            ImageView imageView_Cat = (ImageView) convertView.findViewById(R.id.imageView_CatIcon);
            if (checkincount == 0 && checkincount <= 100){
                imageView_Cat.setImageResource(R.drawable.bronze);
            }else{
            if (checkincount == 101 && checkincount <= 500){
                imageView_Cat.setImageResource(R.drawable.silver);
            }else{
            if (checkincount >500){
                imageView_Cat.setImageResource(R.drawable.gold);
            }}}

            return convertView;

        }


}
