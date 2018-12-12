package edu.infnet.al.thiagotorres_assessment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.mobsandgeeks.saripaar.annotation.NotEmpty;

public class MainActivity extends AppCompatActivity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
