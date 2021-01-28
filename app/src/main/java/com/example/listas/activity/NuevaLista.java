package com.example.listas.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.listas.ConexionSQLiteHelper;
import com.example.listas.R;
import com.example.listas.adaptador.AdaptadorNuevaLista;
import com.example.listas.modelo.Cosa;
import com.example.listas.modelo.Lista;
import com.example.listas.utulidades.Utilidades;

import java.util.ArrayList;

public class NuevaLista extends AppCompatActivity {
    EditText editNombre,editCantidad,editNombreLista;
    Button btnAgregar,btnAgregarLista;

    RecyclerView recyclerNuevaLista;
    ArrayList<Cosa> cosas;
    ArrayList<Lista> listas;
    Lista lista;

    ConexionSQLiteHelper con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_lista);

        editNombre = findViewById(R.id.editNombre);
        editCantidad = findViewById(R.id.editCantidad);
        editNombreLista = findViewById(R.id.editNombreLista);
        btnAgregar = findViewById(R.id.btnAgregar);
        btnAgregarLista = findViewById(R.id.btnAgregarLista);
        recyclerNuevaLista = findViewById(R.id.recyclerNuevaLista);
        recyclerNuevaLista.setLayoutManager(new LinearLayoutManager(this));

        cosas = new ArrayList<>();
        final AdaptadorNuevaLista adaptadorNuevaLista = new AdaptadorNuevaLista(cosas);
        mostrarLista(adaptadorNuevaLista);


        //agrega elementos al recycleView y agrega al array
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cosa cosa = new Cosa(editNombre.getText().toString(),
                        Integer.parseInt(editCantidad.getText().toString()));
                agregar(cosas,cosa);
                registarCosa();
                editNombre.setText("");
                editCantidad.setText("");
                mostrarLista(adaptadorNuevaLista);

            }

        });

        Bundle objetoRecivido = getIntent().getBundleExtra("bundleListas");
        listas = new ArrayList<>();
        if(objetoRecivido!=null){
            listas = (ArrayList<Lista>) objetoRecivido.getSerializable("listas");
        }

        //boton final que agrega el array a la Lista principal
        btnAgregarLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //agregamos la lista a la memoria
                lista=new Lista(editNombreLista.getText().toString(),cosas);
                listas.add(lista);
                Intent intent = new Intent(NuevaLista.this,MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("lista",listas);
                intent.putExtra("bundle",bundle);
                startActivity(intent);
                finish();
            }
        });

    }

    private void registarCosa() {
        //HACEMOS LA CONEXION
        con = new ConexionSQLiteHelper(this,"bd_cosas",null,1);

        SQLiteDatabase db = con.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE,editNombre.getText().toString());
        values.put(Utilidades.CAMPO_CANTIDAD,editCantidad.getText().toString());

        Long idResultante =db.insert(Utilidades.NOMBRE_TABLA,Utilidades.CAMPO_NOMBRE,values);
        Toast.makeText(this,"Nombre: "+idResultante,Toast.LENGTH_SHORT).show();
        con.close();
    }


    private void mostrarLista(AdaptadorNuevaLista adaptadorNuevaLista) {
        recyclerNuevaLista.setAdapter(adaptadorNuevaLista);
    }

    private void agregar(ArrayList arrayList,Cosa cosa) {
        arrayList.add(cosa);
    }


}
