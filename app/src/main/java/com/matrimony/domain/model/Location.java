package com.matrimony.domain.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Location implements Serializable {

    @SerializedName("street")
    public Street street;

    @SerializedName("city")
    public String city;

    @SerializedName("state")
    public String state;

    @SerializedName("country")
    public String country;

    @SerializedName("postcode")
    public String postcode;

    @SerializedName("coordinates")
    public Coordinates coordinates;

    @SerializedName("timezone")
    public Timezone timezone;
}
