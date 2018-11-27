package com.samuordieres.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.samuordieres.model.Usuario;

@Repository("usuarioDao")
public class UsuarioDAOImpl extends AbstractDAO<Integer, Usuario> implements UsuarioDAO {

	@Override
	public void deleteUsuarioByLogin(String login) {
		Query query = getSession().createSQLQuery("delete from usuarios where login = :login");
        query.setString("login", login);
        query.executeUpdate();
	}

	@Override
	public Usuario findById(int id) {
		return getByKey(id);
	}

	@Override
	public void saveUsuario(Usuario usuario) {
		
		persist(usuario);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> findAllUsuarios() {
		Criteria criteria = createEntityCriteria();
        return (List<Usuario>) criteria.list();
	}

	@Override
	public Usuario findUsuarioByLogin(String login) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("login", login));
        return (Usuario) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Usuario validateUser (Usuario usuario) {
  
	    List<Usuario> usuarios;
	    
	    Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("login", usuario.getLogin()));
        usuarios = (List<Usuario>) criteria.list();
        
	    return usuarios.size() > 0 ? usuarios.get(0) : null;

	    
	    
	    }

}
