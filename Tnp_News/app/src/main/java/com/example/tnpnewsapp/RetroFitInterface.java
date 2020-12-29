package com.example.tnpnewsapp;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface RetroFitInterface {

    @GET()
    @Streaming
    Call<ResponseBody> downloadImage(@Url String fileUrl);
}

