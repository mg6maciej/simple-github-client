package pl.mg6.simplegithubclient.ui;

import android.app.Activity;
import android.os.Bundle;

import pl.mg6.simplegithubclient.SimpleGithubClientApp;

public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SimpleGithubClientApp.inject(this, this);
    }
}
