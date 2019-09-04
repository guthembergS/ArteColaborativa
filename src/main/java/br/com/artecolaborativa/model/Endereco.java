package br.com.artecolaborativa.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author guthemberg.souza
 */

@Entity
@NamedQueries(
        {
            @NamedQuery(
                    name = "Endereco.PorId",
                    query = "SELECT e FROM Endereco e WHERE e.idEndereco = :id"
            )
        }
)

public class Endereco implements Serializable{
    @Id
    @Column(name = "ID_ENDERECO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idEndereco;
        
    @Column(name = "LOGADOURO")
    protected String logadouro;

    @Column(name = "BAIRRO")
    protected String bairro;
    
    @Column(name = "CIDADE")
    protected String cidade;
    
    @Column(name = "ESTADO")
    protected String estado;

    @Column(name = "CEP")
    protected String cep;
    
    @Column(name = "NUMERO")
    protected String numero;
    
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
    
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
