package pl.mg6.simplegithubclient.data;

import android.os.Parcel
import android.os.Parcelable
import groovy.transform.CompileStatic
import groovy.transform.Immutable
import groovy.transform.builder.Builder

@CompileStatic
@Immutable
final class User implements Parcelable {

    long id
    String login
    String avatarUrl

    String getLoginUppercase() {
        login.toUpperCase()
    }

    @Override
    int describeContents() {
        0
    }

    @Override
    void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.login);
        dest.writeString(this.avatarUrl);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public User createFromParcel(Parcel source) {
            new User(id: source.readLong(),
                    login: source.readString(),
                    avatarUrl: source.readString())
        }

        public Object[] newArray(int size) {
            new User[size]
        }
    };
}
