package pl.mg6.simplegithubclient.event;

import java.util.List;

import pl.mg6.simplegithubclient.data.User;

public final class GetIdolsResponseEvent {

    private final List<User> idols;

    public GetIdolsResponseEvent(List<User> idols) {
        this.idols = idols;
    }

    public List<User> getIdols() {
        return idols;
    }
}
