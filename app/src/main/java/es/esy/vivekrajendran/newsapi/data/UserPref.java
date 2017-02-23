package es.esy.vivekrajendran.newsapi.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

public class UserPref {

    private Context context;
    private SharedPreferences mSharedPreferences;

    private UserPref(Context context) {
        this.context = context;
        mSharedPreferences = context.getSharedPreferences(PrefContract.UserPref.PREF_NAME, Context.MODE_PRIVATE);
    }

    public static UserPref getInstance(@NonNull Context context) {
        return new UserPref(context);
    }

    public boolean isJStringAvailable() {
        //returns TRUE if JString is not null
        boolean availbilty = mSharedPreferences.getString(PrefContract.Json.JSTRING, null) != null;
        Log.i("TAG", "isJStringAvailable: " + availbilty);
        return availbilty;
    }

    public String getJString() {
        return mSharedPreferences.getString(PrefContract.Json.JSTRING, null);
    }

    public void setJString(String jString) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(PrefContract.Json.JSTRING, jString);
        editor.apply();
    }
}
