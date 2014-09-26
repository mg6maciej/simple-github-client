package pl.mg6.simplegithubclient.data;

import hrisey.Parcelable;
import lombok.Value;
import lombok.experimental.Builder;

@Parcelable
@Value
public final class Repo implements android.os.Parcelable {

    long id;
    String name;
    String description;
    int stargazersCount;
    int watchersCount;
}
