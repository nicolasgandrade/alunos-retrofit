package com.nicolasgandrade.alunosretrofit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nicolasgandrade.alunosretrofit.R;
import com.nicolasgandrade.alunosretrofit.model.Aluno;

import java.util.List;

public class AlunoAdapter extends RecyclerView.Adapter<AlunoHolder> {
    private final List<Aluno> alunos;
    Context context;

    public AlunoAdapter(List<Aluno> alunos, Context context) {
        this.alunos = alunos;
        this.context = context;
    }

    @NonNull
    @Override
    public AlunoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AlunoHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_aluno, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AlunoHolder holder, int position) {
        holder.textNome.setText(alunos.get(position).getNome());
        holder.textRa.setText(String.valueOf(alunos.get(position).getRa()));
        holder.textCep.setText(alunos.get(position).getCep());
    }

    @Override
    public int getItemCount() {
        return alunos != null ? alunos.size() : 0;
    }
}
