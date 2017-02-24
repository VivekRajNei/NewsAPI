package es.esy.vivekrajendran.newsapi.fragments;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import es.esy.vivekrajendran.newsapi.R;
import es.esy.vivekrajendran.newsapi.data.UserPref;
import es.esy.vivekrajendran.newsapi.model.ImageNews;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class ProviderFragment extends Fragment {

    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_providers, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private class DownloadTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Integer doInBackground(String... params) {

            Integer result = 0;
            HttpURLConnection urlConnection;
            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                int statusCode = urlConnection.getResponseCode();

                if (statusCode == 200) {
                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        response.append(line);
                    }
                    UserPref.getInstance(getContext()).setJString(response.toString());
                    parseResult(response.toString());
                    result = 1;
                } else {
                    result = 0;
                }
            } catch (Exception e) {
                Log.d("TAG", e.getLocalizedMessage());
            }
            return result;
        }

        private void parseResult(final String result) {
            try {
                JSONObject response = new JSONObject(result);
                JSONArray posts = response.optJSONArray("posts");
                for (int i = 0; i < posts.length(); i++) {
                    JSONObject post = posts.optJSONObject(i);
                    ImageNews item = new ImageNews();
                    item.setTitle(post.optString("title"));
                    item.setImageUrl(post.optString("thumbnail"));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null) return false;
        NetworkInfo.State networkState = networkInfo.getState();
        return (networkState == NetworkInfo.State.CONNECTED || networkState == NetworkInfo.State.CONNECTING);
    }
}