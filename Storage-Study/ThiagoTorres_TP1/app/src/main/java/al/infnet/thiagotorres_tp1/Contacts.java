package al.infnet.thiagotorres_tp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Contacts extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    ContactAdapter adapter;
    private List<Contact> contatos = new ArrayList<Contact>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        fillCustomList();

        mRecyclerView = findViewById(R.id.contactsList);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        adapter = new ContactAdapter(contatos);
        mRecyclerView.setAdapter(adapter);
    }


    public void fillCustomList() {
        for (int i = 0; i < 10; i++) {
            contatos.add(new Contact("Nome da pessoa " + i, "(99) 9999-9999", "email@teste.com", "cidade"));
        }
    }

}
