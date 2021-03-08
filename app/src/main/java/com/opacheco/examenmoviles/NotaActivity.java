package com.opacheco.examenmoviles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class NotaActivity extends AppCompatActivity {

    EditText et_tituloNota;
    EditText et_contenidoNota;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota);

        et_tituloNota = findViewById(R.id.et_tituloNota);
        et_contenidoNota = findViewById(R.id.et_contenidoNota);


        Bundle bundle = getIntent().getExtras();

        if (bundle == null){

        } else {
            System.out.println(bundle);
            String titulo = bundle.getString("titulo");
            String contenido = bundle.getString("contenido");

            et_tituloNota.setText(titulo);
            et_contenidoNota.setText(contenido);

        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu2, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        String titulo;
        String contenido;

        item.getItemId();
        if (item.getItemId() == R.id.opcion_guardarNotas){
            Toast.makeText(this, "Has elegido guardar nota", Toast.LENGTH_SHORT).show();

            titulo = et_tituloNota.getText().toString();
            contenido = et_contenidoNota.getText().toString();

            sp = getSharedPreferences("notas", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString(titulo, contenido);
            editor.commit();



        }

        return true;
    }
}