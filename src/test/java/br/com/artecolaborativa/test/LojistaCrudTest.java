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
        String senha = "1234%%5467";
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
        lojista.setEmail(email);
        lojista.setSenha(senha);

        em.persist(lojista);
        em.flush();

        TypedQuery<Lojista> queryVerificaLojista = em.createNamedQuery("Lojista.PorNome", Lojista.class);
        queryVerificaLojista.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryVerificaLojista.setParameter("nome", nome);

        assertNotNull(queryVerificaLojista.getSingleResult());

    }

    @Test
    public void updateLojista() {

        logger.info("Executando updateLojista()");

        String email = "emailUpdateLojista@mail.com";

        TypedQuery<Lojista> queryLojista = em.createNamedQuery("Lojista.PorNome", Lojista.class);
        queryLojista.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryLojista.setParameter("nome", "Horta Intinerante");
        Lojista lojistaAtual = (Lojista) queryLojista.getSingleResult();

        assertNotNull(lojistaAtual);

        lojistaAtual.setEmail(email);

        em.flush();

        assertEquals(queryLojista.getSingleResult().getEmail(), email);
    }

    @Test
    public void updateLojistaMerge() {

        logger.info("Executando updateLojistaMerge()");

        Double vlAluguel = 80.00;
        String senha = "ojuaracactos";

        TypedQuery<Lojista> queryLojista = em.createNamedQuery("Lojista.PorNome", Lojista.class);
        queryLojista.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryLojista.setParameter("nome", "Horta Intinerante");
        Lojista lojistaAtual = (Lojista) queryLojista.getSingleResult();

        assertNotNull(lojistaAtual);

        lojistaAtual.setSenha(senha);
        lojistaAtual.setAluguel(vlAluguel);

        em.clear();
        em.merge(lojistaAtual);
        em.flush();

        assertEquals(queryLojista.getSingleResult().getSenha(), senha);
        assertEquals(queryLojista.getSingleResult().getAluguel(), vlAluguel);
    }

    @Test
    public void deleteLojista() {

        logger.info("Executando deleteLojista()");

        long idLojista = 8;

        Query queryLojista = em.createNamedQuery("Lojista.PorId", Lojista.class);
        queryLojista.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryLojista.setParameter("idUsuario", idLojista);
        Lojista lojistaAtual = (Lojista) queryLojista.getSingleResult();

        assertNotNull(lojistaAtual);

        em.remove(lojistaAtual);
        em.flush();

        assertEquals(0, queryLojista.getResultList().size());

    }

}
