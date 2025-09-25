package com.example.armazenar_contatos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.armazenar_contatos.dao.ContatoDAO;
import com.example.armazenar_contatos.model.Contato;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etNomeContato = findViewById(R.id.etNomeContato);
        Button btSalvarContato = findViewById(R.id.btSalvarContato);
        ContatoDAO contatoDAO = new ContatoDAO(this);

        btSalvarContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = etNomeContato.getText().toString().trim();

                if (!nome.isEmpty()) {
                    Contato contato = new Contato(0, nome);
                    contatoDAO.inserirContato(contato);

                    setResult(RESULT_OK);
                    finish();
                } else {
                    etNomeContato.setError("Digite um nome");
                }
            }
        });
    }
}