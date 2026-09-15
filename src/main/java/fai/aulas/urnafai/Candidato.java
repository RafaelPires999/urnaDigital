/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fai.aulas.urnafai;

/**
 *
 * @author rafa-pires
 */
public class Candidato {
    private String numero;
    private String nome;
    private String partido;
    private String vice;
    private String cargo;
    
    public Candidato(String numero, String nome, String partido){
        this(numero, nome, partido, null, null);
    }
    
    public Candidato(String numero, String nome, String partido, String vice){
        this(numero, nome, partido, vice, null);
    }
    
    public Candidato(String numero, String nome, String partido, String vice, String cargo){
        this.numero = numero;
        this.nome = nome;
        this.partido = partido;
        this.vice = vice;
        this.cargo = cargo;
    }
    
    public String getNome(){
        return nome;
    }
    
    public String getVice(){
        return vice;
    }
    
    public Boolean temVice(){
        return vice != null;
    }
    
    public String getNumero(){
        return numero;
    }
    
    public String getPartido(){
        return partido;
    }
    
    public String getCargo(){
        return cargo;
    }

    void add(Candidato candidato) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}