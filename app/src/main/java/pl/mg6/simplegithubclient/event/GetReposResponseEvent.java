package pl.mg6.simplegithubclient.event;

import java.util.List;

import pl.mg6.simplegithubclient.data.Repo;

public final class GetReposResponseEvent {

    private final List<Repo> repos;

    public GetReposResponseEvent(List<Repo> repos) {
        this.repos = repos;
    }

    public List<Repo> getRepos() {
        return repos;
    }
}
