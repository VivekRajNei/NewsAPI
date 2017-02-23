package es.esy.vivekrajendran.newsapi.util;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import es.esy.vivekrajendran.newsapi.R;
import es.esy.vivekrajendran.newsapi.data.NewsContract;

public class NewsCursorAdapter extends CursorAdapter {

    private Context context;
    public NewsCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
        this.context = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_news_heading, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ImageView newsImage = (ImageView) view.findViewById(R.id.img_item_news_main);
        ImageView newsProviderImage = (ImageView) view.findViewById(R.id.img_item_news_provider);
        TextView headline = (TextView) view.findViewById(R.id.tv_item_news_headline);
        TextView newsProviderName = (TextView) view.findViewById(R.id.tv_item_news_providername);
        TextView newsPublished = (TextView) view.findViewById(R.id.tv_item_news_timepublished);
        ImageButton addToFav = (ImageButton) view.findViewById(R.id.imgbtn_item_news_addtofav);
        ImageButton share = (ImageButton) view.findViewById(R.id.imgbtn_item_news_share);
        final ImageButton optionsMenu = (ImageButton) view.findViewById(R.id.imgbtn_item_news_optionsmenu);

        int columnNewsImage = cursor.getColumnIndexOrThrow(NewsContract.News.COLUMN_URL_TO_IMAGE);
        int columnHeadline = cursor.getColumnIndexOrThrow(NewsContract.News.COLUMN_TITLE);
        int columnProviderName = cursor.getColumnIndexOrThrow(NewsContract.News.COLUMN_AUTHOR);
        
        Glide.with(context)
                .load(cursor.getString(columnNewsImage))
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(newsImage);
        headline.setText(cursor.getString(columnHeadline));
        newsProviderName.setText(cursor.getString(columnProviderName));
        optionsMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(optionsMenu);
            }
        });
    }

    private void showPopupMenu(View view) {
        PopupMenu popup = new PopupMenu(context, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_overflow, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    private class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.menu_overflow_hide:
                    Toast.makeText(context, "Hide", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.menu_overflow_fewer:
                    Toast.makeText(context, "Fewer", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.menu_overflow_goto_provider:
                    Toast.makeText(context, "Go to provider", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.menu_overflow_hide_goto_top:
                    Toast.makeText(context, "Go to top", Toast.LENGTH_SHORT).show();
                    break;
                default:
            }
            return false;
        }
    }
}
