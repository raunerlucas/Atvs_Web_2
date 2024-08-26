package com.web.atvSixth.model.Repository.Pessoa;

import com.web.atvSixth.model.Entity.Pesssoa.PessoaJuridica;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PessoaJuridicaRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(PessoaJuridica pJuridica) {
        em.persist(pJuridica);
    }

    public PessoaJuridica pessoaJuridicaByUsuarioId(Long id) {
        Query query = em.createQuery("from PessoaJuridica pj where pj.usuario.id = :id");
        query.setParameter("id", id);
        return query.getResultList().isEmpty() ? null : (PessoaJuridica) query.getResultList().get(0);
    }

    public PessoaJuridica pessoaJuridica(Long id) {
        return em.find(PessoaJuridica.class, id);
    }

    public List<PessoaJuridica> pessoasJuridicas() {
        Query query = em.createQuery("from PessoaJuridica");
        return query.getResultList();
    }


    public List<PessoaJuridica> pessoasJuridicasByName(String name) {
        Query query = em.createQuery("from PessoaJuridica pj where lower(pj.nome) like lower(:name)");
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    public void remove(Long id) {
        PessoaJuridica p = em.find(PessoaJuridica.class, id);
        em.remove(p);
    }

    public void update(PessoaJuridica pJuridica) {
        em.merge(pJuridica);
    }

}
