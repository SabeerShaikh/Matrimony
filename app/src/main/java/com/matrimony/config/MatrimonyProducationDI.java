package com.matrimony.config;

import android.app.Application;

import com.matrimony.BuildConfig;
import com.matrimony.domain.repository.MetrimonyRepository;
import com.matrimony.domain.repository.MetrimonyRepositoryImpl;
import com.matrimony.domain.repository.remote.api.MetrimonyService;
import com.matrimony.module.base.MatrimonyViewModelFactory;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MatrimonyProducationDI implements DI {
    public static final String HEADER_AUTHORIZTION = "Authorization";
    protected final long REQUEST_TIME_OUT_S = 180L;
    protected final long HTTP_CACHE_SIZE_MB = 10 * 1024 * 1024;

    protected static MatrimonyViewModelFactory matrimonyViewModelFactory;
    protected static Retrofit singletonRetrofit;
    protected final String HEADER_CONTENT_TYPE = "Content-Type";
    protected final String JSON_CONTENT = "application/json";
    protected final Application application;

    public MatrimonyProducationDI(Application application) {
        this.application = application;
    }

    /* @Override
     public HashMap<String, String> getAuthHeader() {
         HashMap<String, String> authHeader = new HashMap<>();
         ;
         authHeader.put(HEADER_CONTENT_TYPE, "text/plain");
         return authHeader;
     }
 */
    @Override
    public OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.readTimeout(REQUEST_TIME_OUT_S, TimeUnit.SECONDS);
        okHttpClientBuilder.writeTimeout(REQUEST_TIME_OUT_S, TimeUnit.SECONDS);
        okHttpClientBuilder.connectTimeout(REQUEST_TIME_OUT_S, TimeUnit.SECONDS);


        okHttpClientBuilder.addInterceptor(new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader(HEADER_AUTHORIZTION,
                                "")
                        .addHeader(HEADER_CONTENT_TYPE, JSON_CONTENT)
                        .build();
                return chain.proceed(request);
            }
        });

        return okHttpClientBuilder.build();
    }


    @Override
    public Application provideApplication() {
        return application;
    }

    @Override
    public Retrofit provideRetrofit() {
        if (singletonRetrofit == null) {
            synchronized (Retrofit.class) {
                singletonRetrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(provideOkHttpClient())
                        .baseUrl(BuildConfig.API_SERVER)
                        .build();

            }
        }
        return singletonRetrofit;
    }

    @Override
    public MatrimonyViewModelFactory provideViewModelFactory() {
        if (matrimonyViewModelFactory == null) {
            synchronized ((MatrimonyViewModelFactory.class)) {
                matrimonyViewModelFactory = new MatrimonyViewModelFactory(provideApplication());
            }
        }
        return matrimonyViewModelFactory;
    }


    @Override
    public MetrimonyRepository provideMetrimonyRepository() {
        return new MetrimonyRepositoryImpl(provideMetrimonyService(),provideApplication());
    }

    @Override
    public MetrimonyService provideMetrimonyService() {
        return provideRetrofit().create(MetrimonyService.class);
    }

}
