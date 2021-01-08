package com.example.myfriends;

import java.io.Serializable;

public class Person implements Serializable {
    private String name;
    private int birth;

    public Person(String name, int birth){
        this.name = name;
        this.birth = birth;
    }

    public String getName() {
        return name;
    }

    public int getBirth() {
        return birth;
    }

    public void setName(String newName){
        this.name = newName;
    }

    public void setBirth(int newBirth){
        this.birth = newBirth;
    }

    @Override
    public String toString() {
        return name + '\'' +
                ", Født: " + birth;
    }
}
