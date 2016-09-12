package inclass_08.group5.com.inclass08;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pawan on 3/14/2016.
 */
public class StoriesDAO {

    private SQLiteDatabase db;

    public StoriesDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public long save(Story story){
        ContentValues values = new ContentValues();
        values.put(StoryTable.COLUMN_STORYTITLE,story.getStoryTitle());
        values.put(StoryTable.COLUMN_STORYBYLINE, story.getStoryByline());
        values.put(StoryTable.COLUMN_STORYABSTRACT, story.getStoryAbstract());
        values.put(StoryTable.COLUMN_STORYCREATEDDATE, story.getStoryCreated_date());
        values.put(StoryTable.COLUMN_STORYTHUMB_IMAGE_URL, story.getStoryThumb_image_url());
        values.put(StoryTable.COLUMN_STORYNORMAL_IMAGE, story.getStoryNormal_image_url());

        return db.insert(StoryTable.TABLENAME,null,values);
    }

    public boolean delete(Story story){
        return db.delete(StoryTable.TABLENAME, StoryTable.COLUMN_ID + "=?", new String[]{story.get_id() + ""})>0;
    }

    public boolean deleteAll() {
        return db.delete(StoryTable.TABLENAME,null,null)>0;
    }

    public Story get(long id){

        Story story = null;
        Cursor c;


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            c = db.query(true, StoryTable.TABLENAME, new String[]{StoryTable.COLUMN_STORYTITLE,
                            StoryTable.COLUMN_STORYBYLINE, StoryTable.COLUMN_STORYABSTRACT,
                            StoryTable.COLUMN_STORYCREATEDDATE,
                            StoryTable.COLUMN_STORYTHUMB_IMAGE_URL, StoryTable.COLUMN_STORYNORMAL_IMAGE},
                    StoryTable.COLUMN_ID + "=?", new String[]{id + ""}, null, null, null, null, null);
            if (c != null && c.moveToFirst()){
                story = buildNoteFromCursor(c);
                if (!c.isClosed()){
                    c.close();
                }
            }
        }
        return story;
    }

    public List<Story> getAll(){
        List<Story> stories = new ArrayList<Story>();
        /*db.query(NotesTable.TABLENAME,new String[]{NotesTable.COLUMN_ID, NotesTable.COLUMN_SUBJECT, NotesTable.COLUMN_TEXT},
                null,null,null,null,new String("ASC"));*/
        Cursor c = db.query(StoryTable.TABLENAME, new String[]{StoryTable.COLUMN_ID,
                        StoryTable.COLUMN_STORYTITLE,
                        StoryTable.COLUMN_STORYBYLINE, StoryTable.COLUMN_STORYABSTRACT,
                        StoryTable.COLUMN_STORYCREATEDDATE,
                        StoryTable.COLUMN_STORYTHUMB_IMAGE_URL, StoryTable.COLUMN_STORYNORMAL_IMAGE},
                null, null, null, null, null);
        if (c != null && c.moveToFirst()){
            do {
                Story story = buildNoteFromCursor(c);
                if (story != null){
                    stories.add(story);
                }
            }while (c.moveToNext());
            if (!c.isClosed()){
                c.close();
            }
        }
        return stories;
    }


    private Story buildNoteFromCursor(Cursor c){
        Story story = null;
        if (c != null){
            story = new Story();
            story.set_id(c.getLong(0));
            story.setStoryTitle(c.getString(1));
            story.setStoryByline(c.getString(2));
            story.setStoryAbstract(c.getString(3));
            story.setStoryCreated_date(c.getString(4));
            story.setStoryThumb_image_url(c.getString(5));
            story.setStoryNormal_image_url(c.getString(6));
        }

        return story;
    }
}
