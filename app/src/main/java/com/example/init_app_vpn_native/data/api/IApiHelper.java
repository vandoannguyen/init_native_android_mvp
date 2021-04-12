package com.example.init_app_vpn_native.data.api;

import com.example.init_app_vpn_native.data.api.model.Repo;
import com.example.init_app_vpn_native.data.api.model.User;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface IApiHelper {
    @GET("/users/{user}")
    Observable<Object> getData(@Path("user") String user);

    @GET("/users")
    Observable<List<User>> getUsers();
}
