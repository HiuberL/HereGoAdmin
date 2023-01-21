package com.herego.api.services.contracts;

import java.sql.SQLException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.herego.api.dictionaries.ResponseEnum;
import com.herego.api.exceptions.ConnectionException;
import com.herego.api.exceptions.NotFoundException;
import com.herego.api.models.GetUsersResponse;
import com.herego.api.repositories.contracts.UserRepository;

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
}