package pl.mg6.simplegithubclient;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;
import pl.mg6.simplegithubclient.api.GithubApi;
import pl.mg6.simplegithubclient.api.GithubClient;
import pl.mg6.simplegithubclient.ui.DetailsActivity;
import pl.mg6.simplegithubclient.ui.MainActivity;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

@Module(
        injects = {
                MainActivity.class,
                DetailsActivity.class
        }
)
@SuppressWarnings("unused")
public final class SimpleGithubClientModule {

    private final Context context;

    public SimpleGithubClientModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public GithubClient provideClient(GithubApi api, EventBus eventBus) {
        return new GithubClient(api, eventBus);
    }

    @Provides
    @Singleton
    public GithubApi provideGithubApi(Gson gson) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://api.github.com")
                .setRequestInterceptor(createRequestInterceptor())
                .setConverter(new GsonConverter(gson))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        return restAdapter.create(GithubApi.class);
    }

    private RequestInterceptor createRequestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade requestFacade) {
                requestFacade.addHeader("Accept", "application/vnd.github.v3+json");
                requestFacade.addHeader("User-Agent", "https://github.com/mg6maciej/simple-github-client");
            }
        };
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    @Provides
    @Singleton
    public EventBus provideEventBus() {
        return EventBus.getDefault();
    }

    @Provides
    @Singleton
    public SimpleGithubClientPrefs providePrefs(SharedPreferences prefs, Gson gson) {
        return new SimpleGithubClientPrefs(prefs, gson);
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
