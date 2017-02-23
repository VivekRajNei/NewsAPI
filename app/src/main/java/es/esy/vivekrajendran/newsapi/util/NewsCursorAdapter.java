package es.esy.vivekrajendran.newsapi.util;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import es.esy.vivekrajendran.newsapi.R;
import es.esy.vivekrajendran.newsapi.data.NewsDBContract;

public class NewsCursorAdapter extends CursorAdapter {

    private Cursor cursor;

    public NewsCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
        this.cursor = c;
    }

    private ImageView newsImage;
    private ImageView newsProviderImage;
    private TextView headline;
    private TextView newsProviderName;
    private TextView newsPublished;
    private ImageButton addToFav;
    private ImageButton share;
    private ImageButton optionsMenu;

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news_heading, parent, false);
        newsImage = (ImageView) itemView.findViewById(R.id.img_item_news_main);
        newsProviderImage = (ImageView) itemView.findViewById(R.id.img_item_news_provider);
        headline = (TextView) itemView.findViewById(R.id.tv_item_news_headline);
        newsProviderName = (TextView) itemView.findViewById(R.id.tv_item_news_providername);
        newsPublished = (TextView) itemView.findViewById(R.id.tv_item_news_timepublished);
        addToFav = (ImageButton) itemView.findViewById(R.id.imgbtn_item_news_addtofav);
        share = (ImageButton) itemView.findViewById(R.id.imgbtn_item_news_share);
        optionsMenu = (ImageButton) itemView.findViewById(R.id.imgbtn_item_news_optionsmenu);
        return itemView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        if (view != null) {

        }
    }



    @Override
    public int getCount() {
        if (cursor == null) {
            Log.i("TAG", "getCount: 0");
            return 0;
        }
        Log.i("TAG", "getCount: " + cursor.getCount());
        return cursor.getCount();
    }
}
