package com.matrimony.domain.repository.remote;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;

import okhttp3.ResponseBody;

public interface APIService {

    default APIErrorResponse errorResponseHandler(ResponseBody responseBody) {

        APIErrorResponse apiErrorResponse = APIErrorResponse.defaultAPIErrorResponse;
        try {
            apiErrorResponse = (new Gson()).fromJson(responseBody.string(), APIErrorResponse.class);
        } catch (JsonSyntaxException | IOException e) {
            e.printStackTrace();
        }
        return apiErrorResponse;
    }
}
