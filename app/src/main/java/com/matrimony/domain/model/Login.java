package com.matrimony.domain.model;

import com.google.gson.annotations.SerializedName;

public class Login {
    @SerializedName("uuid")
    public String uuid;
    @SerializedName("username")
    public String username;

    @SerializedName("password")
    public String password;

    @SerializedName("salt")
    public String salt;

    @SerializedName("md5")
    public String md5;

    @SerializedName("sha1")
    public String sha1;

    @SerializedName("sha256")
    public String sha256;
}
