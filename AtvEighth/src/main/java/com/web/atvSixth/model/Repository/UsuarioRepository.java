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
        return em.createQuery("from Usuario u where u.login = :login", Usuario.class)
                .setParameter("login", login)
                .getSingleResult();
    }
}
