package com.nicolasgandrade.alunosretrofit.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nicolasgandrade.alunosretrofit.R;

public class AlunoHolder extends RecyclerView.ViewHolder {
    public TextView textNome;
    public TextView textRa;
    public TextView textCep;

    public AlunoHolder(View itemView) {
        super(itemView);
        textNome = itemView.findViewById(R.id.textNome);
        textRa = itemView.findViewById(R.id.textRa);
        textCep = itemView.findViewById(R.id.textCep);
    }
}
