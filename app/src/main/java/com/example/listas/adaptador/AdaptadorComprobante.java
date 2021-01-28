package com.example.listas.adaptador;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listas.activity.ComprobanteActivity;
import com.example.listas.modelo.Cosa;
import com.example.listas.R;

import java.util.ArrayList;

public class AdaptadorComprobante extends RecyclerView.Adapter<AdaptadorComprobante.ViewHolderComprobante> {

    ArrayList<Cosa> cosas;
    ComprobanteActivity vista;
    float total;

    public AdaptadorComprobante(ArrayList<Cosa> cosas,ComprobanteActivity vista) {
        this.cosas = cosas;
        this.vista = vista;
    }

    public float getTotal() {
        total = 0;
        for(int i=0;i<cosas.size();i++){
            total +=cosas.get(i).getSubTotal();
        }
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @NonNull
    @Override
    public ViewHolderComprobante onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_comprobante,null,false);

        return new ViewHolderComprobante(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderComprobante holder, final int position) {

        holder.nombreElemento.setText(cosas.get(position).getNombre().toString());
        holder.cantidadElemento.setText(""+cosas.get(position).getCantidad());
        holder.precioElemento.setText(""+cosas.get(position).getPrecio());
        holder.subTotal.setText(""+cosas.get(position).getSubTotal());
        vista.editTotal.setText(""+getTotal());

        holder.cantidadElemento.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String cantidad = s.toString();
                if(cantidad!=""){
                    cosas.get(position).setCantidad(Integer.parseInt(cantidad));
                    cosas.get(position).setSubTotal(validar(position));
                }else{
                    cosas.get(position).setPrecio(0);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString()!=""){
                    holder.subTotal.setText(""+cosas.get(position).getSubTotal());
                    vista.editTotal.setText(""+getTotal());
                }else{
                    holder.subTotal.setText("");
                }
            }
        });
        holder.precioElemento.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String precio = s.toString();
                if(precio!=""){
                    cosas.get(position).setPrecio(Float.parseFloat(precio));
                    cosas.get(position).setSubTotal(validar(position));
                }else{
                    cosas.get(position).setPrecio(0);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString()!=""){
                    holder.subTotal.setText(""+cosas.get(position).getSubTotal());
                    vista.editTotal.setText(""+getTotal());
                }else{
                    holder.subTotal.setText("");
                }

            }
        });
    }

    private float validar(int position) {
        float sub=0;
        sub=cosas.get(position).getCantidad()*cosas.get(position).getPrecio();
        return sub;
    }

    @Override
    public int getItemCount() {
        return cosas.size();
    }

    public class ViewHolderComprobante extends RecyclerView.ViewHolder {
        TextView nombreElemento,subTotal;
        EditText cantidadElemento,precioElemento;

        public ViewHolderComprobante(@NonNull View itemView) {
            super(itemView);
            nombreElemento = itemView.findViewById(R.id.textNombreElemento);
            subTotal =itemView.findViewById(R.id.textSubTotal);
            cantidadElemento = itemView.findViewById(R.id.editCantidadElemento);
            precioElemento = itemView.findViewById(R.id.editPrecioElemento);
        }
    }
}
