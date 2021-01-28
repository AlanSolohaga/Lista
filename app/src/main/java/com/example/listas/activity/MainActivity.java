package com.example.listas.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listas.R;
import com.example.listas.adaptador.AdaptadorListaDeLista;
import com.example.listas.modelo.Lista;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView textPrueba;
    Button btnListaNueva,btnVerListas,btnEnviarLista;
    RecyclerView recyclerListas;
    ArrayList<Lista> listas;

    Bundle objetoRecibido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnEnviarLista = findViewById(R.id.btnEnviarLista);
        btnListaNueva = findViewById(R.id.btnListaNueva);
        btnVerListas = findViewById(R.id.btnVerListas);
        recyclerListas = findViewById(R.id.recyclerListaMain);
        recyclerListas.setLayoutManager(new LinearLayoutManager(this));

        textPrueba = findViewById(R.id.textPrueba);

        objetoRecibido = getIntent().getBundleExtra("bundle");
        listas = new ArrayList<>();
        agregarLista(objetoRecibido);

        AdaptadorListaDeLista adaptador = new AdaptadorListaDeLista(listas);
        recyclerListas.setAdapter(adaptador);

        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ComprobanteActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("lista",listas.get(recyclerListas.getChildAdapterPosition(v)));
                intent.putExtra("bundle",bundle);
                startActivity(intent);
            }
        });


    }

    private void agregarLista(Bundle objetoRecibido) {
        if(objetoRecibido!=null){
            listas = (ArrayList<Lista>) objetoRecibido.getSerializable("lista");
        }
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnListaNueva:
                Toast.makeText(view.getContext(),"Boton lista nueva"+R.id.btnListaNueva,Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this,NuevaLista.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("listas",listas);
                intent.putExtra("bundleListas",bundle);
                startActivity(intent);
                finish();
                break;
            case R.id.btnVerListas:
                Toast.makeText(view.getContext(),"Boton ver listas"+R.id.btnListaNueva,Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnEnviarLista:
                Toast.makeText(view.getContext(),"Boton enviar lista"+R.id.btnListaNueva,Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
