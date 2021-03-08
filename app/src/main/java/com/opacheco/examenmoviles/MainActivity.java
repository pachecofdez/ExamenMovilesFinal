package com.opacheco.examenmoviles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tv_textoNotas;
    ListView lista;
    ArrayList<String> nota = new ArrayList<>();
    SharedPreferences sp;
    ArrayAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }

    @Override
    protected void onResume() {
        super.onResume();

        tv_textoNotas = findViewById(R.id.tv_textoNotas);
        lista = findViewById(R.id.lista);

        sp = getSharedPreferences("notas", Context.MODE_PRIVATE);
        sp.getAll().keySet();

        for (String titulo:sp.getAll().keySet()){
            nota.add(titulo);

        }

        if (nota.size() == 0){
            tv_textoNotas.setText("No hay notas almacenadas");
        } else {
            tv_textoNotas.setText("Hay notas");
        }


        System.out.println(nota);

        adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, nota);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String titulodeNota = nota.get(position);
                String contenidodeNota = sp.getString(titulodeNota, null);

                Intent it = new Intent(getApplicationContext(), NotaActivity.class);
                it.putExtra("titulo", titulodeNota);
                it.putExtra("contenido", contenidodeNota);

                startActivity(it);


            }
        });

    }

    // Aquí creo el menu y lo inflo
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu1, menu);
        return true;
    }


    // Aquí controlo que opcion del menu se elige
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        item.getItemId();

        if (R.id.opcion_crearNotas == item.getItemId()){
            Toast.makeText(this, "Has elegido crear notas", Toast.LENGTH_SHORT).show();

            // Con el intent lanzamos otra actividad
            Intent it = new Intent(this, NotaActivity.class);
            startActivity(it);



        }

        if (R.id.opcion_borrarNotas == item.getItemId()){
            Toast.makeText(this, "Has elegido borrar notas", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = sp.edit();


            for (String borrar: sp.getAll().keySet()){
                editor.remove(borrar);
            }
                editor.commit();
                adaptador.notifyDataSetChanged();
                nota.clear();
                tv_textoNotas.setText("No hay notas almacenadas");


        }


        return true;
    }











}