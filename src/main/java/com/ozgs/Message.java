package com.ozgs;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Message {

    private int ID;
    private String author,recipient,text;
    private Timestamp timestamp;
    private boolean pm;

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTimestamp() {

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYY:M:dd hh:mm");

        return simpleDateFormat.format(timestamp);
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isPm() {
        return pm;
    }

    public void setPm(boolean pm) {
        this.pm = pm;
    }
    

    
}