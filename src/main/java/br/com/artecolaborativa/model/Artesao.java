package br.com.artecolaborativa.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author guthemberg.souza
 */
@Entity
@DiscriminatorValue(value = "A")
@NamedQueries(
        {
            @NamedQuery(
                    name = "Artesao.PorNome",
                    query = "SELECT a FROM Artesao a WHERE a.nome = :nome"
            )
        }
)


public class Artesao extends Usuario implements Serializable{
    
    @OneToMany(mappedBy = "artesao", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected List<Produto> produtos = new ArrayList<>();

    @Column(name="MARCA")
    protected String marca;
    
    public List<Produto> getProdutos() {
        return produtos;
    }

    public boolean setProdutos(Produto produto) {
        return this.produtos.add(produto);
    }
    
    public String getMarca() {
        return marca;
    }

    public void setMarca(String nomeMarca) {
        this.marca = nomeMarca;
    }
}
