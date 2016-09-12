package videos.group5.com.jsondemo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by pawan on 2/22/2016.
 */
public class PersonUtil {

    static public class JSONParseperson {
        ArrayList<Person> personsList;
        Person person;

        static ArrayList<Person> parsePerson (String in) throws JSONException {
            ArrayList<Person> personList = new ArrayList<Person>();

            JSONObject root = new JSONObject(in);
            JSONArray personsJSONarray = root.getJSONArray("persons");

            for (int i = 0; i < personsJSONarray.length() ; i++) {
                JSONObject personJSONObj = personsJSONarray.getJSONObject(i);
                Person person = new Person();

                personList.add(person);
            }

            return personList;
        }
    }
}
