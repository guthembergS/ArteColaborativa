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
public class Endereco implements Serializable{
    @Id
    @Column(name = "ID_ENDERECO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idEndereco;
        
    @NotNull
    @Column(name = "LOGADOURO")
    protected String logadouro;

    @NotNull
    @Column(name = "BAIRRO")
    protected String bairro;
    
    @NotNull
    @Column(name = "CIDADE")
    protected String cidade;
    
    @NotNull
    @Column(name = "ESTADO")
    protected String estado;

    @Column(name = "CEP")
    protected String cep;

    public String getLogadouro() {
        return logadouro;
    }

    public void setLogadouro(String logadouro) {
        this.logadouro = logadouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public long getId() {
        return idEndereco;
    }
}
