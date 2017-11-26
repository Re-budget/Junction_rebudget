package nordea.junction.rebudget.preferences;

import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 * Shared Preferences model class.
 *
 * Created by Bel on 25/11/2017.
 */

public class LocalSharedPreferences {

    private SharedPreferences mSharedPreferences;

    @Inject
    public LocalSharedPreferences(SharedPreferences mSharedPreferences) {
            this.mSharedPreferences = mSharedPreferences;
        }

    public void putData(String key, int data) {
        mSharedPreferences.edit().putInt(key,data).apply();
    }

    public int getData(String key) {
        return mSharedPreferences.getInt(key,0);
    }
}
