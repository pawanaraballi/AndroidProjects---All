package pawan.com.firebasedemoapplication;

/**
 * Created by Pawan on 4/11/2016.
 */
public class BlogPost {
    private String author;
    private String title;

    public BlogPost() {
        // empty default constructor, necessary for Firebase to be able to deserialize blog posts
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

}
