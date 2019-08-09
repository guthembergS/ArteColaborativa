
package br.com.artecolaborativa.model;

import com.sun.istack.internal.NotNull;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import static javax.swing.text.StyleConstants.Size;

/**
 *
 * @author guthemberg.souza
 */
@Entity
public class Usuario implements Serializable {

    @Id
    @Column(name = "ID_USUARIO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idUsuario;

    @NotNull
    @Column(name = "nome")
    protected String nome;

    @NotNull
    @Column(name = "senha")
    protected String senha;

    @NotNull
    @Column(name = "email")
    protected String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }
}
