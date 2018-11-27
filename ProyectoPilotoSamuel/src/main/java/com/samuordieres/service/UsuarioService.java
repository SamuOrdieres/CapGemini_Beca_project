package com.samuordieres.service;

import java.util.List;

import com.samuordieres.model.Usuario;

public interface UsuarioService {
	
	Usuario findById(int id);
    
    void saveUsuario(Usuario usuario);
     
    void updateUsuario(Usuario usuario);
     
    void deleteUsuarioByLogin(String login);
 
    List<Usuario> findAllUsuarios(); 
     
    Usuario findUsuarioByLogin(String login);
 
    boolean isUsuarioLoginUnique(Integer id, String login);

	Usuario validateUser(Usuario usuario);
    
    

}

