package al.infnet.thiagotorres_tp1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

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

//    public void saveData(View view) {
//        ArrayList<String > contato = new ArrayList<>();
//        contato.add(nomeForm.getText().toString());
//        contato.add(telefoneForm.getText().toString());
//        contato.add(emailForm.getText().toString());
//        contato.add(cidadeForm.getText().toString());
//
//        FileOutputStream outputStream;
//        String filename = "contato";
//
//        try {
//            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
//            outputStream.write(contato.getBytes());
//            outputStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }

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
