package com.matrimony.domain.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Id implements Serializable {
    @SerializedName("name")
    public String name;

    @SerializedName("value")
    public String value;
}
