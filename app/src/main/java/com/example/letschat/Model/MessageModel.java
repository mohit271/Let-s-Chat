package com.example.letschat.Model;

public class MessageModel {
    String Uid,message;
    long timeStamp;


    public MessageModel(String uid, String message, long timeStamp) {
        Uid = uid;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public MessageModel(String uid, String message) {
        Uid = uid;
        this.message = message;
    }
    public  MessageModel(){}

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
