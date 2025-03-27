package validation;

import model.Categoria;

public class CategoriaRules {
    

    public static boolean validarCategoriaNome(Categoria cat) {
        return cat.getNome() != null && !cat.getNome().isBlank();
    }

    public static boolean validarIdCategoria(Categoria cat) {
        return cat.getUsuario().getId() > 0;
    }

    
}
