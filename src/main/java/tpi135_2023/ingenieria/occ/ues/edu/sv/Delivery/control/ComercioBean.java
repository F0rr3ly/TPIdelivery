package tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.control;

import jakarta.ejb.Stateless;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.Comercio;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.ComercioTipoComercio;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.ComercioTipoComercioPK;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.Sucursal;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.TipoComercio;

/**
 *
 * @author EnriqueVII
 */
@Stateless
public class ComercioBean {
    @PersistenceContext
    EntityManager em;
    
    public void insertarSucursal(Sucursal sucursal, long id ){
        Comercio comercio = new Comercio(id);
        Comercio comercioid = findcomercioById(comercio);
        sucursal.setIdComercio(comercioid);
        em.persist(sucursal);
    }
    
    public List<Comercio> ListAll(){
        return em.createNamedQuery("Comercio.findAll").getResultList();
    }
    public void InsertarComercio(Comercio comercio){
        em.persist(comercio);
    }
    public Comercio findcomercioById(Comercio comercio){
        return em.find(Comercio.class, comercio.getIdComercio());
    }
    public int ListAlltp(Long idComercio){
        if (idComercio != null && em != null) {
            Query q = em.createNamedQuery("ComercioTipoComercio.countByIdPersona");
            q.setParameter("idComercio", idComercio);
            return ((Long) q.getSingleResult()).intValue(); 
        }
        return 0; 
    }
    public TipoComercio findTipoComercioById(TipoComercio tipoComercio){
     return em.find(TipoComercio.class ,tipoComercio.getIdTipoComercio());
 }
    public void InsertarTPC(int idComercio , int idTipocomercio){
        long idComerciol = idComercio;
        Comercio BuscarC=new Comercio(idComerciol);
        TipoComercio BuscarT=new TipoComercio(idTipocomercio);
        Comercio encontradoC=findcomercioById(BuscarC);
        TipoComercio encontradoT=findTipoComercioById(BuscarT);
        ComercioTipoComercio comercioT=new ComercioTipoComercio(new ComercioTipoComercioPK(idComercio,encontradoT.getIdTipoComercio()));
        comercioT.setComercio(encontradoC);
        comercioT.setTipoComercio(encontradoT);
        em.persist(comercioT);
    }
}
