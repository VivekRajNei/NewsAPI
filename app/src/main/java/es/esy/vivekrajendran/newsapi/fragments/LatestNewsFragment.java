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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import es.esy.vivekrajendran.newsapi.R;
import es.esy.vivekrajendran.newsapi.data.NewsDBContract;
import es.esy.vivekrajendran.newsapi.util.MyResourceCursorAdapter;

public class LatestNewsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private final int LATEST_NEWS_LOADER = 452;
    private MyResourceCursorAdapter mNewsCursorAdapter;
    private Cursor cursor;
    private ListView mListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_latest, container, false);
        mListView = (ListView) view.findViewById(R.id.lv_frag_latest);

        //initListOnClickListener();
        return mListView;
    }

    private void initListOnClickListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "Clicked item id: " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
        Log.i("TAG", "onLoadFinished: Cursor count: " + data.getCount());
        //mNewsCursorAdapter = new MyResourceCursorAdapter(getContext(), R.layout.item_news_heading, cursor);
        //mListView.setAdapter(mNewsCursorAdapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        //mNewsCursorAdapter.changeCursor(null);
    }
}
