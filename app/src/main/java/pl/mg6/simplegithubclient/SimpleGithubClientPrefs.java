package pl.mg6.simplegithubclient;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Collections;
import java.util.List;

public final class SimpleGithubClientPrefs {

    private final SharedPreferences prefs;
    private final Gson gson;

    public SimpleGithubClientPrefs(SharedPreferences prefs, Gson gson) {
        this.prefs = prefs;
        this.gson = gson;
    }

    public String getFanName() {
        return prefs.getString("fan_name", "mg6maciej");
    }

    public void setFanName(String fanName) {
        prefs.edit()
                .putString("fan_name", fanName)
                .apply();
    }

    public List<Long> getRepoIdsForReview() {
        String json = prefs.getString("repo_ids_for_review", null);
        if (json == null) {
            return Collections.emptyList();
        } else {
            return gson.fromJson(json, new TypeToken<List<Long>>() {
            }.getType());
        }
    }

    public void setRepoIdsForReview(List<Long> repoIdsForReview) {
        String json = gson.toJson(repoIdsForReview, new TypeToken<List<Long>>() {
        }.getType());
        prefs.edit()
                .putString("repo_ids_for_review", json)
                .apply();
    }
}
