package mad.pawan.videos.intentsexample;

import java.io.Serializable;

/**
 * Created by pawan on 1/31/2016.
 */
public class User implements Serializable{
    String name;
    double age;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public User (String name, double age){
        super();
        this.name = name;
        this.age = age;
    }
}
