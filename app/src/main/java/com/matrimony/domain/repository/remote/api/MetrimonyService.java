package com.matrimony.domain.repository.remote.api;

import com.matrimony.domain.model.BioData;
import com.matrimony.domain.repository.remote.APIService;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface  MetrimonyService extends APIService {

    @GET("api/")
    Call<BioData> getAllData(@Query("results") int pageSize);
}

