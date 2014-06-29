package pl.mg6.simplegithubclient.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import pl.mg6.simplegithubclient.R;
import pl.mg6.simplegithubclient.SimpleGithubClientPrefs;
import pl.mg6.simplegithubclient.data.Repo;

public final class ReposAdapter extends BaseAdapter {

    private final LayoutInflater inflater;
    private final List<Repo> repos;
    private final SimpleGithubClientPrefs prefs;

    public ReposAdapter(Context context, List<Repo> repos, SimpleGithubClientPrefs prefs) {
        this.inflater = LayoutInflater.from(context);
        this.repos = repos;
        this.prefs = prefs;
    }

    @Override
    public int getCount() {
        return repos.size();
    }

    @Override
    public Repo getItem(int position) {
        return repos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return repos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.repo_item, parent, false);
        }
        TextView name = (TextView) convertView.findViewById(R.id.repo_item_name);
        TextView description = (TextView) convertView.findViewById(R.id.repo_item_description);
        View toReview = convertView.findViewById(R.id.repo_item_to_review);
        Repo repo = getItem(position);
        name.setText(repo.getName());
        description.setText(repo.getDescription());
        if (prefs.getRepoIdsForReview().contains(repo.getId())) {
            toReview.setBackgroundColor(0xFF00DEAD);
        } else {
            toReview.setBackgroundColor(0x00000000);
        }
        return convertView;
    }
}
