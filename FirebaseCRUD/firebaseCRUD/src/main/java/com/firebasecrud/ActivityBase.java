package com.firebasecrud;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by dhaval.mehta on 16-Jan-18.
 */

public class ActivityBase extends FragmentActivity {

    String TAG = "==ActivityBase==";

    AppBarLayout appBarLayout;
    LinearLayout llContainerSub;
    ImageView ivBaseNavIcon;
    TextView tvTitle;
    //
    private FirebaseDatabase mFirebaseInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_base);
            App.showLogTAG(TAG);

            initialFirebase();
            initialization();
            setFonts();
            setClickEvents();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void initialFirebase() {
        try {
            mFirebaseInstance = FirebaseDatabase.getInstance();
            mFirebaseInstance.getReference(Constants.USER_NAME_TITLE_KEY).setValue(Constants.USER_NAME_TITLE); // app_title/database name
            mFirebaseInstance.getReference(Constants.USER_NAME_TITLE_KEY).addValueEventListener(new ValueEventListener() { // databse name title key
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    App.showLog(TAG + "==App title updated==");

                    String appTitle = dataSnapshot.getValue(String.class);
                    App.showLog(TAG + "==appTitle==onDataChange==" + appTitle);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    App.showLog(TAG + "==onCancelled==Failed to read app title value.==" + error.toException());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void initialization() {
        try {
            appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
            llContainerSub = (LinearLayout) findViewById(R.id.llContainerSub);
            ivBaseNavIcon = (ImageView) findViewById(R.id.ivBaseNavIcon);
            tvTitle = (TextView) findViewById(R.id.tvTitle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setFonts() {
        try {
            tvTitle.setTypeface(App.getExoBold());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setClickEvents() {
        try {
            ivBaseNavIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void showExitDialog() {
        try {
            final Dialog dialog = new Dialog(ActivityBase.this);
            dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND); // for dialog shadow
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
            dialog.setContentView(R.layout.popup_exit);

            // set values for custom dialog components - text, image and button
            TextView tvExitMessage = (TextView) dialog.findViewById(R.id.tvMessage);
            TextView tvCancel = (TextView) dialog.findViewById(R.id.tvCancel);
            TextView tvOK = (TextView) dialog.findViewById(R.id.tvOk);


            String strAlertMessageExit = "Are you sure you want to exit ?";
            String strYes = "Exit";
            String strNo = "Cancel";

            tvExitMessage.setText(strAlertMessageExit);
            tvCancel.setText(strNo);
            tvOK.setText(strYes);

            tvExitMessage.setTypeface(App.getExoSemiBold());
            tvCancel.setTypeface(App.getExoReg());
            tvOK.setTypeface(App.getExoReg());

            dialog.show();

            tvCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            dialog.dismiss();
                        }
                    }, 280);
                }
            });

            tvOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    dialog.dismiss();

                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            finishAffinity();
                            onBackPressed();
                            return;
                        }
                    }, 280);

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    } // url = https://github.com/rgvmehta99/Firebase-CRUD.git


    @Override
    public void onBackPressed() {
        App.myFinishActivity(ActivityBase.this);
    }
}
