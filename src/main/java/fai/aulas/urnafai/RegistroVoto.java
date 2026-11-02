package fai.aulas.urnafai;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "voto")
public class RegistroVoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_voto")
    private Integer idVoto;

    @Column(name = "id_sessao")
    private Integer idSessao;

    @Column(name = "id_cargo")
    private Integer idCargo;

    @Column(name = "voto")
    private String voto;

    public RegistroVoto(Integer idSessao, Integer idCargo, String voto) {
        this.idSessao = idSessao;
        this.idCargo = idCargo;
        this.voto = voto;
    }

    public RegistroVoto() {
    }

    public Integer getIdVoto() {
        return idVoto;
    }

    public Integer getIdSessao() {
        return idSessao;
    }

    public Integer getIdCargo() {
        return idCargo;
    }

    public String getVoto() {
        return voto;
    }
}
