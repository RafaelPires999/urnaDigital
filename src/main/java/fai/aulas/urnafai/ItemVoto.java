package fai.aulas.urnafai;

/**
 * Uma escolha dentro do voto: o cargo e o número digitado
 * (ou "BRANCO" para voto em branco).
 *
 * @author rafa-pires
 */
public class ItemVoto {
    private String cargo;
    private String valor;

    public ItemVoto(String cargo, String valor) {
        this.cargo = cargo;
        this.valor = valor;
    }

    public ItemVoto() {
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
