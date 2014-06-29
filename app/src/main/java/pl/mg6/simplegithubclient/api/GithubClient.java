package pl.mg6.simplegithubclient.api;

import java.util.List;

import de.greenrobot.event.EventBus;
import pl.mg6.simplegithubclient.data.Repo;
import pl.mg6.simplegithubclient.data.User;
import pl.mg6.simplegithubclient.event.GetIdolsResponseEvent;
import pl.mg6.simplegithubclient.event.GetReposResponseEvent;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public final class GithubClient {

    private final GithubApi api;
    private final EventBus eventBus;

    public GithubClient(GithubApi api, EventBus eventBus) {
        this.api = api;
        this.eventBus = eventBus;
    }

    public void getIdols(User user) {
        api.getIdols(user.getLogin(), new Callback<List<User>>() {
            @Override
            public void success(List<User> idols, Response response) {
                eventBus.post(new GetIdolsResponseEvent(idols));
            }

            @Override
            public void failure(RetrofitError retrofitError) {
            }
        });
    }

    public void getRepos(User user) {
        api.getRepos(user.getLogin(), new Callback<List<Repo>>() {
            @Override
            public void success(List<Repo> repos, Response response) {
                eventBus.post(new GetReposResponseEvent(repos));
            }

            @Override
            public void failure(RetrofitError retrofitError) {

            }
        });
    }
}
