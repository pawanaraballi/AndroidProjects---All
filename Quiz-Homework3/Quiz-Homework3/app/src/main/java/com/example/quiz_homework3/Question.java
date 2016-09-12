package com.example.quiz_homework3;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Raghu on 2/16/16.
 */
public class Question implements Parcelable
{
    int id;
    String question;
    String link;
    List<String> options=new LinkedList<>();
    List<String> points=new LinkedList<>();


    public List<String> getOptions() {
        return options;
    }

    public String getQuestion() {

        return question;
    }

    protected Question(Parcel in) {
        id = in.readInt();
        question = in.readString();
        link = in.readString();
        options = in.createStringArrayList();
        points = in.createStringArrayList();

    }

    public static final Creator<Question> CREATOR = new Creator<Question>()
    {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public String getLink() {
        return link;
    }

    public Question() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setOptions(String s)
    {
        options.add(s);
    }

    public void setPoints(String s)
    {
        points.add(s);
    }

    public int getPoints(int m)
    {
        return Integer.parseInt(points.get(m));
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", options=" + options +
                ", points=" + points +
                ", link='" + link + '\'' +
                '}';
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(question);
        dest.writeString(link);
        dest.writeStringList(options);
        dest.writeStringList(points);

    }


}
