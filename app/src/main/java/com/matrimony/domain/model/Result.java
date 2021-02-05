package com.matrimony.domain.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Result implements Serializable {
    @SerializedName("gender")
    public String gender;

    @SerializedName("name")
    public Name name;

    @SerializedName("location")
    public Location location;

    @SerializedName("email")
    public String email;

    @SerializedName("login")
    public Login login;

    @SerializedName("dob")
    public Dob dob;

    @SerializedName("registered")
    public Registered registered;

    @SerializedName("phone")
    public String phone;

    @SerializedName("cell")
    public String cell;

    @SerializedName("id")
    public Id id;

    @SerializedName("picture")
    public Picture picture;

    @SerializedName("nat")
    public String nat;

}
