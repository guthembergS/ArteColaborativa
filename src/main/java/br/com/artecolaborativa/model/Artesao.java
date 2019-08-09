/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artecolaborativa.model;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author guthemberg.souza
 */
@Entity
@DiscriminatorValue(value = "A")
public class Artesao extends Usuario implements Serializable{
    
    
    
}
