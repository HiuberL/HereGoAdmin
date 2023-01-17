package com.herego.api.controllers;

import java.sql.SQLException;

import javax.inject.Inject;
import javax.ws.rs.GET;
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

import com.herego.api.exceptions.ConnectionException;
import com.herego.api.exceptions.NotFoundException;
import com.herego.api.models.GenericResponse;
import com.herego.api.models.GetUsersResponse;
import com.herego.api.services.contracts.UserService;

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
    })

    public Response getUserById(@PathParam("id") String idUser)
            throws ConnectionException, NotFoundException, SQLException {
        return Response.ok().entity(this.userService.getUserById(idUser)).build();
    }
}