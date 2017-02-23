package es.esy.vivekrajendran.newsapi.data;

import android.content.ContentResolver;
import android.net.Uri;

public class NewsContract {

    public static final String DB_NAME = "News.db";
    public static final int DB_VERSION = 1;

    public static final String PATH_NEWS = "news";
    public static final String PATH_PROVIDER = "provider";

    public static final String CONTENT_AUTHORITY = "es.esy.vivekrajendran.newsapi";
    public static final String CONTENT_PROVIDER = "content://" + CONTENT_AUTHORITY;
    public static final Uri URI_BASE = Uri.parse(CONTENT_PROVIDER);

    private NewsContract() {}

    public static final class News {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(URI_BASE, PATH_NEWS);

        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of pets.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_NEWS;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single pet.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_NEWS + "/#";

        public static final String TABLE_NAME = "news";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_AUTHOR = "author";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_URL_TO_IMAGE = "urlToImage";
        public static final String COLUMN_URL = "url";
        public static final String COLUMN_PUBLISHED_AT = "publishedAt";
        public static final String COLUMN_FAV = "favourite";

        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT NOT NULL, " +
                COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
                COLUMN_AUTHOR + " TEXT NOT NULL, " +
                COLUMN_URL + " TEXT NOT NULL, " +
                COLUMN_URL_TO_IMAGE + " TEXT NOT NULL, " +
                COLUMN_PUBLISHED_AT + " TEXT NOT NULL ," +
                COLUMN_FAV + " INTEGER);";

        public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static final class Provider {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(URI_BASE, PATH_PROVIDER);

        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of pets.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PROVIDER;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single pet.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PROVIDER + "/#";
        public static final String TABLE_NAME = "provider";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_PROVIDER_ID = "providerId";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_URL = "url";
        public static final String COLUMN_CATEGORY = "category";
        public static final String COLUMN_URL_TO_IMAGE = "urlsToLogos";
        public static final String COLUMN_FAV = "favourite";

        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXITS " + TABLE_NAME + " (" +
                COLUMN_ID + " TEXT PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PROVIDER_ID + " TEXT NOT NULL, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_CATEGORY + " TEXT NOT NULL, " +
                COLUMN_URL + " TEXT NOT NULL, " +
                COLUMN_URL_TO_IMAGE + " TEXT NOT NULL, " +
                COLUMN_DESCRIPTION + " TEXT NOT NULL ," +
                COLUMN_FAV + " INTEGER);";

        public static final String DROP_TABLE = "DROP TABLE IF EXITS " + TABLE_NAME;
    }
}
