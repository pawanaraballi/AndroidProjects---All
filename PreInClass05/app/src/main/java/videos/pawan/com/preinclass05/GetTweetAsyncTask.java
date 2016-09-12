package videos.pawan.com.preinclass05;

import android.content.Context;
import android.os.AsyncTask;

import java.util.LinkedList;

/**
 * Created by pawan on 2/15/2016.
 */
public class GetTweetAsyncTask extends AsyncTask<String,Void,LinkedList<String>> {

    //MainActivity mainActivity;
    IData mainActivity;

    public GetTweetAsyncTask(IData mainActivity) {
        this.mainActivity = mainActivity;
    }

    //public GetTweetsAsyncTask(MainActivity mainActivity) {
    //    this.mainActivity = mainActivity;
    //}

    @Override
    protected LinkedList<String> doInBackground(String... params) {

        LinkedList<String> tweets = new LinkedList<String>();
        tweets.add("Tweet 0");
        tweets.add("Tweet 1");
        tweets.add("Tweet 2");
        tweets.add("Tweet 3");
        tweets.add("Tweet 4");
        return tweets;
    }

    @Override
    protected void onPostExecute(LinkedList<String> strings) {
        mainActivity.setupData(strings);
    }

    static public interface IData{
        public void setupData(LinkedList<String> strings);

        public Context getContext();
    }

}
