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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Pattern;

import java.util.List;

import bolts.Task;

public class Cadastrar extends AppCompatActivity implements Validator.ValidationListener{

    Validator validator;
    private FirebaseAuth mAuth;

    @Pattern(regex = "^[a-zA-Z0-9]{4,10}$", message = "Nome inválido. Min. 6, Max. 10 char.")
    EditText nome;

    @Email(message = "Email inválido")
    EditText loginEmail;

    @Password(message = "Senha inválida. Min. 6 char.")
    EditText senha;

    @ConfirmPassword(message = "Senhas imcompatíveis")
    EditText confirmarSenha;

    @Pattern(regex = "^[0-9]{11}$" , message = "CPF Inválido")
    EditText cpf;

    Button cadastrar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acivity_cadastrar);

        nome = findViewById(R.id.loginFragmentLogin);
        loginEmail = findViewById(R.id.loginFragmentSenha);
        senha = findViewById(R.id.senhaEditText);
        confirmarSenha = findViewById(R.id.confirmarSenhaEditText);
        cpf = findViewById(R.id.cpfEditText);
        cadastrar = findViewById(R.id.cadastrarUsuarioButton);

        validator = new Validator(this);
        validator.setValidationListener(this);

        mAuth = FirebaseAuth.getInstance();

    }

    public void startValidation(View view) {
        validator.validate();
    }

    @Override
    public void onValidationSucceeded() {
        mAuth.createUserWithEmailAndPassword(loginEmail.getText().toString(), senha.getText().toString())
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull com.google.android.gms.tasks.Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        goToLoginActivity();
                    } else {
                        senha.setText("");
                        confirmarSenha.setText("");
                        Toast.makeText(Cadastrar.this, "Falha ao cadastrar usuário", Toast.LENGTH_SHORT).show();
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

    public void goToLoginActivity() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
