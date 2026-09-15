/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fai.aulas.urnafai;

/**
 *
 * @author rafa-pires
 */
public class Eleitor {
    private String cpf;
    private String titulo;
    private String nome;
    private Boolean javotou;
    
    public Eleitor(String cpf, String titulo, String nome, Boolean javotou){
        this.cpf = cpf;
        this.titulo = titulo;
        this.nome = nome;
        this.javotou = javotou;
    }
    
    public String getCpf(){
        return cpf;
    }
    
    public String getTitulo(){
        return titulo;
    }
    
    public String getNome(){
        return nome;
    }
    
    public Boolean isJaVotou(){
        return javotou;
    }
    
    public void setJaVotou(Boolean javotou){
        this.javotou = javotou;
    }
}
