package videos.group5.com.pullparserdemo;

import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by pawan on 2/22/2016.
 */
public class PersonUtil {

    static public class PersonsPullParser extends DefaultHandler{
        ArrayList<Person> personsList;
        Person person;

        static ArrayList<Person> parsePerson(InputStream in) throws XmlPullParserException, IOException {
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(in, "UTF-8");
            Person person = null;
            ArrayList<Person> personList = new ArrayList<Person>();
            int event = parser.getEventType();

            while (event != XmlPullParser.END_DOCUMENT){
                switch (event){
                    case XmlPullParser.START_TAG:
                        if(parser.getName().equals("person")){
                            person = new Person();
                            //personList.add(person);
                            person.setId(parser.getAttributeValue(null,"id"));
                        }else if(parser.getName().equals("name")){
                            person.setName(parser.nextText().trim());

                        }else if(parser.getName().equals("age")){
                            person.setAge(parser.nextText().trim());

                        }else if(parser.getName().equals("department")){
                            person.setDepartment(parser.nextText().trim());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("person")){
                            personList.add(person);
                            person = null;
                        }
                        break;
                }

                event = parser.next();
            }
            return personList;
        }
    }
}
