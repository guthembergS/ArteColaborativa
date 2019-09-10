/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artecolaborativa.test;

import br.com.artecolaborativa.model.ProdutoEstoque;
import br.com.artecolaborativa.model.VendaLojistaProduto;
import java.util.List;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author thiago
 */
public class VendaLojistaProdutoCrudTest extends GenericTest {

    @Test
    public void createVendaLojistaProduto() {
        logger.info("Executando createVendaLojistaProduto()");
        
        VendaLojistaProduto novaVenda = new VendaLojistaProduto();
        //produto estoque
        TypedQuery<ProdutoEstoque> queryProdEst = em.createNamedQuery("ProdutoEstoque.PorProdLojista", ProdutoEstoque.class);
        queryProdEst.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryProdEst.setParameter("idProduto", 3); // produto 14
        queryProdEst.setParameter("idUsuario", 1); // lojista 2
        ProdutoEstoque prodEstAtual = (ProdutoEstoque) queryProdEst.getSingleResult();
        
        assertNotNull(prodEstAtual);
        
        novaVenda.setProdutoEstoque(prodEstAtual);
        novaVenda.setDataVenda(getData(9, 10, 2019));
        novaVenda.setQuantidade(2);
        
        em.flush();
        
        
        TypedQuery<VendaLojistaProduto> queryVenda = em.createNamedQuery("VendaLojistaProduto.PorParametros", VendaLojistaProduto.class);
        queryVenda.setParameter("idProd", 3); // produto 14
        queryProdEst.setParameter("idLoj", 1); // lojista 2
        List<ProdutoEstoque> vendasProd = queryProdEst.getResultList();
        
        assertEquals(vendasProd.isEmpty(),false);
    }

    @Test
    public void readVendaLojistaProduto() {

    }

    @Test
    public void updateVendaLojistaProduto() {

    }

    @Test
    public void deleteVendaLojistaProduto() {

    }

}
