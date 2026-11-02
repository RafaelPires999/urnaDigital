/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fai.aulas.urnafai;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author rafa-pires
 */
@Entity
@Table(name = "eleitores")
public class Eleitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_eleitor")
    private Integer idEleitor;
    
    @Column(name = "cpf")
    private String cpf;
    
    @Column(name = "titulo")
    private String titulo;
    
    @Column(name = "nome")
    private String nome;
    
    @Column(name = "ja_votou")
    private boolean javotou;
    
    public Eleitor(String cpf, String titulo, String nome, Boolean javotou){
        this.cpf = cpf;
        this.titulo = titulo;
        this.nome = nome;
        this.javotou = javotou;
    }
    
    public Eleitor(){
    }

    public Integer getIdEleitor() {
        return idEleitor;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isJavotou() {
        return javotou;
    }

    public void setJavotou(boolean javotou) {
        this.javotou = javotou;
    }
}
