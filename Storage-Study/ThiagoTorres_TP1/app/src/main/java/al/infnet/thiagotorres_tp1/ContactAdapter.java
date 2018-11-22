package al.infnet.thiagotorres_tp1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.println;

public class ContactAdapter extends RecyclerView.Adapter {

    ArrayList<Contact> contacts;
    Context context;

    public ContactAdapter (Context context, ArrayList<Contact> itens) {
        this.context = context;
        contacts = itens;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist_contact, parent, false);
        ContactsViewHolder viewHolder = new ContactsViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }


    public class ContactsViewHolder extends RecyclerView.ViewHolder {

        public EditText nomeForm;
        public EditText telefoneForm;
        public EditText emailForm;
        public EditText cidadeForm;

        public ContactsViewHolder(View itemView) {
            super(itemView);

            nomeForm = itemView.findViewById(R.id.formNome);
            telefoneForm = itemView.findViewById(R.id.formTelefone);
            emailForm = itemView.findViewById(R.id.formEmail);
            cidadeForm = itemView.findViewById(R.id.formCidade);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                }
            });

        }
    }

}
