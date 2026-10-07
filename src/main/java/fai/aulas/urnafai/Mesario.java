/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fai.aulas.urnafai;

/**
 *
 * @author rafa-pires
 */
class Mesario {
    private String nome;
    private String matricula;
    private String usuario;
    private String senha;
    
    public Mesario(String nome, String matricula, String usuario, String senha){
        this.nome = nome;
        this.matricula = matricula;
        this.usuario = usuario;
        this.senha = senha;
    }
    public Mesario() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}