package com.weldoncardoso.sistemacrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.common.internal.constants.ListAppsActivityContract;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.weldoncardoso.sistemacrud.model.Professor;

import java.util.ArrayList;
import java.util.List;

public class ConsultarActivity extends AppCompatActivity {

    private ListView list;
    private Button btn_Criar;
    private DatabaseReference dbRef;
    private List<Professor> listProfessor = new ArrayList<Professor>();
    private ArrayAdapter<Professor> professorArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        dbRef = FirebaseDatabase.getInstance().getReference();

        list = findViewById(R.id.listView);
        btn_Criar = findViewById(R.id.btnCadastrar);

        dbRef.child("Professores").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listProfessor.clear();
                for (DataSnapshot objSnapshot:snapshot.getChildren()){
                    Professor professor = objSnapshot.getValue(Professor.class);
                    listProfessor.add(professor);
                }
                professorArrayAdapter = new ArrayAdapter<Professor>(ConsultarActivity.this,
                        android.R.layout.simple_list_item_1, listProfessor);
                list.setAdapter(professorArrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn_Criar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ConsultarActivity.this, ProfessorActivity.class));
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Professor professorSelecionado = (Professor) parent.getItemAtPosition(position);
                Intent intent = new Intent(ConsultarActivity.this,AtualizarProfessorActivity.class);
                intent.putExtra("id_professor1", professorSelecionado.getId());
                startActivity(intent);
            }
        });

    }
}