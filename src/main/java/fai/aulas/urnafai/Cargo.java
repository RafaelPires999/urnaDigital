/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fai.aulas.urnafai;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author rafa_
 */
@Entity
@Table(name="cargo")
public class Cargo {
    @Id
    @Column(name = "id_cargo")
    private Integer idCargo;
    
    @Column(name = "nome")
    private String nome;
    
    public Cargo(){
    }

    public Integer getIdCargo() {
        return idCargo;
    }

    public String getNome() {
        return nome;
    }
}
