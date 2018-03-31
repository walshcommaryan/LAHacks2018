package com.example.ryan.laparking;
public class Model{
    String name; //name of symptom
    int value; /* 0 -&gt; checkbox disable, 1 -&gt; checkbox enable */
    int id; //symptom id number

    Model(String name, int value, int id_number){
        this.name = name;
        this.value = value;
        this.id = id_number;
    }
    public String getName(){
        return this.name;
    }
    public int getValue(){
        return this.value;
    }
    public int getId(){
        return this.id;
    }
    public void setValue(int val){
        this.value = val;
    }
    public void setName(String val){
        this.name = val;
    }
    public void setId(int val){
        this.id = val;
    }

}
