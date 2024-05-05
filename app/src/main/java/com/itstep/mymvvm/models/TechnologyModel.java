package com.itstep.mymvvm.models;

import androidx.annotation.NonNull;

public class TechnologyModel {

    private String name;

    private int age;

    public TechnologyModel(){}

    public TechnologyModel(String name, int age) {
        this.name = name;
        this.age = age;
    }



    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @NonNull
    @Override
    public String toString() {
        return "ToString: " + this.name;
    }
}
