package com.matrimony.domain.repository.remote;

import com.google.gson.annotations.SerializedName;

public class APIErrorResponse {

    public static APIErrorResponse defaultAPIErrorResponse = new APIErrorResponse();

    static {
        defaultAPIErrorResponse.error = "Unknown error";
        defaultAPIErrorResponse.message = "Internal application error";
        defaultAPIErrorResponse.statusCode = 7001;
    }

    @SerializedName("statusCode")
    private int statusCode;

    @SerializedName("error")
    private String error;

    @SerializedName("message")
    private String message;

    public int getStatusCode() {
        return statusCode;
    }

    public String getError() {
        return error == null ? ((message != null) ? message : "Oops , seems like something went wrong") : error;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "APIErrorResponse{" +
                "statusCode=" + statusCode +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
