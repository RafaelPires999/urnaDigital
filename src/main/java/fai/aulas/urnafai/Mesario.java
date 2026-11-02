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
@Table(name = "mesarios")
class Mesario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mesario")
    private Integer idMesario;
    
    @Column(name = "nome")
    private String nome;
    
    @Column(name = "matricula")
    private String matricula;
    
    @Column(name = "usuario")
    private String usuario;
    
    @Column(name = "senha")
    private String senha;
    
    public Mesario(String nome, String matricula, String usuario, String senha){
        this.nome = nome;
        this.matricula = matricula;
        this.usuario = usuario;
        this.senha = senha;
    }
    public Mesario() {
    }

    public Integer getIdMesario() {
        return idMesario;
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