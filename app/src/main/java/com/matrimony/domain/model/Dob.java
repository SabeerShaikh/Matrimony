package com.matrimony.domain.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Dob {
    @SerializedName("date")
    public Date date;
    @SerializedName("age")
    public int age;
}
