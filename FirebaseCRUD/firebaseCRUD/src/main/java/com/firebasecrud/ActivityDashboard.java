package com.firebasecrud;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityDashboard extends ActivityBase {

    @BindView(R.id.fab)
    FloatingActionButton fab;
    // //
    @BindView(R.id.cardUserDatabase)
    CardView cardUserDatabase;

    @BindView(R.id.tvUserDatabase)
    TextView tvUserDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            ViewGroup.inflate(this, R.layout.activity_dashboard, llContainerSub);
            ButterKnife.bind(this);

            setFonts();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setFonts() {
        try {
            tvUserDatabase.setTypeface(App.getExoSemiBold());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.cardUserDatabase)
    void userDatabaseOnClick() {
        try {
            Intent iv = new Intent(ActivityDashboard.this, ActivityUserDatabaseList.class);
            App.myStartActivity(ActivityDashboard.this, iv);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.fab)
    void fabOnClick() {
        try {
            Intent iv = new Intent(ActivityDashboard.this, ActivityAbout.class);
            App.myStartActivity(ActivityDashboard.this, iv);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onBackPressed() {
        showExitDialog();
    }
}
