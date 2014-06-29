package pl.mg6.simplegithubclient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.mg6.simplegithubclient.api.GithubApi;
import pl.mg6.simplegithubclient.api.MockGithubApi;

@Module(
        library = true,
        overrides = true
)
public final class MockModule {

    @Provides
    @Singleton
    public GithubApi provideMockApi() {
        return new MockGithubApi();
    }
}
