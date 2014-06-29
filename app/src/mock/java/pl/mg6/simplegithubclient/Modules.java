package pl.mg6.simplegithubclient;

import android.content.Context;

public final class Modules {

    public static Object[] get(Context context) {
        return new Object[]{
                new SimpleGithubClientModule(context),
                new MockModule()
        };
    }
}
