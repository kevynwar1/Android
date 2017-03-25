package br.com.helloworld.estudantes.modelo;

import java.io.Serializable;

/**
 * Created by kevyn on 23/03/2017.
 */

public class TipoEstudante implements Serializable {
    private int id_tipo_estudante;
    private String conteudo;

    public TipoEstudante(int id_tipo_estudante, String conteudo) {
        this.setId_tipo_estudante(id_tipo_estudante);
        this.setConteudo(conteudo);
    }


    public int getId_tipo_estudante() {
        return id_tipo_estudante;
    }

    public void setId_tipo_estudante(int id_tipo_estudante) {
        this.id_tipo_estudante = id_tipo_estudante;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
}

