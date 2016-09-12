package inclass11.group05.com.expenseapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Truong Pham on 4/11/2016.
 */
public class expense implements Parcelable, Serializable {

    String category,date,name,user,pushid, amount;

    public expense(String category, String date, String name, String user, String pushid, String amount) {
        this.category = category;
        this.date = date;
        this.name = name;
        this.user = user;
        this.pushid = pushid;
        this.amount = amount;
    }

    public expense() {

    }

    protected expense(Parcel in) {
        category = in.readString();
        date = in.readString();
        name = in.readString();
        user = in.readString();
        pushid = in.readString();
        amount = in.readString();
    }

    public static final Creator<expense> CREATOR = new Creator<expense>() {
        @Override
        public expense createFromParcel(Parcel in) {
            return new expense(in);
        }

        @Override
        public expense[] newArray(int size) {
            return new expense[size];
        }
    };

    @Override
    public String toString() {
        return "expense{" +
                "category='" + category + '\'' +
                ", date='" + date + '\'' +
                ", name='" + name + '\'' +
                ", user='" + user + '\'' +
                ", pushid='" + pushid + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }

    public String getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getUser() {
        return user;
    }

    public String getPushid() {
        return pushid;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPushid(String pushid) {
        this.pushid = pushid;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(category);
        dest.writeString(date);
        dest.writeString(name);
        dest.writeString(user);
        dest.writeString(pushid);
        dest.writeString(amount);
    }
}