package es.esy.vivekrajendran.newsapi.util;


import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.ResourceCursorAdapter;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import es.esy.vivekrajendran.newsapi.R;

public class MyResourceCursorAdapter extends ResourceCursorAdapter {

    public MyResourceCursorAdapter(Context context, int layout, Cursor c) {
        super(context, layout, c, 0);
        Log.i("TAG", "MyResourceCursorAdapter: " + c.getCount());
    }

    @Override
    public void bindView(View itemView, Context context, Cursor cursor) {
        ImageView newsImage = (ImageView) itemView.findViewById(R.id.img_item_news_main);
        ImageView newsProviderImage = (ImageView) itemView.findViewById(R.id.img_item_news_provider);
        TextView headline = (TextView) itemView.findViewById(R.id.tv_item_news_headline);
        TextView newsProviderName = (TextView) itemView.findViewById(R.id.tv_item_news_providername);
        TextView newsPublished = (TextView) itemView.findViewById(R.id.tv_item_news_timepublished);
        ImageButton addToFav = (ImageButton) itemView.findViewById(R.id.imgbtn_item_news_addtofav);
        ImageButton share = (ImageButton) itemView.findViewById(R.id.imgbtn_item_news_share);
        ImageButton optionsMenu = (ImageButton) itemView.findViewById(R.id.imgbtn_item_news_optionsmenu);
        Log.i("TAG", "bindView: " + cursor.getCount());
    }

    @Override
    public int getCount() {
        int count = getCursor().getCount();
        Log.i("TAG", "getCount: " + count);
        return count;
    }
}
