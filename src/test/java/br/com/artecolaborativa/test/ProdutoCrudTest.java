/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artecolaborativa.test;

import br.com.artecolaborativa.model.Artesao;
import br.com.artecolaborativa.model.Endereco;
import br.com.artecolaborativa.model.Produto;
import static br.com.artecolaborativa.test.GenericTest.logger;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author thiago
 */
public class ProdutoCrudTest extends GenericTest {

    @Test
    public void createProduto() {

        Produto produto = new Produto();

        String descricao = "Novo Produto de Teste";
        Double preco = 10.00;

        long idArtesao = 3;

        TypedQuery<Artesao> query = em.createNamedQuery("Artesao.PorId", Artesao.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("idUsuario", idArtesao);

        Artesao artesao = (Artesao) query.getSingleResult();

        assertNotNull(artesao);

        produto.setDescricao(descricao);
        produto.setPreco(preco);
        produto.setArtesao(artesao);

        em.persist(produto);
        em.flush();

        assertNotNull(produto.getArtesao().getIdUsuario());

    }

    @Test
    public void updateProduto() {

        logger.info("Executando updateProduto()");

        String descricao = "Produto Update Teste";
        Double preco = 15.00;

        long idProduto = 17;

        TypedQuery<Produto> query = em.createNamedQuery("Produto.PorId", Produto.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("idProduto", idProduto);

        Produto produtoUpdate = query.getSingleResult();

        assertNotNull(produtoUpdate);

        produtoUpdate.setDescricao(descricao);
        produtoUpdate.setPreco(preco);

        em.flush();

        produtoUpdate = query.getSingleResult();

        assertEquals(descricao, produtoUpdate.getDescricao());
        assertEquals(preco, produtoUpdate.getPreco());
    }

    @Test
    public void updateProdutoMerge() {

        logger.info("Executando updateProdutoMerge()");

        String descricao = "Produto Update Teste";
        Double preco = 15.00;

        long idProduto = 17;

        TypedQuery<Produto> query = em.createNamedQuery("Produto.PorId", Produto.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("idProduto", idProduto);

        Produto produtoUpdate = query.getSingleResult();

        assertNotNull(produtoUpdate);

        produtoUpdate.setDescricao(descricao);
        produtoUpdate.setPreco(preco);

        em.clear();
        em.merge(produtoUpdate);
        em.flush();

        produtoUpdate = query.getSingleResult();

        assertEquals(descricao, produtoUpdate.getDescricao());
        assertEquals(preco, produtoUpdate.getPreco());

    }

    @Test
    public void deleteProduto() {

        logger.info("Executando deleteProduto()");

        long idProduto = 18;

        TypedQuery<Produto> queryProduto = em.createNamedQuery("Produto.PorId", Produto.class);
        queryProduto.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryProduto.setParameter("idProduto", idProduto);

        Produto produto = queryProduto.getSingleResult();

        em.remove(produto);
        em.flush();

        assertEquals(0, queryProduto.getResultList().size());

    }

}
