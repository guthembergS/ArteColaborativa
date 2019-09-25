
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
    @NotBlank
    @NotNull
    @Size (max = 50)
    @Column(name = "NOME")
    protected String nome;
    @NotBlank
    @NotNull
    @Size (min = 3,max = 20)
    @Pattern (regexp = "((?=.*\\p{Digit}) (?=.*\\p{Lower}) (?=.*\\p{Upper}).{3,30})")
    @Column(name = "SENHA")
    protected String senha;
    @Email
    @NotBlank
    @NotNull
    @Size (max = 35)
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
