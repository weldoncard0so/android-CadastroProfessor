package com.weldoncardoso.sistemacrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.weldoncardoso.sistemacrud.model.Professor;

public class ProfessorActivity extends AppCompatActivity {

    EditText nomeProfessor;
    EditText salario;
    EditText id_professor;
    Button btn_submit;
    Button btn_cancelar;
    Professor professor;

    FirebaseDatabase database;
    DatabaseReference myRef;
    Intent ite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Professores");

        ite = ProfessorActivity.this.getIntent();

        btn_submit = findViewById(R.id.btn_atualizar);
        btn_cancelar = findViewById(R.id.btn_cancelar);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });

        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limparDados();
            }
        });

        nomeProfessor = findViewById(R.id.nome_professor);
        salario = findViewById(R.id.salario);
        id_professor = findViewById(R.id.id_professor);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Professores");

        if (ite !=null){
            professor = (Professor) ite.getSerializableExtra("valor");
            if (professor != null){
                id_professor.setText(professor.getId()+"");
                nomeProfessor.setText(professor.getNome());
                salario.setText(professor.getSalario().toString());
            }
        }
    }
    public void salvar(){
        String id = id_professor.getText().toString();
        String nome = nomeProfessor.getText().toString();
        Double salario1 = Double.parseDouble(salario.getText().toString());

        professor = new Professor(id, nome, salario1);

        myRef.child(professor.getId()).setValue(professor);
        limparDados();
    }


    private void limparDados(){
        id_professor.setText("");
        nomeProfessor.setText("");
        salario.setText("");
    }
}