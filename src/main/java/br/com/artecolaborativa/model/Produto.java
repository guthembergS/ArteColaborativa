package br.com.artecolaborativa.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author guto
 */
@Entity
@Table(name = "PRODUTO")
@NamedQueries(
        {
            @NamedQuery(
                    name = "Produto.PorId",
                    query = "SELECT p FROM Produto p WHERE p.idProduto = :idProduto"
            )            
        }
)
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
    @JoinColumn(name = "ID_ARTESAO", referencedColumnName = "ID_USUARIO", nullable = false)
    protected Artesao artesao;

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.idProduto == null && other.idProduto != null) || (this.idProduto != null && !this.idProduto.equals(other.idProduto))) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(getIdProduto());
    }
    
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

    public void setArtesao(Artesao artesao) {
        this.artesao = artesao;
    }
}
