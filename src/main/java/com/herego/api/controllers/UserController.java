package com.herego.api.controllers;

import java.sql.SQLException;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import com.herego.api.controllers.dto.UpdateUser;
import com.herego.api.dictionaries.ConfigurationsEnum.UserState;
import com.herego.api.exceptions.ConnectionException;
import com.herego.api.exceptions.CrudException;
import com.herego.api.exceptions.NotFoundException;
import com.herego.api.models.GenericResponse;
import com.herego.api.models.GetUsersResponse;
import com.herego.api.models.Users;
import com.herego.api.services.UserService;

@Path("/users")
public class UserController {
    @Inject
    UserService userService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "getUserById", description = "Obtiene un usuario según su ID")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetUsersResponse.class))),
            @APIResponse(responseCode = "503", description = "Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
            @APIResponse(responseCode = "501", description = "Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
            @APIResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class)))
    })
    public Response getUserById(@PathParam("id") String idUser)
            throws ConnectionException, NotFoundException, SQLException {
        return Response.ok().entity(this.userService.getUserById(idUser)).build();
    }

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
    public Response createUser(@RequestBody Users request)
            throws ConstraintViolationException, ConnectionException, NotFoundException, SQLException, CrudException {
        return Response.ok().entity(this.userService.createUser(request)).build();
    }

    @POST
    @Path("u/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "updateUser", description = "Actualiza un usuario")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
            @APIResponse(responseCode = "503", description = "Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
            @APIResponse(responseCode = "501", description = "Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
            @APIResponse(responseCode = "400", description = "Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class)))
    })
    public Response updateUser(@PathParam("id") String phoneUser, @RequestBody UpdateUser request)
            throws ConstraintViolationException, ConnectionException, NotFoundException, SQLException, CrudException {
        return Response.ok().entity(this.userService.updateUser(phoneUser, request)).build();
    }

    @POST
    @Path("d/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "deleteUser", description = "Actualiza el estado de un usuario")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
            @APIResponse(responseCode = "503", description = "Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
            @APIResponse(responseCode = "501", description = "Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
            @APIResponse(responseCode = "400", description = "Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class)))
    })
    public Response deleteUser(@PathParam("id") String phoneUser,
            @NotNull @HeaderParam("state") UserState state)
            throws ConstraintViolationException, ConnectionException, NotFoundException, SQLException, CrudException {
        return Response.ok().entity(this.userService.deleteUser(phoneUser, state)).build();
    }
}