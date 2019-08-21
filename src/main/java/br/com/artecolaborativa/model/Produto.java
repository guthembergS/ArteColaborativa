package br.com.artecolaborativa.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author guto
 */
@Entity
@Table
public class Produto implements Serializable {
    @Id
    @Column(name = "ID_PRODUTO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idProduto;
    
    @Column(name = "DESCRICAO")
    protected String descricao;
    
    @Column(name = "PRECO")
    protected Double preco;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_ARTESAO", referencedColumnName = "ID_USUARIO",nullable = false )
    protected Artesao artesao;
    
    public Long getIdProduto() {
        return idProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
    
    
    public Artesao getArtesao() {
        return artesao;
    }

    public void setArtesao(Artesao artsao) {
        this.artesao = artesao;
    }
}
