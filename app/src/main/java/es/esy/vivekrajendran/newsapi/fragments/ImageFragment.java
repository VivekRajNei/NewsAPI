package es.esy.vivekrajendran.newsapi.fragments;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import es.esy.vivekrajendran.newsapi.R;
import es.esy.vivekrajendran.newsapi.data.PrefContract;
import es.esy.vivekrajendran.newsapi.data.UserPref;
import es.esy.vivekrajendran.newsapi.model.ImageNews;
import es.esy.vivekrajendran.newsapi.util.ImagesRecyclerAdapter;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class ImageFragment extends Fragment {

    private ProgressBar progressBar;
    private RecyclerView mRecyclerView;
    private ImagesRecyclerAdapter imagesRecyclerAdapter;
    private ArrayList<ImageNews> imageNewsArrayList;
    private String url = "http://stacktips.com/?json=get_category_posts&slug=news&count=70";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_images, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = (ProgressBar) view.findViewById(R.id.pgbar_imagesfragment);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recy_imagesfragment);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(imagesRecyclerAdapter);
        getData(url);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null) return false;
        NetworkInfo.State networkState = networkInfo.getState();
        return (networkState == NetworkInfo.State.CONNECTED || networkState == NetworkInfo.State.CONNECTING);
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
        @Override
        protected void onPostExecute(Integer result) {
            setAdapter(result);
        }

    }

    public void getData(String url) {
        if (UserPref.getInstance(getContext()).isJStringAvailable()) {
            Log.i("TAG", "getData: Jstring available");
            parseResult(UserPref.getInstance(getContext()).getJString());
            setAdapter(1);
        } else if (isNetworkAvailable()) {
            new DownloadTask().execute(url);
            Log.i("TAG", "getData: Network available");
        } else {
            Log.i("TAG", "getData: Network unavailable");
        }
    }

    private void parseResult(final String result) {
        try {
            JSONObject response = new JSONObject(result);
            JSONArray posts = response.optJSONArray("posts");
            imageNewsArrayList = new ArrayList<>();
            for (int i = 0; i < posts.length(); i++) {
                JSONObject post = posts.optJSONObject(i);
                ImageNews item = new ImageNews();
                item.setTitle(post.optString("title"));
                item.setImageUrl(post.optString("thumbnail"));
                imageNewsArrayList.add(item);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setAdapter(int result) {
        progressBar.setVisibility(View.GONE);

        if (result == 1) {
            imagesRecyclerAdapter = new ImagesRecyclerAdapter(getContext(), imageNewsArrayList);
            mRecyclerView.setAdapter(imagesRecyclerAdapter);
        } else {
            Toast.makeText(getContext(), "Failed to fetch data!", Toast.LENGTH_SHORT).show();
        }
    }


}