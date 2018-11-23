package al.infnet.thiagotorres_tp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Contacts extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    ContactAdapter adapter;
    private ArrayList<Contact> contatos = new ArrayList<>();

    String nomeContatoLista;
    String telefoneContatoLista;
    String emailContatoLista;
    String cidadeContatoLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        fillContactList();

        mRecyclerView = findViewById(R.id.contactsList);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        adapter = new ContactAdapter(this, contatos);
        mRecyclerView.setAdapter(adapter);
    }


    public void fillContactList() {

        File arq;
        String lstrlinha;
        int index = 0;

        try {
            arq = new File(getDir(), "ContactsList");
            BufferedReader br = new BufferedReader(new FileReader(arq));

            while ((lstrlinha = br.readLine()) != null)
            {
                setContactField(index, lstrlinha);
                index++;
                if (index % 4 == 0) {
                    index = 0;
                    Contact contato = new Contact(nomeContatoLista, telefoneContatoLista, emailContatoLista, cidadeContatoLista);
                    contatos.add(contato);
                }
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Erro ao carregar contatos", Toast.LENGTH_SHORT).show();
        }

    }

    private void setContactField(int index, String data){
        switch (index){
            case 0:
                nomeContatoLista = data;
                break;

            case 1:
                telefoneContatoLista = data;
                break;

            case 2:
                emailContatoLista = data;
                break;

            case 3:
                cidadeContatoLista = data;
                break;
        }
    }

    private String getDir() {
        File root = getFilesDir();
        return root.toString();
    }

}
