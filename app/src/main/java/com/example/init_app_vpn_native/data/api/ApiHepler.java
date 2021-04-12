package com.example.init_app_vpn_native.data.api;

import com.example.init_app_vpn_native.data.api.model.Repo;
import com.example.init_app_vpn_native.data.api.model.User;
import com.example.init_app_vpn_native.data.api.provider.RetrofitClient;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Retrofit;

public class ApiHepler implements IApiHelper {
    Retrofit client;
    IApiHelper apiHelper;

    public ApiHepler() {
        client = RetrofitClient.getClient("https://api.github.com");
        apiHelper = client.create(IApiHelper.class);
    }

    @Override
    public Observable<Object> getData(String user) {
        return apiHelper.getData(user);
    }

    @Override
    public Observable<List<User>> getUsers() {
        return apiHelper.getUsers();
    }
}
