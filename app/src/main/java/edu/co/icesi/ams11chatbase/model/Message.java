package edu.co.icesi.ams11chatbase.model;

public class Message {

    public Message(){

    }

    public Message(String id, String body, long ts){
        this.id = id;
        this.body = body;
        this.date = ts;
    }

    public String id;
    public String body;
    public long date;
}
