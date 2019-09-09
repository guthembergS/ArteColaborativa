/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artecolaborativa.test;

import br.com.artecolaborativa.model.Lojista;
import br.com.artecolaborativa.model.Produto;
import br.com.artecolaborativa.model.ProdutoEstoque;
import static br.com.artecolaborativa.test.GenericTest.logger;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import jdk.nashorn.internal.runtime.JSType;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author thiago
 */
public class ProdutoEstoqueCrudTest extends GenericTest {

    @Test
    public void createProdutoEstoque() {
        logger.info("Executando createProdutoEstoque()");
        
        TypedQuery<Produto> queryProduto = em.createNamedQuery("Produto.PorId", Produto.class);
        queryProduto.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryProduto.setParameter("idProduto", 9);
        Produto prodAtual = (Produto) queryProduto.getSingleResult();
        
        TypedQuery<Lojista> queryLojista = em.createNamedQuery("Lojista.PorId", Lojista.class);
        queryLojista.setParameter("idUsuario", 1);
        Lojista lojAtual = (Lojista) queryLojista.getSingleResult();
                
        ProdutoEstoque prod = new ProdutoEstoque();
       
        prod.setProduto(prodAtual);
        prod.setLojista(lojAtual);
        prod.setQuantidade(6);
        
        em.persist(prod);
        em.flush();
        
        TypedQuery<ProdutoEstoque> queryProdEst = em.createNamedQuery("ProdutoEstoque.PorProdLojista", ProdutoEstoque.class);
        queryProdEst.setParameter("idProduto", prodAtual.getIdProduto());
        queryProdEst.setParameter("idUsuario", lojAtual.getIdUsuario());
        ProdutoEstoque prodEstAtual = (ProdutoEstoque) queryProdEst.getSingleResult();
        
        assertNotNull(prodEstAtual);
        
    }

    @Test
    public void updateProdutoEstoque() {
        logger.info("Executando updateProdutoEstoque()");

        Integer quantidade = 4;

        TypedQuery<ProdutoEstoque> queryProdEst = em.createNamedQuery("ProdutoEstoque.PorProdLojista", ProdutoEstoque.class);
        queryProdEst.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryProdEst.setParameter("idProduto", 14); // produto 14
        queryProdEst.setParameter("idUsuario", 2); // lojista 2
        ProdutoEstoque prodEstAtual = (ProdutoEstoque) queryProdEst.getSingleResult();
        
        assertNotNull(prodEstAtual);

        prodEstAtual.setQuantidade(quantidade);

        em.flush();

        assertEquals(queryProdEst.getSingleResult().getQuantidade(), quantidade);
    }

    
    @Test
    public void updateProdutoEstoqueMerge() {
        logger.info("Executando updateProdutoEstoqueMerge()");

        Integer quantidade = 3;

        TypedQuery<ProdutoEstoque> queryProdEst = em.createNamedQuery("ProdutoEstoque.PorProdLojista", ProdutoEstoque.class);
        queryProdEst.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryProdEst.setParameter("idProduto", 1); // produto 1
        queryProdEst.setParameter("idUsuario", 1); // lojista 1
        ProdutoEstoque prodEstAtual = (ProdutoEstoque) queryProdEst.getSingleResult();
        
        assertNotNull(prodEstAtual);

        prodEstAtual.setQuantidade(quantidade);

        em.clear();
        em.merge(prodEstAtual);
        em.flush();
        
        assertEquals(queryProdEst.getSingleResult().getQuantidade(), quantidade);
    }
    
    @Test
    public void deleteProdutoEstoque() {
        logger.info("Executando deleteProdutoEstoque()");

        long idLojista = 8;

        Query queryProdEst = em.createNamedQuery("ProdutoEstoque.PorProdLojista", ProdutoEstoque.class);
        queryProdEst.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryProdEst.setParameter("idProduto", 17); // produto 17
        queryProdEst.setParameter("idUsuario", 2); // lojista 2
        ProdutoEstoque produtoAtual = (ProdutoEstoque) queryProdEst.getSingleResult();

        assertNotNull(produtoAtual);

        em.remove(produtoAtual);
        em.flush();

        assertEquals(0, queryProdEst.getResultList().size());

    }

}
