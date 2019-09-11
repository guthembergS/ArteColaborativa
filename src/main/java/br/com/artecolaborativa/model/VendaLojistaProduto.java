package br.com.artecolaborativa.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author guto
 */
@Entity
@Table(name = "VENDA_LOJISTA_PRODUTO")
@NamedQueries(
        {
            @NamedQuery(
                    name = "VendaLojistaProduto.PorParametros",
                    query = "SELECT v FROM VendaLojistaProduto v WHERE v.produtoEstoque.produto.idProduto = :idProd and v.produtoEstoque.lojista.idUsuario = :idLoj"
            ),
            @NamedQuery(
                    name = "VendaLojistaProduto.PorPId",
                    query = "SELECT v FROM VendaLojistaProduto v WHERE v.idVendaLojista = :idVenda"
            )
        }
)
public class VendaLojistaProduto implements Serializable{
    
    @Id
    @Column(name = "ID_VENDA_LOJISTA")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idVendaLojista;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_ESTOQUE", referencedColumnName = "ID_PRODUTO_ESTOQUE",nullable = false )
    protected ProdutoEstoque produtoEstoque;
    
    @Temporal(TemporalType.DATE)
    @Column(name="DATA_VENDA")
    protected Date dataVenda;
    
    @Column(name="QUANTIDADE")
    protected Integer quantidade;
    
    public Long getIdVenda(){
        return idVendaLojista;
    }
    
    public Date getDataVenda(){
        return dataVenda;
    }
    
    public void setDataVenda(Date dataVenda){
        this.dataVenda = dataVenda;
    }
    
    public ProdutoEstoque getProdutoEstoque(){
        return produtoEstoque;
    }
    
    public void setProdutoEstoque(ProdutoEstoque produtoEstoque){
        this.produtoEstoque = produtoEstoque;
    }
    
    public void setQuantidade(Integer quant){
        this.quantidade = quant;
    }
    
    public Integer getQuantidade(){
        return quantidade;
    }
}
