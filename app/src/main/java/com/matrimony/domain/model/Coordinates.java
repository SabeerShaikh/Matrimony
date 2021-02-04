package com.matrimony.domain.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Coordinates implements Serializable {

    @SerializedName("latitude")
    public String latitude;

    @SerializedName("longitude")
    public String longitude;
}
