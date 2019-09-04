package br.com.artecolaborativa.test;

import br.com.artecolaborativa.model.Artesao;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.Query;
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
    
    @Test
    public void updateArtesao() {
        logger.info("Executando createArtesaoMerge()");
        String email = "entre.linhas@mail.com";
        
        TypedQuery<Artesao> queryArtesao = em.createNamedQuery("Artesao.PorNome", Artesao.class);
        queryArtesao.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryArtesao.setParameter("nome", "Thais Souza");
        Artesao artesaoAtual =(Artesao) queryArtesao.getSingleResult();
        
        assertNotNull(queryArtesao.getSingleResult());
        
        artesaoAtual.setEmail(email);
        
        em.flush();

        assertEquals(queryArtesao.getSingleResult().getEmail(),email);
    }
    
    @Test
    public void updateArtesaoMerge() {
        logger.info("Executando createArtesaoMerge()");
        String marca = "Ojuara Arte Cactos ";
        String senha = "ojuaracactos";
        
        TypedQuery<Artesao> queryArtesao = em.createNamedQuery("Artesao.PorNome", Artesao.class);
        queryArtesao.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryArtesao.setParameter("nome", "Ojuara Silva");
        Artesao artesaoAtual =(Artesao) queryArtesao.getSingleResult();
        
        assertNotNull(queryArtesao.getSingleResult());
        
        artesaoAtual.setMarca(marca);
        artesaoAtual.setSenha(senha);
        
        em.clear();
        em.merge(artesaoAtual);
        em.flush();
        
        assertEquals(queryArtesao.getSingleResult().getSenha(),senha);
        assertEquals(queryArtesao.getSingleResult().getMarca(),marca);
    }
    
    @Test
    public void deleteArtesao() {
        logger.info("Executando deleteArtesao()");

        long idArtesao = 7;

        Query queryArtesao = em.createNamedQuery("Artesao.PorId",Artesao.class);
        queryArtesao.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryArtesao.setParameter("idUsuario", 7);
        Artesao artesaoAtual =(Artesao) queryArtesao.getSingleResult();
        
        assertNotNull(artesaoAtual);

        em.remove(artesaoAtual);
        em.flush();

        assertEquals(0, queryArtesao.getResultList().size());

    }

    
}
