/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artecolaborativa.model;

import java.io.Serializable;
import java.util.Date;
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
import static javax.swing.text.StyleConstants.Size;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author guto
 */
@Entity
@Table(name = "PRODUTO_ESTOQUE")
@NamedQueries(
        {
            @NamedQuery(
                    name = "ProdutoEstoque.PorProdLojista",
                    query = "SELECT p FROM ProdutoEstoque p WHERE p.produto.idProduto = :idProduto and p.lojista.idUsuario = :idUsuario"
            )
        }
)
public class ProdutoEstoque implements Serializable{

    @Id
    @Column(name = "ID_PRODUTO_ESTOQUE")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idProdutoEstoque;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_PRODUTO", referencedColumnName = "ID_PRODUTO",nullable = false )
    protected Produto produto;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_LOJISTA", referencedColumnName = "ID_USUARIO",nullable = false )
    protected Lojista lojista;
  
    @NotNull
    @Min(value=0)
    @Column(name = "QUANTIDADE")
    protected Integer quantidade;
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProdutoEstoque)) {
            return false;
        }
        ProdutoEstoque other = (ProdutoEstoque) object;
        if ((this.idProdutoEstoque == null && other.idProdutoEstoque != null) || (this.idProdutoEstoque != null && !this.idProdutoEstoque.equals(other.idProdutoEstoque))) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(getIdProdutoEstoque());
    }
    
    public void setProduto(Produto produto){
        this.produto = produto;
    }
    
    public Produto getProduto() {
        return produto;
    }
    
    public void setLojista(Lojista lojista){
        this.lojista = lojista;
    }
    
    public Lojista getLojista() {
        return lojista;
    }
    
    public void setQuantidade(Integer quantidade){
        this.quantidade = quantidade;
    }
    
    public Integer getQuantidade() {
        return quantidade;
    }
    
    public Long getIdProdutoEstoque(){
        return idProdutoEstoque;
    }
    
}
