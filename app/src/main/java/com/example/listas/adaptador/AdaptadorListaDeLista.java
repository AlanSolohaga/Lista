package com.example.listas.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listas.modelo.Lista;
import com.example.listas.R;

import java.util.ArrayList;

public class AdaptadorListaDeLista extends RecyclerView.Adapter<AdaptadorListaDeLista.ViewHolderLista>
        implements View.OnClickListener{

    private View.OnClickListener listener;

    ArrayList<Lista> listas;

    public AdaptadorListaDeLista(ArrayList<Lista> listas) {
        this.listas = listas;
    }

    @NonNull
    @Override
    public ViewHolderLista onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.item_lista_de_lista,null,false);

        view.setOnClickListener(this);

        return new ViewHolderLista(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderLista holder, int position) {
        holder.txtlista.setText(listas.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return listas.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    public class ViewHolderLista extends RecyclerView.ViewHolder {
        TextView txtlista;
        public ViewHolderLista(@NonNull View itemView) {
            super(itemView);
            txtlista = itemView.findViewById(R.id.etiNombreLista);
        }
    }
}
