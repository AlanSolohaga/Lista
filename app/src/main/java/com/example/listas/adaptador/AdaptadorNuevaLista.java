package com.example.listas.adaptador;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listas.modelo.Cosa;
import com.example.listas.R;

import java.util.ArrayList;

public class AdaptadorNuevaLista extends RecyclerView.Adapter<AdaptadorNuevaLista.ViewHolderLista>
        implements View.OnClickListener{
    //implements View.OnClickListener para generar el evento onClick

    //creamos la variable View.OnClickListener que usaremos en el evento setOnClickListener para referenciar
    private View.OnClickListener listener;

    //Array de lo que queremos listar
    ArrayList<Cosa> cosas;
    //Constructor que recibe el Array de cosas
    public AdaptadorNuevaLista(ArrayList<Cosa> cosas) {
        this.cosas = cosas;
    }

    //Creamos un view y lo inflamos con el item_nueva_lista y lo retornamos al holder
    @NonNull
    @Override
    public ViewHolderLista onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //posible error en el iflate puede ser (R.layout.item_nueva_lista,NULL,false)
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_nueva_lista,null,false);

        //creamos el llamado setOnClickListener
        v.setOnClickListener(this);
        return new ViewHolderLista(v);
    }

    //El holder recibe la vista y nos sirve para mostrar segun la posicion
    @Override
    public void onBindViewHolder(@NonNull ViewHolderLista holder, int position) {
        holder.editNombre.setText(cosas.get(position).getNombre());
        holder.editCantidad.setText(""+cosas.get(position).getCantidad());
    }

    //nos muestra el tamaño del array
    @Override
    public int getItemCount() {
        return cosas.size();
    }

    //creamos el evento setOnClick que lo referenciamos con la variable que creamos arriba
    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(listener !=null){
            listener.onClick(v);
        }
    }

    //clase que trabajamos para anexar el recycler con el diseño de item_nueva_lista
    //Creamos los elementos y los casteamos con los del item_nueva_lista
    public class ViewHolderLista extends RecyclerView.ViewHolder {

        TextView editNombre,editCantidad;

        public ViewHolderLista(@NonNull View itemView) {
            super(itemView);
            editNombre = itemView.findViewById(R.id.etiNombre);
            editCantidad = itemView.findViewById(R.id.etiCantidad);
        }
    }
}
