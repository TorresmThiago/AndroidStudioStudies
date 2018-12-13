package edu.infnet.al.thiagotorresassessment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

public class Login extends AppCompatActivity implements Validator.ValidationListener{

    Validator validator;
    private FirebaseAuth mAuth;

    @NotEmpty
    EditText loginEmail;
    @NotEmpty
    EditText senha;

    Button logar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmail = findViewById(R.id.loginFragmentLogin);
        senha = findViewById(R.id.loginFragmentSenha);

        mAuth = FirebaseAuth.getInstance();

        validator = new Validator(this);
        validator.setValidationListener(this);

        logar = findViewById(R.id.loginUsuarioButton);
        logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.validate();
            }
        });
    }

    public void goToLoggedActivity() {
        Intent intent = new Intent(this, LoggedIn.class);
        startActivity(intent);
    }

    @Override
    public void onValidationSucceeded() {
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
