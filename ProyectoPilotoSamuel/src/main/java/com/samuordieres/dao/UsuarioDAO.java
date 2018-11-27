package com.samuordieres.dao;

import java.util.List;

import com.samuordieres.model.Usuario;

public interface UsuarioDAO {

	Usuario findById(int id);
	 
    void saveUsuario(Usuario usuario);
     
    void deleteUsuarioByLogin(String login);
     
    List<Usuario> findAllUsuarios();
 
    Usuario findUsuarioByLogin(String login);
    
    Usuario validateUser(Usuario usuario);

}

