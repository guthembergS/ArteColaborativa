/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artecolaborativa.test;

import br.com.artecolaborativa.model.ProdutoEstoque;
import br.com.artecolaborativa.model.VendaLojistaProduto;
import br.com.artecolaborativa.test.GenericTest;
import java.util.Date;
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
    public void createVendaLojistaProduto(){

        logger.info("Executando createVendaLojistaProduto()");

        VendaLojistaProduto novaVenda = new VendaLojistaProduto();

        int quantidadeBaixa = 2;

        //produto estoque
        TypedQuery<ProdutoEstoque> queryProdEst = em.createNamedQuery("ProdutoEstoque.PorProdLojista", ProdutoEstoque.class);
        queryProdEst.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryProdEst.setParameter("idProduto", 3); // produto 3
        queryProdEst.setParameter("idUsuario", 1); // lojista 1

        ProdutoEstoque prodEstAtual = (ProdutoEstoque) queryProdEst.getSingleResult();

        assertNotNull(prodEstAtual);

        novaVenda.setProdutoEstoque(prodEstAtual);
        novaVenda.setDataVenda(getData(9, 11, 2019));
        novaVenda.setQuantidade(quantidadeBaixa);

        em.persist(novaVenda);
        em.flush();

        TypedQuery<VendaLojistaProduto> queryVenda = em.createNamedQuery("VendaLojistaProduto.PorParametros", VendaLojistaProduto.class);
        queryVenda.setParameter("idProd", 3); // produto 3
        queryVenda.setParameter("idLoj", 1); // lojista 1

        List<VendaLojistaProduto> vendasProd = queryVenda.getResultList();

        assertEquals(vendasProd.isEmpty(), false);

    }

    @Test
    public void updateVendaLojistaProduto() {
        Integer quant = 3;
        TypedQuery<VendaLojistaProduto> queryVenda = em.createNamedQuery("VendaLojistaProduto.PorPId", VendaLojistaProduto.class);
        queryVenda.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryVenda.setParameter("idVenda", 7); 
        VendaLojistaProduto vendasProd = (VendaLojistaProduto) queryVenda.getSingleResult();

        assertNotNull(vendasProd);
        
        vendasProd.setQuantidade(quant);
        
        em.flush();
        
        vendasProd = (VendaLojistaProduto) queryVenda.getSingleResult();
        
        assertEquals(vendasProd.getQuantidade(), quant);
        
    }
    
     @Test
    public void updateVendaLojistaProdutoMerge() {
        Date dataVenda = getData(9, 11, 2019);
        TypedQuery<VendaLojistaProduto> queryVenda = em.createNamedQuery("VendaLojistaProduto.PorPId", VendaLojistaProduto.class);
        queryVenda.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryVenda.setParameter("idVenda", 8); 
        VendaLojistaProduto vendasProd = (VendaLojistaProduto) queryVenda.getSingleResult();

        assertNotNull(vendasProd);
        
        vendasProd.setDataVenda(dataVenda);
        
        em.clear();
        em.merge(vendasProd);
        em.flush();
        
        assertEquals(queryVenda.getSingleResult().getDataVenda(), dataVenda);
        
    
    }
    
        
    @Test
    public void deleteProdutoEstoque() {
        
        TypedQuery<VendaLojistaProduto> queryVenda = em.createNamedQuery("VendaLojistaProduto.PorPId", VendaLojistaProduto.class);
        queryVenda.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryVenda.setParameter("idVenda", 9); 
        VendaLojistaProduto vendaProd = (VendaLojistaProduto) queryVenda.getSingleResult();

        assertNotNull(vendaProd);
    
        em.remove(vendaProd);
        em.flush();

        assertEquals(0, queryVenda.getResultList().size());

    }
    
}
