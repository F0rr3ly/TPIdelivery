package tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.resources;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.boundary.RestResourcePattern;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.Comercio;

/**
 *
 * @author EnriqueVII
 */
@Path("comercio")
@RequestScoped
public class comercioBean {

    @Inject
    private comercioRest ComRe;

//    @POST
//    @Produces({MediaType.APPLICATION_JSON})
//    @Consumes({MediaType.APPLICATION_JSON})    
//    public Response Insertar(Comercio comercio, @Context UriInfo info) {
//        if (comercio != null) {
//            try {
//                ComRe.InsertarComercio(comercio);
//                if (comercio.getIdComercio() != null) {
//                    UriBuilder uriBuilder = info.getAbsolutePathBuilder();
//                    uriBuilder.path(comercio.getIdComercio().toString());
//                    return Response.created(uriBuilder.build()).build();
//                }
//
//            } catch (Exception ex) {
//                Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
//            }
//            return Response.status(Response.Status.BAD_REQUEST).header(RestResourcePattern.WRONG_PARAMETER, JsonbBuilder.create().toJson(comercio)).build();
//        }
//        return Response.status(Response.Status.BAD_REQUEST).header(RestResourcePattern.NULL_PARAMETER, null).build();
//    }
//    
    
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})   
    public Response crear(Comercio nuevo) {
        ComRe.crear(nuevo);
        return Response.ok(nuevo)
                .header("Registro-Creado", nuevo)
                .build();
    }
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findAll1() {
        Long a = ComRe.contar();
        return Response.ok(ComRe.findAll()).header("Contar", a).build();
    }

}
