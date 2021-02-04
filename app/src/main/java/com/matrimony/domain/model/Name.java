package com.matrimony.domain.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Name implements Serializable {

    @SerializedName("title")
    public String title;

    @SerializedName("first")
    public String first;

    @SerializedName("last")
    public String last;
}
