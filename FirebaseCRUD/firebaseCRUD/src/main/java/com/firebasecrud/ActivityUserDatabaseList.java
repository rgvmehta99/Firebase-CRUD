package com.firebasecrud;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebasecrud.swipereveallayout.SwipeRevealLayout;
import com.firebasecrud.swipereveallayout.ViewBinderHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dhaval.mehta on 16-Jan-18.
 */

public class ActivityUserDatabaseList extends ActivityBase {

    String TAG = "==ActivityUserDatabaseList==";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;

    @BindView(R.id.fabAdd)
    FloatingActionButton fabAdd;

    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.toolBar)
    Toolbar toolBar;

    // ------------- No Data tag -------------
    @BindView(R.id.llNoData)
    LinearLayout llNoData;

    @BindView(R.id.tvNoData)
    TextView tvNoData;
    //
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    //
    ArrayList<UserModel> arrayList = new ArrayList<>();
    RVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            ViewGroup.inflate(this, R.layout.activity_user_database_list, llContainerSub);
            ButterKnife.bind(this);
            App.showLogTAG(TAG);

            appBarLayout.setVisibility(View.GONE);
            initialFirebase();
            initialization();
            setFonts();
            readUserDatabase();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void initialFirebase() {
        try {
            mFirebaseInstance = FirebaseDatabase.getInstance();
            mFirebaseDatabase = mFirebaseInstance.getReference(Constants.USER_DB_TABLE); // table name
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void initialization() {
        try {
            //
            linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setNestedScrollingEnabled(false);

            //
            collapsingToolbarLayout.setTitle("User Database");
            collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBarCollapsed);
            collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.CollapsedAppBarExpand);

            collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(this, R.color.colorAccent));
            collapsingToolbarLayout.setStatusBarScrimColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

            collapsingToolbarLayout.setCollapsedTitleTypeface(App.getExoBold());
            collapsingToolbarLayout.setExpandedTitleTypeface(App.getExoSemiBold());

            fabAdd.setRippleColor(ContextCompat.getColor(this, R.color.clrTRWhite));
            fabAdd.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorAccent)));
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
            tvNoData.setTypeface(App.getExoSemiBold());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.fabAdd)
    void addUserForm() {
        try {
            Intent iv = new Intent(ActivityUserDatabaseList.this, ActivityUserDatabaseAddForm.class);
            App.myStartActivity(ActivityUserDatabaseList.this, iv);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    void readUserDatabase() {
        try {
            mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        UserModel model = postSnapshot.getValue(UserModel.class);
                        App.showLog(TAG + "Username - ID : " + model.id);

                        arrayList.add(new UserModel(
                                model.id,
                                model.name,
                                model.email,
                                model.mobile,
                                model.address,
                                model.city,
                                model.country,
                                model.dob
                        ));
                    }
                    // //
                    if (arrayList != null && arrayList.size() > 0) {
                        recyclerView.setVisibility(View.VISIBLE);
                        llNoData.setVisibility(View.GONE);

                        adapter = new RVAdapter(ActivityUserDatabaseList.this, arrayList);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    } else {
                        recyclerView.setVisibility(View.GONE);
                        llNoData.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    Log.w(TAG, "Failed to read value.", error.toException());
                    recyclerView.setVisibility(View.GONE);
                    llNoData.setVisibility(View.VISIBLE);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public class RVAdapter extends RecyclerView.Adapter<RVAdapter.VersionViewHolder> {
        ArrayList<UserModel> mArrayList;
        Context mContext;
        private final ViewBinderHelper binderHelper = new ViewBinderHelper();


        public RVAdapter(Context context, ArrayList<UserModel> arrayList) {
            mArrayList = arrayList;
            mContext = context;
            binderHelper.setOpenOnlyOne(true);
        }


        @Override
        public RVAdapter.VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_activity_user_database_item,
                    viewGroup, false);
            RVAdapter.VersionViewHolder viewHolder = new RVAdapter.VersionViewHolder(view);
            return viewHolder;
        }


        @Override
        public void onBindViewHolder(final RVAdapter.VersionViewHolder versionViewHolder, final int position) {
            try {
                if (mArrayList != null && mArrayList.size() > 0) {
                    final UserModel model = mArrayList.get(position);

                    if (model != null) {
                        binderHelper.bind(versionViewHolder.swipeRevealLayout, model.id);
                        //
                        if (model.name != null && model.name.length() > 0) {
                            versionViewHolder.tvName.setText(model.name);
                        }
                        //
                        if (model.email != null && model.email.length() > 0) {
                            versionViewHolder.tvEmail.setText(model.email);
                        }
                        //
                        if (model.mobile != null && model.mobile.length() > 0) {
                            versionViewHolder.tvMobile.setText(model.mobile);
                        }
                        //
                        if (model.dob != null && model.dob.length() > 0) {
                            versionViewHolder.tvDOB.setText(model.dob);
                        }
                    }

                    //
                    versionViewHolder.cardMain.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (model != null && model.name != null && model.id != null) {
                                Toast.makeText(ActivityUserDatabaseList.this, "Username : "
                                        + model.name + " & ID: " + model.id, Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(ActivityUserDatabaseList.this, "== Username & ID == NOT FOUND ==", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                    //
                    versionViewHolder.rlEdit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            versionViewHolder.swipeRevealLayout.close(true);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent iv = new Intent(ActivityUserDatabaseList.this, ActivityUserDatabaseEditForm.class);
                                    iv.putExtra(Constants.USER_FIREBASE_ID, model.id);
                                    iv.putExtra(Constants.USER_NAME, model.name);
                                    iv.putExtra(Constants.USER_EMAIL, model.email);
                                    iv.putExtra(Constants.USER_MOBILE_NO, model.mobile);
                                    iv.putExtra(Constants.USER_ADDRESS, model.address);
                                    iv.putExtra(Constants.USER_CITY, model.city);
                                    iv.putExtra(Constants.USER_COUNTRY, model.country);
                                    iv.putExtra(Constants.USER_DOB, model.dob);
                                    App.myStartActivity(ActivityUserDatabaseList.this, iv);
                                }
                            }, 400);
                        }
                    });

                    //
                    versionViewHolder.rlDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            versionViewHolder.swipeRevealLayout.close(true);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    showRemoveUserDialog(model.id, position);
                                }
                            }, 400);
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return mArrayList.size();
        }


        class VersionViewHolder extends RecyclerView.ViewHolder {

            TextView tvName, tvEmail, tvMobile, tvDOB;
            CardView cardMain;
            LinearLayout llMain;
            RelativeLayout rlEdit, rlDelete;
            SwipeRevealLayout swipeRevealLayout;


            public VersionViewHolder(View itemView) {
                super(itemView);

                tvName = (TextView) itemView.findViewById(R.id.tvName);
                tvEmail = (TextView) itemView.findViewById(R.id.tvEmail);
                tvMobile = (TextView) itemView.findViewById(R.id.tvMobile);
                tvDOB = (TextView) itemView.findViewById(R.id.tvDOB);
                cardMain = (CardView) itemView.findViewById(R.id.cardMain);
                llMain = (LinearLayout) itemView.findViewById(R.id.llMain);

                rlEdit = (RelativeLayout) itemView.findViewById(R.id.rlEdit);
                rlDelete = (RelativeLayout) itemView.findViewById(R.id.rlDelete);
                swipeRevealLayout = (SwipeRevealLayout) itemView.findViewById(R.id.swipeRevealLayout);
                /*
                * FONTS
                * */
                tvName.setTypeface(App.getExoSemiBold());
                tvEmail.setTypeface(App.getExoReg());
                tvMobile.setTypeface(App.getExoReg());
                tvDOB.setTypeface(App.getExoReg());
            }
        }


        public void showRemoveUserDialog(final String id, final int listPosition) {
            try {
                final Dialog dialog = new Dialog(ActivityUserDatabaseList.this);
                dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND); // for dialog shadow
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
                dialog.setContentView(R.layout.popup_exit);
                dialog.setCancelable(false);

                TextView tvExitMessage = (TextView) dialog.findViewById(R.id.tvMessage);
                TextView tvCancel = (TextView) dialog.findViewById(R.id.tvCancel);
                TextView tvOK = (TextView) dialog.findViewById(R.id.tvOk);

                String strAlertMessageExit = "Are you sure you want to delete this record ?";
                String strYes = "Delete";
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
                        arrayList.clear();

                        new Handler().postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                mFirebaseDatabase.child(id).removeValue(new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                        App.showLog("== User data is removed ==");
                                        Snackbar.make(fabAdd, "User deleted successfully.", Snackbar.LENGTH_LONG).show();
                                    }
                                });
                            }
                        }, 280);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onBackPressed() {
        Intent iv = new Intent(ActivityUserDatabaseList.this, ActivityDashboard.class);
        App.myFinishActivityRefresh(ActivityUserDatabaseList.this, iv);
    }
}
