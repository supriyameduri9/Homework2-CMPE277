package com.example.cmpe277_pocketnews.api;

import com.example.cmpe277_pocketnews.Models.NewsList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("top-headlines")
    Call<NewsList> getNews(@Query("country") String Country, @Query("apikey") String apikey);

    @GET("everything")
    Call<NewsList> getNewsSearch(@Query("q") String keyword, @Query("apiKey") String apikey);
    }
