package com.matrimony.module.ui.mainscreen.adapter.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Members")
public class UIMembers implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int position;
    public String memberId;
    public String title;
    @ColumnInfo(name = "first_name")
    public String firstName;
    @ColumnInfo(name = "last_name")
    public String secondName;
    public int age;
    public String streetName;
    public String city;
    public String state;
    public String country;
    public String postcode;
    public String imageUrl;
    public String acceptOrDeclined;

    public UIMembers(int position, String memberId, String title, String firstName, String secondName, int age, String streetName, String city, String state, String country, String postcode, String imageUrl, String acceptOrDeclined) {
        this.position = position;
        this.memberId = memberId;
        this.title = title;
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.streetName = streetName;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postcode = postcode;
        this.imageUrl = imageUrl;
        this.acceptOrDeclined = acceptOrDeclined;
    }

    public UIMembers() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAcceptOrDeclined() {
        return acceptOrDeclined;
    }

    public void setAcceptOrDeclined(String acceptOrDeclined) {
        this.acceptOrDeclined = acceptOrDeclined;
    }
}
