package br.com.artecolaborativa.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author guthemberg.souza
 */
public class Lojista extends Usuario implements Serializable{
    
    /*@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "LOJISTA_ARTESAO", joinColumns = {
        @JoinColumn(name = "ID_LOJISTA", referencedColumnName = "ID_USUARIO", nullable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "ID_ARTESAO", referencedColumnName = "ID_USUARIO", nullable = false)
            }
    )
    public List<Artesao> pratos = new ArrayList<Artesao>();
    */    

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, optional = true)
    @JoinColumn(name = "ID_ENDERECO", referencedColumnName = "ID_ENDERECO")
    protected Endereco cartaoCredito;
    
    @Column(name = "aluguel")
    public double aluguel;
    
    @Column(name = "taxaDeVenda")
    public double taxaDeVenda;
    
}