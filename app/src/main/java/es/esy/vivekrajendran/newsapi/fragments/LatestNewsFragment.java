package es.esy.vivekrajendran.newsapi.fragments;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import es.esy.vivekrajendran.newsapi.R;
import es.esy.vivekrajendran.newsapi.WebviewActivity;
import es.esy.vivekrajendran.newsapi.data.NewsContract;
import es.esy.vivekrajendran.newsapi.util.NewsCursorAdapter;

public class LatestNewsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int LATEST_NEWS_LOADER = 452;
    private NewsCursorAdapter newsCursorAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_latest, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView latestNewsListView = (ListView) view.findViewById(R.id.lv_frag_latest);
        View emptyView = view.findViewById(R.id.empty_view);

        latestNewsListView.setEmptyView(emptyView);
        newsCursorAdapter = new NewsCursorAdapter(getActivity(), null);
        latestNewsListView.setAdapter(newsCursorAdapter);

        latestNewsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Uri contentUri = ContentUris.withAppendedId(NewsContract.URI_BASE, position);
                Toast.makeText(getContext(), "Clicked item id: " + position + " uri : " + contentUri.toString(),
                        Toast.LENGTH_SHORT).show();
                /*Intent intent = new Intent(getContext(), WebviewActivity.class);
                intent.setData(contentUri);
                startActivity(intent);*/
            }
        });
        getLoaderManager().initLoader(LATEST_NEWS_LOADER, null, this);
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