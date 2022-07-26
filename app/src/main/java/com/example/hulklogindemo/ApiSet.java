package com.example.hulklogindemo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiSet {
    @GET("edgecourse/api/get_nonce/?controller=user&method=register")
    Call<ModelClass> getData();

    @FormUrlEncoded
    @POST("edgecourse/api/user/generate_auth_cookie/?")
    Call<ModelClassLogin> getLoginInformation(@Field("username")String username, @Field("password")String password);


}
