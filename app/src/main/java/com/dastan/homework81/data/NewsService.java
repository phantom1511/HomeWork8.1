package com.dastan.homework81.data;

import com.dastan.homework81.data.entity.News;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.dastan.homework81.data.ApiEndpoints.NEWS;

public interface NewsService {
    @GET(NEWS)
    Call<News> currentNews(@Query("q") String country, @Query("apiKey") String key);
}
