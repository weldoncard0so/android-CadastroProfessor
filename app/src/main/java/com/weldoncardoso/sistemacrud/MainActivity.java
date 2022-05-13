package com.weldoncardoso.sistemacrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button bt_login;
    private Button bt_cadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_login = findViewById(R.id.bt_login);
        bt_cadastro = findViewById(R.id.bt_cadastro);

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    telaLogar();
            }
        });
        
        bt_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                telaCadastro();
            }
        });
    }

    private void telaCadastro() {
        startActivity(new Intent(this, CadastroActivity.class));
    }

    private void telaLogar() {
        startActivity(new Intent(this, LoginActivity.class));
    }
}