package edu.co.icesi.ams11chatbase.model;

public class Message {

    public Message(){

    }

    public Message(String id, String body){
        this.id = id;
        this.body = body;
    }

    public String body;
    public String id;
}
