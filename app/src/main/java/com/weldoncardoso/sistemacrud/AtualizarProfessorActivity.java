package com.weldoncardoso.sistemacrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.weldoncardoso.sistemacrud.model.Professor;

import java.util.Objects;

public class AtualizarProfessorActivity extends AppCompatActivity {

    private EditText nome_professor;
    private EditText id_professor;
    private EditText salario;
    private Button btn_atualizar;
    private Button btn_deletar;
    private Button btn_cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_professor);

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
        String id_professor1 = getIntent().getStringExtra("id_professor1");
        DatabaseReference professor = dbRef.child("Professores").child(id_professor1);

        id_professor = findViewById(R.id.id_professor);
        nome_professor = findViewById(R.id.nome_professor);
        salario = findViewById(R.id.salario);
        btn_atualizar = findViewById(R.id.btn_atualizar);
        btn_deletar = findViewById(R.id.btn_deletar);
        btn_cancelar = findViewById(R.id.btn_cancelar);

        professor.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    id_professor.setText(Objects.requireNonNull(snapshot.child("id_professor").getValue().toString()));
                    nome_professor.setText(Objects.requireNonNull(snapshot.child("nome_professor").getValue().toString()));
                    salario.setText(Objects.requireNonNull(snapshot.child("salario").getValue().toString()));
                } else {
                    Toast.makeText(AtualizarProfessorActivity.this, "Os dados não carregaram",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn_atualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Professor p = new Professor();
                p.setId(id_professor1);
                p.setNome(nome_professor.getText().toString());
                p.setSalario(Double.parseDouble(salario.getText().toString()));
                professor.setValue(p);
                clearInputs();
                startActivity(new Intent(AtualizarProfessorActivity.this,ConsultarActivity.class));
            }
        });

        btn_deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                professor.removeValue();
                clearInputs();
                startActivity(new Intent(AtualizarProfessorActivity.this,ConsultarActivity.class));
            }
        });

        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearInputs();
                startActivity(new Intent(AtualizarProfessorActivity.this,ConsultarActivity.class));
            }
        });
    }
    private void clearInputs(){
        id_professor.setText("");
        nome_professor.setText("");
        salario.setText("");
    }
}