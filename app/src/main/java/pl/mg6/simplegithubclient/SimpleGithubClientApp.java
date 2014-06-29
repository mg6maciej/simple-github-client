package pl.mg6.simplegithubclient;

import android.app.Application;
import android.content.Context;

import dagger.ObjectGraph;

public final class SimpleGithubClientApp extends Application {

    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = ObjectGraph.create(Modules.get(this));
    }

    public static void inject(Context context, Object root) {
        SimpleGithubClientApp app = (SimpleGithubClientApp) context.getApplicationContext();
        app.objectGraph.inject(root);
    }
}
