
package tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.resources;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.Comercio;
/**
 *
 * @author EnriqueVII
 */
@Path("comercio")
@RequestScoped
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class comercioBean {
    @Inject
    comercioBean comerciobean;
    @GET
    public List<Comercio> listAll() {
        return comerciobean.listAll();
    } 
}
