package inclass_08.group5.com.inclass08;

/**
 * Created by Pawan on 2/29/2016.
 */
public class Story {
    private long _id;

    String storyTitle,storyByline,storyAbstract,storyCreated_date,storyThumb_image_url,storyNormal_image_url;

    public Story() {
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public Story(long _id, String storyTitle, String storyByline, String storyAbstract, String storyCreated_date, String storyThumb_image_url, String storyNormal_image_url) {

        this._id = _id;
        this.storyTitle = storyTitle;
        this.storyByline = storyByline;
        this.storyAbstract = storyAbstract;
        this.storyCreated_date = storyCreated_date;
        this.storyThumb_image_url = storyThumb_image_url;
        this.storyNormal_image_url = storyNormal_image_url;
    }

    public Story(String storyTitle, String storyByline, String storyAbstract, String storyCreated_date, String storyThumb_image_url, String storyNormal_image_url) {
        this.storyTitle = storyTitle;
        this.storyByline = storyByline;
        this.storyAbstract = storyAbstract;
        this.storyCreated_date = storyCreated_date;
        this.storyThumb_image_url = storyThumb_image_url;
        this.storyNormal_image_url = storyNormal_image_url;
    }

    @Override
    public String toString() {
        return "Story{" +
                "storyTitle='" + storyTitle + '\'' +
                ", storyByline='" + storyByline + '\'' +
                ", storyAbstract='" + storyAbstract + '\'' +
                ", storyCreated_date='" + storyCreated_date + '\'' +
                ", storyThumb_image_url='" + storyThumb_image_url + '\'' +
                ", storyNormal_image_url='" + storyNormal_image_url + '\'' +
                '}';
    }

    public String getStoryTitle() {
        return storyTitle;
    }

    public void setStoryTitle(String storyTitle) {
        this.storyTitle = storyTitle;
    }

    public String getStoryByline() {
        return storyByline;
    }

    public void setStoryByline(String storyByline) {
        this.storyByline = storyByline;
    }

    public String getStoryAbstract() {
        return storyAbstract;
    }

    public void setStoryAbstract(String storyAbstract) {
        this.storyAbstract = storyAbstract;
    }

    public String getStoryCreated_date() {
        return storyCreated_date;
    }

    public void setStoryCreated_date(String storyCreated_date) {
        this.storyCreated_date = storyCreated_date;
    }

    public String getStoryThumb_image_url() {
        return storyThumb_image_url;
    }

    public void setStoryThumb_image_url(String storyThumb_image_url) {
        this.storyThumb_image_url = storyThumb_image_url;
    }

    public String getStoryNormal_image_url() {
        return storyNormal_image_url;
    }

    public void setStoryNormal_image_url(String storyNormal_image_url) {
        this.storyNormal_image_url = storyNormal_image_url;
    }
}
