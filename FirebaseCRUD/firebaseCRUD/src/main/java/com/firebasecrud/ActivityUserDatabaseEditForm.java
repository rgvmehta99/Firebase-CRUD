package com.firebasecrud;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dhaval.mehta on 17-Jan-18.
 */

public class ActivityUserDatabaseEditForm extends ActivityBase {

    String TAG = "==ActivityUserDatabaseEditForm==";

    @BindView(R.id.edtName)
    EditText edtName;

    @BindView(R.id.edtEmail)
    EditText edtEmail;

    @BindView(R.id.edtMobile)
    EditText edtMobile;

    @BindView(R.id.edtAddress)
    EditText edtAddress;

    @BindView(R.id.edtCity)
    EditText edtCity;

    @BindView(R.id.edtCountry)
    EditText edtCountry;

    @BindView(R.id.edtDOB)
    EditText edtDOB;

    @BindView(R.id.fabDone)
    FloatingActionButton fabDone;
    // // ---------------
    @BindView(R.id.tilayoutName)
    TextInputLayout tilayoutName;

    @BindView(R.id.tilayoutEmail)
    TextInputLayout tilayoutEmail;

    @BindView(R.id.tilayoutMobile)
    TextInputLayout tilayoutMobile;

    @BindView(R.id.tilayoutAddress)
    TextInputLayout tilayoutAddress;

    @BindView(R.id.tilayoutCity)
    TextInputLayout tilayoutCity;

    @BindView(R.id.tilayoutCountry)
    TextInputLayout tilayoutCountry;

    @BindView(R.id.tilayoutDOB)
    TextInputLayout tilayoutDOB;

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    //
    String userId = "", userName = "", userEmail = "", userMobileNo = "",
            userAddress = "", userCity = "", userCountry = "", userDOB = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            ViewGroup.inflate(this, R.layout.activity_user_database_list_edit_form, llContainerSub);
            ButterKnife.bind(this);
            App.showLogTAG(TAG);

            ivBaseNavIcon.setVisibility(View.VISIBLE);
            tvTitle.setText("Edit User Database");

            getIntentData();
            initialFirebase();
            setIntentData();
            setFonts();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void getIntentData() {
        try {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                //
                if (bundle.getString(Constants.USER_FIREBASE_ID) != null &&
                        bundle.getString(Constants.USER_FIREBASE_ID).length() > 0) {
                    userId = bundle.getString(Constants.USER_FIREBASE_ID);
                    App.showLog(TAG + " ==userId== " + userId);
                }
                //
                if (bundle.getString(Constants.USER_NAME) != null &&
                        bundle.getString(Constants.USER_NAME).length() > 0) {
                    userName = bundle.getString(Constants.USER_NAME);
                    App.showLog(TAG + " ==userName== " + userName);
                }
                //
                if (bundle.getString(Constants.USER_EMAIL) != null &&
                        bundle.getString(Constants.USER_EMAIL).length() > 0) {
                    userEmail = bundle.getString(Constants.USER_EMAIL);
                    App.showLog(TAG + " ==userEmail== " + userEmail);
                }
                //
                if (bundle.getString(Constants.USER_MOBILE_NO) != null &&
                        bundle.getString(Constants.USER_MOBILE_NO).length() > 0) {
                    userMobileNo = bundle.getString(Constants.USER_MOBILE_NO);
                    App.showLog(TAG + " ==userMobileNo== " + userMobileNo);
                }
                //
                if (bundle.getString(Constants.USER_ADDRESS) != null &&
                        bundle.getString(Constants.USER_ADDRESS).length() > 0) {
                    userAddress = bundle.getString(Constants.USER_ADDRESS);
                    App.showLog(TAG + " ==userAddress== " + userAddress);
                }
                //
                if (bundle.getString(Constants.USER_CITY) != null &&
                        bundle.getString(Constants.USER_CITY).length() > 0) {
                    userCity = bundle.getString(Constants.USER_CITY);
                    App.showLog(TAG + " ==userCity== " + userCity);
                }
                //
                if (bundle.getString(Constants.USER_COUNTRY) != null &&
                        bundle.getString(Constants.USER_COUNTRY).length() > 0) {
                    userCountry = bundle.getString(Constants.USER_COUNTRY);
                    App.showLog(TAG + " ==userCountry== " + userCountry);
                }
                //
                if (bundle.getString(Constants.USER_DOB) != null &&
                        bundle.getString(Constants.USER_DOB).length() > 0) {
                    userDOB = bundle.getString(Constants.USER_DOB);
                    App.showLog(TAG + " ==userDOB== " + userDOB);
                }
            }
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


    private void setIntentData() {
        try {
            edtName.setText(userName);
            edtEmail.setText(userEmail);
            edtMobile.setText(userMobileNo);
            edtAddress.setText(userAddress);
            edtCity.setText(userCity);
            edtCountry.setText(userCountry);
            edtDOB.setText(userDOB);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setFonts() {
        try {
            edtName.setTypeface(App.getExoReg());
            edtEmail.setTypeface(App.getExoReg());
            edtMobile.setTypeface(App.getExoReg());
            edtAddress.setTypeface(App.getExoReg());
            edtCity.setTypeface(App.getExoReg());
            edtCountry.setTypeface(App.getExoReg());
            edtDOB.setTypeface(App.getExoReg());
            // //
            tilayoutName.setTypeface(App.getExoSemiBold());
            tilayoutEmail.setTypeface(App.getExoSemiBold());
            tilayoutMobile.setTypeface(App.getExoSemiBold());
            tilayoutAddress.setTypeface(App.getExoSemiBold());
            tilayoutCity.setTypeface(App.getExoSemiBold());
            tilayoutCountry.setTypeface(App.getExoSemiBold());
            tilayoutDOB.setTypeface(App.getExoSemiBold());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.fabDone)
    void fabDoneClick() {
        try {

            App.hideSoftKeyboard(ActivityUserDatabaseEditForm.this);

            String nm = edtName.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();
            String mobile = edtMobile.getText().toString().trim();
            String address = edtAddress.getText().toString().trim();
            String city = edtCity.getText().toString().trim();
            String country = edtCountry.getText().toString().trim();
            String dob = edtDOB.getText().toString().trim();

            Validator(nm, email, mobile, address, city, country, dob);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    void Validator(String nm, String email, String mobile, String address, String city, String country, String dob) {
        try {
            if (nm == null || nm.length() == 0) {
                Snackbar.make(fabDone, "Please enter your name", Snackbar.LENGTH_LONG).show();
            } else if (email == null || email.length() == 0) {
                Snackbar.make(fabDone, "Please enter your email address", Snackbar.LENGTH_LONG).show();
            } else if (mobile == null || mobile.length() == 0) {
                Snackbar.make(fabDone, "Please enter your mobile number", Snackbar.LENGTH_LONG).show();
            } else if (address == null || address.length() == 0) {
                Snackbar.make(fabDone, "Please enter your address", Snackbar.LENGTH_LONG).show();
            } else if (city == null || city.length() == 0) {
                Snackbar.make(fabDone, "Please enter your city", Snackbar.LENGTH_LONG).show();
            } else if (country == null || country.length() == 0) {
                Snackbar.make(fabDone, "Please enter your country", Snackbar.LENGTH_LONG).show();
            } else if (dob == null || dob.length() == 0) {
                Snackbar.make(fabDone, "Please enter your date of birth", Snackbar.LENGTH_LONG).show();
            } else {
                addUserDatabase(nm, email, mobile, address, city, country, dob);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void addUserDatabase(String nm, String email, String mobile, String address, String city,
                                 String country, String dob) {
        try {
            UserModel model = new UserModel(userId, nm, email, mobile, address, city, country, dob);
            mFirebaseDatabase.child(userId).setValue(model);

            addUserDatabaseDataChangeListener();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void addUserDatabaseDataChangeListener() {
        try {
            mFirebaseDatabase.child(userId).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    UserModel model = dataSnapshot.getValue(UserModel.class);

                    if (model == null) {
                        App.showLog("== User data is null! ==");
                        return;
                    }
                    App.showLog("== User data is changed!" + model.name + ", " + model.email);
                    Snackbar.make(fabDone, "User updated successfully.", Snackbar.LENGTH_LONG).show();
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    App.showLog("== Failed to read user ==" + error.toException());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onBackPressed() {
        Intent iv = new Intent(ActivityUserDatabaseEditForm.this, ActivityUserDatabaseList.class);
        App.myFinishActivityRefresh(ActivityUserDatabaseEditForm.this, iv);
    }

}
