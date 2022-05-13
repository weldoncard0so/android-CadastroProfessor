package com.weldoncardoso.sistemacrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.weldoncardoso.sistemacrud.model.Usuario;

public class CadastroActivity extends AppCompatActivity {

    private EditText etCnome;
    private EditText etCemail;
    private EditText etCsenha;
    private Button bt_cadastrar;

    private FirebaseAuth mAuth;

    private Usuario u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        etCnome = findViewById(R.id.etNome);
        etCemail = findViewById(R.id.etEmail);
        etCsenha = findViewById(R.id.etSenha);
        bt_cadastrar = findViewById(R.id.bt_cadastrar);

        mAuth = FirebaseAuth.getInstance();

        bt_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recuperarDados();
                criarLogin();
            }
        });
    }

    private void criarLogin() {
        mAuth.createUserWithEmailAndPassword(u.getEmail(),u.getSenha())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            u.setId(user.getUid());
                            u.salvarDados();
                            startActivity(new Intent(CadastroActivity.this, MainActivity.class));
                        } else {
                            Toast.makeText(CadastroActivity.this, "Erro ao criar Login.",Toast.LENGTH_SHORT);
                        }
                    }
                });
    }

    private void recuperarDados() {
        if(etCnome.getText().toString() == "" || etCemail.getText().toString() == "" || etCsenha.getText().toString() == ""){
            Toast.makeText(this, "Você deve preencher todos os dados",Toast.LENGTH_LONG);
        } else {
            u = new Usuario();
            u.setNome(etCnome.getText().toString());
            u.setEmail(etCemail.getText().toString());
            u.setSenha(etCsenha.getText().toString());


        }
    }
}