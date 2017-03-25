package br.com.helloworld.estudantes.modelo;

/**
 * Created by kevyn on 23/03/2017.
 */

public class Estudante {
    private int id_estudante;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;

    public Estudante(int id_estudante, String nome, String telefone, String endereco, String email) {
        this.id_estudante = id_estudante;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
    }

    public int getId_estudante() {
        return id_estudante;
    }

    public void setId_estudante(int id_estudante) {
        this.id_estudante = id_estudante;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
