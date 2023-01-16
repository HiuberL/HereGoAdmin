package com.herego.api.repositories.contracts;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.herego.api.configurations.DriverConnection;
import com.herego.api.dictionaries.StoreProcedureEnum;
import com.herego.api.exceptions.ConnectionExceptions;
import com.herego.api.models.Users;
import com.herego.api.repositories.implementations.UserImpl;
import static com.herego.api.utils.DataFormat.formaterSp;

@Dependent
public class UserRepository implements UserImpl {
    @Inject
    DriverConnection driverConnection;

    @Override
    public List<Users> getUsers() {
        return null;
    }

    @Override
    public Optional<Users> getUserById(String idUser) throws ConnectionExceptions, SQLException {
        Connection connection = driverConnection.createConection();
        CallableStatement cstmt = null;
        ResultSet resultSet = null;
        connection.prepareCall(formaterSp(StoreProcedureEnum.C_USER_ID), ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        return null;
    }

    @Override
    public boolean createUser(Users newUser) {
        return false;
    }

    @Override
    public boolean updateUser(Users editUser) {
        return false;
    }

    @Override
    public boolean deleteUser(String idUser) {
        return false;
    }

}
