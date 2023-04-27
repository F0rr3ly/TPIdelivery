/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.control;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.Direccion;

/**
 *
 * @author sayaka
 */
@Stateless
public class DireccionBean {
    @PersistenceContext
    EntityManager em;
    public void insertarDir(Direccion direccion){
        em.persist(direccion);
    }
}
