package inclass_08.group5.com.inclass08;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Pawan on 3/14/2016.
 */
public class StoryTable {

    static final String TABLENAME = "stories";
    static final String COLUMN_ID = "_id";
    static final String COLUMN_STORYTITLE = "storyTitle";
    static final String COLUMN_STORYBYLINE = "storyByline";
    static final String COLUMN_STORYABSTRACT = "storyAbstract";
    static final String COLUMN_STORYCREATEDDATE = "storyCreated_date";
    static final String COLUMN_STORYTHUMB_IMAGE_URL = "storyThumb_image_url";
    static final String COLUMN_STORYNORMAL_IMAGE = "storyNormal_image_url";


    static public void onCreate(SQLiteDatabase db){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE "+TABLENAME+" (");
        stringBuilder.append(COLUMN_ID + " integer primary key autoincrement, ");
        stringBuilder.append(COLUMN_STORYTITLE + " text not null, ");
        stringBuilder.append(COLUMN_STORYBYLINE + " text not null, ");
        stringBuilder.append(COLUMN_STORYABSTRACT + " text not null, ");
        stringBuilder.append(COLUMN_STORYCREATEDDATE + " text not null, ");
        stringBuilder.append(COLUMN_STORYTHUMB_IMAGE_URL + " text , ");
        stringBuilder.append(COLUMN_STORYNORMAL_IMAGE + " text );");
        try{
            db.execSQL(stringBuilder.toString());
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    static public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLENAME);
        StoryTable.onCreate(db);
    }

    static public void onDeleteAll(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLENAME);
    }
}
