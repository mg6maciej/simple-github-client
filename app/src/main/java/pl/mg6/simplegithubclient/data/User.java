package pl.mg6.simplegithubclient.data;

import android.os.Parcel;

public final class User implements android.os.Parcelable {

    private final long id;
    private final String login;
    private final String avatarUrl;

    private User(long id, String login, String avatarUrl) {
        this.id = id;
        this.login = login;
        this.avatarUrl = avatarUrl;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getLoginUppercase() {
        return login.toUpperCase();
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private long id;
        private String login;
        private String avatarUrl;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder login(String login) {
            this.login = login;
            return this;
        }

        public Builder avatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
            return this;
        }

        public User build() {
            return new User(id, login, avatarUrl);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.login);
        dest.writeString(this.avatarUrl);
    }

    private User(Parcel in) {
        this.id = in.readLong();
        this.login = in.readString();
        this.avatarUrl = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {

        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
