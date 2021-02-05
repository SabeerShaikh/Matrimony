package com.matrimony.config;

import android.app.Application;

import com.matrimony.domain.repository.MetrimonyRepository;
import com.matrimony.domain.repository.remote.api.MetrimonyService;
import com.matrimony.module.base.MatrimonyViewModelFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public interface DI {
    /**
     * Repositories
     */
    MetrimonyRepository provideMetrimonyRepository();
    /** ENDS Repositories Providers */

    /**
     * API Service Providers
     **/

    MetrimonyService provideMetrimonyService();

    /**
     * ENDS API Service Providers
     **/


    Application provideApplication();

    OkHttpClient provideOkHttpClient();

    Retrofit provideRetrofit();

    MatrimonyViewModelFactory provideViewModelFactory();

}

