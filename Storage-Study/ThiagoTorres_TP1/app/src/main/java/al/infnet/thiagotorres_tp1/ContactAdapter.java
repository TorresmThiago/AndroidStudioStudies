package al.infnet.thiagotorres_tp1;

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

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactsViewHolder> {

    public static ContactListItemClick contactListItemClick;
    ArrayList<Contact> contacts;
    Context context;

    public ContactAdapter (Context context, ArrayList<Contact> contacts, ContactListItemClick contactListItemClick) {
        this.context = context;
        this.contacts = contacts;
        this.contactListItemClick = contactListItemClick;
    }

    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist_contact, parent, false);
        ContactsViewHolder viewHolder = new ContactsViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, int position) {
        Contact contato = contacts.get(position);
        holder.nomeForm.setText(contato.getNome());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }


    public class ContactsViewHolder extends RecyclerView.ViewHolder {

        public TextView nomeForm;
//        public EditText telefoneForm;
//        public EditText emailForm;
//        public EditText cidadeForm;

        public ContactsViewHolder(View itemView) {
            super(itemView);

            nomeForm = itemView.findViewById(R.id.contactName);
//            telefoneForm = itemView.findViewById(R.id.formTelefone);
//            emailForm = itemView.findViewById(R.id.formEmail);
//            cidadeForm = itemView.findViewById(R.id.formCidade);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    contactListItemClick.onContactClick(contacts.get(getLayoutPosition()));

//                    Intent intent = new Intent(context, MainActivity.class);
//                    context.startActivity(intent);

                }
            });

        }
    }

}
