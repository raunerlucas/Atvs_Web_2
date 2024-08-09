package com.web.atvSixth.model.Repository;

import com.web.atvSixth.model.Entity.Venda;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
public class VendaRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Venda venda) {
        em.persist(venda);
    }

    public Venda venda(Long id) {
        return em.find(Venda.class, id);
    }

    public List<Venda> vendasByData(LocalDate data) {
        Query query = em.createQuery("from Venda where data >= :data");
        query.setParameter("data", data);
        return query.getResultList();
    }

    public List<Venda> vendasByDataAndPessoa(LocalDate data, Long id) {
        Query query = em.createQuery("from Venda where data >= :data and pessoa.id = :id");
        query.setParameter("data", data);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List<Venda> vendasByPessoa(Long id) {
        Query query = em.createQuery("from Venda where pessoa.id = :id");
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List<Venda> vendas() {
        Query query = em.createQuery("from Venda");
        return query.getResultList();
    }

    public void remove(Long id) {
        Venda p = em.find(Venda.class, id);
        em.remove(p);
    }

    public void update(Venda venda) {
        em.merge(venda);
    }
}
