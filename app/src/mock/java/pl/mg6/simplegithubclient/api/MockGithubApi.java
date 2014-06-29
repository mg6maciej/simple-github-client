package pl.mg6.simplegithubclient.api;

import android.os.Handler;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import pl.mg6.simplegithubclient.data.Repo;
import pl.mg6.simplegithubclient.data.User;
import retrofit.Callback;

public final class MockGithubApi implements GithubApi {

    private final Handler handler = new Handler();

    @Override
    public void getIdols(final String user, final Callback<List<User>> callback) {
        callLater(new Runnable() {
            @Override
            public void run() {
                List<User> idols;
                if ("mg6maciej".equals(user)) {
                    idols = Arrays.asList(
                            buildMojomboUser(),
                            buildJakeWhartonUser()
                    );
                } else {
                    idols = Arrays.asList(
                            buildHelloKittyUser()
                    );
                }
                callback.success(idols, null);
            }
        });
    }

    private User buildMojomboUser() {
        return User.builder()
                .login("mojombo")
                .avatarUrl("https://avatars.githubusercontent.com/u/1?")
                .build();
    }

    private User buildJakeWhartonUser() {
        return User.builder()
                .login("JakeWharton")
                .avatarUrl("https://avatars.githubusercontent.com/u/66577?")
                .build();
    }

    private User buildHelloKittyUser() {
        return User.builder()
                .login("HelloKitty")
                .avatarUrl("http://4.bp.blogspot.com/-LgUHkGHIrUQ/UmArRGThQ1I/AAAAAAAAAS8/VToYbTFJFgI/s1600/Hello-Kitty-hello-kitty-19285460-849-757.gif")
                .build();
    }

    @Override
    public void getRepos(final String user, final Callback<List<Repo>> callback) {
        callLater(new Runnable() {
            @Override
            public void run() {
                List<Repo> repos;
                if ("mojombo".equals(user)) {
                    repos = Arrays.asList(
                            buildChronicRepo(),
                            buildGodRepo(),
                            buildSemverRepo(),
                            buildClippyRepo(),
                            buildMojomboGithubComRepo(),
                            buildMasteringGitBasicsRepo()
                    );
                } else if ("JakeWharton".equals(user)) {
                    repos = Arrays.asList(
                            buildU2020Repo(),
                            buildActionBarSherlockRepo()
                    );
                } else if ("HelloKitty".equals(user)) {
                    repos = Collections.emptyList();
                } else {
                    throw new IllegalStateException();
                }
                callback.success(repos, null);
            }
        });
    }

    private Repo buildChronicRepo() {
        return Repo.builder()
                .id(1)
                .name("chronic")
                .description("Chronic is a pure Ruby natural language date parser.")
                .build();
    }

    private Repo buildGodRepo() {
        return Repo.builder()
                .id(2)
                .name("god")
                .description("Ruby process monitor")
                .build();
    }

    private Repo buildSemverRepo() {
        return Repo.builder()
                .id(3)
                .name("semver")
                .description("Semantic Versioning Specification")
                .build();
    }

    private Repo buildClippyRepo() {
        return Repo.builder()
                .id(4)
                .name("clippy")
                .description("Clippy is a very simple Flash widget that makes it possible to place arbitrary text onto the client's clipboard.")
                .build();
    }

    private Repo buildMojomboGithubComRepo() {
        return Repo.builder()
                .id(5)
                .name("mojombo.github.com")
                .description("Old location of Jekyll source for tom.preston-werner.com")
                .build();
    }

    private Repo buildMasteringGitBasicsRepo() {
        return Repo.builder()
                .id(6)
                .name("mastering-git-basics")
                .build();
    }

    private Repo buildU2020Repo() {
        return Repo.builder()
                .id(7)
                .name("u2020")
                .description("A sample Android app which showcases advanced usage of Dagger among other open source libraries.")
                .stargazersCount(819)
                .watchersCount(81)
                .build();
    }

    private Repo buildActionBarSherlockRepo() {
        return Repo.builder()
                .id(8)
                .name("ActionBarSherlock")
                .description("Action bar implementation which uses the native action bar on Android 4.0+ and a custom implementation on pre-4.0 through a single API and theme.")
                .stargazersCount(5873)
                .watchersCount(767)
                .build();
    }

    private void callLater(Runnable runnable) {
        handler.postDelayed(runnable, 2000);
    }
}
