package com.example.armazenar_contatos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.armazenar_contatos.adapter.ContatoAdapter;
import com.example.armazenar_contatos.dao.ContatoDAO;
import com.example.armazenar_contatos.model.Contato;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ContatoAdapter contatoAdapter;
    private List<Contato> contatos;
    private ContatoDAO contatoDAO;

    //fonte: https://developer.android.com/training/basics/intents/result?hl=pt-br
    private final ActivityResultLauncher<Intent> cadastroLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK) {
            contatos.clear();
            contatos.addAll(contatoDAO.listarContatos());
            contatoAdapter.notifyDataSetChanged();
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        contatoDAO = new ContatoDAO(this);
        carregarContatos();

        Button btInserir = findViewById(R.id.btInserir);
        btInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
                cadastroLauncher.launch(intent);
            }
        });

        Button btLimpar = findViewById(R.id.btLimpar);
        btLimpar.setOnClickListener(v -> {
            contatoDAO.deletarTodos(); //deletar todos os contatos do banco
            contatos.clear(); //limpar a lista
            contatoAdapter.notifyDataSetChanged(); //avisar que a lista mudou
        });
    }

    private void carregarContatos() {
        contatos = contatoDAO.listarContatos();
        contatoAdapter = new ContatoAdapter(contatos, this);

        ListView lvContato = findViewById(R.id.lvContatos);
        lvContato.setAdapter(contatoAdapter);
    }

}