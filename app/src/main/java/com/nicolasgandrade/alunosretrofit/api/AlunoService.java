package com.nicolasgandrade.alunosretrofit.api;

import com.nicolasgandrade.alunosretrofit.model.Aluno;
import com.nicolasgandrade.alunosretrofit.model.CepResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface AlunoService {

    @GET("alunos")
    Call<List<Aluno>> getAlunos();

    @POST("alunos")
    Call<Aluno> postAluno(@Body Aluno aluno);

    @GET
    Call<CepResponse> getCepInfo(@Url String url);
}
