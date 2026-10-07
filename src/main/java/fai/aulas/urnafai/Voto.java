package fai.aulas.urnafai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Voto {
    private int idSessao;
    private List<ItemVoto> itens = new ArrayList<>();

    public Voto(int idSessao) {
        this.idSessao = idSessao;
    }

    public Voto() {
    }

    public int getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(int idSessao) {
        this.idSessao = idSessao;
    }

    public List<ItemVoto> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public void setItens(List<ItemVoto> itens) {
        this.itens = new ArrayList<>(itens);
    }

    public void adicionarItem(ItemVoto item) {
        itens.add(item);
    }

    public boolean estaCompleto(int quantidadeCargos) {
        return itens.size() == quantidadeCargos;
    }
}
