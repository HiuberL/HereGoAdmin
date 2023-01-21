package com.herego.api.repositories.contracts;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.herego.api.configurations.DriverConnection;
import com.herego.api.dictionaries.ResponseEnum;
import com.herego.api.dictionaries.StoreProcedureEnum;
import com.herego.api.exceptions.ConnectionException;
import com.herego.api.exceptions.NotFoundException;
import com.herego.api.models.Users;
import com.herego.api.repositories.implementations.UserImpl;
import static com.herego.api.utils.DataFormat.formaterFun;

@Dependent
public class UserRepository implements UserImpl {
    @Inject
    DriverConnection driverConnection;
    @Inject
    Logger log;

    @Override
    public List<Users> getUsers() {
        return null;
    }

    @Override
    public Optional<Users> getUserById(String idUser) throws ConnectionException, SQLException, NotFoundException {
        Connection connection = driverConnection.createConection();
        CallableStatement cstmt = null;
        ResultSet resultSet = null;
        Optional<Users> responseUsers = Optional.empty();
        cstmt = connection.prepareCall(formaterFun(StoreProcedureEnum.C_USER_ID), ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        cstmt.setString(1, idUser);
        resultSet = cstmt.executeQuery();
        ResultSetMetaData md = resultSet.getMetaData();
        int columns = md.getColumnCount();
        HashMap<String, Object> row = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        while (resultSet.next()) {
            for (int i = 1; i <= columns; i++) {
                row.put(md.getColumnName(i), resultSet.getObject(i));
            }
        }
        if (!row.isEmpty())
            responseUsers = Optional.ofNullable(mapper.convertValue(row, new TypeReference<Users>() {
            }));
        else {
            throw new NotFoundException(ResponseEnum.NOTFOUND);
        }
        return responseUsers;
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
