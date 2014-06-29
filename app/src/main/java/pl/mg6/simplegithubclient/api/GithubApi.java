package pl.mg6.simplegithubclient.api;

import java.util.List;

import pl.mg6.simplegithubclient.data.Repo;
import pl.mg6.simplegithubclient.data.User;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface GithubApi {

    @GET("/users/{user}/following")
    void getIdols(@Path("user") String user, Callback<List<User>> callback);

    @GET("/users/{user}/repos")
    void getRepos(@Path("user") String user, Callback<List<Repo>> callback);
}
