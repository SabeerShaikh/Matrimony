package com.matrimony.domain.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class BioData implements Serializable {
    @SerializedName("results")
    public List<Result> results;
}
