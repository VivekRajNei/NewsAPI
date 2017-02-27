package es.esy.vivekrajendran.newsapi.data;

/**
 * Created by user on 20/2/17.
 */

public class PrefContract {

    public static final class UserPref {
        public static final String PREF_NAME = "User Preferences";
        public static final String USER_NAME = "username";
        public static final String USER_EMAIL = "email";
        public static final String USER_LOGGED_IN = "isLogged";
        public static final String USER_THEME = "userTheme";
    }

    public final class Json {
        public static final String PREF_NAME = "Json";
        public static final String JSTRING = "jString";
    }

    public static final class DbTime {
        public static final String PREF_NAME = "DbTime";
        public static final String NEWS = "news";
        public static final String PROVIDERS = "providers";
        public static final String IMAGE = "image";
        public static final String VIDEO = "video";
    }
}