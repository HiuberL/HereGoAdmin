package com.herego.api.controllers;

import java.sql.SQLException;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import com.herego.api.exceptions.ConnectionException;
import com.herego.api.exceptions.CrudException;
import com.herego.api.exceptions.NotFoundException;
import com.herego.api.models.Community;
import com.herego.api.models.GenericResponse;
import com.herego.api.services.CommunityService;

@Path("/community")
public class CommunityController {
        @Inject
        CommunityService communityService;

        // @GET
        // @Path("/")
        // @Produces(MediaType.APPLICATION_JSON)
        // @Operation(summary = "getUsers", description = "Obtiene todos los usuario
        // según su ID")
        // @APIResponses(value = {
        // @APIResponse(responseCode = "200", description = "Success", content =
        // @Content(mediaType = "application/json", schema = @Schema(implementation =
        // GetUsersResponse.class))),
        // @APIResponse(responseCode = "503", description = "Error", content =
        // @Content(mediaType = "application/json", schema = @Schema(implementation =
        // GenericResponse.class))),
        // @APIResponse(responseCode = "501", description = "Error", content =
        // @Content(mediaType = "application/json", schema = @Schema(implementation =
        // GenericResponse.class))),
        // @APIResponse(responseCode = "404", description = "Not Found", content =
        // @Content(mediaType = "application/json", schema = @Schema(implementation =
        // GenericResponse.class)))
        // })
        // public Response getUserById(
        // @QueryParam("search") @NotNull(message = "Pámetros search no puede ser nulo")
        // String search,
        // @QueryParam("page") @NotNull(message = "Parámetro page es requerido") int
        // page,
        // @QueryParam("max") @NotNull(message = "Parámetro max es requerido") int max)
        // throws ConnectionException, NotFoundException, SQLException {
        // return Response.ok().entity(this.userService.getUsers(search, page,
        // max)).build();
        // }

        // @GET
        // @Path("/{id}")
        // @Produces(MediaType.APPLICATION_JSON)
        // @Operation(summary = "getUserById", description = "Obtiene un usuario según
        // su ID")
        // @APIResponses(value = {
        // @APIResponse(responseCode = "200", description = "Success", content =
        // @Content(mediaType = "application/json", schema = @Schema(implementation =
        // GetUsersResponse.class))),
        // @APIResponse(responseCode = "503", description = "Error", content =
        // @Content(mediaType = "application/json", schema = @Schema(implementation =
        // GenericResponse.class))),
        // @APIResponse(responseCode = "501", description = "Error", content =
        // @Content(mediaType = "application/json", schema = @Schema(implementation =
        // GenericResponse.class))),
        // @APIResponse(responseCode = "404", description = "Not Found", content =
        // @Content(mediaType = "application/json", schema = @Schema(implementation =
        // GenericResponse.class)))
        // })
        // public Response getUserById(@PathParam("id") String idUser)
        // throws ConnectionException, NotFoundException, SQLException {
        // return Response.ok().entity(this.userService.getUserById(idUser)).build();
        // }

        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        @Operation(summary = "createUser", description = "Crea un nuevo usuario")
        @APIResponses(value = {
                        @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
                        @APIResponse(responseCode = "503", description = "Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
                        @APIResponse(responseCode = "501", description = "Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
                        @APIResponse(responseCode = "400", description = "Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class)))
        })
        public Response createUser(
                        @HeaderParam("phone") @NotBlank(message = "El teléfono es necesario para contemplar la solicitud") String phone,
                        @RequestBody Community request)
                        throws ConstraintViolationException, ConnectionException, NotFoundException, SQLException,
                        CrudException {
                return Response.ok().entity(this.communityService.createCommunity(request, phone)).build();
        }

        // @POST
        // @Path("u/{id}")
        // @Consumes(MediaType.APPLICATION_JSON)
        // @Produces(MediaType.APPLICATION_JSON)
        // @Operation(summary = "updateUser", description = "Actualiza un usuario")
        // @APIResponses(value = {
        // @APIResponse(responseCode = "200", description = "Success", content =
        // @Content(mediaType = "application/json", schema = @Schema(implementation =
        // GenericResponse.class))),
        // @APIResponse(responseCode = "503", description = "Error", content =
        // @Content(mediaType = "application/json", schema = @Schema(implementation =
        // GenericResponse.class))),
        // @APIResponse(responseCode = "501", description = "Error", content =
        // @Content(mediaType = "application/json", schema = @Schema(implementation =
        // GenericResponse.class))),
        // @APIResponse(responseCode = "400", description = "Error", content =
        // @Content(mediaType = "application/json", schema = @Schema(implementation =
        // GenericResponse.class)))
        // })
        // public Response updateUser(@PathParam("id") String phoneUser, @RequestBody
        // UpdateUser request)
        // throws ConstraintViolationException, ConnectionException, NotFoundException,
        // SQLException,
        // CrudException {
        // return Response.ok().entity(this.userService.updateUser(phoneUser,
        // request)).build();
        // }

        // @POST
        // @Path("d/{id}")
        // @Consumes(MediaType.APPLICATION_JSON)
        // @Produces(MediaType.APPLICATION_JSON)
        // @Operation(summary = "deleteUser", description = "Actualiza el estado de un
        // usuario")
        // @APIResponses(value = {
        // @APIResponse(responseCode = "200", description = "Success", content =
        // @Content(mediaType = "application/json", schema = @Schema(implementation =
        // GenericResponse.class))),
        // @APIResponse(responseCode = "503", description = "Error", content =
        // @Content(mediaType = "application/json", schema = @Schema(implementation =
        // GenericResponse.class))),
        // @APIResponse(responseCode = "501", description = "Error", content =
        // @Content(mediaType = "application/json", schema = @Schema(implementation =
        // GenericResponse.class))),
        // @APIResponse(responseCode = "400", description = "Error", content =
        // @Content(mediaType = "application/json", schema = @Schema(implementation =
        // GenericResponse.class)))
        // })
        // public Response deleteUser(@PathParam("id") String phoneUser,
        // @NotNull(message = "Parámetro state es requerido") @HeaderParam("state")
        // Integer state)
        // throws ConstraintViolationException, ConnectionException, NotFoundException,
        // SQLException,
        // CrudException {
        // return Response.ok().entity(this.userService.deleteUser(phoneUser,
        // state)).build();
        // }
}