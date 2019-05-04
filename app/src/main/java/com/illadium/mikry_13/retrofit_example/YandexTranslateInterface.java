package com.illadium.mikry_13.retrofit_example;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YandexTranslateInterface {
    final String key = "";

    @GET("/api/v1.5/tr.json/translate?key=" + key)
    Call<YaTranslate> translation(@Query("text") String text, @Query("lang") String translate_dir);
}