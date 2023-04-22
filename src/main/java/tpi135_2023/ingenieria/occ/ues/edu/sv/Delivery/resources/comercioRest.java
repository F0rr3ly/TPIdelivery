package tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.resources;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.io.Serializable;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.Comercio;

/**
 *
 * @author EnriqueVII
 */
@Stateless
@LocalBean
public class comercioRest extends abstractDataAccess<Comercio> implements Serializable{

    private EntityManagerFactory emf;

    @PersistenceContext(unitName = "tpi135_2023.ingenieria.occ.ues.edu.sv_Delivery_war_1.0.0-SNAPSHOTPU")
    EntityManager em;

    public comercioRest() {
        super(Comercio.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

//    @Transactional
//    public List<Comercio> findAll(){
//        EntityManager em = emf.createEntityManager();
//        List<Comercio> listaT = em.createNamedQuery("Comercio.findAll").getResultList();
//        em.close();
//        return listaT;
//    }
//    
//    public void InsertarComercio(Comercio comercio){
//        EntityManager em = emf.createEntityManager();
//        em.persist(comercio );
//    }
//    
}
