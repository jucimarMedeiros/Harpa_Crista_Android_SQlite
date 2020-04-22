package br.com.harpa_crista.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.harpa_crista.Helper.DbHelperHarpa;

public class HarpaDao {

    private SQLiteDatabase leitura;

    public HarpaDao(Context context) {
        DbHelperHarpa helperHarpa = new DbHelperHarpa(context);
        leitura = helperHarpa.getReadableDatabase();
    }

    public List<Harpa> getHinos() throws SQLException {

        ArrayList<Harpa> harpas = new ArrayList<>();

        String sql = "SELECT * FROM " + DbHelperHarpa.TAB_HINOS + ";";


        Cursor c = leitura.rawQuery(sql,null);

        while (c.moveToNext()){
            Harpa harpa = new Harpa();

            harpa.setId(c.getString(c.getColumnIndex("id")));
            harpa.setTitulo(c.getString(c.getColumnIndex("titulo")));
            harpa.setMusica(c.getString(c.getColumnIndex("musica")));


            harpas.add(harpa);
        }

        return harpas;
    }

}
