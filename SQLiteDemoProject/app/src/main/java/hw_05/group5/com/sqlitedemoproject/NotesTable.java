package hw_05.group5.com.sqlitedemoproject;

import android.database.sqlite.SQLiteDatabase;

import android.database.SQLException;
import android.util.Log;

/**
 * Created by Pawan on 3/14/2016.
 */
public class NotesTable {

    static final String TABLENAME = "notes";
    static final String COLUMN_ID = "_id";
    static final String COLUMN_SUBJECT = "subject";
    static final String COLUMN_TEXT = "text";

    static public void onCreate(SQLiteDatabase db){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE "+TABLENAME+" (");
        stringBuilder.append(COLUMN_ID + " integer primary key autoincrement, ");
        stringBuilder.append(COLUMN_SUBJECT + " text not null, ");
        stringBuilder.append(COLUMN_TEXT + " text not null);");
        try{
            db.execSQL(stringBuilder.toString());
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    static public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        Log.d("pawan","table created");
        db.execSQL("DROP TABLE IF EXISTS " + TABLENAME);
        NotesTable.onCreate(db);
    }
}
