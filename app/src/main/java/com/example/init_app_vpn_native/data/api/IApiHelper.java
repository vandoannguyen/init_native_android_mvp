package com.example.init_app_vpn_native.data.api;

import com.example.init_app_vpn_native.data.api.model.Repo;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface IApiHelper {
    @GET("/users/{user}/repos")
    Observable<List<Repo>> getData(@Path("user") String user);
}
