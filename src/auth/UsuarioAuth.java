package auth;

import dao.interfaces.UsuarioDao;
import model.Usuario;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsuarioAuth {

    private UsuarioDao usuarioDao;

    public UsuarioAuth(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }


    public boolean validarUsuarioExistente(Integer id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID do usuário inválido.");
        }
        Usuario usuario = usuarioDao.findByUsuarioId(id);
        return usuario != null; 
    }


    public boolean verificarEmailExistente(String email) {
        if (!validarEmailFormato(email)) {
            throw new IllegalArgumentException("Formato de email inválido.");
        }
        Usuario usuario = usuarioDao.findByEmail(email);
        return usuario != null; 
    }


    public boolean validarNomeUsuario(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome de usuário não pode ser vazio.");
        }
        return true; 
    }


    public boolean validarSenhaForte(String senha) {
        if (senha == null || senha.length() < 8) {
            throw new IllegalArgumentException("A senha deve ter no mínimo 8 caracteres.");
        }

        String regex = "^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(senha);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("A senha deve conter pelo menos uma letra e um número.");
        }
        return true;
    }

    private boolean validarEmailFormato(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(regex);  
    }
}