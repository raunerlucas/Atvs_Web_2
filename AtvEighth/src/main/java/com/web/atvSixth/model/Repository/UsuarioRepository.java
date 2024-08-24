package com.web.atvSixth.model.Repository;

import com.web.atvSixth.model.Entity.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;


@Repository
public class UsuarioRepository {

    @PersistenceContext
    private EntityManager em;

    public Usuario usuario(String login) {
        if (login.isBlank())
            return null;
        var query = em.createQuery("from Usuario u where lower(u.login) = lower(:login)", Usuario.class);
        query.setParameter("login", login);
        return query.getSingleResult();
    }

    public Usuario usuario(Long id){
        return em.find(Usuario.class, id);
    }

    public void save(Usuario usuario){
        usuario.setLogin(usuario.getLogin().toLowerCase());
        em.persist(usuario);
    }

    public void alterUsuario(Usuario usuario){
        usuario.setLogin(usuario.getLogin().toLowerCase());
        em.merge(usuario);
    }

    public void removeUsuario(Long id ){
        em.remove(usuario(id));
    }

}
