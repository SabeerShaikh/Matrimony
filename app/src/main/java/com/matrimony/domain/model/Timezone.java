package com.matrimony.domain.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Timezone implements Serializable {
    @SerializedName("offset")
    public String offset;

    @SerializedName("description")
    public String description;

}
