package com.web.atvSixth.model.Repository;

import com.web.atvSixth.model.Entity.Role;
import com.web.atvSixth.model.Entity.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Role role){
        em.persist(role);
    }

    public Role role(Long id){
        return em.find(Role.class,id);
    }

    public List<Role> roles(){
        return em.createQuery("from Role", Role.class).getResultList();
    }

    public void removeRole(Long id){
        em.remove(role(id));
    }

    public void alterRole(Role role){
        em.merge(role);
    }
}
