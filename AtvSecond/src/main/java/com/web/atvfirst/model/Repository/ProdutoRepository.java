package com.web.atvfirst.model.Repository;

import com.web.atvfirst.model.Entity.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProdutoRepository  {

    @PersistenceContext
    private EntityManager em;

    public void save(Produto produto){em.persist(produto);}

    public Produto produto(Long id){
        return em.find(Produto.class, id);
    }

    public List<Produto> produtos(){
        Query query = em.createQuery("from Produto");
        return query.getResultList();
    }

    public void remove(Long id){
        Produto p = em.find(Produto.class, id);
//        Produto p = produto(id);
        em.remove(p);
    }

    public void update(Produto produto){
        em.merge(produto);
    }
}
