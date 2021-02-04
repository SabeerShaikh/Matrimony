package com.matrimony.config;

import android.app.Application;


import com.matrimony.module.base.MatrimonyViewModelFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public interface DI {
    /**
     * Repositories
     */

    /** ENDS Repositories Providers */

    /**
     * API Service Providers
     **/



    /**
     * ENDS API Service Providers
     **/


    Application provideApplication();

    OkHttpClient provideOkHttpClient();

    Retrofit provideRetrofit();

    MatrimonyViewModelFactory provideViewModelFactory();

}

