package inclass_08.group5.com.inclass08;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by Pawan on 3/14/2016.
 */
public class DatabaseDataManager {

    private Context mContext;
    private DatabaseOpenHelper databaseOpenHelper;
    private SQLiteDatabase db;
    private StoriesDAO storiesDAO;

    public DatabaseDataManager(Context mContext){
        this.mContext = mContext;
        databaseOpenHelper = new DatabaseOpenHelper(this.mContext);
        db = databaseOpenHelper.getWritableDatabase();
        storiesDAO = new StoriesDAO(db);
    }

    public void close(){
        if(db != null){
            db.close();
        }
    }

    public StoriesDAO getStoriesDAO(){
        return this.storiesDAO;
    }

    public long saveStory(Story story){
        return this.storiesDAO.save(story);
    }

    public boolean deleteStory(Story story){
        return this.storiesDAO.delete(story);
    }

    public boolean deleteStoryAll(){
        return this.storiesDAO.deleteAll();
    }

    public Story getStory(long id){
        return  this.storiesDAO.get(id);
    }
    public List<Story> getAllStory(){
        return this.storiesDAO.getAll();
    }
}
