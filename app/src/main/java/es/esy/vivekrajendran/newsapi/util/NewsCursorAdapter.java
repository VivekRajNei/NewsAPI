package es.esy.vivekrajendran.newsapi.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.ShareCompat;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import es.esy.vivekrajendran.newsapi.R;
import es.esy.vivekrajendran.newsapi.WebviewActivity;
import es.esy.vivekrajendran.newsapi.data.NewsContract;

public class NewsCursorAdapter extends CursorAdapter {

    private Activity activity;

    public NewsCursorAdapter(Activity activity, Cursor c) {
        super(activity.getApplicationContext(), c, 0);
        this.activity = activity;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_news_heading, parent, true);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        ImageView newsImage = (ImageView) view.findViewById(R.id.img_item_news_main);
        ImageView newsProviderImage = (ImageView) view.findViewById(R.id.img_item_news_provider);
        TextView headline = (TextView) view.findViewById(R.id.tv_item_news_headline);
        TextView newsProviderName = (TextView) view.findViewById(R.id.tv_item_news_providername);
        TextView newsPublished = (TextView) view.findViewById(R.id.tv_item_news_timepublished);
        final ImageView addToFav = (ImageView) view.findViewById(R.id.img_item_news_addtofav);
        ImageView share = (ImageView) view.findViewById(R.id.img_item_news_share);
        final ImageView optionsMenu = (ImageView) view.findViewById(R.id.img_item_news_optionsmenu);

        int columnNewsImage = cursor.getColumnIndexOrThrow(NewsContract.News.COLUMN_URL_TO_IMAGE);
        int columnHeadline = cursor.getColumnIndexOrThrow(NewsContract.News.COLUMN_TITLE);
        int columnProviderName = cursor.getColumnIndexOrThrow(NewsContract.News.COLUMN_AUTHOR);
        final int columnNewsURL = cursor.getColumnIndexOrThrow(NewsContract.News.COLUMN_URL);
        int columnPublished = cursor.getColumnIndexOrThrow(NewsContract.News.COLUMN_URL);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebviewActivity.class);
                intent.putExtra("url", columnNewsURL);
                context.startActivity(intent);
            }
        });
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
        newsPublished.setText(cursor.getString(columnPublished));
        addToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToFav.setImageResource(R.drawable.ic_turned_in_black_24dp);
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareCompat.IntentBuilder
                        .from(activity)
                        .setType("text/plain")
                        .setChooserTitle("Open using")
                        .startChooser();
            }
        });
    }

    private void showPopupMenu(View view) {
        PopupMenu popup = new PopupMenu(activity, view);
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
                    Toast.makeText(activity, "Hide", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.menu_overflow_fewer:
                    Toast.makeText(activity, "Fewer", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.menu_overflow_goto_provider:
                    Toast.makeText(activity, "Go to provider", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.menu_overflow_hide_goto_top:
                    Toast.makeText(activity, "Go to top", Toast.LENGTH_SHORT).show();
                    break;
                default:
            }
            return false;
        }
    }
}
