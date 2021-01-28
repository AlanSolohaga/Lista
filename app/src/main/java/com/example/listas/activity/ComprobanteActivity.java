package com.example.listas.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.listas.R;
import com.example.listas.adaptador.AdaptadorComprobante;
import com.example.listas.modelo.Cosa;
import com.example.listas.modelo.Lista;

public class ComprobanteActivity extends AppCompatActivity {
    Bundle bundle;
    Lista lista;
    TextView nombreLista;
    EditText nombreElemento;
    EditText cantidad;
    EditText precio;
    public EditText editTotal;
    Button btnAgregarElemento;
    RecyclerView recyclerComprobante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprobante);

        nombreLista = findViewById(R.id.txtNombreLista);
        nombreElemento = findViewById(R.id.editNombreElemento);
        cantidad = findViewById(R.id.editCantidadElemento);
        precio = findViewById(R.id.editPrecioNuevo);
        editTotal = findViewById(R.id.editTotal);
        btnAgregarElemento = findViewById(R.id.btnAgregar);
        recyclerComprobante = findViewById(R.id.recyclerListaComprobante);

        recyclerComprobante.setLayoutManager(new LinearLayoutManager(this));

        //Bundle que recibe del intent de ListaDeLista, una lista seleccionada
        bundle = getIntent().getBundleExtra("bundle");
        lista = new Lista();
        lista = (Lista) bundle.getSerializable("lista");

        final AdaptadorComprobante adaptador = new AdaptadorComprobante(lista.getCosas(),this);

        nombreLista.setText(lista.getNombre().toString());

        mostrar(adaptador);

        btnAgregarElemento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cosa cosa = new Cosa(nombreElemento.getText().toString(),
                        Integer.parseInt(cantidad.getText().toString()),
                        Float.parseFloat(precio.getText().toString()));
                lista.getCosas().add(cosa);
                mostrar(adaptador);
                //editTotal.setText(""+lista.getTotal());
                //editTotal.setText(""+adaptador.getTotal());
            }
        });

    }


    private void mostrar(AdaptadorComprobante adaptador) {
        editTotal.setText(""+adaptador.getTotal());
        recyclerComprobante.setAdapter(adaptador);

    }
}
