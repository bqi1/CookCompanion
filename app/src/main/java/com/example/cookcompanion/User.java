package com.example.cookcompanion;

import java.util.ArrayList;

public class User {
    private ArrayList<Ingredient> storage;
    private String username;
    private String personal_message;

    public String getPersonal_message() {
        return personal_message;
    }

    public void setPersonal_message(String personal_message) {
        this.personal_message = personal_message;
    }
    public User(){

    }
    public User(String username){
        storage = new ArrayList<Ingredient>();
        this.username = username;
        personal_message = "Nothing";
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<Ingredient> getStorage() {
        return storage;
    }
}
