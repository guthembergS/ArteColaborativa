/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artecolaborativa.test;

import br.com.artecolaborativa.model.Artesao;
import br.com.artecolaborativa.model.Endereco;
import static br.com.artecolaborativa.test.GenericTest.logger;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author thiago
 */
public class EnderecoCrudTest extends GenericTest {

    @Test
    public void createEndereco() {

        logger.info("Executando createEndereco()");

        String bairro = "Caetes I";
        String cep = "53530785";
        String cidade = "Abreu e Lima";
        String estado = "Pernambuco";
        String logradouro = "Rua das amelias";

        //Criação do Objeto que será persistido
        Endereco endereco = new Endereco();

        endereco.setBairro(bairro);
        endereco.setCep(cep);
        endereco.setCidade(cidade);
        endereco.setEstado(estado);
        endereco.setLogadouro(logradouro);

        //Sincronização com o banco
        em.persist(endereco);
        em.flush();

        assertNotNull(endereco.getId());

    }

    @Test
    public void updateEndereco() {

        logger.info("Executando updateEndereco()");

        String bairro = "Caetes II";
        String cep = "53530785";
        String cidade = "Abreu e Lima";
        String estado = "Pernambuco";
        String logradouro = "Rua das amelias";
        long idEndereco = 3;

        //Busca pelo endereço que será atualizado
        TypedQuery<Endereco> enderecoAntigo = em.createNamedQuery("Endereco.PorId", Endereco.class);
        enderecoAntigo.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        enderecoAntigo.setParameter("id", idEndereco);
        Endereco endereco = enderecoAntigo.getSingleResult();

        //Verifica se houve retorno
        assertNotNull(enderecoAntigo);

        endereco.setBairro(bairro);
        endereco.setCep(cep);
        endereco.setCidade(cidade);
        endereco.setEstado(estado);
        endereco.setLogadouro(logradouro);

        //Sincroniza alterações com o banco
        em.flush();

        //Executa a query para confirmar a atualização
        Endereco enderecoAtualizado = enderecoAntigo.getSingleResult();

        assertEquals(bairro, enderecoAtualizado.getBairro());
        assertEquals(estado, enderecoAtualizado.getEstado());
        assertEquals(cidade, enderecoAtualizado.getCidade());
        assertEquals(cep, enderecoAtualizado.getCep());
        assertEquals(logradouro, enderecoAtualizado.getLogadouro());

    }

    @Test
    public void updateEnderecoMerge() {

        logger.info("Executando updateEnderecoMerge()");

        String bairro = "Caetes II";
        String cep = "53530785";
        String cidade = "Abreu e Lima";
        String estado = "Pernambuco";
        String logradouro = "Rua das amelias";
        long idEndereco = 3;

        //Busca pelo endereço que será atualizado
        TypedQuery<Endereco> enderecoAntigo = em.createNamedQuery("Endereco.PorId", Endereco.class);
        enderecoAntigo.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        enderecoAntigo.setParameter("id", idEndereco);
        Endereco endereco = enderecoAntigo.getSingleResult();

        //Verifica se houve retorno
        assertNotNull(enderecoAntigo);

        endereco.setBairro(bairro);
        endereco.setCep(cep);
        endereco.setCidade(cidade);
        endereco.setEstado(estado);
        endereco.setLogadouro(logradouro);

        /**
         * Limpa o entity manager para simular o uso de um entity manager vazio,
         * realiza o merge com as alterações e sincroniza com o banco, esse
         * cenário ocorre quando existe mais de um em, um para leitura e um para
         * escrita por exemplo o de leitura terá o objeto mas o de escrita não,
         * nesse caso o merge é relizado no em de escrita e a partir dele as
         * informações vão pro banco.
         *
         */
        em.clear();
        em.merge(endereco);
        em.flush();

        //Executa a query para confirmar a atualização
        Endereco enderecoAtualizado = enderecoAntigo.getSingleResult();

        assertEquals(bairro, enderecoAtualizado.getBairro());
        assertEquals(estado, enderecoAtualizado.getEstado());
        assertEquals(cidade, enderecoAtualizado.getCidade());
        assertEquals(cep, enderecoAtualizado.getCep());
        assertEquals(logradouro, enderecoAtualizado.getLogadouro());

    }

    @Test
    public void deleteEndereco() {

        logger.info("Executando deleteEndereco()");

        long idEndereco = 5;

        TypedQuery<Endereco> queryEndereco = em.createNamedQuery("Endereco.PorId", Endereco.class);
        queryEndereco.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryEndereco.setParameter("id", idEndereco);
        Endereco endereco = queryEndereco.getSingleResult();

        em.remove(endereco);
        em.flush();

        assertEquals(0, queryEndereco.getResultList().size());

    }

}
