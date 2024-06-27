package com.web.atvfirst.model.Repository.Pessoa;

import com.web.atvfirst.model.Entity.Pesssoa.PessoaFisica;
import com.web.atvfirst.model.Entity.Pesssoa.PessoaJuridica;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PessoaJuridicaRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(PessoaJuridica pJuridica){
        em.persist(pJuridica);
    }

    public PessoaJuridica pessoaJuridica(Long id){
        return em.find(PessoaJuridica.class, id);
    }

    public List<PessoaJuridica> pessoasJuridicas(){
        Query query = em.createQuery("from PessoaJuridica");
        return query.getResultList();
    }

    public void remove(Long id){
        PessoaJuridica p = em.find(PessoaJuridica.class, id);
        em.remove(p);
    }

    public void update(PessoaJuridica pJuridica){
        em.merge(pJuridica);
    }
}
