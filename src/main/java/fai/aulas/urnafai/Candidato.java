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
    private String foto;
    
    public Candidato(String numero, String nome, String partido){
        this(numero, nome, partido, null, null, null);
    }
    
    public Candidato(String numero, String nome, String partido, String vice){
        this(numero, nome, partido, vice, null, null);
    }
    
     public Candidato(String numero, String nome, String partido, String vice, String cargo){
        this(numero, nome, partido, vice, cargo, null);
    }
    
    public Candidato(String numero, String nome, String partido, String vice, String cargo, String foto){
        this.numero = numero;
        this.nome = nome;
        this.partido = partido;
        this.vice = vice;
        this.cargo = cargo;
        this.foto = foto;
    }
    
    public Candidato(){
    }

     public String getNumero() {
        return numero;
    }
 
    public void setNumero(String numero) {
        this.numero = numero;
    }
 
    public String getNome() {
        return nome;
    }
 
    public void setNome(String nome) {
        this.nome = nome;
    }
 
    public String getPartido() {
        return partido;
    }
 
    public void setPartido(String partido) {
        this.partido = partido;
    }
 
    public String getVice() {
        return vice;
    }
 
    public void setVice(String vice) {
        this.vice = vice;
    }
 
    public Boolean temVice(){
        return vice != null;
    }
 
    public String getCargo() {
        return cargo;
    }
 
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
 
    public String getFoto() {
        return foto;
    }
 
    public void setFoto(String foto) {
        this.foto = foto;
    }
 
    public boolean temFoto(){
        return foto != null && !foto.isEmpty();
    }

}