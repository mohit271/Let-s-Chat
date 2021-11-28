package com.example.letschat.Model;

public class User {
    String userName,email,password,lastMessage,profilePic,userId,status;

    public User(String userName, String email, String password, String lastMessage, String profilePic, String userId,String status) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.lastMessage = lastMessage;
        this.profilePic = profilePic;
        this.userId = userId;
        this.status=status;
    }

    public User(){

    }
    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getUserId() {
        return userId;
    }



    public void setUserId(String userId) {
        this.userId = userId;
    }
}
