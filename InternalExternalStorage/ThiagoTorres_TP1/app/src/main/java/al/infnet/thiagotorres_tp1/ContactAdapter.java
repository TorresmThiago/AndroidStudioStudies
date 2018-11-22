package al.infnet.thiagotorres_tp1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter {

    List<Contact> contacts;

    public ContactAdapter (List<Contact> itens) {
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
        //Nothing for now;
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
        }
    }

}
