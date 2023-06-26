package com.example.demo12;
import javafx.scene.control.ToggleGroup;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class User {
    String firstName;
    String lastName;
    String birthdate;
    String roleID;
    String email;

    String title;

    public User(String firstName, String lastName, String birthdate, String roleID, String email, String title) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.roleID = roleID;
        this.email = email;
        this.title = title;
    }



    public String getFirstName(){
        return firstName;
    }
    public void seFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public String getBirthdate(){
        return birthdate;
    }
    public void setBirthdate(String birthdate){
        this.birthdate = birthdate;
        LocalDateTime now = LocalDateTime.of(2023, 04, 03, 0, 0); //др
        LocalDateTime birthday = LocalDateTime.now(); //текущее время
        long age = ChronoUnit.YEARS.between(birthday, now);//сколько лет на текущее время

    }
    public String getRoleID(){
        return roleID;
    }
    public void getRoleID(String roleID){
        this.roleID = roleID;
    }
    public String getEmail(){
        return email;
    }
    public void setUser(String email) {
        this.email = email;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
}