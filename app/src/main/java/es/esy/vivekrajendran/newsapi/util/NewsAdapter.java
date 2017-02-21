package es.esy.vivekrajendran.newsapi.util;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import es.esy.vivekrajendran.newsapi.R;
import es.esy.vivekrajendran.newsapi.model.News;


public class NewsAdapter extends RecyclerView.Adapter {

    private ArrayList<News> mNewsArrayList;
    private Context context;

    public NewsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_news_heading, parent, false);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CursorLoader mCursorLoader = new CursorLoader(context);
        Cursor cursor = mCursorLoader.loadInBackground();
    }

    @Override
    public int getItemCount() {
        return mNewsArrayList.size();
    }

    private class NewsHolder extends RecyclerView.ViewHolder {

        public NewsHolder(View itemView) {
            super(itemView);
        }
    }

    public void exchangeCursor(Cursor cursor) {

    }
}
