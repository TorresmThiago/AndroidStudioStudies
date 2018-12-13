package edu.infnet.al.thiagotorresassessment.ContactRecycleView;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import edu.infnet.al.thiagotorresassessment.R;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactsViewHolder> {

    ArrayList<Contact> contacts;
    Context context;

    public ContactAdapter (Context context, ArrayList<Contact> contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.asset_item_list, parent, false);
        return new ContactsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, int position) {
        Contact contato = contacts.get(position);
        holder.nomeForm.setText(contato.getNome());
        holder.telefoneForm.setText(contato.getTelefone());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }


    public class ContactsViewHolder extends RecyclerView.ViewHolder {

        public TextView nomeForm;
        public TextView telefoneForm;

        public ContactsViewHolder(View itemView) {
            super(itemView);

            nomeForm = itemView.findViewById(R.id.contactName);
            telefoneForm = itemView.findViewById(R.id.contactTelefone);

        }
    }

}
