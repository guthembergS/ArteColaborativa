/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artecolaborativa.model;

import java.util.List;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 *
 * @author guto
 */
class ValidadorVendaProduto implements ConstraintValidator<ValidaVendaProduto, String>{

    protected EntityManager em;
    List<ProdutoEstoque> produtoEstoque = (List<ProdutoEstoque>) new ProdutoEstoque();
    
    @Override
    public void initialize(ValidaVendaProduto a) {
        
        TypedQuery<ProdutoEstoque> queryProdEst = em.createNamedQuery("ProdutoEstoque.PorProdLojista", ProdutoEstoque.class);
        queryProdEst.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryProdEst.setParameter("idProduto", 3); // produto 3
        queryProdEst.setParameter("idUsuario", 1); // lojista 1

        ProdutoEstoque prodEstAtual = (ProdutoEstoque) queryProdEst.getSingleResult();

        
        
    }

    @Override
    public boolean isValid(String t, ConstraintValidatorContext cvc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
