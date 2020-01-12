package com.dastan.homework81.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.dastan.homework81.BuildConfig.BASE_URL;

public class RetrofitBuilder {
    private static NewsService newsService;

    public static NewsService getNewsService() {
        if (newsService == null) {
            newsService = buildRetrofit();
        }
        return newsService;
    }

    private static NewsService buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsService.class);
    }
}
