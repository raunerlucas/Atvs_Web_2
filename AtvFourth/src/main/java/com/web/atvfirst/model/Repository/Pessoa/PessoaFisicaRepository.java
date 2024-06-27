package com.web.atvfirst.model.Repository.Pessoa;

import com.web.atvfirst.model.Entity.Pesssoa.PessoaFisica;
import com.web.atvfirst.model.Entity.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PessoaFisicaRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(PessoaFisica pFisica){
        em.persist(pFisica);
    }

    public PessoaFisica pessoaFisica(Long id){
        return em.find(PessoaFisica.class, id);
    }

    public List<PessoaFisica> pessoasFisicas(){
        Query query = em.createQuery("from PessoaFisica");
        return query.getResultList();
    }

    public void remove(Long id){
        PessoaFisica p = em.find(PessoaFisica.class, id);
        em.remove(p);
    }

    public void update(PessoaFisica pFisica){
        em.merge(pFisica);
    }
}
