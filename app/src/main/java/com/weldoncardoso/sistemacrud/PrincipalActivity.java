package com.weldoncardoso.sistemacrud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PrincipalActivity extends AppCompatActivity {

    Button btn_cadastrar;
    Button btn_consultar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        btn_cadastrar = findViewById(R.id.btn_cadastrar);
        btn_consultar = findViewById(R.id.btn_consultar);

        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                telaCadastrar();
            }
        });

        btn_consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                telaConsultar();
            }
        });
    }

    private void telaCadastrar() {
        startActivity(new Intent(this, ProfessorActivity.class));
    }

    private void telaConsultar() {
        startActivity(new Intent(this, ConsultarActivity.class));
    }
}