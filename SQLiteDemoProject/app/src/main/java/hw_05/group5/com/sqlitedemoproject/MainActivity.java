package hw_05.group5.com.sqlitedemoproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseDataManager dm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dm = new DatabaseDataManager(this);

        dm.saveNote(new Note("This is the first subject","I have gone crazy to see this functionality!!!!"));
        dm.saveNote(new Note("Note 2","Text Note 2"));
        dm.saveNote(new Note("Note 3","Text Note 3"));
        dm.updateNote(new Note(1,"jhsdsaj","kjfshakjh"));

        List<Note> notes = dm.getAllNote();

        Log.d("demo",notes.toString());
    }

    @Override
    protected void onDestroy() {
        dm.close();
        super.onDestroy();
    }
}
