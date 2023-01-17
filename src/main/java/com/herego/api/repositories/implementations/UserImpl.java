package com.herego.api.repositories.implementations;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.herego.api.exceptions.ConnectionException;
import com.herego.api.models.Users;

public interface UserImpl {
    public List<Users> getUsers();

    public Optional<Users> getUserById(String idUser) throws ConnectionException, SQLException;

    public boolean createUser(Users newUser);

    public boolean updateUser(Users editUser);

    public boolean deleteUser(String idUser);

}
