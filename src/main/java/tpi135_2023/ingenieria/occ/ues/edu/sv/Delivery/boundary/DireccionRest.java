/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.boundary;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import java.util.logging.Level;
import java.util.logging.Logger;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.control.DireccionBean;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.Direccion;

/**
 *
 * @author sayaka
 */
@RequestScoped
@Path("direccion")

public class DireccionRest {
    @Inject
    DireccionBean direccionB;
    
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response insertarDir(Direccion direccion, @Context UriInfo info){
        if (direccion != null) {
            try {
                direccionB.insertarDir(direccion);
                if (direccion.getIdDireccion() != null) {
                    UriBuilder uriBuilder = info.getAbsolutePathBuilder();
                    uriBuilder.path(direccion.getIdDireccion().toString());
                    return Response.created(uriBuilder.build()).build();
                }

            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            }
            return Response.status(Response.Status.BAD_REQUEST).header(RestResourcePattern.WRONG_PARAMETER, JsonbBuilder.create().toJson(direccion)).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).header(RestResourcePattern.NULL_PARAMETER, null).build();
    
    }
        
    
    
}
