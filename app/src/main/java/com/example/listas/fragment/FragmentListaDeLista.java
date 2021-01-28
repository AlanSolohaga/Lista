package com.example.listas.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.listas.R;
import com.example.listas.activity.ComprobanteActivity;
import com.example.listas.adaptador.AdaptadorListaDeLista;
import com.example.listas.modelo.Cosa;
import com.example.listas.modelo.Lista;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentListaDeLista#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentListaDeLista extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Cosa cosa,cosa1,cosa2;
    ArrayList<Cosa> cosas,cosas1;
    ArrayList<Lista> listas;
    View view;
    RecyclerView recyclerView;
    public FragmentListaDeLista() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentListaDeLista.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentListaDeLista newInstance(String param1, String param2) {
        FragmentListaDeLista fragment = new FragmentListaDeLista();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lista_de_lista, container, false);
        recyclerView = view.findViewById(R.id.recyclerListaDeLista);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        cosa = new Cosa("COSA",1,50);

        //Array de "cosas"
        cosas = new ArrayList<>();
        cosas.add(cosa);

        //Lista agrega el array de "cosa 1" = cosa2
        Lista lista = new Lista("Lista 3",cosas);

        //ArrayList de las Listas
        listas = new ArrayList<>();
        agregarLista(lista);

        AdaptadorListaDeLista adapter = new AdaptadorListaDeLista(listas);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Selección: "+listas.get
                        (recyclerView.getChildAdapterPosition(v)).getNombre(),
                            Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(getContext(), ComprobanteActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("lista",listas.get(recyclerView.getChildAdapterPosition(v)));
                intent.putExtra("bundle",bundle);
                startActivity(intent);
            }
        });
        mostrar(adapter);

        return view;
    }

    private void agregarLista(Lista lista) {
        listas.add(lista);
    }

    private void mostrar(AdaptadorListaDeLista adapter) {
        recyclerView.setAdapter(adapter);
    }
}
