package br.com.artecolaborativa.test;

import br.com.artecolaborativa.model.Artesao;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author thiago
 */
public class ArtesaoCrudTest extends GenericTest {

    @Test
    public void createArtesao() {
        logger.info("Executando createArtesao()");

        String nome = "Thiago Antonio";
        String marca = "App Arts";
        String senha = "123456";
        String email = "thiago.antonio@mail.com";
       
        Artesao artesao = new Artesao();
        artesao.setNome(nome);
        artesao.setEmail(email);
        artesao.setSenha(senha);
        artesao.setMarca(marca);
        
        em.persist(artesao);
        em.flush();

        TypedQuery<Artesao> queryArtesao = em.createNamedQuery("Artesao.PorNome", Artesao.class);
        queryArtesao.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryArtesao.setParameter("nome", nome);

        assertNotNull(queryArtesao.getSingleResult());
    }

    
}
