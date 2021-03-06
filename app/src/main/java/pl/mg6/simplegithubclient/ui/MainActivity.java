package pl.mg6.simplegithubclient.ui;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import pl.mg6.simplegithubclient.R;
import pl.mg6.simplegithubclient.SimpleGithubClientPrefs;
import pl.mg6.simplegithubclient.api.GithubClient;
import pl.mg6.simplegithubclient.data.User;
import pl.mg6.simplegithubclient.event.GetIdolsResponseEvent;
import pl.mg6.simplegithubclient.ui.adapter.IdolsAdapter;

public final class MainActivity extends BaseActivity {

    private static final String KEY_IDOLS = "idols";

    @Inject
    GithubClient client;
    @Inject
    EventBus eventBus;
    @Inject
    SimpleGithubClientPrefs prefs;

    private ArrayList<User> idols;

    private ListView idolsView;
    private TextView emptyView;
    private EditText fanEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState != null) {
            this.idols = savedInstanceState.getParcelableArrayList(KEY_IDOLS);
        }
        eventBus.register(this);
        initIdolsView();
        initFanEdit();
        if (this.idols == null) {
            loadIdols();
        } else {
            updateIdolsView();
        }
    }

    private void loadIdols() {
        User fan = User.builder().login(fanEdit.getText().toString()).build();
        client.getIdols(fan);
        idolsView.setAdapter(null);
        emptyView.setText("Loading...");
    }

    @SuppressWarnings("unused")
    public void onEvent(GetIdolsResponseEvent event) {
        this.idols = new ArrayList<User>(event.getIdols());
        updateIdolsView();
    }

    private void initIdolsView() {
        idolsView = (ListView) findViewById(R.id.main_idols_list);
        emptyView = (TextView) findViewById(R.id.main_empty_view);
        idolsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onIdolClick(position);
            }
        });
        idolsView.setEmptyView(emptyView);
    }

    private void initFanEdit() {
        fanEdit = (EditText) findViewById(R.id.main_fan_edit);
        fanEdit.setText(prefs.getFanName());
        fanEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (fanEdit.length() > 0) {
                    String fanName = fanEdit.getText().toString();
                    prefs.setFanName(fanName);
                    loadIdols();
                }
                return true;
            }
        });
    }

    private void onIdolClick(int position) {
        User idol = idols.get(position);
        DetailsActivity.intent(this)
                .idol(idol)
                .start();
    }

    private void updateIdolsView() {
        idolsView.setAdapter(new IdolsAdapter(this, idols));
        emptyView.setText("Empty...");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(KEY_IDOLS, this.idols);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        eventBus.unregister(this);
    }
}
