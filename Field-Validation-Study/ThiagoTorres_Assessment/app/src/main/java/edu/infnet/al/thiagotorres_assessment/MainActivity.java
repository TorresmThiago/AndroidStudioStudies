package edu.infnet.al.thiagotorres_assessment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Digits;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Max;
import com.mobsandgeeks.saripaar.annotation.Min;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Pattern;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Validator.ValidationListener {

    Validator validator;

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