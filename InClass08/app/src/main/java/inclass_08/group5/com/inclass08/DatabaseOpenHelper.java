package inclass_08.group5.com.inclass08;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Pawan on 3/14/2016.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {
    static final String DB_NAME ="stories.db";
    static final int DB_VERSION = 1;



    public DatabaseOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        Log.d("pawan","db created");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        StoryTable.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        StoryTable.onUpgrade(db,oldVersion,newVersion);
    }

}
