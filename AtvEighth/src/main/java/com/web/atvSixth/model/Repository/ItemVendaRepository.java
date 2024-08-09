package com.web.atvSixth.model.Repository;

import com.web.atvSixth.model.Entity.ItemVenda;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemVendaRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(ItemVenda itemVenda){
        em.persist(itemVenda);
    }

    public ItemVenda itemVenda(Long id){
        return em.find(ItemVenda.class, id);
    }

    public List<ItemVenda> itensVendas(){
        Query query = em.createQuery("from ItemVenda");
        return query.getResultList();
    }

    public void remove(Long id){
        ItemVenda p = em.find(ItemVenda.class, id);
        em.remove(p);
    }

    public void update(ItemVenda itemVenda){
        em.merge(itemVenda);
    }
}
