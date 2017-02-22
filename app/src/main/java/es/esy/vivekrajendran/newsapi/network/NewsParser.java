package es.esy.vivekrajendran.newsapi.network;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import es.esy.vivekrajendran.newsapi.data.NewsDBContract;

public class NewsParser {

    private final Context context;
    public static final String TAG = "TAG";


    private NewsParser(Context context) {
        this.context = context;
    }

    public static NewsParser getInstance(Context context) {
        return new NewsParser(context);
    }

    public Boolean resolveJSON(String json) {
        try {
            JSONObject rootElement = new JSONObject(json);
            //if (!("ok".equals(rootElement.getString("status")))) {   }
            JSONArray sources = rootElement.getJSONArray("articles");
            JSONObject itemObject;

            Log.i(TAG, "resolveJSON: " + sources.length());
            for (int i = 0; i < sources.length(); i++) {
                itemObject = sources.getJSONObject(i);
                ContentValues contentValues = new ContentValues();
                contentValues.put(NewsDBContract.News.COLUMN_AUTHOR, itemObject.getString("author"));
                contentValues.put(NewsDBContract.News.COLUMN_TITLE, itemObject.getString("title"));
                contentValues.put(NewsDBContract.News.COLUMN_DESCRIPTION, itemObject.getString("description"));
                contentValues.put(NewsDBContract.News.COLUMN_URL, itemObject.getString("url"));
                contentValues.put(NewsDBContract.News.COLUMN_URL_TO_IMAGE, itemObject.getString("urlToImage"));
                contentValues.put(NewsDBContract.News.COLUMN_PUBLISHED_AT, itemObject.getString("publishedAt"));
                storeOnDB(NewsDBContract.URI_NEWS, contentValues);
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            Log.i(TAG, "resolveJSON: " + e.getMessage());
            return false;
        }
    }


    private void storeOnDB(Uri mUri, ContentValues contentValues) {
        ContentResolver mContentResolver = context.getContentResolver();
        mContentResolver.insert(mUri, contentValues);
    }
}
