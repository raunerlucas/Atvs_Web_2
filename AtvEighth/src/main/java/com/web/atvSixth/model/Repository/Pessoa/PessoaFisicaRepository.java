package com.web.atvSixth.model.Repository.Pessoa;

import com.web.atvSixth.model.Entity.Pesssoa.PessoaFisica;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PessoaFisicaRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(PessoaFisica pFisica) {
        em.persist(pFisica);
    }

    public PessoaFisica pessoaFisica(Long id) {
        return em.find(PessoaFisica.class, id);
    }

    public List<PessoaFisica> pessoasFisicas() {
        Query query = em.createQuery("from PessoaFisica", PessoaFisica.class);
        return query.getResultList();
    }

    public List<PessoaFisica> pessoasFisicasByName(String name) {
        Query query = em.createQuery("from PessoaFisica pf where lower(pf.nome) like lower(:name)", PessoaFisica.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    public void remove(Long id) {
        PessoaFisica p = em.find(PessoaFisica.class, id);
        em.remove(p);
    }

    public void update(PessoaFisica pFisica) {
        em.merge(pFisica);
    }
}
