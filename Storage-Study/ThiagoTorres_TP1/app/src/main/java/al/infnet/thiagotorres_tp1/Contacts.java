package al.infnet.thiagotorres_tp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        fillCustomList();

        mRecyclerView = findViewById(R.id.contactsList);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        adapter = new ContactAdapter(this, contatos);
        mRecyclerView.setAdapter(adapter);
    }


    public void fillCustomList() {


        String lstrNomeArq;
        File arq;
        String lstrlinha;
        TextView txtLer;


        try
        {
            txtLer = findViewById(R.id.teste);
            txtLer.setText("");

            arq = new File(getDir(), "ContactsList");
            BufferedReader br = new BufferedReader(new FileReader(arq));

            System.out.println("Deu Até aqui");

            while ((lstrlinha = br.readLine()) != null)
            {
                if (!txtLer.getText().toString().equals(""))
                {
                    txtLer.append("\n");
                }
                txtLer.append(lstrlinha);
            }

            System.out.println("Deu bom");

        }
        catch (Exception e)
        {
            System.out.println("Deu ruim");
        }

    }

    private String getDir() {
        File root = getFilesDir();
        return root.toString();
    }

}
