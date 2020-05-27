package com.example.android.singnin.Storge;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.android.singnin.Model.UserLogin;

    public class SharedPrefManager {

        private static final String SHARED_PREF_NAME = "my_shared_preff";

        private static SharedPrefManager mInstance;
        private Context mCtx;

        private SharedPrefManager(Context mCtx) {
            this.mCtx = mCtx;
        }


        public static synchronized SharedPrefManager getInstance(Context mCtx) {
            if (mInstance == null) {
                mInstance = new SharedPrefManager(mCtx);
            }
            return mInstance;
        }


        public void saveUser(UserLogin user) {

            SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putInt("id", user.getId());
            editor.putString("email", user.getEmail());
            editor.putString("name", user.getName());

            editor.apply();

        }

        public boolean isLoggedIn() {
            SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            return sharedPreferences.getInt("id", -1) != -1;
        }

        public UserLogin getUser() {
            SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            return new UserLogin(
                    sharedPreferences.getInt("id", -1),
                    sharedPreferences.getString("email", null),
                    sharedPreferences.getString("name", null)
            );
        }

        public void clear() {
            SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
        }

    }