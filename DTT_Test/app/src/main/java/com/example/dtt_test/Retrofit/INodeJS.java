package com.example.dtt_test.Retrofit;

import com.example.dtt_test.Entity.Data;


import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface INodeJS {
    @Headers("Access-Key: 98bww4ezuzfePCYFxJEWyszbUXc7dxRx")
    @GET("/api/house")
    Call<List<Data>> getHousesList();

}
