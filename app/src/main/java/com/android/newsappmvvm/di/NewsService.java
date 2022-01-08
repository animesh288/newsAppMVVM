package com.android.newsappmvvm.di;

import com.android.newsappmvvm.model.MainNews;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsService {

    @GET("top-headlines")
    Call<MainNews> getData(@Query("country")String country,
                           @Query("apiKey")String apiKey
    );
}
