package com.example.cookcompanion;

public class MainMessage {
    private String message;
    public MainMessage(){
        message = "Insert Text Here";
    }
    public void changeMessage(String new_message){
        message = new_message;
    }
    public String getMessage(){
        return message;
    }

}
