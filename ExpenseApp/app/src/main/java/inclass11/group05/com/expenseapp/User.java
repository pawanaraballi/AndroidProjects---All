package inclass11.group05.com.expenseapp;

/**
 * Created by Truong Pham on 4/11/2016.
 */
public class User {
    private String userFullname;
    private String userEmail;
    private String userPassword;

    public User() {
    }

    public User(String name, String email, String password) {
        this.userFullname = name;
        this.userEmail = email;
        this.userPassword = password;
    }

    public String getUserFullname() {
        return userFullname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserFullname(String userFullname) {
        this.userFullname = userFullname;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }


}
