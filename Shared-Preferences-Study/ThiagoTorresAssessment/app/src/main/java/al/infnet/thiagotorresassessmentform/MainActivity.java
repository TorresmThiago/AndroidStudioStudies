package al.infnet.thiagotorresassessmentform;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private final String NAME_KEY = "nome";
    private final String ENDERECO_KEY = "endereco";
    private final String ESTADO_KEY = "estado";
    private final String CIDADE_KEY = "cidade";
    private final String TELEFONE_KEY = "telefone";
    private final String EMAIL_KEY = "email";

    private String mName;
    private String mEndereco;
    private String mEstado;
    private String mCidade;
    private String mTelefone;
    private String mEmail;

    private SharedPreferences mPreferences;
    private String sharedPrefFile = "al.infnet.thiagotorresassessmentform.sharedprefs";

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor preferencesEditor = mPreferences.edit();

        TextView nameView = findViewById(R.id.formNome);
        String name = nameView.getText().toString();
        preferencesEditor.putString(NAME_KEY, name);

        TextView enderecoView = findViewById(R.id.formEndereco);
        String endereco = enderecoView.getText().toString();
        preferencesEditor.putString(ENDERECO_KEY, endereco);

        TextView estadoView = findViewById(R.id.formEstado);
        String estado = estadoView.getText().toString();
        preferencesEditor.putString(ESTADO_KEY, estado);

        TextView cidadeView = findViewById(R.id.formCidade);
        String cidade = cidadeView.getText().toString();
        preferencesEditor.putString(CIDADE_KEY, cidade);

        TextView telefoneView = findViewById(R.id.formTelefone);
        String telefone = telefoneView.getText().toString();
        preferencesEditor.putString(TELEFONE_KEY, telefone);

        TextView emailView = findViewById(R.id.formEmail);
        String email = emailView.getText().toString();
        preferencesEditor.putString(EMAIL_KEY, email);

        preferencesEditor.apply();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        //Retrieve the saved data here
        mName = mPreferences.getString(NAME_KEY, "");
        mEndereco = mPreferences.getString(ENDERECO_KEY, "");
        mEstado = mPreferences.getString(ESTADO_KEY, "");
        mCidade = mPreferences.getString(CIDADE_KEY, "");
        mTelefone = mPreferences.getString(TELEFONE_KEY, "");
        mEmail = mPreferences.getString(EMAIL_KEY, "");

        setFields();
    }

//    public void clearData() {
//        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
//        preferencesEditor.clear();
//        preferencesEditor.apply();
//    }

    public void setFields(){
        TextView nameView = findViewById(R.id.formNome);
        nameView.setText(mName);

        TextView enderecoView = findViewById(R.id.formEndereco);
        enderecoView.setText(mEndereco);

        TextView estadoView = findViewById(R.id.formEstado);
        estadoView.setText(mEstado);

        TextView cidadeView = findViewById(R.id.formCidade);
        cidadeView.setText(mCidade);

        TextView telefoneView = findViewById(R.id.formTelefone);
        telefoneView.setText(mTelefone);

        TextView emailView = findViewById(R.id.formEmail);
        emailView.setText(mEmail);
    }
}
