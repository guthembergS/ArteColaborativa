/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artecolaborativa.test;

import br.com.artecolaborativa.model.Artesao;
import br.com.artecolaborativa.model.Endereco;
import br.com.artecolaborativa.model.Lojista;
import static br.com.artecolaborativa.test.GenericTest.logger;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author thiago
 */
public class LojistaCrudTest extends GenericTest {

    @Test
    public void createLojista() {

        logger.info("Executando createLojista()");

        String nome = "Thiago Antonio";
        Double taxa = 5.0;
        Double aluguel = 50.00;
        String email = "taoalu@gmail.com";
        long idEndereco = 2;

        //Query para buscar o endereço do lojista       
        TypedQuery<Endereco> enderecoQuery = em.createNamedQuery("Endereco.PorId", Endereco.class);
        enderecoQuery.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        enderecoQuery.setParameter("id", idEndereco);
        Endereco endereco = enderecoQuery.getSingleResult();

        assertNotNull(enderecoQuery);

        Lojista lojista = new Lojista();

        lojista.setNome(nome);
        lojista.setTaxaVenda(taxa);
        lojista.setAluguel(aluguel);
        lojista.setEndereco(endereco);

        em.persist(lojista);
        em.flush();

        TypedQuery<Lojista> queryVerificaLojista = em.createNamedQuery("Lojista.PorNome", Lojista.class);
        queryVerificaLojista.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryVerificaLojista.setParameter("nome", nome);

        assertNotNull(queryVerificaLojista.getSingleResult());

    }

}
