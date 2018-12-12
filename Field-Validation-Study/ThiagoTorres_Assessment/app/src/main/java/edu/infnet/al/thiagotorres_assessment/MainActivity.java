package edu.infnet.al.thiagotorres_assessment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Validator.ValidationListener {

    Validator validator;

    @NotEmpty
    EditText nome;

    @NotEmpty
    EditText loginEmail;

    @NotEmpty
    EditText senha;

    @NotEmpty
    EditText confirmarSenha;

    @NotEmpty
    EditText cpf;

    Button cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = findViewById(R.id.nomeEditText);
        loginEmail = findViewById(R.id.loginEditText);
        senha = findViewById(R.id.senhaEditText);
        confirmarSenha = findViewById(R.id.confirmarSenhaEditText);
        cpf = findViewById(R.id.cpfEditText);
        cadastrar = findViewById(R.id.cadastrarUsuarioButton);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.validate();
            }
        });

        validator = new Validator(this);
        validator.setValidationListener(this);

    }

    @Override
    public void onValidationSucceeded() {
        Toast.makeText(this, "Usuário cadastrado!", Toast.LENGTH_SHORT).show();
    }

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
