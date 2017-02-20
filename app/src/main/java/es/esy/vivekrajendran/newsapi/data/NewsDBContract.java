package es.esy.vivekrajendran.newsapi.data;

/**
 * Created by user on 20/2/17.
 */

public class NewsDBContract {

    public static final String DB_NAME = "News.db";
    public static final int DB_VERSION = 1;

    private NewsDBContract() {}

    public static final class News {
        public static final String TABLE_NAME = "News";
        public static final String COLUMN_ID = "_ID";
        //public static final String COLUMN_
    }
}
