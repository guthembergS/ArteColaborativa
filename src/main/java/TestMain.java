
import br.com.artecolaborativa.model.Artesao;
import br.com.artecolaborativa.model.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author guto
 */
public class TestMain {

    public static void main(String[] args) {

        Artesao usuario1 = new Artesao();

        usuario1.setNome("Moema Souza");
        usuario1.setEmail("m.souza@mail.com");
        usuario1.setSenha("123456");

        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction et;

        try {
            //EntityManagerFactory realiza a leitura das informações relativas à "persistence-unit".
            emf = Persistence.createEntityManagerFactory("arte_colaborativa");
            em = emf.createEntityManager(); //Criação do EntityManager.
            et = em.getTransaction(); //Recupera objeto responsável pelo gerenciamento de transação.
            et.begin();
            em.persist(usuario1);
            et.commit();
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }

}
