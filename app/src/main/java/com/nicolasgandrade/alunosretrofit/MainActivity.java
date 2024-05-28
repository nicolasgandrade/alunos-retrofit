package com.nicolasgandrade.alunosretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nicolasgandrade.alunosretrofit.activities.Cadastro;
import com.nicolasgandrade.alunosretrofit.adapter.AlunoAdapter;
import com.nicolasgandrade.alunosretrofit.api.AlunoService;
import com.nicolasgandrade.alunosretrofit.api.ApiClient;
import com.nicolasgandrade.alunosretrofit.model.Aluno;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton btnAdd;
    private RecyclerView recyclerAluno;
    private List<Aluno> listaAlunos;
    private AlunoAdapter alunoAdapter;
    private AlunoService alunoService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        recyclerAluno = findViewById(R.id.recyclerAluno);
        alunoService = ApiClient.getAlunoService();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Cadastro.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        obterAlunos();
    }

    private void configurarRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerAluno.setLayoutManager(layoutManager);
        alunoAdapter = new AlunoAdapter(listaAlunos, this);
        recyclerAluno.setAdapter(alunoAdapter);
        recyclerAluno.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private void obterAlunos() {
        retrofit2.Call<List<Aluno>> call = alunoService.getAlunos();
        call.enqueue(new Callback<List<Aluno>>() {
            @Override
            public void onResponse(Call<List<Aluno>> call, Response<List<Aluno>> response) {
                listaAlunos = response.body();
                configurarRecycler();
            }

            @Override
            public void onFailure(Call<List<Aluno>> call, Throwable t) {
                Log.e("API", "Erro ao obter alunos: " + t.getMessage());
            }
        });
    }
}