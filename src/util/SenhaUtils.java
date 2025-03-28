package util;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class SenhaUtils {

    private static final Argon2 argon2 = Argon2Factory.create();


    public static String gerarHash(String senha) {
        return argon2.hash(2, 65536, 1, senha);
    }


    public static boolean verificarSenha(String senha, String hash) {
        return argon2.verify(hash, senha);
    }

    
}
