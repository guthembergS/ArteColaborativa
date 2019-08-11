package br.com.artecolaborativa.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 *
 * @author guthemberg.souza
 */
@Entity
@DiscriminatorValue(value = "A")
public class Artesao extends Usuario implements Serializable{
    
    @OneToMany(mappedBy = "ARTESAO", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected List<Produto> produtos = new ArrayList<>();

    
    public List<Produto> getProdutos() {
        return produtos;
    }

    public boolean setProdutos(Produto produto) {
        return this.produtos.add(produto);
    }
}
