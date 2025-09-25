package com.example.armazenar_contatos.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.armazenar_contatos.R;
import com.example.armazenar_contatos.model.Contato;

import java.util.List;

public class ContatoAdapter extends BaseAdapter {

    private final List<Contato> contatos;
    private final Activity activity;

    public ContatoAdapter(List<Contato> contatos, Activity activity) {
        this.contatos = contatos;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return contatos.size();
    }

    @Override
    public Object getItem(int position) {
        return contatos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(R.layout.item_contato, parent, false);
        Contato contato = contatos.get(position);

        TextView tvNomeContatoLista = view.findViewById(R.id.tvNomeContatoLista);
        tvNomeContatoLista.setText(contato.getNome());

        return view;
    }
}
