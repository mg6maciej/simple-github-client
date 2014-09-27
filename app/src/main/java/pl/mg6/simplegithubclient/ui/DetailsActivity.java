package pl.mg6.simplegithubclient.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import hugo.weaving.DebugLog;
import pl.mg6.simplegithubclient.R;
import pl.mg6.simplegithubclient.SimpleGithubClientPrefs;
import pl.mg6.simplegithubclient.api.GithubClient;
import pl.mg6.simplegithubclient.data.Repo;
import pl.mg6.simplegithubclient.data.User;
import pl.mg6.simplegithubclient.event.GetReposResponseEvent;
import pl.mg6.simplegithubclient.ui.adapter.ReposAdapter;

public final class DetailsActivity extends BaseActivity {

    private static final String EXTRA_IDOL = "idol";
    private static final String KEY_REPOS = "repos";

    @Inject
    GithubClient client;
    @Inject
    EventBus eventBus;
    @Inject
    SimpleGithubClientPrefs prefs;

    private User idol;
    private ArrayList<Repo> repos;

    private ListView reposView;
    private ReposAdapter adapter;
    private TextView emptyView;

    @Override
    @DebugLog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);
        this.idol = getIntent().getParcelableExtra(EXTRA_IDOL);
        if (savedInstanceState != null) {
            this.repos = savedInstanceState.getParcelableArrayList(KEY_REPOS);
        }
        eventBus.register(this);
        initReposView();
        if (this.repos == null) {
            loadRepos();
        } else {
            updateReposView();
        }
    }

    private void loadRepos() {
        client.getRepos(idol);
        adapter = null;
        reposView.setAdapter(null);
        emptyView.setText("Loading...");
    }

    @SuppressWarnings("unused")
    @DebugLog
    public void onEvent(GetReposResponseEvent event) {
        this.repos = new ArrayList<Repo>(event.getRepos());
        updateReposView();
    }

    private void initReposView() {
        reposView = (ListView) findViewById(R.id.details_repos_list);
        emptyView = (TextView) findViewById(R.id.details_empty_view);
        reposView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                swapRepoForReview(id);
            }
        });
        reposView.setEmptyView(emptyView);
    }

    private void swapRepoForReview(long id) {
        List<Long> repoIdsForReview = prefs.getRepoIdsForReview();
        List<Long> newRepoIdsForReview = new ArrayList<Long>(repoIdsForReview);
        if (newRepoIdsForReview.contains(id)) {
            newRepoIdsForReview.remove(id);
        } else {
            newRepoIdsForReview.add(id);
        }
        prefs.setRepoIdsForReview(newRepoIdsForReview);
        adapter.notifyDataSetChanged();
    }

    private void updateReposView() {
        adapter = new ReposAdapter(this, repos, prefs);
        reposView.setAdapter(adapter);
        TextView emptyView = (TextView) findViewById(R.id.details_empty_view);
        emptyView.setText("Empty...");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(KEY_REPOS, repos);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        eventBus.unregister(this);
    }

    public static IntentBuilder intent(Context context) {
        return new IntentBuilder(context);
    }

    public static final class IntentBuilder {

        private final Context context;
        private User idol;

        IntentBuilder(Context context) {
            this.context = context;
        }

        public IntentBuilder idol(User idol) {
            this.idol = idol;
            return this;
        }

        public void start() {
            if (idol == null) {
                throw new IllegalStateException("idol is null");
            }
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra(EXTRA_IDOL, idol);
            context.startActivity(intent);
        }
    }
}
