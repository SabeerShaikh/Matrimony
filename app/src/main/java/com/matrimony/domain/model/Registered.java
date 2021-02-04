package com.matrimony.domain.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Registered implements Serializable {
    @SerializedName("date")
    public Date date;

    @SerializedName("age")
    public int age;
}
