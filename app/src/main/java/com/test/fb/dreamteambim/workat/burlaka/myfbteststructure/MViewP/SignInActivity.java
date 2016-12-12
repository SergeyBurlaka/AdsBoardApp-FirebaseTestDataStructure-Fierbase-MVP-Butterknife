package com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.MViewP;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ui.ResultCodes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.R;

import java.util.Arrays;

public class SignInActivity extends AppCompatActivity {

    private static final String TAG = "AndroidBash";
    private static final int RC_SIGN_IN = 9001;

    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mFirebaseAuth;

    private Button mSignInButton;
    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // Initialize FirebaseAuth
        //You need to include google-services.json (downloaded from firebase console) file under the "app" folder of this project.
        mFirebaseAuth = FirebaseAuth.getInstance();
        firebaseLogin();
    }



    private void firebaseLogin() {
        if (mFirebaseAuth.getCurrentUser() != null) {
            // already signed in
            //Toast.makeText(SignInActivity.this, "already signed in",
                   // Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
            finish();

        } else {

            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setProviders(Arrays.asList(
                                    new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build()))
                            .build(),
                    RC_SIGN_IN);

            // not signed in
        }
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            // user is signed in!
           // Toast.makeText(SignInActivity.this, "user is signed in!",
                    //Toast.LENGTH_SHORT).show();

            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        // Sign in canceled
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(SignInActivity.this, "Authentication failed.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        // No network
        if (resultCode == ResultCodes.RESULT_NO_NETWORK) {
            Toast.makeText(SignInActivity.this, "No network",
                    Toast.LENGTH_SHORT).show();

            return;
        }
        //Toast.makeText(SignInActivity.this, "on A result",
               // Toast.LENGTH_SHORT).show();
    }

}