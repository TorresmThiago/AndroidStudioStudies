package edu.infnet.al.thiagotorresassessment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

public class MainActivity extends AppCompatActivity {

    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
            new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    goToLoggedActivity();
                }

                @Override
                public void onCancel() {
                    Log.d("BBBBBBBBBBBBBBBB", String.valueOf(345));
                }

                @Override
                public void onError(FacebookException exception) {
                    Log.d("CCCCCCCCCCCC", String.valueOf(exception));
                }
            });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void goToLoginActivity(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void goToCadastrarActivity(View view) {
        Intent intent = new Intent(this, Cadastrar.class);
        startActivity(intent);
    }

    public void goToLoggedActivity() {
        Intent intent = new Intent(this, LoggedIn.class);
        startActivity(intent);
    }
}
