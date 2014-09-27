package pl.mg6.simplegithubclient.data;

import android.os.Parcel;

import hrisey.Parcelable;
import lombok.Value;
import lombok.experimental.Builder;

@Value
@Builder
@Parcelable
public class User implements android.os.Parcelable
{

    long id;
    String login;
    String avatarUrl;


    public String getLoginUppercase() {
        return login.toUpperCase();
    }
}
