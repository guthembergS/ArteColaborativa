
package br.com.artecolaborativa.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    @Column(name = "NOME")
    protected String nome;

    @Column(name = "SENHA")
    protected String senha;

    @Column(name = "EMAIL")
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
