package pl.mg6.simplegithubclient.event;

import java.util.List;

import lombok.Value;
import pl.mg6.simplegithubclient.data.User;

@Value
public final class GetIdolsResponseEvent {

    List<User> idols;


}
