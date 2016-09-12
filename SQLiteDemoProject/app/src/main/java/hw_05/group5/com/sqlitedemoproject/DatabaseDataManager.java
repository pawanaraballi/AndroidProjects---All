package hw_05.group5.com.sqlitedemoproject;

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
    private NotesDAO notesDAO;

    public DatabaseDataManager(Context mContext){
        this.mContext = mContext;
        databaseOpenHelper = new DatabaseOpenHelper(this.mContext);
        db = databaseOpenHelper.getWritableDatabase();
        notesDAO = new NotesDAO(db);
    }

    public void close(){
        if(db != null){
            db.close();
        }
    }

    public NotesDAO getNotesDAO(){
        return this.notesDAO;
    }

    public long saveNote(Note note){
        return this.notesDAO.save(note);
    }

    public boolean updateNote(Note note){
        return this.notesDAO.update(note);
    }

    public boolean deleteNote(Note note){
        return this.notesDAO.delete(note);
    }

    public Note getNote(long id){
        return  this.notesDAO.get(id);
    }

    public List<Note> getAllNote(){
        return this.notesDAO.getAll();
    }
}
