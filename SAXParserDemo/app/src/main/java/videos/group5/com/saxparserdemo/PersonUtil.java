package videos.group5.com.saxparserdemo;

import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by pawan on 2/22/2016.
 */
public class PersonUtil {

    static public class PersonsSAXParser extends DefaultHandler{
        ArrayList<Person> personsList;
        Person person;
        StringBuilder xmlInnerText;

        static public ArrayList<Person> parsePerson(InputStream in){
            PersonsSAXParser parser = new PersonsSAXParser();
            try {
                Xml.parse(in,Xml.Encoding.UTF_8,parser);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }
            return parser.getPersonsList();
        }

        public ArrayList<Person> getPersonsList() {
            return personsList;
        }

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            xmlInnerText = new StringBuilder();
            personsList = new ArrayList<Person>();
        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            if (localName.equals("person")) {
                person = new Person();
                person.setId(attributes.getValue("id"));
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            if(localName.equals("person")){
                personsList.add(person);
            }else if (localName.equals("name")){
                person.setName(xmlInnerText.toString().trim());
            }else if (localName.equals("age")){
                person.setAge(xmlInnerText.toString().trim());
            }else if (localName.equals("department")){
                person.setDepartment(xmlInnerText.toString().trim());
            }
            xmlInnerText.setLength(0); //closing the buffer
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            xmlInnerText.append(ch,start,length);
        }
    }
}
