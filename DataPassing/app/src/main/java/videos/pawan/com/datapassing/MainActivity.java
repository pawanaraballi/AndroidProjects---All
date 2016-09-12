package videos.pawan.com.datapassing;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements GetTweetsAsyncTask.IData {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new GetTweetsAsyncTask(this).execute("some url...");
    }

    public void setupData(LinkedList<String> data){
        ListView listView = (ListView) findViewById(R.id.listView);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,android.R.id.text1,data);
        listView.setAdapter(arrayAdapter);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
