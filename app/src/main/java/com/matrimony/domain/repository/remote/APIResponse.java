package com.matrimony.domain.repository.remote;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class APIResponse<T> {

    @SerializedName("code")
    private int code;

    @SerializedName("status")
    private boolean status;

    @SerializedName("errMessage")
    private String msg;

    @SerializedName("data")
    private T data;

    public static APIResponse map(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, APIResponse.class);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public boolean status() {
        return status;
    }

    @Override
    public String toString() {
        return "APIResponse{" +
                "code=" + code +
                ", status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
