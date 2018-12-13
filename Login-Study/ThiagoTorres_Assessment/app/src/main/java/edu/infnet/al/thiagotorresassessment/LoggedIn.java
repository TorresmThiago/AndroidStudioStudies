package edu.infnet.al.thiagotorresassessment;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

import edu.infnet.al.thiagotorresassessment.ContactRecycleView.Contact;
import edu.infnet.al.thiagotorresassessment.ContactRecycleView.ContactAdapter;

public class LoggedIn extends AppCompatActivity {

    ArrayList<String> nomes = new ArrayList<>();
    ArrayList<String> telefones = new ArrayList<>();
    ArrayList<Contact> contatos = new ArrayList<>();

    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        nomes.add("Hallison");
        nomes.add("Douglas");
        nomes.add("Jonh");

        telefones.add("(21) 91234-5678");
        telefones.add("(21) 98765-4321");
        telefones.add("(21) 91827-3645");

        writeToFile();

        mRecyclerView = findViewById(R.id.contactsList);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        adapter = new ContactAdapter(this, contatos);
        mRecyclerView.setAdapter(adapter);
    }

    public void writeToFile() {
        Random random = new Random();
        byte[] nomeDados;
        byte[] telefoneDados;
        byte[] newline;

        try {

            for (int i = 0; i < 20; i ++){

                FileOutputStream fos;

                newline = ("\r\n").getBytes();
                nomeDados = nomes.get(random.nextInt(nomes.size())).getBytes();
                telefoneDados = telefones.get(random.nextInt(telefones.size())).getBytes();

                fos = openFileOutput("ContactsList", Context.MODE_APPEND);

                fos.write(nomeDados);
                fos.write(newline);
                fos.write(telefoneDados);
                fos.write(newline);
                fos.flush();
                fos.close();

            }

            fillContactList();

        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Erro ao salvar contato!", Toast.LENGTH_SHORT).show();
        }

    }

    public void fillContactList() {

        File arq;
        int index = 0;
        String lstrlinha;
        String root = getFilesDir().toString();

        Contact contact = new Contact(null, null);

        try {
            arq = new File(root, "ContactsList");
            BufferedReader br = new BufferedReader(new FileReader(arq));

            while ((lstrlinha = br.readLine()) != null) {
                if(index % 2 == 0){
                    contact = new Contact(null, null);
                    contact.setNome(lstrlinha);
                } else {
                    contact.setTelefone(lstrlinha);
                    contatos.add(contact);
                }
                index++;
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Erro ao carregar contatos", Toast.LENGTH_SHORT).show();
        }
    }
}
