package fai.aulas.urnafai;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author rafa-pires
 */
public final class Hash {

    private Hash() {
    }

    /** MD5 em hexadecimal (usado no CPF do eleitor). */
    public static String md5(String texto) {
        return gerar("MD5", texto);
    }

    /** SHA-256 em hexadecimal: 64 caracteres, cabe em senha VARCHAR(64). */
    public static String sha256(String texto) {
        return gerar("SHA-256", texto);
    }


    public static String senha(String usuario, String senha) {
        return sha256(usuario + ":" + senha);
    }

    /** Compara dois hashes em tempo constante. */
    public static boolean iguais(String a, String b) {
        if (a == null || b == null) {
            return false;
        }
        return MessageDigest.isEqual(a.getBytes(StandardCharsets.UTF_8),
                                     b.getBytes(StandardCharsets.UTF_8));
    }

    private static String gerar(String algoritmo, String texto) {
        try {
            MessageDigest md = MessageDigest.getInstance(algoritmo);
            byte[] hash = md.digest(texto.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
