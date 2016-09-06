package com.aaruush16.webarch.aaruush16.Firebase;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aaruush16.webarch.aaruush16.MainActivity;
import com.aaruush16.webarch.aaruush16.R;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    Button signIn;
    private static final int RC_SIGN_IN = 100;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            fireIntent();
        }
        setContentView(R.layout.activity_login);
        signIn=(Button)findViewById(R.id.btnSignIn);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                if (auth.getCurrentUser() != null) {
                    fireIntent();
                } else {
                    signIn();
                }
            }
        });
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/xirod.ttf");  // adding custom font(xirod)
        title=(TextView)findViewById(R.id.title);
        title.setTypeface(custom_font);

    }

    private void fireIntent(){
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void signIn() {
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                        .setTheme(R.style.CustomFirebaseTheme)
                        .setLogo(R.drawable.aaruushlogo)
                        .setProviders(AuthUI.GOOGLE_PROVIDER,AuthUI.FACEBOOK_PROVIDER)
                        .build(),
                RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            handleSignInResponse(resultCode, data);
            return;
        }
        Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
    }

    @MainThread
    private void handleSignInResponse(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            fireIntent();
            return;
        }

        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "No Account Selected", Toast.LENGTH_SHORT).show();
            return;
        }
    }

}
