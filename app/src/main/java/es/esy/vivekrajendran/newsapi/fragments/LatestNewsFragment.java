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
import es.esy.vivekrajendran.newsapi.data.NewsDBContract;
import es.esy.vivekrajendran.newsapi.util.NewsCursorAdapter;


public class LatestNewsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private final int LATEST_NEWS_LOADER = 452;
    private NewsCursorAdapter mNewsCursorAdapter;
    private Cursor cursor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_latest, container, false);
        ListView mListView = (ListView) view.findViewById(R.id.lv_frag_latest);
        mListView.setAdapter(mNewsCursorAdapter);
        return mListView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mNewsCursorAdapter = new NewsCursorAdapter(getContext(), cursor);
        getLoaderManager().initLoader(LATEST_NEWS_LOADER, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(
                getContext(),
                NewsDBContract.URI_NEWS,
                null,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.i("TAG", "onLoadFinished: Cursor count" + data.getCount());
        mNewsCursorAdapter.changeCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mNewsCursorAdapter.changeCursor(null);
    }
}
