package fai.aulas.urnafai;

import java.time.LocalDateTime;

/**
 * Sessão de votação aberta por um mesário.
 * fim == null significa que a sessão ainda está aberta.
 *
 * @author rafa-pires
 */
public class Sessao {
    private int idSessao;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private Mesario mesario;

    public Sessao() {
    }

    public int getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(int idSessao) {
        this.idSessao = idSessao;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }

    public Mesario getMesario() {
        return mesario;
    }

    public void setMesario(Mesario mesario) {
        this.mesario = mesario;
    }

    public boolean estaAberta() {
        return fim == null;
    }
}
