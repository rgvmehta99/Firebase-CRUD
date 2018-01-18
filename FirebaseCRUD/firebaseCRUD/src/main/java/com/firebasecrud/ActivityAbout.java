package com.firebasecrud;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dhaval.mehta on 17-Jan-18.
 */

public class ActivityAbout extends ActivityBase {

    String TAG = "=ActivityAbout==";

    @BindView(R.id.fabBack)
    FloatingActionButton fabBack;

    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.toolBar)
    Toolbar toolBar;

    @BindView(R.id.tvAboutText)
    TextView tvAboutText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            ViewGroup.inflate(this, R.layout.activity_about, llContainerSub);
            ButterKnife.bind(this);
            App.showLogTAG(TAG);

            appBarLayout.setVisibility(View.GONE);
            initialization();
            setFonts();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void initialization() {
        try {
            //
            collapsingToolbarLayout.setTitle("About");
            collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBarCollapsed);
            collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.CollapsedAppBarExpand);

            collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(this, R.color.colorAccent));
            collapsingToolbarLayout.setStatusBarScrimColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

            collapsingToolbarLayout.setCollapsedTitleTypeface(App.getExoBold());
            collapsingToolbarLayout.setExpandedTitleTypeface(App.getExoSemiBold());

            fabBack.setRippleColor(ContextCompat.getColor(this, R.color.clrTRWhite));
            fabBack.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorAccent)));
            //
            toolBar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setFonts() {
        try {
            tvAboutText.setTypeface(App.getExoReg());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.fabBack)
    void onBackClick() {
        try {
            onBackPressed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onBackPressed() {
        App.myFinishActivity(ActivityAbout.this);
    }
}
