package al.infnet.thiagotorres_tp1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    EditText nomeForm;
    EditText telefoneForm;
    EditText emailForm;
    EditText cidadeForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nomeForm = findViewById(R.id.formNome);
        telefoneForm = findViewById(R.id.formTelefone);
        emailForm = findViewById(R.id.formEmail);
        cidadeForm = findViewById(R.id.formCidade);
    }

    public void saveContact(View view) {

        byte[] nomeDados;
        byte[] telefoneDados;
        byte[] emailDados;
        byte[] cidadeDados;
        byte[] newline;

        try {

            FileOutputStream fos;

            newline = ("\r\n").getBytes();
            nomeDados = nomeForm.getText().toString().getBytes();
            telefoneDados = telefoneForm.getText().toString().getBytes();
            emailDados = emailForm.getText().toString().getBytes();
            cidadeDados = cidadeForm.getText().toString().getBytes();

            fos = openFileOutput("ContactsList", Context.MODE_APPEND);

            fos.write(nomeDados);
            fos.write(newline);
            fos.write(telefoneDados);
            fos.write(newline);
            fos.write(emailDados);
            fos.write(newline);
            fos.write(cidadeDados);
            fos.write(newline);
            fos.flush();
            fos.close();

            clearForm();

            Toast.makeText(getApplicationContext(), "Contato salvo!", Toast.LENGTH_SHORT).show();

        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Erro ao salvar contato!", Toast.LENGTH_SHORT).show();
        }

    }

    public void clearForm() {
        nomeForm.setText("");
        telefoneForm.setText("");
        emailForm.setText("");
        cidadeForm.setText("");
    }

    public void clearForm(View view) {
        nomeForm.setText("");
        telefoneForm.setText("");
        emailForm.setText("");
        cidadeForm.setText("");
    }

    public void openContacts(View view) {
        Intent intent = new Intent(this, Contacts.class);
        startActivity(intent);
    }
}
