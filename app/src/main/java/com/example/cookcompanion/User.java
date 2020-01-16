package com.example.cookcompanion;

import java.util.ArrayList;

public class User {
    private ArrayList<Ingredient> storage;
    private Integer counter=0;
    public User(){
        storage = new ArrayList<Ingredient>();
    }
    public String getString(){
        return counter.toString();
    }
    public void increment(){
        counter+=1;
    }

}
