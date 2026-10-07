package fai.aulas.urnafai;

/**
 * Cifra de César usada para gravar os votos no banco sem deixá-los em texto puro.
 * Dígitos giram dentro de 0-9 e letras maiúsculas dentro de A-Z.
 *
 * @author rafa-pires
 */
public final class CifraCesar {

    private static final int CHAVE = 3;

    private CifraCesar() {
    }

    public static String cifrar(String texto) {
        return deslocar(texto, CHAVE);
    }

    public static String decifrar(String texto) {
        return deslocar(texto, -CHAVE);
    }

    private static String deslocar(String texto, int chave) {
        StringBuilder sb = new StringBuilder();
        for (char c : texto.toCharArray()) {
            if (Character.isDigit(c)) {
                int digito = c - '0';
                sb.append((char) ('0' + Math.floorMod(digito + chave, 10)));
            } else if (Character.isUpperCase(c)) {
                int letra = c - 'A';
                sb.append((char) ('A' + Math.floorMod(letra + chave, 26)));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
