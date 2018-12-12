package edu.infnet.al.thiagotorres_assessment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.InterstitialAd;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Pattern;

import java.io.FileOutputStream;
import java.util.List;


public class MainActivity extends AppCompatActivity implements Validator.ValidationListener {

    private InterstitialAd mInterstitialAd;
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

        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

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

        mInterstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
                nome.setText("");
                loginEmail.setText("");
                senha.setText("");
                confirmarSenha.setText("");
                cpf.setText("");
            }
        });

    }

    @Override
    public void onValidationSucceeded() {
        writeToFile();
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

    public void writeToFile() {
        byte[] nomeToFile;
        byte[] loginEmailToFile;
        byte[] senhaToFile;
        byte[] cpfToFile;
        byte[] newline;

        try {

            FileOutputStream fos;

            newline = ("\r\n").getBytes();
            nomeToFile = nome.getText().toString().getBytes();
            loginEmailToFile = loginEmail.getText().toString().getBytes();
            senhaToFile = senha.getText().toString().getBytes();
            cpfToFile = cpf.getText().toString().getBytes();

            fos = openFileOutput("Cadastro", Context.MODE_APPEND);

            fos.write(nomeToFile);
            fos.write(newline);
            fos.write(loginEmailToFile);
            fos.write(newline);
            fos.write(senhaToFile);
            fos.write(newline);
            fos.write(cpfToFile);
            fos.flush();
            fos.close();

            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Toast.makeText(this, "O anúncio ainda não foi carregado!", Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Erro ao salvar contato!", Toast.LENGTH_SHORT).show();
        }
    }
}
