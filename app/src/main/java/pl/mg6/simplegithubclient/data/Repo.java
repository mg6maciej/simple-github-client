package pl.mg6.simplegithubclient.data;

import android.os.Parcel;

public final class Repo implements android.os.Parcelable {

    private final long id;
    private final String name;
    private final String description;
    private final int stargazersCount;
    private final int watchersCount;

    private Repo(long id, String name, String description, int stargazersCount, int watchersCount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.stargazersCount = stargazersCount;
        this.watchersCount = watchersCount;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getStargazersCount() {
        return stargazersCount;
    }

    public int getWatchersCount() {
        return watchersCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Repo)) {
            return false;
        }
        Repo repo = (Repo) o;
        return this.id == repo.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private long id;
        private String name;
        private String description;
        private int stargazersCount;
        private int watchersCount;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder stargazersCount(int stargazersCount) {
            this.stargazersCount = stargazersCount;
            return this;
        }

        public Builder watchersCount(int watchersCount) {
            this.watchersCount = watchersCount;
            return this;
        }

        public Repo build() {
            return new Repo(id, name, description, stargazersCount, watchersCount);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeInt(this.stargazersCount);
        dest.writeInt(this.watchersCount);
    }

    private Repo(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.description = in.readString();
        this.stargazersCount = in.readInt();
        this.watchersCount = in.readInt();
    }

    public static final Creator<Repo> CREATOR = new Creator<Repo>() {

        public Repo createFromParcel(Parcel source) {
            return new Repo(source);
        }

        public Repo[] newArray(int size) {
            return new Repo[size];
        }
    };
}
