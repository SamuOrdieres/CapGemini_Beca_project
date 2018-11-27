package com.samuordieres.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.samuordieres.dao.UsuarioDAO;
import com.samuordieres.model.Usuario;

@Service("usuarioService")
@Transactional
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
    private UsuarioDAO dao;

	@Override
	public void deleteUsuarioByLogin(String login) {
		// TODO Auto-generated method stub
		dao.deleteUsuarioByLogin(login);
	}

	@Override
	public Usuario findById(int id) {		
		return dao.findById(id);
	}

	@Override
	public void saveUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		 dao.saveUsuario(usuario);
	}

	@Override
	public void updateUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		Usuario entity = dao.findById(usuario.getId());
        if(entity!=null){
            entity.setLogin(usuario.getLogin());
            entity.setPassword(usuario.getPassword());
            entity.setNivel(usuario.getNivel());
        }
		
	}

	@Override
	public List<Usuario> findAllUsuarios() {
		return dao.findAllUsuarios();
	}

	@Override
	public Usuario findUsuarioByLogin(String login) {
		return dao.findUsuarioByLogin(login);
	}
	
	public boolean isUsuarioLoginUnique(Integer id, String login) {
        Usuario usuario = findUsuarioByLogin(login);
        return ( usuario == null || ((id != null) && (usuario.getId() == id)));
    }

	@Override
	public Usuario validateUser(Usuario usuario) {
		return dao.validateUser(usuario);
	}

}
