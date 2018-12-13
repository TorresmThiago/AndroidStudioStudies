package edu.infnet.al.thiagotorresassessment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Password;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;

    EditText loginEmail;
    EditText senha;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmail = findViewById(R.id.loginFragmentLogin);
        senha = findViewById(R.id.loginFragmentSenha);

        mAuth = FirebaseAuth.getInstance();

    }

    public void goToLoggedActivity() {
        Intent intent = new Intent(this, LoggedIn.class);
        startActivity(intent);
    }

    public void login(View view) {
        mAuth.signInWithEmailAndPassword(loginEmail.getText().toString(), senha.getText().toString())
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        goToLoggedActivity();
                    } else {
                        Toast.makeText(Login.this, "Email ou senha inválidos", Toast.LENGTH_SHORT).show();
                    }
               }
        });
    }

}
