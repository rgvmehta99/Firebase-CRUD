package com.firebasecrud;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


/**
 * Created by dhaval.mehta on 16-Jan-18.
 */

public class App extends Application {

    static Typeface tf_Exo_REG, tf_Exo_SM_BLD, tf_Exo_BLD;
    static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Typeface getExoReg() {
        tf_Exo_REG = Typeface.createFromAsset(mContext.getAssets(), "fonts/exo_2.ttf");
        return tf_Exo_REG;
    }


    public static Typeface getExoSemiBold() {
        tf_Exo_SM_BLD = Typeface.createFromAsset(mContext.getAssets(), "fonts/exo_2_semibold.ttf");
        return tf_Exo_SM_BLD;
    }


    public static Typeface getExoBold() {
        tf_Exo_BLD = Typeface.createFromAsset(mContext.getAssets(), "fonts/exo_2_bold.ttf");
        return tf_Exo_BLD;
    }


    public static void myStartActivity(Activity activity, Intent intent) {
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public static void myFinishActivityRefresh(Activity activity, Intent intent) {
        activity.finish();
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }


    public static void myFinishActivity(Activity activity) {
        activity.finish();
        activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }


    public static void showLog(String strMessage) {
        Log.v("==MSG==", "--strMsg--" + strMessage);
    }


    public static void showLogTAG(String strMessage) {
        Log.e("==TAG==", "--scr--" + strMessage);
    }


    public static void hideSoftKeyboard(Activity activity) {
        try {
            View view = activity.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
