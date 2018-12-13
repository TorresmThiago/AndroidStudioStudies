package edu.infnet.al.thiagotorresassessment;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class LoggedIn extends AppCompatActivity {

    ArrayList<String> nomes = new ArrayList<>();
    ArrayList<String> telefones = new ArrayList<>();

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

    }

    public void writeToFile() {
        Random random = new Random();
        byte[] nomeDados;
        byte[] telefoneDados;
        byte[] newline;

        try {

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
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Erro ao salvar contato!", Toast.LENGTH_SHORT).show();
        }

    }

    public void fillContactList() {

        File arq;
        int index = 0;
        String lstrlinha;
        String root = getFilesDir().toString();

        //Classe a ser criada

        try {
            arq = new File(root, "ContactsList");
            BufferedReader br = new BufferedReader(new FileReader(arq));

            while ((lstrlinha = br.readLine()) != null)
            {
                if(index % 2 == 0){
                    //New classe
                    //setUmAtributo
                } else {
                    //setOutroAtributo
                    //AddToList
                }
                index++;
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Erro ao carregar contatos", Toast.LENGTH_SHORT).show();
        }
    }
}
