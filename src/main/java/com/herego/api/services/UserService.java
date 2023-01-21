package com.herego.api.services;

import java.sql.SQLException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;

import com.herego.api.dictionaries.ResponseEnum;
import com.herego.api.exceptions.ConnectionException;
import com.herego.api.exceptions.CrudException;
import com.herego.api.exceptions.NotFoundException;
import com.herego.api.models.GenericResponse;
import com.herego.api.models.GetUsersResponse;
import com.herego.api.models.Users;
import com.herego.api.repositories.UserRepository;

@ApplicationScoped
public class UserService {
    @Inject
    UserRepository userRepository;

    public GetUsersResponse getUserById(String idUser) throws ConnectionException, NotFoundException, SQLException {
        GetUsersResponse response = new GetUsersResponse();
        response.initValues(ResponseEnum.OK);
        response.setResponse(
                this.userRepository.getUserById(idUser)
                        .orElseThrow(() -> new NotFoundException(ResponseEnum.NOTFOUND)));
        return response;
    }

    public GenericResponse createUser(@Valid Users newUser) throws ConnectionException, SQLException, CrudException {
        GenericResponse response = new GenericResponse();
        response.initValues(ResponseEnum.OK);
        this.userRepository.createUser(newUser);
        return response;
    }
}