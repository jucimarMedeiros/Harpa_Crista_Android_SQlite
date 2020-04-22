package br.com.harpa_crista.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toolbar;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.harpa_crista.Adapter.AdapterHarpa;
import br.com.harpa_crista.Model.Harpa;
import br.com.harpa_crista.Model.HarpaDao;
import br.com.harpa_crista.R;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Harpa> listaHarpa = new ArrayList<>();
    private AdapterHarpa adapterHarpa;
    private Harpa harpaSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerHarpa);
        try {
            carregarListaHinos();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        adapterHarpa = new AdapterHarpa(listaHarpa,getApplicationContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterHarpa);

        recyclerView.addOnItemTouchListener(
                new br.com.nazareno.helper.RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new br.com.nazareno.helper.RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                harpaSelecionada = listaHarpa.get(position);
                                Intent intent = new Intent(getApplicationContext(), ViewHarpaActivity.class);
                                intent.putExtra("harpa",harpaSelecionada);
                                startActivity(intent);
                                overridePendingTransition(0,0);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );


    }

    public void carregarListaHinos() throws SQLException {
        HarpaDao harpaDao = new HarpaDao(getApplicationContext());
        listaHarpa = harpaDao.getHinos();
    }

    }

