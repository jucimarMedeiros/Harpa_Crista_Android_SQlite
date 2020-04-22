package br.com.harpa_crista.Model;

import java.io.Serializable;

public class Harpa implements Serializable {

    private String id;
    private String titulo;
    private String musica;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMusica() {
        return musica;
    }

    public void setMusica(String musica) {
        this.musica = musica;
    }

}
