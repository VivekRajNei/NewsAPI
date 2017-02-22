package es.esy.vivekrajendran.newsapi.util;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import es.esy.vivekrajendran.newsapi.R;
import es.esy.vivekrajendran.newsapi.data.NewsDBContract;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {


    private Context context;
    private Cursor cursor;

    public NewsAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
        /*CursorLoader mCursorLoader = new CursorLoader(
                context,
                NewsDBContract.URI_NEWS,
                null,
                null,
                null,
                null);
        cursor = mCursorLoader.loadInBackground();*/
    }


    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_news_heading, parent, false);
        return new NewsAdapter.NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsHolder holder, int position) {
        cursor.move(position);
        holder.headline.setText(cursor.getString(
                cursor.getColumnIndexOrThrow(NewsDBContract.News.COLUMN_TITLE)));
        holder.newsProviderName.setText(
                cursor.getColumnIndexOrThrow(NewsDBContract.News.COLUMN_AUTHOR));
        holder.newsPublished.setText(
                cursor.getColumnIndexOrThrow(NewsDBContract.News.COLUMN_PUBLISHED_AT));

        /*holder.headline.setText("Headline");
        holder.newsImage.setImageResource(R.drawable.com_facebook_auth_dialog_background);
        holder.newsPublished.setText("Today");
        holder.newsProviderImage.setImageResource(R.drawable.common_google_signin_btn_icon_dark_focused);
        holder.newsProviderName.setText("SourceForge");*/
    }


    @Override
    public int getItemCount() {
        if (cursor == null) {
            Log.i("TAG", "getItemCount: " + 0);
            return 0;
        }
        Log.i("TAG", "getItemCount: " + cursor.getCount());
        return cursor.getCount();
    }

    public class NewsHolder extends RecyclerView.ViewHolder {
        public ImageView newsImage;
        public ImageView newsProviderImage;
        public TextView headline;
        public TextView newsProviderName;
        public TextView newsPublished;
        public ImageButton addToFav;
        public ImageButton share;
        public ImageButton optionsMenu;

        NewsHolder(View itemView) {
            super(itemView);
            newsImage = (ImageView) itemView.findViewById(R.id.img_item_news_main);
            newsProviderImage = (ImageView) itemView.findViewById(R.id.img_item_news_provider);
            headline = (TextView) itemView.findViewById(R.id.tv_item_news_headline);
            newsProviderName = (TextView) itemView.findViewById(R.id.tv_item_news_providername);
            newsPublished = (TextView) itemView.findViewById(R.id.tv_item_news_timepublished);
            addToFav = (ImageButton) itemView.findViewById(R.id.imgbtn_item_news_addtofav);
            share = (ImageButton) itemView.findViewById(R.id.imgbtn_item_news_share);
            optionsMenu = (ImageButton) itemView.findViewById(R.id.imgbtn_item_news_optionsmenu);
        }
    }

}
