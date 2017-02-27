package es.esy.vivekrajendran.newsapi.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserPref {

    private Context context;
    private SharedPreferences jsonSharedPreferences;
    private SharedPreferences dbSharedPrefernces;

    private UserPref(Context context) {
        this.context = context;
        dbSharedPrefernces = context.getSharedPreferences(PrefContract.DbTime.PREF_NAME, Context.MODE_PRIVATE);
        jsonSharedPreferences = context.getSharedPreferences(PrefContract.Json.PREF_NAME, Context.MODE_PRIVATE);
    }

    public static UserPref getInstance(@NonNull Context context) {
        return new UserPref(context);
    }

    public boolean isJStringAvailable() {
        //returns TRUE if JString is not null
        boolean availbilty = jsonSharedPreferences.getString(PrefContract.Json.JSTRING, null) != null;
        Log.i("TAG", "isJStringAvailable: " + availbilty);
        return availbilty;
    }

    public boolean isImageFetchable() {
        return (getCurrentTime() - getImage()) > 86400;
    }

    public boolean isVideoFetchable() {
        return (getCurrentTime() - getVideo()) > 86400;
    }

    public boolean isNewsFetchable() {
        return (getCurrentTime() - getNews()) > 86400;
    }

    public boolean isProvidersFetchable() {
        return (getCurrentTime() - getProviders()) > 86400;
    }

    private long getImage() {
        return jsonSharedPreferences.getLong(PrefContract.DbTime.IMAGE, getCurrentTime());
    }

    private long getVideo() {
        return jsonSharedPreferences.getLong(PrefContract.DbTime.VIDEO, getCurrentTime());
    }

    private long getNews() {
        return jsonSharedPreferences.getLong(PrefContract.DbTime.NEWS, getCurrentTime());
    }

    private long getProviders() {
        return jsonSharedPreferences.getLong(PrefContract.DbTime.PROVIDERS, getCurrentTime());
    }

    public void setImageTime() {
        SharedPreferences.Editor editor = dbSharedPrefernces.edit();
        editor.putLong(PrefContract.DbTime.IMAGE, getCurrentTime());
        editor.apply();
    }

    public void setNewsTime() {
        SharedPreferences.Editor editor = dbSharedPrefernces.edit();
        editor.putLong(PrefContract.DbTime.NEWS, getCurrentTime());
        editor.apply();
    }

    public void setProviderTime() {
        SharedPreferences.Editor editor = dbSharedPrefernces.edit();
        editor.putLong(PrefContract.DbTime.PROVIDERS, getCurrentTime());
        editor.apply();
    }

    public void setVideoTime() {
        SharedPreferences.Editor editor = dbSharedPrefernces.edit();
        editor.putLong(PrefContract.DbTime.VIDEO, getCurrentTime());
        editor.apply();
    }

    private long getCurrentTime() {
        return System.currentTimeMillis()/1000;
    }

    /**
     * Jstring portion
     * @return
     */

    public String getJString() {
        return jsonSharedPreferences.getString(PrefContract.Json.JSTRING, null);
    }

    public void setJString(String jString) {
        SharedPreferences.Editor editor = jsonSharedPreferences.edit();
        editor.putString(PrefContract.Json.JSTRING, jString);
        editor.apply();
    }
}