package tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.boundary;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.boundary.RestResourcePattern;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.control.TipoComercioBean;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.TipoComercio;

/**
 *
 * @author f0rr3ly
 */
@Path("tipocomercio")
@RequestScoped
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class TipoComercioRest {
  @Inject
   TipoComercioBean TipoComBean;
  @GET
  public List<TipoComercio> allList(){
      return TipoComBean.allListTipoCom();
  }
  @POST
  public Response insertTipoCom(TipoComercio tipoCom,@Context UriInfo info){
      if (tipoCom != null) {
            try {
                TipoComBean.insertTipoCom(tipoCom);
                if(tipoCom.getIdTipoComercio()!=null){
                    UriBuilder uriBuilder=info.getAbsolutePathBuilder();
                    uriBuilder.path(tipoCom.getIdTipoComercio().toString());
                    return Response.created(uriBuilder.build()).build();
                }
     
            } catch (Exception ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,ex.getMessage(),ex);
            }
            return Response.status(Response.Status.BAD_REQUEST).header(RestResourcePattern.WRONG_PARAMETER,JsonbBuilder.create().toJson(tipoCom)).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).header(RestResourcePattern.NULL_PARAMETER, null).build();
  }
  @GET
    @Path("/{id}")
    public Response findIdTipoCom(@PathParam("id") int id) {
        try {
            TipoComercio nuevoEn = new TipoComercio(id);
            TipoComercio encontradoEn = TipoComBean.findIdTipoCom(nuevoEn);
            if (encontradoEn == null) {
                return Response.status(Response.Status.NOT_FOUND).header(RestResourcePattern.ID_NOT_FOUND, Collections.EMPTY_LIST).build();
            }
            return Response.ok().entity(encontradoEn).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).header(RestResourcePattern.WRONG_PARAMETER, Collections.EMPTY_LIST).build();
        }

    }
}
