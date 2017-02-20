package es.esy.vivekrajendran.newsapi.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Created by user on 20/2/17.
 */

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

}
