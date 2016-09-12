package pawan.com.firebasedemoapplication;

/**
 * Created by Pawan on 4/11/2016.
 */
public class DinasaurFacts {
    long height;
    double length;
    long weight;

    public DinasaurFacts() {
        // empty default constructor, necessary for Firebase to be able to deserialize blog posts
    }

    public long getHeight() {
        return height;
    }

    public double getLength() {
        return length;
    }

    public long getWeight() {
        return weight;
    }
}
