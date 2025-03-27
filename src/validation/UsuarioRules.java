package validation;

import model.Usuario;

public class UsuarioRules {
    
    public static boolean validarNomeUsuario(Usuario u) {
        return u.getNome() != null && !u.getNome().isBlank();
    }

    public static boolean validarEmailUsuario(Usuario u) {
        return u.getEmail() != null && !u.getEmail().isBlank();
    }

    public static boolean validarSenhaUsuario(Usuario u) {
        return u.getSenha() != null && !u.getSenha().isBlank();
    }
}
