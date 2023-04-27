/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.boundary;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
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

import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.control.ComercioBean;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.Comercio;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.ComercioTipoComercio;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.Sucursal;

/**
 *
 * @author EnriqueVII
 */
@Path("comercio")
@RequestScoped
public class ComercioRest {

    @Inject
    ComercioBean BeanForComercio;

    @GET
    public List<Comercio> listAll() {
        return BeanForComercio.ListAll();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response Insertar(Comercio comercio, @Context UriInfo info) {
        if (comercio != null) {
            try {
                BeanForComercio.InsertarComercio(comercio);
                if (comercio.getIdComercio() != null) {
                    UriBuilder uriBuilder = info.getAbsolutePathBuilder();
                    uriBuilder.path(comercio.getIdComercio().toString());
                    return Response.created(uriBuilder.build()).build();
                }

            } catch (Exception ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
            return Response.status(Response.Status.BAD_REQUEST).header(RestResourcePattern.WRONG_PARAMETER, JsonbBuilder.create().toJson(comercio)).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).header(RestResourcePattern.NULL_PARAMETER, null).build();
    }

    @GET
    @Path("/{id}")
    public Response FindID(@PathParam("id") Long id) {
        try {
            Comercio nuevo = new Comercio(id);
            Comercio encontrado = BeanForComercio.findcomercioById(nuevo);
            if (encontrado == null) {
                return Response.status(Response.Status.NOT_FOUND).header(RestResourcePattern.ID_NOT_FOUND, Collections.EMPTY_LIST).build();
            }
            return Response.ok().entity(encontrado).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).header(RestResourcePattern.WRONG_PARAMETER, Collections.EMPTY_LIST).build();
        }

    }

    @GET
    @Path("/{id}/tipocomercio")
    public Response listAlltipo(@PathParam("id") Long id) {
        Comercio nuevo = new Comercio(id);
        Comercio encontrado = BeanForComercio.findcomercioById(nuevo);
        if (encontrado != null) {
            return Response.ok(BeanForComercio.ListAlltp(id)).header(RestResourcePattern.CONTAR_REGISTROS, BeanForComercio.ListAlltp(id)).build();
        }
        return Response.status(Response.Status.NOT_FOUND).header(RestResourcePattern.ID_NOT_FOUND, Collections.EMPTY_LIST).build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/{id}/tipocomercio/{ID}")
    public Response InsertarTPC(@PathParam("id") int id, @PathParam("ID") int ID) {
        long idn = id;
        Comercio nuevo = new Comercio(idn);
        Comercio encontrado = BeanForComercio.findcomercioById(nuevo);
        if (encontrado != null) {
            BeanForComercio.InsertarTPC(id, ID);
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).header(RestResourcePattern.WRONG_PARAMETER, Collections.EMPTY_LIST).build();
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}/sucursal")
    //esto pa probar test 7 
    public Response insertarSucursal(@PathParam("id") int id, Sucursal sucursal, @Context UriInfo info) {
        long idn = id;
        Comercio nuevo = new Comercio(idn);
        Comercio encontrado = BeanForComercio.findcomercioById(nuevo);
        if (encontrado != null) {
            BeanForComercio.insertarSucursal(sucursal, idn);
            if (sucursal.getIdSucursal() != null) {
                    UriBuilder uriBuilder = info.getAbsolutePathBuilder();
                    uriBuilder.path(sucursal.getIdSucursal().toString());
                    return Response.created(uriBuilder.build()).build();
                }
            return Response.status(Response.Status.NOT_FOUND).header(RestResourcePattern.ID_NOT_FOUND, null).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).header(RestResourcePattern.WRONG_PARAMETER, Collections.EMPTY_LIST).build();
    }
    
    
}
