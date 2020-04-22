package br.com.harpa_crista.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toolbar;

import br.com.harpa_crista.Model.Harpa;
import br.com.harpa_crista.R;

public class ViewHarpaActivity extends AppCompatActivity {

    private Harpa harpaSelecionada;
    private TextView musica,titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_harpa);


        musica = findViewById(R.id.txtMusicaHarpa);
        titulo = findViewById(R.id.txtTituloViewHarpa);

        Bundle hc = getIntent().getExtras();
        harpaSelecionada = (Harpa) hc.getSerializable("harpa");


        titulo.setText(harpaSelecionada.getTitulo());
        musica.setText(harpaSelecionada.getMusica());

    }
}
