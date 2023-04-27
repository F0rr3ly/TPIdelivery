package tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.control;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.TipoComercio;

/**
 *
 * @author f0rr3ly
 */
@Stateless
public class TipoComercioBean {

 @PersistenceContext
 EntityManager em;
 public List<TipoComercio> allListTipoCom(){
     return em.createNamedQuery("TipoComercio.findAll").getResultList();
 }
 public void insertTipoCom(TipoComercio tipoCom){
     em.persist(tipoCom);
 }
 public TipoComercio findIdTipoCom(TipoComercio tipoCom){
     return em.find(TipoComercio.class ,tipoCom.getIdTipoComercio());
 }
 
}
