package es.esy.vivekrajendran.newsapi.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import es.esy.vivekrajendran.newsapi.R;
import es.esy.vivekrajendran.newsapi.data.NewsContract;
import es.esy.vivekrajendran.newsapi.data.UserPref;
import es.esy.vivekrajendran.newsapi.network.NewsAsync;
import es.esy.vivekrajendran.newsapi.util.NetworkChecker;
import es.esy.vivekrajendran.newsapi.util.NewsCursorAdapter;

public class LatestNewsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int LATEST_NEWS_LOADER = 452;
    private NewsCursorAdapter newsCursorAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try {
            return inflater.inflate(R.layout.fragment_latest, container, false);
        } catch (Exception e){
            Log.i("TAG", "onCreateView: LatestNewsFragment "+ e.getMessage());
            return null;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {
            ListView latestNewsListView = (ListView) view.findViewById(R.id.lv_frag_latest);
            View emptyView = view.findViewById(R.id.empty_view);

            String url = "https://newsapi.org/v1/articles?source=the-next-web&sortBy=latest&apiKey=a65e2431ef9141ab93e78509b14554d0";
            getData(url);
            latestNewsListView.setEmptyView(emptyView);
            newsCursorAdapter = new NewsCursorAdapter(getActivity(), null);
            latestNewsListView.setAdapter(newsCursorAdapter);
            getLoaderManager().initLoader(LATEST_NEWS_LOADER, null, this);
        } catch (Exception e) {
            Log.i("TAG", "onViewCreated: LatestNewsFragment "+ e.getMessage());
        }
    }

    public void getData(String url) {
        if (NetworkChecker.getInstance(getContext()).isNetworkAvailable()
                && UserPref.getInstance(getContext()).isImageFetchable()) {
            new NewsAsync(getContext())
                    .execute(url, NewsAsync.NEWS);
        } else {
            Log.i("TAG", "LatestNewsFragment getData: Network unavailable: "
                    + "Fetchable " + UserPref.getInstance(getContext()).isNewsFetchable()
                    + ": NetworkStatus " + NetworkChecker.getInstance(getContext()).isNetworkAvailable());
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(
                getContext(),
                NewsContract.News.CONTENT_URI,
                null,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        newsCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        newsCursorAdapter.swapCursor(null);
    }
}
