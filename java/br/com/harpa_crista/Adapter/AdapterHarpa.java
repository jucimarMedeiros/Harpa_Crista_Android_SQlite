package br.com.harpa_crista.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.harpa_crista.Model.Harpa;
import br.com.harpa_crista.R;

public class AdapterHarpa extends RecyclerView.Adapter<AdapterHarpa.MyViewHolder> {

    private List<Harpa> listaHarpa;
    private Context context;

    public AdapterHarpa(List<Harpa> listaHarpa, Context context) {
        this.listaHarpa = listaHarpa;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.harpa_detalhe,parent,false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Harpa harpa = listaHarpa.get(position);
        holder.titulo.setText(harpa.getTitulo());
    }

    @Override
    public int getItemCount() {
        return listaHarpa.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView titulo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.txtTituloHarpa);
        }
    }
}



