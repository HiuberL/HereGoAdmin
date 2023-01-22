package com.herego.api.services;

import java.sql.SQLException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;

import com.herego.api.dictionaries.ResponseEnum;
import com.herego.api.exceptions.ConnectionException;
import com.herego.api.exceptions.CrudException;
import com.herego.api.models.Community;
import com.herego.api.models.GenericResponse;
import com.herego.api.repositories.CommunityRepository;

@ApplicationScoped
public class CommunityService {
    @Inject
    CommunityRepository communityRepository;

    // public GetUsersResponse getUsers(String search, int page, int max)
    // throws ConnectionException, NotFoundException, SQLException {
    // GetUsersResponse response = new GetUsersResponse();
    // response.initValues(ResponseEnum.OK);
    // response.setResponse(this.userRepository.getUsers(search, page, max));
    // return response;
    // }

    // public GetUsersResponse getUserById(String idUser) throws
    // ConnectionException, NotFoundException, SQLException {
    // GetUsersResponse response = new GetUsersResponse();
    // response.initValues(ResponseEnum.OK);
    // response.setResponse(
    // this.userRepository.getUserById(idUser)
    // .orElseThrow(() -> new NotFoundException(ResponseEnum.NOTFOUND)));
    // return response;
    // }

    public GenericResponse createCommunity(@Valid Community newCommunity, String phone)
            throws ConnectionException, SQLException, CrudException {
        GenericResponse response = new GenericResponse();
        response.initValues(ResponseEnum.OK);
        this.communityRepository.createCommunity(newCommunity, phone);
        return response;
    }

    // public GenericResponse updateUser(String phoneUser, @Valid UpdateUser
    // editUser)
    // throws ConnectionException, SQLException, CrudException {
    // GenericResponse response = new GenericResponse();
    // response.initValues(ResponseEnum.OK);
    // this.userRepository.updateUser(phoneUser, editUser);
    // return response;
    // }

    // public GenericResponse deleteUser(String phoneUser, int userState)
    // throws ConnectionException, SQLException, CrudException {
    // GenericResponse response = new GenericResponse();
    // response.initValues(ResponseEnum.OK);
    // this.userRepository.deleteUser(phoneUser, userState);
    // return response;
    // }
}