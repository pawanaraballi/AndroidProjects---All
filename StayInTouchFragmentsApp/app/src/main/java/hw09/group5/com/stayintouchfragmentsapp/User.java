package hw09.group5.com.stayintouchfragmentsapp;

import java.io.Serializable;

/**
 * Created by sujit on 4/23/2016.
 */
public class User implements Serializable {
    private String userFullname;
    private String userEmail;
    private String userPassword;
    private String phoneNo;
    private String profilepic;
    private boolean read = true;
    private String pushId;

    public User(){}

    @Override
    public String toString() {
        return "User{" +
                "userFullname='" + userFullname + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", profilepic='" + profilepic + '\'' +
                ", read=" + read +
                '}';
    }

    public User(String userFullname, String userEmail, String userPassword, String phoneNo, String profilepic,String pushId, boolean read) {
        this.userFullname = userFullname;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.phoneNo = phoneNo;
        this.profilepic = profilepic;
        this.pushId = pushId;
        this.read = read;
    }

    public String getUserFullname() {
        return userFullname;
    }

    public void setUserFullname(String userFullname) {
        this.userFullname = userFullname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }


    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    @Override
    public boolean equals(Object obj) {
        User userObj = (User) obj;
        if (this.getPushId().equals(userObj.getPushId())) {
            return true;
        }
        return false;
    }
}
