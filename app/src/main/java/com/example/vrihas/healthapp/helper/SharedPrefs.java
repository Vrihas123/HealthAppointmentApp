package com.example.vrihas.healthapp.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Meghal on 6/9/2016.
 */
public class SharedPrefs {

    // Shared preferences file name
    private static final String PREF_NAME = "AndroidHiveLogin";
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final String KEY_IS_REGISTERED = "isRegistered";
    private static final String KEY_IS_DATA_ADDED = "isDataAdded";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_FCM = "fcm";
    private static final String KEY_ACCESS_TOKEN = "access_token";
    private static final int KEY_VERSION=1;
    // LogCat tag
    private static String TAG = "Shared Preference";
    // Shared Preferences
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;

    public static int getKeyVersion() {
        return KEY_VERSION;
    }

    public SharedPrefs(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn) {

        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        // commit changes
        editor.commit();
        Log.d(TAG, "User login session modified!");
    }
    public void setRegister(boolean isRegistered) {

        editor.putBoolean(KEY_IS_REGISTERED, isRegistered);
        // commit changes
        editor.commit();
        Log.d(TAG, "User login session modified!");
    }
    public void setDoctorData(boolean isDoctorDataAdded){
        editor.putBoolean(KEY_IS_DATA_ADDED, isDoctorDataAdded);
        editor.commit();
    }
    public boolean isDocInfoAdded(){
        return pref.getBoolean(KEY_IS_DATA_ADDED, false);
    }
    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }
    public boolean isRegistered(){
        return pref.getBoolean(KEY_IS_REGISTERED,false);
    }

    public String getUsername() {

        return pref.getString(KEY_USERNAME, "USER NAME");
    }


    public void setFCM(String fcm) {
        editor.putString(KEY_FCM, fcm);
        editor.commit();
    }

    public String getFcm() {
        return pref.getString(KEY_FCM, null);
    }

    public void setUsername(String username) {

        editor.putString(KEY_USERNAME, username);
        editor.commit();


    }

    public void setEmailId(String emailId) {

        editor.putString(KEY_EMAIL, emailId);
        editor.commit();

    }

    public String getUserId() {

        return pref.getString(KEY_USER_ID, "Not Available");

    }

    public void setUserId(String userId) {

        editor.putString(KEY_USER_ID, userId);
        editor.commit();

    }

    public void setAccessToken(String accessToken) {
        editor.putString(KEY_ACCESS_TOKEN, accessToken);
        editor.commit();
    }

    public String getAccessToken() {

        return pref.getString(KEY_ACCESS_TOKEN, null);
    }

    public String getEmail() {

        return pref.getString(KEY_EMAIL, "Your Email Address");
    }

}
