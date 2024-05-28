package com.nicolasgandrade.alunosretrofit.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nicolasgandrade.alunosretrofit.MainActivity;
import com.nicolasgandrade.alunosretrofit.R;
import com.nicolasgandrade.alunosretrofit.adapter.AlunoAdapter;
import com.nicolasgandrade.alunosretrofit.api.AlunoService;
import com.nicolasgandrade.alunosretrofit.api.ApiClient;
import com.nicolasgandrade.alunosretrofit.model.Aluno;
import com.nicolasgandrade.alunosretrofit.model.CepResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cadastro extends AppCompatActivity {
    private final String viacepUrl = "https://viacep.com.br/ws/";

    EditText editTextRa;
    EditText editTextNome;
    EditText editTextCep;
    EditText editTextLogradouro;
    EditText editTextComplemento;
    EditText editTextBairro;
    EditText editTextCidade;
    EditText editTextUf;
    Button btnCadastrar;
    AlunoService alunoService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        editTextRa = findViewById(R.id.editTextRa);
        editTextNome = findViewById(R.id.editTextNome);
        editTextCep = findViewById(R.id.editTextCep);
        editTextLogradouro = findViewById(R.id.editTextLogradouro);
        editTextComplemento = findViewById(R.id.editTextComplemento);
        editTextBairro = findViewById(R.id.editTextBairro);
        editTextCidade = findViewById(R.id.editTextCidade);
        editTextUf = findViewById(R.id.editTextUf);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        alunoService = ApiClient.getAlunoService();

        editTextCep.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && editTextCep.getText().length() == 8) {
                    buscarCep(editTextCep.getText().toString());
                }
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarAluno();
            }
        });
    }

    private void buscarCep(String cep) {
        String finalUrl = viacepUrl + cep + "/json";

        retrofit2.Call<CepResponse> call = alunoService.getCepInfo(finalUrl);
        call.enqueue(new Callback<CepResponse>() {
            @Override
            public void onResponse(Call<CepResponse> call, Response<CepResponse> response) {
                CepResponse cepResponse = response.body();
                assert cepResponse != null;
                editTextLogradouro.setText(cepResponse.getLogradouro());
                editTextComplemento.setText(cepResponse.getComplemento());
                editTextBairro.setText(cepResponse.getBairro());
                editTextCidade.setText(cepResponse.getLocalidade());
                editTextUf.setText(cepResponse.getUf());
            }

            @Override
            public void onFailure(Call<CepResponse> call, Throwable t) {
                Log.e("API", "Erro ao obter os dados do cep fornecido: " + t.getMessage());
            }
        });
    }

    private void cadastrarAluno() {
        Aluno aluno = new Aluno(Integer.parseInt(editTextRa.getText().toString()),
                editTextNome.getText().toString(),
                editTextCep.getText().toString(),
                editTextLogradouro.getText().toString(),
                editTextComplemento.getText().toString(),
                editTextBairro.getText().toString(),
                editTextCidade.getText().toString(),
                editTextUf.getText().toString());

        retrofit2.Call<Aluno> call = alunoService.postAluno(aluno);
        call.enqueue(new Callback<Aluno>() {
            @Override
            public void onResponse(Call<Aluno> call, Response<Aluno> response) {
                Toast.makeText(Cadastro.this, "Aluno adicionado com sucesso!", Toast.LENGTH_LONG).show();
                Intent i = new Intent(Cadastro.this, MainActivity.class);
                startActivity(i);
            }

            @Override
            public void onFailure(Call<Aluno> call, Throwable t) {
                Toast.makeText(Cadastro.this, "Erro ao adicionar aluno, tente novamente.", Toast.LENGTH_LONG).show();
            }
        });
    }
}