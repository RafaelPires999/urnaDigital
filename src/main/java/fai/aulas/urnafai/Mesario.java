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
    
    public String getNome(){
        return nome;
    }
    
    public String getMatricula(){
        return matricula;
    }
    
    public String getUsuario(){
        return usuario;
    }
    
    public String getSenha(){
        return senha;
    }
}